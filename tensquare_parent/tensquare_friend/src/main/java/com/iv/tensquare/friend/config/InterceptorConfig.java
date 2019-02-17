package com.iv.tensquare.friend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.iv.tensquare.friend.interceptor.JwtInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	protected void addInterceptors(InterceptorRegistry registry) {

		//注册拦截器 要声明拦截器对象和要拦截的请求
		registry.addInterceptor(jwtInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/**/login/**");
	}
	
	
	
}
