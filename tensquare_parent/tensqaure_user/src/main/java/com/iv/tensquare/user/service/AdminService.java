package com.iv.tensquare.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iv.tensquare.user.dao.AdminDao;
import com.iv.tensquare.user.pojo.Admin;

import util.IdWorker;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private IdWorker idWorker;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Admin login(Admin admin) {
		//根据用户名查询Admin
		Admin adminLogin = adminDao.findByLoginname(admin.getLoginname());
		//然后拿数据库中的密码和用户输入的密码匹配是否相同
		if(adminLogin != null && encoder.matches(admin.getPassword(), adminLogin.getPassword())) {
			//数据库中的实体密码 和 用户输入的密码一致，登录成功
			return adminLogin;
		}
		return null;
	}

	public void add(Admin admin) {
		admin.setId(idWorker.nextId() + "");
		//密码加密
		admin.setPassword( encoder.encode( admin.getPassword() ) );
		adminDao.save(admin);
	}

	public void update(Admin admin) {
		adminDao.save(admin);
	}

	public Admin findById(String adminId) {
		return adminDao.findById(adminId).get();
	}

	public void deleteById(String adminId) {
		adminDao.deleteById(adminId);
	}

	public List<Admin> findAll() {
		return adminDao.findAll();
	}
	
	
	
	
}
