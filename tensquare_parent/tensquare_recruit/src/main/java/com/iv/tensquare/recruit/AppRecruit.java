package com.iv.tensquare.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import util.IdWorker;

@SpringBootApplication
public class AppRecruit {

	public static void main(String[] args) {
		SpringApplication.run(AppRecruit.class, args);
	}
	
	@Bean
	public IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
	
}
