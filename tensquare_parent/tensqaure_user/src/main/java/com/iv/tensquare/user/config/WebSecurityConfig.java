package com.iv.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//authorizeRequests 所有security 注解配置实现的开端，表示开始说明需要的权限
		//需要的权限分为两部分：1是拦截的路径；2是访问该路径需要的权限
		//antMatchers 表示拦截的路径，permitAll 表示任何权限都可以访问，直接放行所有
		//anyRequest:任何的请求，authenticated认证后才能进行访问
		//.and().csrf().disable()：固定写法，表示使csrf 拦截失效
		
		http.authorizeRequests()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable();
		super.configure(http);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
