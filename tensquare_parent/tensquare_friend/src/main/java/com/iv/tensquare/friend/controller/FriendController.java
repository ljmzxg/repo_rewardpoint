package com.iv.tensquare.friend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.friend.service.FriendService;

import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private FriendService friendService;
	
	@RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
	public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
		//验证是否登陆，并且拿到当前登陆的用户id
		Claims claims = (Claims) request.getAttribute("claims_user");
		if(claims == null) {
			//当前用户没有user 角色
			return new Result(false, StatusCode.LOGINERROR, "权限不足");
		}
		//得到当前登陆用户id
		String userid = claims.getId();
		if(type != null) {
			if("1".equals(type)) {
				//添加好友
				int flag = friendService.addFriend(userid, friendid);
				if(flag == 0) {
					return new Result(false, StatusCode.ERROR, "不能重复添加好友");
				}
				
				if(flag == 1) {
					return new Result(true, StatusCode.OK, "添加成功");
				}
				
			} else if("2".equals(type)) {
				//添加非好友
			}
			return new Result(false, StatusCode.ERROR, "参数异常");
		} 
		
		return new Result(false, StatusCode.ERROR, "参数异常");
	}
	
}
