package com.iv.tensquare.recruit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.recruit.dao.EnterpriseDao;
import com.iv.tensquare.recruit.pojo.Enterprise;

import util.IdWorker;

@Service
@Transactional
public class EnterpriseService {

	@Autowired
	private EnterpriseDao enterpriseDao;
	
	@Autowired
	private IdWorker idWorker;
	
	public List<Enterprise> findByIshot(String ishot) {
		return enterpriseDao.findByIshot(ishot);
	}
	

	public List<Enterprise> findAll() {
		return enterpriseDao.findAll();
	}

	public Enterprise findById(String enterpriseId) {
		return enterpriseDao.findById(enterpriseId).get();
	}

	public void save(Enterprise enterprise) {
		enterprise.setId(idWorker.nextId() + "");
		enterpriseDao.save(enterprise);
	}

	public void update(Enterprise enterprise) {
		enterpriseDao.save(enterprise);
	}

	public void deleteById(String enterpriseId) {
		enterpriseDao.deleteById(enterpriseId);
	}
	
}
