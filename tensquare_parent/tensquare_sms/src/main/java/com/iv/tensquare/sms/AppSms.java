package com.iv.tensquare.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppSms {

	public static void main(String[] args) {
		SpringApplication.run(AppSms.class, args);
	}
}
