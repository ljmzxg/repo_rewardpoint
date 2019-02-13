package com.iv.tensquare.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.user.dao.UserDao;
import com.iv.tensquare.user.pojo.User;

import util.IdWorker;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdWorker idWorker;

	public void save(User user) {
		user.setId(idWorker.nextId() + "");
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
		userDao.deleteById(userId);
	}
	
	
	
	
	
}
