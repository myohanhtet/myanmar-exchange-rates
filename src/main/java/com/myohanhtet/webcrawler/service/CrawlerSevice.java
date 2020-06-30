package com.myohanhtet.webcrawler.service;

import com.myohanhtet.webcrawler.model.Exchange;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.ParseException;

public interface CrawlerSevice {
    public Exchange getAll(String $bank) throws IOException, ParseException;
    public Exchange yoma(String bankName, Document doc) throws ParseException, IOException;
    public Exchange aya(String bankName,Document doc) throws IOException, ParseException;
    public Exchange kbz(String bankName,Document doc) throws IOException, ParseException;
    public Exchange uab(String bankName,Document doc);
}
