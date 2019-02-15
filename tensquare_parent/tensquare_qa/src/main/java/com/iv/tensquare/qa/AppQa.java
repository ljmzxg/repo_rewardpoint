package com.iv.tensquare.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import util.IdWorker;
import util.JwtUtil;

@SpringBootApplication
@EnableEurekaClient
public class AppQa {
	
	public static void main(String[] args) {
		SpringApplication.run(AppQa.class, args);
	}
	
	@Bean
	public IdWorker idWorker () {
		return new IdWorker(1, 1);
	}
	
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}

}
