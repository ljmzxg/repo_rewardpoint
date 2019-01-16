package com.iv.tensquare.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import util.IdWorker;

@SpringBootApplication
public class AppQa {
	
	public static void main(String[] args) {
		SpringApplication.run(AppQa.class, args);
	}
	
	@Bean
	public IdWorker idWorker () {
		return new IdWorker(1, 1);
	}

}
