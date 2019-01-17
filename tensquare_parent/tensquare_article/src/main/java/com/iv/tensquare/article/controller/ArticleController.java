package com.iv.tensquare.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.article.pojo.Article;
import com.iv.tensquare.article.service.ArticleService;
import com.spring4all.swagger.EnableSwagger2Doc;

import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@EnableSwagger2Doc
@Api("文章")
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@ApiOperation("文章审核")
	@ApiImplicitParam(name = "articleId", value = "文章ID", required = true, dataType = "String")
	@RequestMapping(value = "/examine/{articleId}", method = RequestMethod.PUT)
	public Result updateState(@PathVariable String articleId) {
		articleService.updateState(articleId);
		return new Result(true, StatusCode.OK, "审核成功");
	}
	
	@ApiOperation("文章点赞")
	@ApiImplicitParam(name = "articleId", value = "文章ID", required = true, dataType = "String")
	@RequestMapping(value = "/thumbup/{articleId}", method = RequestMethod.PUT)
	public Result thumbup(@PathVariable String articleId) {
		articleService.addThumbup(articleId);
		return new Result(true, StatusCode.OK, "点赞成功");
	}
	
	
	@ApiOperation("查询所有文章")
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", articleService.findAll());
	}

	@ApiOperation("根据articleId查询文章")
	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
	public Result findById(@PathVariable String articleId) {
		return new Result(true, StatusCode.OK, "查询成功", articleService.findById(articleId));
	}

	@ApiOperation("保存文章")
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Article article) {
		articleService.save(article);
		return new Result(true, StatusCode.OK, "添加成功");
	}

	@ApiOperation("更新文章")
	@RequestMapping(value = "/{articleId}", method = RequestMethod.PUT)
	public Result update(@PathVariable String articleId, @RequestBody Article article) {
		article.setId(articleId);
		articleService.update(article);
		return new Result(true, StatusCode.OK, "更新成功");
	}

	@ApiOperation("根据articleId删除文章")
	@RequestMapping(value = "/{articleId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String articleId) {
		articleService.deleteById(articleId);
		return new Result(true, StatusCode.OK, "删除成功");
	}

}
