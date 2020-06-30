package com.myohanhtet.webcrawler.service.impl;

import com.myohanhtet.webcrawler.model.Buy;
import com.myohanhtet.webcrawler.model.Exchange;
import com.myohanhtet.webcrawler.model.Sell;
import com.myohanhtet.webcrawler.service.CrawlerSevice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CrawlerServiceImpl implements CrawlerSevice {

    @Value("${kbz.url}")
    private String kbzUrl;

    @Value("${aya.url}")
    private String ayaUrl;

    @Value("${yoma.url}")
    private String yomaUrl;

    @Value("${uab.url}")
    private String uabUrl;

    @Override
    public Exchange getAll(String bank) throws IOException, ParseException {
        Exchange exchange = null;

        String bankName = bank.toLowerCase();
        switch (bankName){
            case "yoma":
                Document yomaDoc = document(yomaUrl);
                exchange = yoma(bankName,yomaDoc);
                break;
            case "kbz":
                Document kbzDoc = document(kbzUrl);
                exchange = kbz(bankName,kbzDoc);
                break;
            case "aya":
                Document ayaDoc = document(ayaUrl);
                exchange = aya(bankName,ayaDoc);
                break;
            case "uab":
                Document uabDoc = document(uabUrl);
                exchange = uab(bankName,uabDoc);
            default:
                Document defaultDoc = document(yomaUrl);
                yoma(bankName,defaultDoc);
        }
        return exchange;
    }

    @Override
    public Exchange yoma(String bankName,Document doc) throws ParseException, IOException {

        Exchange yoma = new Exchange();
        String dateString = doc.select(".page-content-section .mb3:nth-child(3) .exrate-date").text();

        yoma.setBank("Yoma");
        yoma.setDate(getDate("MMM dd, yyyy (E)",dateString));

        Buy buy = new Buy();
        buy.setUSD(doc.select(".page-content-section .exratedetailTlb:nth-child(4) tr:nth-of-type(2) .buyrate").text());
        buy.setEUR(doc.select(".page-content-section tr:nth-of-type(3) .buyrate").text());
        buy.setSGD(doc.select(".page-content-section tr:nth-of-type(4) .buyrate").text());
        buy.setMYR(doc.select(".page-content-section tr:nth-of-type(6) .buyrate").text());
        buy.setTHB(doc.select(".page-content-section tr:nth-of-type(7) .buyrate").text());

        Sell sell = new Sell();
        sell.setUSD(doc.select(".page-content-section .exratedetailTlb:nth-child(4) tr:nth-of-type(2) .sellrate").text());
        sell.setEUR(doc.select(".page-content-section tr:nth-of-type(3) .sellrate").text());
        sell.setSGD(doc.select(".page-content-section tr:nth-of-type(4) .sellrate").text());
        sell.setMYR(doc.select(".page-content-section tr:nth-of-type(6) .sellrate").text());
        sell.setTHB(doc.select(".page-content-section tr:nth-of-type(7) .sellrate").text());

        yoma.setBuy(buy);
        yoma.setSell(sell);

        return yoma;
    }

    @Override
    public Exchange aya(String bankName,Document doc) throws IOException, ParseException {

        Exchange aya = new Exchange();

        aya.setBank("AYA");

        String dateString =  doc.select(".tablepress.tablepress-id-104  .row-1 > .column-1").text()
                .replaceAll("st|nd|rd|th|[\\[\\](){}]", "");
        aya.setDate(getDate("d MMMM yyyy hh:mm aaa",dateString));

        Buy buy = new Buy();
        buy.setUSD(doc.select("[class] [class='row-2'] [class='column-3']").text());
        buy.setEUR(doc.select("[class] [class='row-3'] [class='column-3']").text());
        buy.setSGD(doc.select("[class] [class='row-4'] [class='column-3']").text());
        aya.setBuy(buy);

        Sell sell = new Sell();
        sell.setUSD(doc.select("[class] [class='row-2'] [class='column-4']").text());
        sell.setEUR(doc.select("[class] [class='row-3'] [class='column-4']").text());
        sell.setSGD(doc.select("[class] [class='row-4'] [class='column-4']").text());
        aya.setSell(sell);

        return aya;
    }

    @Override
    public Exchange kbz(String bankName,Document doc) throws IOException, ParseException {

        String reateRegex = "([^0-9]|\\s)+";
        Exchange kbz = new Exchange();
        kbz.setBank("KBZ");
        String dateString = doc.select(".inner-column-1.kadence-column_6e8702-29.wp-block-kadence-column > .kt-inside-inner-col > .has-text-color.has-vivid-red-color > strong")
                .text()
                .replaceAll("([A-Z][a-z \\s\\-()]*)", "");
        kbz.setDate(getDate("dd/MM/YYYY",dateString));

        Buy buy = new Buy();
        buy.setUSD(doc.select(".inner-column-1.kadence-column_f853c8-08.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(2)")
                .text()
                .replaceAll(reateRegex, ""));
        buy.setEUR(doc.select(".inner-column-3.kadence-column_2c52f6-af.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(2)")
                .text()
                .replaceAll(reateRegex, ""));
        buy.setSGD(doc.select(".inner-column-2.kadence-column_52cef0-90.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(2)")
                .text()
                .replaceAll(reateRegex, ""));
        buy.setTHB(doc.select(".inner-column-4.kadence-column_254854-b4.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(2)")
                .text()
                .replaceAll(reateRegex, ""));
        kbz.setBuy(buy);

        Sell sell = new Sell();
        sell.setUSD(doc.select(".inner-column-1.kadence-column_f853c8-08.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(3)")
                .text()
                .replaceAll(reateRegex, ""));
        sell.setEUR(doc.select(".inner-column-3.kadence-column_2c52f6-af.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(3)")
                .text()
                .replaceAll(reateRegex, ""));
        sell.setSGD(doc.select(".inner-column-2.kadence-column_52cef0-90.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(3)")
                .text()
                .replaceAll(reateRegex, ""));
        sell.setTHB(doc.select(".inner-column-4.kadence-column_254854-b4.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(3)")
                .text()
                .replaceAll(reateRegex, ""));
        kbz.setSell(sell);

        return kbz;
    }

    @Override
    public Exchange uab(String bankName, Document doc) {

        String dateString = doc.select("[class='block block-block block-8 block-block-8 even block-without-title'] p").text();
        System.out.println(dateString);
        return null;

    }

    public Document document(String url){
        try {
            Document doc = Jsoup.connect(url)
                    .get();
            return doc;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public Date getDate(String format,String stringDate) throws ParseException {
        SimpleDateFormat fromUser = new SimpleDateFormat(format);
        Date date = fromUser.parse(stringDate); // Parse it to the exisitng date pattern and return Date type
        return date;
    }
}
