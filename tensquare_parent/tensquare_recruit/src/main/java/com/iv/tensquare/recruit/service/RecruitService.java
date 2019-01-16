package com.iv.tensquare.recruit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.recruit.dao.RecruitDao;
import com.iv.tensquare.recruit.pojo.Recruit;

import util.IdWorker;

@Service
@Transactional
public class RecruitService {

	@Autowired
	private RecruitDao recruitDao;
	
	@Autowired
	private IdWorker idWorker;
	
	public List<Recruit> recommendList (String state) {
		return recruitDao.findTop6ByStateOrderByCreatetimeDesc(state);
	}
	
	public List<Recruit> newList(String state) {
		return recruitDao.findTop6ByStateNotOrderByCreatetimeDesc(state);
	}
	

	public List<Recruit> findAll() {
		return recruitDao.findAll();
	}

	public Recruit findById(String rId) {
		return recruitDao.findById(rId).get();
	}

	public void save(Recruit recruit) {
		recruit.setId(idWorker.nextId() + "");
		recruitDao.save(recruit);
	}

	public void update(Recruit recruit) {
		recruitDao.save(recruit);
	}

	public void deleteById(String recruitId) {
		recruitDao.deleteById(recruitId);
	}
	
}
