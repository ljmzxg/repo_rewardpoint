package com.iv.tensquare.base.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.iv.tensquare.base.dao.LabelDao;
import com.iv.tensquare.base.pojo.Label;

import util.IdWorker;

@Service
@Transactional
public class LabelService {

	@Autowired
	private LabelDao labelDao;
	
	@Autowired
	private IdWorker idWorker;
	
	public List<Label> findAll() {
		return labelDao.findAll();
	}
	
	public Label findById(String id) {
		return labelDao.findById(id).get();
	}
	
	public void save(Label label) {
		label.setId(idWorker.nextId() + "");
		labelDao.save(label);
	}
	
	public void update(Label label) {
		labelDao.save(label);
	}
	
	public void deleteById(String id) {
		labelDao.deleteById(id);
	}

	public List<Label> findSearch(Label label) {
		return labelDao.findAll(new Specification<Label>() {

			/**
			 * @param root 根对象， 也就是把对象封装到哪个对象中， where 类名 = label。getid
			 * @param query 封装的都是查询关键字，例如：group by， order by等
			 * @param cb 用来封装条件对象的，如果直接返回null，表示不需要任何条件
			 * @return
			 */
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if( !StringUtils.isEmpty(label.getLabelname()) ) {
					Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"); //where labelname like '%%'
					list.add(predicate);
				}
				
				if( !StringUtils.isEmpty(label.getState()) ) {
					Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
					list.add(predicate);
				}
				
				Predicate[] parr = new Predicate[list.size()];
				list.toArray(parr);
				return cb.and(parr);
			}
		});
	}

	public Page<Label> pageSearch(Label label, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return labelDao.findAll(new Specification<Label>() {

			/**
			 * @param root 根对象， 也就是把对象封装到哪个对象中， where 类名 = label。getid
			 * @param query 封装的都是查询关键字，例如：group by， order by等
			 * @param cb 用来封装条件对象的，如果直接返回null，表示不需要任何条件
			 * @return
			 */
			public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if( !StringUtils.isEmpty(label.getLabelname()) ) {
					Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"); //where labelname like '%%'
					list.add(predicate);
				}
				
				if( !StringUtils.isEmpty(label.getState()) ) {
					Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
					list.add(predicate);
				}
				
				Predicate[] parr = new Predicate[list.size()];
				list.toArray(parr);
				return cb.and(parr);
			}
		}, pageable);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
