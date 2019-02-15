package com.iv.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import util.IdWorker;

@SpringBootApplication
@EnableEurekaClient
public class AppBase {

	public static void main(String[] args) {
		SpringApplication.run(AppBase.class, args);
	}
	
	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1, 1);
	}
	
}
