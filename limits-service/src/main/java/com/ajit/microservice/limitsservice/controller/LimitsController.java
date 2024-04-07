package com.ajit.microservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajit.microservice.limitsservice.beans.Limits;
import com.ajit.microservice.limitsservice.configurations.LimitConfigurations;

@RestController
public class LimitsController {
	
	@Autowired
	private LimitConfigurations limitConfigurations;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(limitConfigurations.getMinimum(),limitConfigurations.getMaximum());
	}

}
