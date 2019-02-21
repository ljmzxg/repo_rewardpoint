package com.iv.tensquare.spit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.spit.pojo.Spit;
import com.iv.tensquare.spit.service.SpitService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

@RestController
@CrossOrigin
@RequestMapping("/spit")
@RefreshScope
public class SpitController {

	@Autowired
	private SpitService spitService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
	}
	
	@RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
	public Result findById(@PathVariable String spitId) {
		return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Result saveSpit(@RequestBody Spit spit) {
		spitService.save(spit);
		return new Result(true, StatusCode.OK, "保存成功");
	}
	
	@RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
	public Result updateSpit(@RequestBody Spit spit, @PathVariable String spitId) {
		spit.set_id(spitId);
		spitService.update(spit);
		return new Result(true, StatusCode.OK, "修改成功");
	}
	
	@RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable String spitId) {
		spitService.deleteById(spitId);
		return new Result(true, StatusCode.OK, "删除成功");
	}
	
	@RequestMapping(value = "/{parentid}/{page}/{size}", method = RequestMethod.GET)
	public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
		Page<Spit> pageSpit = spitService.findByParentid(parentid, page, size);
		return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(pageSpit.getTotalElements(), pageSpit.getContent()));
	}
	
	@RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
	public Result thumbup(@PathVariable String spitId) {
		
		//控制不能重复点赞，用户ID 需要传入
		String userid = "123";
		if(redisTemplate.opsForValue().get("thumbup_" + userid) != null) {
			return new Result(false, StatusCode.REPERROR, "不能重复点赞");
		}
		
		spitService.thumbupById(spitId);
		redisTemplate.opsForValue().set("thumbup_" + userid, spitId);
		return new Result(true, StatusCode.OK, "点赞成功");
	}
	
	
}
