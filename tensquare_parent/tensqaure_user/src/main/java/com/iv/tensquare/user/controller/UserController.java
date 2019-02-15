package com.iv.tensquare.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.user.pojo.User;
import com.iv.tensquare.user.service.UserService;

import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(@RequestBody User user) {
		User userLogin = userService.login(user.getMobile(), user.getPassword());
		if(userLogin == null) {
			return new Result(false, StatusCode.LOGINERROR, "登录失败");
		}
		
		String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("roles", "user");
		return new Result(true, StatusCode.OK, "登录成功", map);
	}
	
	/**
	 * 用户注册
	 * @param code
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
	public Result regist(@PathVariable String code, @RequestBody User user) {
		//得到缓存中的验证码
		String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkcode_" + user.getMobile());
		if(checkcodeRedis.isEmpty()) {
			return new Result(false, StatusCode.ERROR, "请先获取手机验证码");
		}
		
		if( !checkcodeRedis.equals(code) ) {
			return new Result(false, StatusCode.ERROR, "请输入正确的验证码");
		}
		
		userService.save(user);
		return new Result(true, StatusCode.OK, "注册成功");
	}
	
	/**
	 * 发送短信验证码
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
	public Result sendSms(@PathVariable String mobile) {
		userService.sendSms(mobile);
		return new Result(true, StatusCode.OK, "发送成功");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody User user) {
		userService.save(user);
		return new Result(true, StatusCode.OK, "添加成功");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public Result findById(@PathVariable String userId) {
		return new Result(true, StatusCode.OK, "查询成功", userService.findById(userId));
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public Result updateUser(@RequestBody User user, @PathVariable String userId) {
		user.setId(userId);
		userService.updateUser(user);
		return new Result(true, StatusCode.OK, "更新成功");
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public Result deleteUser(@PathVariable String userId) {
		userService.deleteById(userId);
		return new Result(true, StatusCode.OK, "删除成功");
	}
	
	
}
