package com.iv.tensquare.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import util.IdWorker;

@SpringBootApplication
@EnableEurekaClient
public class AppGathering {

	public static void main(String[] args) {
		SpringApplication.run(AppGathering.class, args);
	}
	
	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
}
