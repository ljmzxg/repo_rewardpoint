package com.iv.tensquare.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.search.pojo.Article;
import com.iv.tensquare.search.service.ArticleService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Article article) {
		articleService.save(article);
		return new Result(true, StatusCode.OK, "添加成功");
	}
	
	@RequestMapping(value = "/{key}/{page}/{size}", method = RequestMethod.GET)
	public Result findByKey(@PathVariable String key , @PathVariable int page , @PathVariable int size) {
		Page<Article> pageData = articleService.findByKey(key, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(pageData.getTotalElements(), pageData.getContent()));
		
	}
	
}
