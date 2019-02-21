package com.iv.tensquare.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.base.pojo.Label;
import com.iv.tensquare.base.service.LabelService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

@RestController
@CrossOrigin
@RequestMapping("/label")
@RefreshScope
public class LabelController {

	@Autowired
	private LabelService labelService;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * 查询所有Label
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		//获取头信息
		String header = request.getHeader("Authorization");
		System.out.println("header" + header);
		
		return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
	}

	/**
	 * 根据labelId 查询label 实体信息
	 * 
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
	public Result findById(@PathVariable("label") String labelId) {
		return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
	}

	/**
	 * 保存label 信息
	 * 
	 * @param label
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Label label) {
		labelService.save(label);
		return new Result(true, StatusCode.OK, "添加成功");
	}

	/**
	 * 更新label 记录
	 * 
	 * @param labelId
	 * @param label
	 * @return
	 */
	@RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
	public Result update(@PathVariable String labelId, @RequestBody Label label) {
		label.setId(labelId);
		labelService.update(label);
		return new Result(true, StatusCode.OK, "更新成功");
	}

	/**
	 * 根据labelId 删除label 实体信息
	 * 
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String labelId) {
		labelService.deleteById(labelId);
		return new Result(true, StatusCode.OK, "删除成功");
	}

	/**
	 * 条件查询
	 * 
	 * @param label
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Result findSearch(@RequestBody Label label) {
		List<Label> labelList = labelService.findSearch(label);
		return new Result(true, StatusCode.OK, "查询成功", labelList);
	}

	/**
	 * 根据条件进行分页查询
	 * @param label
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
	public Result pageSearch(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
		Page<Label> labelPage = labelService.pageSearch(label, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(labelPage.getTotalElements(), labelPage.getContent()));

	}

}
