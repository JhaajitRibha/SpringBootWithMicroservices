package com.ajit.microservice.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.*;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
//		 Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
//				 .filters(f->f.addRequestHeader("myHeaders", "myuri")
//						 .addRequestParameter("param", "value"))
//				 .uri("http://httpbin.org:80");
//
//	        return builder.routes()
//	                .route(routeFunction)
//	                .build();
		
		
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currencyExchange/**")
						.uri("lb://currency-exchange-server"))
				.route(p -> p.path("/currency-onversion/**")
						.uri("lb://currency-conversion-server"))
				.route(p -> p.path("/currency-conversion/feign/**")
						.uri("lb://currency-conversion-server"))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)", 
								"/currency-conversion/feign/${segment}"))
						.uri("lb://currency-conversion-server"))
				.build();
		
	}
}
