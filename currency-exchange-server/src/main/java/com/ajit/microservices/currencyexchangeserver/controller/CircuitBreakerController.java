package com.ajit.microservices.currencyexchangeserver.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;



@RestController
public class CircuitBreakerController {
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	@Retry(name="sample-api",fallbackMethod="hardCodedResponse")
	public String sampliApi() {
		logger.info("received sample api call ....");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummyapi", String.class);
		return forEntity.getBody();
	}
	
	public String hardCodedResponse(Exception ex) {
		 LocalDateTime now = LocalDateTime.now();
	        
	       
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        
	      
	        String formattedDateTime = now.format(formatter);
	        
		logger.info("fallback recieved : {}",formattedDateTime );
		
		return "fallback-response" +" " + formattedDateTime;
	}
}
