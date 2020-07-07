package com.myohanhtet.webcrawler.controller;

import com.myohanhtet.webcrawler.model.Exchange;
import com.myohanhtet.webcrawler.service.CrawlerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class WebController {
    @Autowired
    CrawlerSevice crawlerSevice;

    @RequestMapping("/{bank}")
    public String viewHomePage(Model modle, @PathVariable("bank") String bank) throws ParseException, InterruptedException, ExecutionException, IOException {
        Exchange listExchange = crawlerSevice.getAll(bank);
        modle.addAttribute("liatExchange",listExchange);
        return "index";
    }

}
