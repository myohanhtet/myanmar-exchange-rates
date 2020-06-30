package com.myohanhtet.webcrawler.controller;

import com.myohanhtet.webcrawler.model.Exchange;
import com.myohanhtet.webcrawler.service.CrawlerSevice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ExchangeController {

    @Autowired
    CrawlerSevice crawlerSevice;

    private static final Logger logger = Logger.getLogger(ExchangeController.class);

    @RequestMapping(value = "/{bank}", method = RequestMethod.GET)
    @ResponseBody
    public Exchange getAll(@PathVariable("bank") String bank) throws IOException, ParseException {
        logger.info("Request Bamk: "+ bank);
        return crawlerSevice.getAll(bank);
    }

}
