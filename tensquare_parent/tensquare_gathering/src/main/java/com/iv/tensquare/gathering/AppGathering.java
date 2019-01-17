package com.iv.tensquare.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import util.IdWorker;

@SpringBootApplication
public class AppGathering {

	public static void main(String[] args) {
		SpringApplication.run(AppGathering.class, args);
	}
	
	@Bean
	private IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
}
