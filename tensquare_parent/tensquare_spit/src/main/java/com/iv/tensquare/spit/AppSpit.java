package com.iv.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import util.IdWorker;

@SpringBootApplication
public class AppSpit {

	public static void main(String[] args) {
		SpringApplication.run(AppSpit.class, args);
	}
	
	@Bean
	private IdWorker idWorker () {
		return new IdWorker(1, 1);
	}
	
}
