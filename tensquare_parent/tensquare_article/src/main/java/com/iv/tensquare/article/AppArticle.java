package com.iv.tensquare.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import util.IdWorker;

@SpringBootApplication
public class AppArticle {

	public static void main(String[] args) {
		SpringApplication.run(AppArticle.class, args);
	}
	
	@Bean
	private IdWorker idWorker() {
		return new IdWorker(1, 1);
	}
	
	
	
}
