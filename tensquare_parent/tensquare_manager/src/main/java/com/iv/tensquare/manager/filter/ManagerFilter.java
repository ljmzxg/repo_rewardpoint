package com.iv.tensquare.manager.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import io.jsonwebtoken.Claims;
import util.JwtUtil;

@Component
public class ManagerFilter extends ZuulFilter {
	
	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * 当前过滤器是否开启：true：开启，false：不开启
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器内执行的操作，return 任何Object 都表示继续执行
	 * setSendZuulResponse(false) 表示不再继续执行
	 */
	@Override
	public Object run() throws ZuulException {
		System.out.println("经过了Manager 过滤器");
		//得到request上下文
		RequestContext currContext = RequestContext.getCurrentContext();
		//得到request 域
		HttpServletRequest request = currContext.getRequest();
		
		if(request.getMethod().equals("OPTIONS")) {
			return null;
		}
		
		//如果登录，放行
		if(request.getRequestURI().indexOf("login") > 0) {
			return null;
		}
		
		//得到头信息
		String header = request.getHeader("Authorization");
		//判断是否有头信息
		if(header != null && !"".equals(header)) {
			if(header.startsWith("Bearer ")) {
				String token = header.substring(7);
				try {
					Claims claims = jwtUtil.parseJWT(token);
					String roles = (String) claims.get("roles");
					if(roles != null && "admin".equals(roles)) {
						//把头信息继续往下传
						currContext.addZuulRequestHeader("Authorization", header);
						return null;
					}
				} catch(Exception e) {
					currContext.setSendZuulResponse(false);//终止运行
				}
			}
		}
		
		currContext.setSendZuulResponse(false);
		currContext.setResponseStatusCode(403);
		currContext.setResponseBody("权限不足");
		currContext.getResponse().setContentType("text/html;charset=utf-8");
		return null;
	}

	/**
	 * pre：请求前被调用
	 * route：请求时候被调用
	 * post：在route 和 error 过滤器之后被调用
	 * error：请求时候发生错误被调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
