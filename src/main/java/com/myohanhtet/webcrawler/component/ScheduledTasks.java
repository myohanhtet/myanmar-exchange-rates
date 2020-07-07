package com.myohanhtet.webcrawler.component;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final Logger logger = Logger.getLogger(ScheduledTasks.class);
//    @Scheduled(fixedRate = 5000)
//    public void scheduleTaskWithFixedRate() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        Date now = new Date();
//        String strDate = sdf.format(now);
//        logger.info("Fixed Rate Task :: Execution Time - {}"+ strDate);
//    }
}
