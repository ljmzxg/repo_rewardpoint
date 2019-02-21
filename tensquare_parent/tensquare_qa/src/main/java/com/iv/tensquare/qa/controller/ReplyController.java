package com.iv.tensquare.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.qa.pojo.Reply;
import com.iv.tensquare.qa.service.ReplyService;
import com.spring4all.swagger.EnableSwagger2Doc;

import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@EnableSwagger2Doc
@Api("回答")
@RestController
@CrossOrigin
@RequestMapping("/reply")
@RefreshScope
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@ApiOperation("查询所有回答")
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", replyService.findAll());
	}

	@ApiOperation("根据replyId查询回答")
	@RequestMapping(value = "/{replyId}", method = RequestMethod.GET)
	public Result findById(@PathVariable String replyId) {
		return new Result(true, StatusCode.OK, "查询成功", replyService.findById(replyId));
	}

	@ApiOperation("保存回答")
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Reply reply) {
		replyService.save(reply);
		return new Result(true, StatusCode.OK, "添加成功");
	}

	@ApiOperation("更新回答")
	@RequestMapping(value = "/{replyId}", method = RequestMethod.PUT)
	public Result update(@PathVariable String replyId, @RequestBody Reply reply) {
		reply.setId(replyId);
		replyService.update(reply);
		return new Result(true, StatusCode.OK, "更新成功");
	}

	@ApiOperation("根据replyId删除回答")
	@RequestMapping(value = "/{replyId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String replyId) {
		replyService.deleteById(replyId);
		return new Result(true, StatusCode.OK, "删除成功");
	}
	
	
}
