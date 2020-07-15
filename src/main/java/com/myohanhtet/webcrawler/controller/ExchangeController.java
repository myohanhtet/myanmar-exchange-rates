package com.myohanhtet.webcrawler.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.myohanhtet.webcrawler.model.Exchange;
import com.myohanhtet.webcrawler.service.CrawlerSevice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/api/v1")
@RestController
public class ExchangeController {

    @Autowired
    CrawlerSevice crawlerSevice;

    private static final Logger logger = Logger.getLogger(ExchangeController.class);

    @RequestMapping(value = "/{bank}", method = RequestMethod.GET)
//    @Cacheable(value="exchange-key")
    @ResponseBody
    public Exchange getAll(@PathVariable("bank") String bank) throws IOException, ParseException, InterruptedException, ExecutionException {

        return crawlerSevice.getAll(bank);
    }

    @RequestMapping(value = "/{bank}/{type}")
//    @Cacheable(value="exchange-get-key")
    @ResponseBody
    public HashMap<String, ObjectNode> findOne(@PathVariable("bank") String bank, @PathVariable("type") String type) throws ParseException {
        return crawlerSevice.getOne(bank,type);
    }


}
