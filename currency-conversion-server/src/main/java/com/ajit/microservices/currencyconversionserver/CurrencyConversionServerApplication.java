package com.ajit.microservices.currencyconversionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CurrencyConversionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServerApplication.class, args);
	}

}
