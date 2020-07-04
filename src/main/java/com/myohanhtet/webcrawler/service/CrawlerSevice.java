package com.myohanhtet.webcrawler.service;

import com.myohanhtet.webcrawler.model.Exchange;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface CrawlerSevice {
    public Exchange getAll(String $bank) throws IOException, ParseException, InterruptedException, ExecutionException;
    public String getOne(String bank,String type);
    public Exchange yoma(String bankName, Document doc) throws ParseException, IOException;
    public Exchange aya(String bankName,Document doc) throws IOException, ParseException;
    public Exchange kbz(String bankName,Document doc) throws IOException, ParseException;
    public Exchange uab(String bankName,Document doc) throws ParseException;
    public Exchange agd(String bankName,Document doc) throws IOException, InterruptedException, ExecutionException;
    public Exchange cbbank(String bankName,Document doc);
    public Map<String,String> exchange(String bank);
}
