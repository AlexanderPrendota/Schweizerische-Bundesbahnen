package com.schweizerischebundesbahnen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@ComponentScan("com.schweizerischebundesbahnen")
@EnableSpringConfigured
@EnableAutoConfiguration
@SpringBootApplication

public class SwissrailwaysApplication {

	public static void main(String[] args) throws Exception{

		SpringApplication.run(SwissrailwaysApplication.class, args);
		//open /usr/local/bin/jmeter
	}
}
