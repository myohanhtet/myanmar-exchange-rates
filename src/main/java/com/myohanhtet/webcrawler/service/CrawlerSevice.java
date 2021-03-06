package com.myohanhtet.webcrawler.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.myohanhtet.webcrawler.model.Exchange;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface CrawlerSevice {
    public Exchange getAll(String $bank) throws IOException, ParseException, InterruptedException, ExecutionException;
    public HashMap<String, ObjectNode>  getOne(String bank, String type) throws ParseException;
    public Exchange yoma(String bankName, Document doc) throws ParseException, IOException;
    public Exchange aya(String bankName,Document doc) throws IOException, ParseException;
    public Exchange kbz(String bankName,Document doc) throws IOException, ParseException;
    public Exchange uab(String bankName,Document doc) throws IOException,ParseException;
    public Exchange agd(String bankName,Document doc) throws IOException, InterruptedException, ExecutionException;
    public Exchange cbbank(String bankName,Document doc) throws IOException;
    public Map<String,String> exchange(String bank) throws ParseException;
}
