package com.iv.tensquare.qa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import util.JwtUtil;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtUtil jwtUtil;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("经过了拦截器");
		//无论如何都放行。具体能不能操作还是在具体的操作中取判断
		//拦截器只是负责把头请求中包含token的令牌进行一个姐系验证。
		
		String header = request.getHeader("Authorization");
		if(header != null && !"".equals(header)) {
			//如果包含有Authorization头消息，便进行解析
			if( header.startsWith("Bearer ") ) {
				//得到token
				String token = header.substring(7);
				//对token进行验证
				try {
					Claims claims = jwtUtil.parseJWT(token);
					String roles = (String) claims.get("roles");
					if(roles != null && roles.equals("admin")) {
						request.setAttribute("claims_admin", token);
					}
					
					if(roles != null && roles.equals("user")) {
						request.setAttribute("claims_user", token);
					}
					
				} catch(Exception e) {
					throw new RuntimeException("token 不正确");
				}
			}
		}
		
		return true;
	}
	
}
