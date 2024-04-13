	package com.ajit.microservices.currencyexchangeserver.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ajit.microservices.currencyexchangeserver.CurrencyExchange;
import com.ajit.microservices.currencyexchangeserver.repositories.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController{
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currencyExchange/from/{from}/to/{to}")
	public CurrencyExchange currencyExchangeRetrieval(
			@PathVariable String from,
			@PathVariable String to) {
		
//		CurrencyExchange currencyExchange = new CurrencyExchange(1l,"USD","INR",BigDecimal.valueOf(76));

		CurrencyExchange currencyExchange = currencyExchangeRepo.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new RuntimeException("unable to find data from repo");
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		
		return currencyExchange;
	}

}
