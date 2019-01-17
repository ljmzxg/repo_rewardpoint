package com.iv.tensquare.qa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.qa.dao.ReplyDao;
import com.iv.tensquare.qa.pojo.Reply;

import util.IdWorker;

@Service
@Transactional
public class ReplyService {

	@Autowired
	private ReplyDao replyDao;
	
	@Autowired
	private IdWorker idWorker;

	public List<Reply> findAll() {
		return replyDao.findAll();
	}

	public Reply findById(String replyId) {
		return replyDao.findById(replyId).get();
	}

	public void save(Reply reply) {
		reply.setId(idWorker.nextId() + "");
		replyDao.save(reply);
	}

	public void update(Reply reply) {
		replyDao.save(reply);
	}

	public void deleteById(String replyId) {
		replyDao.deleteById(replyId);
	}
	
	
}
