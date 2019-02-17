package com.iv.tensquare.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.user.dao.UserDao;
import com.iv.tensquare.user.pojo.User;

import util.IdWorker;
import util.JwtUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdWorker idWorker;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private HttpServletRequest request;
	
	public User login(String mobile, String password) {
		User userLogin = userDao.findByMobile(mobile);
		if(userLogin != null && encoder.matches(password, userLogin.getPassword())) {
			return userLogin;
		}
		return null;
	}

	public void save(User user) {
		user.setId(idWorker.nextId() + "");
		user.setPassword( encoder.encode(user.getPassword()) );
		user.setFollowcount(0);//关注数
		user.setFanscount(0);	//粉丝数
		user.setOnline(0L);		//在线时长
		user.setRegdate(new Date());//注册日期
		user.setUpdatedate(new Date());//更新日期
		user.setLastdate(new Date());	//最后登陆日期
		userDao.save(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findById(String userId) {
		return userDao.findById(userId).get();
	}

	public void updateUser(User user) {
		userDao.save(user);
	}

	public void deleteById(String userId) {
//		String header = request.getHeader("Authorization");
//		if(header == null || "".equals(header)) {
//			throw new RuntimeException("权限不足！");
//		}
//		
//		if( !header.startsWith("Bearer ") ) {
//			throw new RuntimeException("权限不足！");
//		}
//		
//		//得到token
//		String token = header.substring(7);
//		try {
//			Claims claims = jwtUtil.parseJWT(token);
//			String roles = (String) claims.get("roles");
//			if(roles == null || !roles.equals("admin")) {
//				throw new RuntimeException("权限不足！");
//			}
//		} catch(Exception e) {
//			throw new RuntimeException("权限不足！");
//		}
		
		//此部分功能等同上面注释代码
		String token = (String) request.getAttribute("claims_admin");
		if(StringUtils.isEmpty(token)) {
			throw new RuntimeException("权限不足！");
		}
		
		userDao.deleteById(userId);
	}

	public void sendSms(String mobile) {
		//生成六位数字随机数
		String checkcode = RandomStringUtils.randomNumeric(6);
		//缓存中存放一份，且有效时间为6小时
		redisTemplate.opsForValue().set("checkcode_" + mobile, checkcode, 6, TimeUnit.HOURS);
		//用户发一份
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile", mobile);
		map.put("checkcode", checkcode);
		rabbitTemplate.convertAndSend("sms", map);
		//控制台输出一份
		System.out.println("验证码为：" + checkcode);
		
	}

	
	
}
