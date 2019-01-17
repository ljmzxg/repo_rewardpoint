package com.iv.tensquare.article.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iv.tensquare.article.dao.ArticleDao;
import com.iv.tensquare.article.pojo.Article;

import util.IdWorker;

@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private IdWorker idWorker;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public void updateState(String id) {
		articleDao.updateState(id);
	}
	
	public void addThumbup(String id) {
		articleDao.addThumbup(id);
	}

	public List<Article> findAll() {
		return articleDao.findAll();
	}

	public Article findById(String articleId) {
		//先从redis 中查找对象
		Article article = (Article) redisTemplate.opsForValue().get("article_" + articleId);
		if(article == null) {
			//从数据库中查找对象
			article = articleDao.findById(articleId).get();
			//存入缓存中,并且10秒后失效，设置超时时间
			redisTemplate.opsForValue().set("article_" + articleId, article, 10, TimeUnit.SECONDS);
		}
		return article;
	}

	public void save(Article article) {
		article.setId(idWorker.nextId() + "");
		articleDao.save(article);
	}

	public void update(Article article) {
		//如有变动，需要删除redis 中的数据，保证数据统一性
		redisTemplate.delete("article_" + article.getId());
		articleDao.save(article);
	}

	public void deleteById(String articleId) {
		//需要删除redis 中的数据
		redisTemplate.delete("article_" + articleId);
		articleDao.deleteById(articleId);
	}
	
	
	
}
