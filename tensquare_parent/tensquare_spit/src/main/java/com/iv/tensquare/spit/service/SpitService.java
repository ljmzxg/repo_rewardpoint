package com.iv.tensquare.spit.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.spit.dao.SpitDao;
import com.iv.tensquare.spit.pojo.Spit;

import util.IdWorker;

@Service
@Transactional
public class SpitService {

	@Autowired
	private SpitDao spitDao;
	
	@Autowired
	private IdWorker idWorker;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Spit> findAll() {
		return spitDao.findAll();
	}
	
	public Spit findById(String id) {
		return spitDao.findById(id).get();
	}
	
	public void save(Spit spit) {
		spit.set_id(idWorker.nextId() + "");
		spit.setPublishtime(new Date());
		spit.setVisits(0);	//浏览量
		spit.setShare(0);	//分享数
		spit.setThumbup(0);	//点赞数
		spit.setComment(0);	//回复数
		spit.setState("1");	//状态
		//如果当前添加的吐槽，有父节点，那么父节点的吐槽回复数要加1
		if(spit.getParentid() != null && !"".equals(spit.getParentid() + "")) {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
			Update update = new Update();
			update.inc("comment", 1);
			mongoTemplate.updateFirst(query, update, "spit");
		}
		spitDao.save(spit);
	}
	
	public void update(Spit spit) {
		spitDao.save(spit);
	}
	
	public void deleteById(String id) {
		spitDao.deleteById(id);
	}
	
	public Page<Spit> findByParentid(String parentid, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return spitDao.findByParentid(parentid, pageable);
	}

	public void thumbupById(String spitId) {
		//方式1
		Spit spit = spitDao.findById(spitId).get();
		if(spit == null) {
			return;
		}
		spit.setThumbup((spit.getThumbup() == null? 0 : spit.getThumbup()) + 1);
		spitDao.save(spit);
		
		//方式2：该方式效率较方式1 高
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(spitId));
		Update update = new Update();
		update.inc("thumbup", 1);
		mongoTemplate.updateFirst(query, update, "spit");
		
	}
	
}
