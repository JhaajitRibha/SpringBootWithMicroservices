package com.ajit.microservices.currencyexchangeserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajit.microservices.currencyexchangeserver.CurrencyExchange;

public interface CurrencyExchangeRepository 
extends JpaRepository<CurrencyExchange,Long>{

	CurrencyExchange findByFromAndTo(String form,String to);
	
	
}
