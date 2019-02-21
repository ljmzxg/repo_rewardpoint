package com.iv.tensquare.recruit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.recruit.pojo.Enterprise;
import com.iv.tensquare.recruit.service.EnterpriseService;

import entity.Result;
import entity.StatusCode;

@RestController
@CrossOrigin
@RequestMapping("/enterprise")
@RefreshScope
public class EnterpriseController {

	@Autowired
	private EnterpriseService enterprisService;
	
	/**
	 * 热门企业
	 * @return
	 */
	@RequestMapping(value = "/search/hostlist", method = RequestMethod.GET)
	public Result hostlist() {
		List<Enterprise> list = enterprisService.findByIshot("1");
		return new Result(true, StatusCode.OK, "查询成功", list);
	}
	
	
	/**
	 * 查询所有Label
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", enterprisService.findAll());
	}

	/**
	 * 根据labelId 查询label 实体信息
	 * 
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/{enterpriseId}", method = RequestMethod.GET)
	public Result findById(@PathVariable("enterpriseId") String enterpriseId) {
		return new Result(true, StatusCode.OK, "查询成功", enterprisService.findById(enterpriseId));
	}

	/**
	 * 保存label 信息
	 * 
	 * @param label
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Enterprise enterprise) {
		enterprisService.save(enterprise);
		return new Result(true, StatusCode.OK, "添加成功");
	}

	/**
	 * 更新label 记录
	 * 
	 * @param labelId
	 * @param label
	 * @return
	 */
	@RequestMapping(value = "/{enterpriseId}", method = RequestMethod.PUT)
	public Result update(@PathVariable String enterpriseId, @RequestBody Enterprise enterprise) {
		enterprise.setId(enterpriseId);
		enterprisService.update(enterprise);
		return new Result(true, StatusCode.OK, "更新成功");
	}

	/**
	 * 根据labelId 删除label 实体信息
	 * 
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/{enterpriseId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String enterpriseId) {
		enterprisService.deleteById(enterpriseId);
		return new Result(true, StatusCode.OK, "删除成功");
	}
	
	
	
	
}
