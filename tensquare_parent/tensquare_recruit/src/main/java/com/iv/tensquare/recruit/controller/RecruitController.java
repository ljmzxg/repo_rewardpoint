package com.iv.tensquare.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.recruit.pojo.Recruit;
import com.iv.tensquare.recruit.service.RecruitService;

import entity.Result;
import entity.StatusCode;

@RestController
@CrossOrigin
@RequestMapping("/recruit")
@RefreshScope
public class RecruitController {

	@Autowired
	private RecruitService recruitService;
	
	/**
	 * 获取推荐招聘信息
	 * @return
	 */
	@RequestMapping(value = "/search/recommend", method = RequestMethod.GET)
	public Result recommendList() {
		return new Result(true, StatusCode.OK, "查询成功", recruitService.recommendList("2"));
	}

	/**
	 * 获取最新招聘信息
	 * @return
	 */
	@RequestMapping(value = "/search/newlist", method = RequestMethod.GET)
	public Result newList() {
		return new Result(true, StatusCode.OK, "查询成功", recruitService.newList("0"));
	}
	
	/**
	 * 查询所有Label
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", recruitService.findAll());
	}

	/**
	 * 根据labelId 查询label 实体信息
	 * 
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/{recruitId}", method = RequestMethod.GET)
	public Result findById(@PathVariable("recruitId") String recruitId) {
		return new Result(true, StatusCode.OK, "查询成功", recruitService.findById(recruitId));
	}

	/**
	 * 保存label 信息
	 * 
	 * @param label
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Recruit recruit) {
		recruitService.save(recruit);
		return new Result(true, StatusCode.OK, "添加成功");
	}

	/**
	 * 更新label 记录
	 * 
	 * @param labelId
	 * @param label
	 * @return
	 */
	@RequestMapping(value = "/{recruitId}", method = RequestMethod.PUT)
	public Result update(@PathVariable String recruitId, @RequestBody Recruit recruit) {
		recruit.setId(recruitId);
		recruitService.update(recruit);
		return new Result(true, StatusCode.OK, "更新成功");
	}

	/**
	 * 根据labelId 删除label 实体信息
	 * 
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/{recruitId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String recruitId) {
		recruitService.deleteById(recruitId);
		return new Result(true, StatusCode.OK, "删除成功");
	}

	
}
