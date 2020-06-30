package com.myohanhtet.webcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class WebCrawlerApplication {

	public static void main(String[] args) {
		//handshake_failure through SSLHandshakeException
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3,TLSv1,TLSv1.1");
		SpringApplication.run(WebCrawlerApplication.class, args);
	}

	@PostConstruct
	public void init(){
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
