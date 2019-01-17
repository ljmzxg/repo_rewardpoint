package com.iv.tensquare.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.qa.pojo.Problem;
import com.iv.tensquare.qa.service.ProblemService;
import com.spring4all.swagger.EnableSwagger2Doc;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@EnableSwagger2Doc
@Api("问题")
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	
	/**
	 * 最新回答列表
	 * @param labelId
	 * @param page
	 * @param size
	 * @return
	 */
	@ApiOperation("最新回答列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "labelId", value = "标签ID", required = true, dataType = "String"),
		@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "size", value = "页数", required = true, dataType = "String")
	})
	@RequestMapping(value = "/newlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
	public Result newList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pageData = problemService.newList(labelId, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageData.getTotalElements(), pageData.getContent()));
	}

	@ApiOperation("热门问答列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "labelId", value = "标签ID", required = true, dataType = "String"),
		@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "size", value = "页数", required = true, dataType = "String")
	})
	@RequestMapping(value = "/hotlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
	public Result hotList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pageData = problemService.hotList(labelId, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));
	}
	
	@ApiOperation("等待问答列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "labelId", value = "标签ID", required = true, dataType = "String"),
		@ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "size", value = "页数", required = true, dataType = "String")
	})
	@RequestMapping(value = "/waitlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
	public Result waitList(@PathVariable String labelId, @PathVariable int page , @PathVariable int size) {
		Page<Problem> pageData = problemService.waitList(labelId, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));
	}
	
	@ApiOperation("查询所有问题")
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
	}

	@ApiOperation("根据labelId查询问题")
	@ApiImplicitParam(name = "labelId", value = "标签ID", required = true, dataType = "String")
	@RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
	public Result findById(@PathVariable String problemId) {
		return new Result(true, StatusCode.OK, "查询成功", problemService.findById(problemId));
	}

	@ApiOperation("保存问题")
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Problem problem) {
		problemService.save(problem);
		return new Result(true, StatusCode.OK, "添加成功");
	}

	@ApiOperation("更新问题")
	@RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
	public Result update(@PathVariable String problemId, @RequestBody Problem problem) {
		problem.setId(problemId);
		problemService.update(problem);
		return new Result(true, StatusCode.OK, "更新成功");
	}

	@ApiOperation("删除问题")
	@ApiImplicitParam(name = "problemId", value = "问题ID", required = true, dataType = "String")
	@RequestMapping(value = "/{problemId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String problemId) {
		problemService.deleteById(problemId);
		return new Result(true, StatusCode.OK, "删除成功");
	}
	
}
