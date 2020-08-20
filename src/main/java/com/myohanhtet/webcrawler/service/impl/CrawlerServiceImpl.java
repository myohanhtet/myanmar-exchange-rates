package com.myohanhtet.webcrawler.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.myohanhtet.webcrawler.model.Buy;
import com.myohanhtet.webcrawler.model.Exchange;
import com.myohanhtet.webcrawler.model.Sell;
import com.myohanhtet.webcrawler.service.CrawlerSevice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.myohanhtet.webcrawler.constant.Banks.*;

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

    @Value("${cb.url}")
    private String cbUrl;

    @Value("${agd.url}")
    private String agdUrl;

//    private static final HttpClient httpClient = HttpClient.newBuilder()
//            .version(HttpClient.Version.HTTP_1_1)
//            .connectTimeout(Duration.ofSeconds(10))
//            .build();
    @Autowired
    ObjectMapper mapper;

    @Override
    public Exchange getAll(String bank) throws ParseException {
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
                break;
            case "cbbank":
                Document cbDoc = document(cbUrl);
                exchange = cbbank(bankName,cbDoc);
                break;
            case "agd":
                Document agdDoc = document(agdUrl);
                exchange = agd(bankName,agdDoc);
                break;
            default:
                Document defaultDoc = document(yomaUrl);
                exchange = yoma(bankName,defaultDoc);
        }

        return exchange;
    }

    @Override
    public HashMap<String, ObjectNode> getOne(String bank, String type) {

        HashMap<String, ObjectNode> returnMap = new HashMap<>();

        if (bank.equalsIgnoreCase("yoma")){

            Map<String,String> yomaExchange = exchange(YOMA_NAME);
            ObjectNode objectNode = mapper.createObjectNode();
            switch (type.toLowerCase()){
                case "usd":
                    objectNode.put("buy", yomaExchange.get("usdBuy"));
                    objectNode.put("sell", yomaExchange.get("usdSell"));
                    break;
                case "eur":
                    objectNode.put("buy", yomaExchange.get("eurBuy"));
                    objectNode.put("sell", yomaExchange.get("eurSell"));
                    break;
                case "sgd":
                    objectNode.put("buy", yomaExchange.get("sgdBuy"));
                    objectNode.put("sell", yomaExchange.get("sgdSell"));
                    break;
                case "myr":
                    objectNode.put("buy", yomaExchange.get("myrBuy"));
                    objectNode.put("sell", yomaExchange.get("myrSell"));
                    break;
                case "thb":
                    objectNode.put("buy", yomaExchange.get("thbBuy"));
                    objectNode.put("sell", yomaExchange.get("thbSell"));
                    break;
            }
            returnMap.put(type, objectNode);

        } else if(bank.equalsIgnoreCase("aya")){

            Map<String,String> ayaExchange = exchange(AYA_NAME);
            ObjectNode objectNode = mapper.createObjectNode();
            switch (type.toLowerCase()){
                case "usd":
                    objectNode.put("buy", ayaExchange.get("usdBuy"));
                    objectNode.put("sell", ayaExchange.get("usdSell"));
                    break;
                case "eur":
                    objectNode.put("buy", ayaExchange.get("eurBuy"));
                    objectNode.put("sell", ayaExchange.get("eurSell"));
                    break;
                case "sgd":
                    objectNode.put("buy", ayaExchange.get("sgdBuy"));
                    objectNode.put("sell", ayaExchange.get("sgdSell"));
                    break;
            }
            returnMap.put(type, objectNode);

        } else if (bank.equalsIgnoreCase("kbz")){
            Map<String,String> kbzExchange = exchange(KBZ_NAME);
            ObjectNode objectNode = mapper.createObjectNode();
            switch (type.toLowerCase()){
                case "usd":
                    objectNode.put("buy", kbzExchange.get("usdBuy"));
                    objectNode.put("sell", kbzExchange.get("usdSell"));
                    break;
                case "eur":
                    objectNode.put("buy", kbzExchange.get("eurBuy"));
                    objectNode.put("sell", kbzExchange.get("eurSell"));
                    break;
                case "sgd":
                    objectNode.put("buy", kbzExchange.get("sgdBuy"));
                    objectNode.put("sell", kbzExchange.get("sgdSell"));
                    break;
                case "thb":
                    objectNode.put("buy", kbzExchange.get("thbBuy"));
                    objectNode.put("sell", kbzExchange.get("thbSell"));
                    break;
            }
            returnMap.put(type, objectNode);
        } else if (bank.equalsIgnoreCase("uab")) {
            Map<String,String> uabExchange = exchange(UAB_NAME);
            ObjectNode objectNode = mapper.createObjectNode();
            switch (type.toLowerCase()) {
                case "usd":
                    objectNode.put("buy", uabExchange.get("usdBuy"));
                    objectNode.put("sell", uabExchange.get("usdSell"));
                    break;
                case "eur":
                    objectNode.put("buy",uabExchange.get("eurBuy"));
                    objectNode.put("sell",uabExchange.get("eurSell"));
                    break;
                case "sgd":
                    objectNode.put("buy",uabExchange.get("sgdBuy"));
                    objectNode.put("sell",uabExchange.get("sgdSell"));
                    break;

            }
            returnMap.put(type, objectNode);
        } else {
            Map<String,String> yomaExchange = exchange(YOMA_NAME);
            ObjectNode objectNode = mapper.createObjectNode();
            returnMap.put(type, objectNode);
        }


        return returnMap;

    }

    @Override
    public Exchange yoma(String bankName,Document doc) throws ParseException {

        Exchange yoma = new Exchange();
        String dateString = doc.select(".page-content-section .mb3:nth-child(3) .exrate-date").text();

        yoma.setBank(YOMA_NAME);
        yoma.setDate(getDate("MMM dd, yyyy (E) • HH:mm",dateString));

        Buy buy = new Buy();
        Map<String,String> yomaExchange = exchange(YOMA_NAME);
        buy.setUSD(yomaExchange.get("usdBuy"));
        buy.setEUR(yomaExchange.get("eurBuy"));
        buy.setSGD(yomaExchange.get("sgdBuy"));
        buy.setMYR(yomaExchange.get("myrBuy"));
        buy.setTHB(yomaExchange.get("thbBuy"));

        Sell sell = new Sell();
        sell.setUSD(yomaExchange.get("usdSell"));
        sell.setEUR(yomaExchange.get("eurSell"));
        sell.setSGD(yomaExchange.get("sgdSell"));
        sell.setMYR(yomaExchange.get("myrSell"));
        sell.setTHB(yomaExchange.get("thbSell"));

        yoma.setBuy(buy);
        yoma.setSell(sell);

        return yoma;
    }

    @Override
    public Exchange aya(String bankName,Document doc) throws ParseException {

        Exchange aya = new Exchange();

        aya.setBank(AYA_NAME);

        String dateString =  doc.select(".tablepress.tablepress-id-104  .row-1 > .column-1").text()
                .replaceAll("[0-9]st|[0-9]nd|[0-9]rd|[0-9]th|[\\[\\](){}]", "");

        aya.setDate(getDate("dd MMMMM yyyy hh:mm a",dateString));
//        aya.setDate(getDate("d MMMM yyyy hh:mm",dateString));

        Map<String,String> ayaExchange = exchange(AYA_NAME);
        Buy buy = new Buy();
        buy.setUSD(ayaExchange.get("usdBuy"));
        buy.setEUR(ayaExchange.get("eruBuy"));
        buy.setSGD(ayaExchange.get("sgdBuy"));
        aya.setBuy(buy);

        Sell sell = new Sell();
        sell.setUSD(ayaExchange.get("usdSell"));
        sell.setEUR(ayaExchange.get("eruSell"));
        sell.setSGD(ayaExchange.get("sgdSell"));
        aya.setSell(sell);

        return aya;
    }

    @Override
    public Exchange kbz(String bankName,Document doc) throws ParseException {

        Map<String,String> kbzRate = exchange(KBZ_NAME);
        Exchange kbz = new Exchange();
        kbz.setBank(KBZ_NAME);
        String dateString = doc.select(".inner-column-1.kadence-column_6e8702-29.wp-block-kadence-column > .kt-inside-inner-col > .has-text-color.has-vivid-red-color > strong")
                .text()
                .replaceAll("([A-Z][a-z \\s\\-()]*)", "");
        kbz.setDate(getDate("dd/MM/yyyy",dateString));
        Buy buy = new Buy();
        buy.setUSD(kbzRate.get("usdBuy"));
        buy.setEUR(kbzRate.get("eurBuy"));
        buy.setSGD(kbzRate.get("sgdBuy"));
        buy.setTHB(kbzRate.get("thbBuy"));
        kbz.setBuy(buy);

        Sell sell = new Sell();
        sell.setUSD(kbzRate.get("usdSell"));
        sell.setEUR(kbzRate.get("eurSell"));
        sell.setSGD(kbzRate.get("sgdSell"));
        sell.setTHB(kbzRate.get("thbSell"));
        kbz.setSell(sell);
        return kbz;
    }

    @Override
    public Exchange uab(String bankName, Document doc) throws ParseException {

        Map<String,String> uabRate = exchange(UAB_NAME);
        String dateString = doc.select(".ex-date")
                .text().substring(14).replaceAll("[^a-zA-Z0-9]","");
        Exchange uab = new Exchange();
        uab.setBank(UAB_NAME);
        uab.setDate(getDate("ddMMMyyyy",dateString));
        Buy buy = new Buy();
        buy.setUSD(uabRate.get("usdBuy"));
        buy.setEUR(uabRate.get("eurBuy"));
        buy.setSGD(uabRate.get("sgdBuy"));
        uab.setBuy(buy);
        Sell sell = new Sell();
        sell.setUSD(uabRate.get("usdSell"));
        sell.setEUR(uabRate.get("eurSell"));
        sell.setSGD(uabRate.get("sgdSell"));

        uab.setSell(sell);
        return uab;
    }

    @Override
    public Exchange agd(String bankName, Document doc) {


//        HttpRequest request = HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create("https://www.agdbank.com/ajax?currency=api"))
//                .header("Content-Type", "application/json")
//                .build();

//        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request,HttpResponse.BodyHandlers.ofString());

        return null;
    }

    @Override
    public Exchange cbbank(String bankName, Document doc) {


        return null;

    }

    @Override
    public Map<String, String> exchange(String bank) {

        String reateRegex = "([^0-9]|\\s)+";

        switch (bank){
            case YOMA_NAME:
                Document yomadoc = document(yomaUrl);
                Map<String, String> yoma = new HashMap<String, String>();
                yoma.put("usdBuy",yomadoc.select(".page-content-section .exratedetailTlb:nth-child(4) tr:nth-of-type(2) .buyrate").text());
                yoma.put("eurBuy",yomadoc.select(".page-content-section tr:nth-of-type(3) .buyrate").text());
                yoma.put("sgdBuy",yomadoc.select(".page-content-section tr:nth-of-type(4) .buyrate").text());
                yoma.put("myrBuy",yomadoc.select(".page-content-section tr:nth-of-type(6) .buyrate").text());
                yoma.put("thbBuy",yomadoc.select(".page-content-section tr:nth-of-type(7) .buyrate").text());
                yoma.put("usdSell",yomadoc.select(".page-content-section .exratedetailTlb:nth-child(4) tr:nth-of-type(2) .sellrate").text());
                yoma.put("eurSell",yomadoc.select(".page-content-section tr:nth-of-type(3) .sellrate").text());
                yoma.put("sgdSell",yomadoc.select(".page-content-section tr:nth-of-type(4) .sellrate").text());
                yoma.put("myrSell",yomadoc.select(".page-content-section tr:nth-of-type(6) .sellrate").text());
                yoma.put("thbSell",yomadoc.select(".page-content-section tr:nth-of-type(7) .sellrate").text());
                return yoma;
            case AYA_NAME:
                Document ayadoc = document(ayaUrl);
                Map<String,String> aya = new HashMap<String, String>();
                aya.put("usdBuy",ayadoc.select("[class] [class='row-2'] [class='column-3']").text());
                aya.put("eruBuy",ayadoc.select("[class] [class='row-3'] [class='column-3']").text());
                aya.put("sgdBuy",ayadoc.select("[class] [class='row-4'] [class='column-3']").text());
                aya.put("usdSell",ayadoc.select("[class] [class='row-2'] [class='column-4']").text());
                aya.put("eruSell",ayadoc.select("[class] [class='row-3'] [class='column-4']").text());
                aya.put("sgdSell",ayadoc.select("[class] [class='row-4'] [class='column-4']").text());
                return aya;
            case KBZ_NAME:
                Document kbzdoc = document(kbzUrl);
                Map<String,String> kbz = new HashMap<String, String>();
                kbz.put("usdBuy",kbzdoc.select(".inner-column-1.kadence-column_f853c8-08.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(2)")
                    .text()
                    .replaceAll(reateRegex, ""));
                kbz.put("eurBuy",kbzdoc.select(".inner-column-3.kadence-column_2c52f6-af.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(2)")
                        .text()
                        .replaceAll(reateRegex, ""));
                kbz.put("sgdBuy",kbzdoc.select(".inner-column-2.kadence-column_52cef0-90.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(2)")
                        .text()
                        .replaceAll(reateRegex, ""));
                kbz.put("thbBuy",kbzdoc.select(".inner-column-4.kadence-column_254854-b4.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(2)")
                        .text()
                        .replaceAll(reateRegex, ""));
                kbz.put("usdSell", kbzdoc.select(".inner-column-1.kadence-column_f853c8-08.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(3)")
                        .text()
                        .replaceAll(reateRegex, ""));
                kbz.put("eurSell", kbzdoc.select(".inner-column-3.kadence-column_2c52f6-af.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(3)")
                        .text()
                        .replaceAll(reateRegex, ""));
                kbz.put("sgdSell", kbzdoc.select(".inner-column-2.kadence-column_52cef0-90.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(3)")
                        .text()
                        .replaceAll(reateRegex, ""));
                kbz.put("thbSell", kbzdoc.select(".inner-column-4.kadence-column_254854-b4.wp-block-kadence-column > .kt-inside-inner-col > p:nth-of-type(3)")
                        .text()
                        .replaceAll(reateRegex, ""));
                return kbz;
            case UAB_NAME:
                Document uabdoc = document(uabUrl);
                Map<String,String> uab = new HashMap<String, String>();
                String[] usdArray = uabdoc.select(".ex-usd").text().split(",");
                uab.put("usdBuy",usdArray[0].replaceAll(reateRegex,""));
                uab.put("usdSell",usdArray[1].replaceAll(reateRegex,""));
                String[] eurArray = uabdoc.select(".ex-eur").text().split(",");
                uab.put("eurBuy",eurArray[0].replaceAll(reateRegex,""));
                uab.put("eurSell",eurArray[1].replaceAll(reateRegex,""));
                String[] sgdArray = uabdoc.select(".ex-sgd").text().split(",");
                uab.put("sgdBuy",sgdArray[0].replaceAll(reateRegex,""));
                uab.put("sgdSell",sgdArray[1].replaceAll(reateRegex,""));
                return uab;
        }
        return null;
    }

    public Document document(String url){
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
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
