package com.iv.tensquare.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import util.JwtUtil;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AppManager {

	public static void main(String[] args) {
		SpringApplication.run(AppManager.class, args);
	}
	
	@Bean
	public JwtUtil jwtUtil () {
		return new JwtUtil();
	}
}
