package com.newt.parser.urlParser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "com.newt.parser.urlParser.controller","com.newt.parser.urlParser.logic" })
@EnableEurekaClient
public class UrlParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlParserApplication.class, args);
	}
}
