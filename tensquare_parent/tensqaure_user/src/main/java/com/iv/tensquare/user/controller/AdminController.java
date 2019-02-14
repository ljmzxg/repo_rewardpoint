package com.iv.tensquare.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iv.tensquare.user.pojo.Admin;
import com.iv.tensquare.user.service.AdminService;

import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	/**
	 * 管理员登陆
	 * @param admin
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(@RequestBody Admin admin) {
		Admin adminLogin = adminService.login(admin);
		if(adminLogin == null) {
			return new Result(false, StatusCode.LOGINERROR, "登录失败");
		}
		
		//使得前后端可以通话的操作，采用JWT 实现
		//生成令牌
		String token = jwtUtil.createJWT(adminLogin.getId(), adminLogin.getLoginname(), "admin");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("role", "admin");
		return new Result(true, StatusCode.OK, "登录成功", map);
	}

	/**
	 * 查询所有Admin 数据
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", adminService.findAll());
	}
	
	/**
	 * 新增
	 * @param admin
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@RequestBody Admin admin) {
		adminService.add(admin);
		return new Result(true, StatusCode.OK, "添加成功");
	}
	
	/**
	 * 修改
	 * @param admin
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value = "/{adminId}", method = RequestMethod.PUT)
	public Result update(@RequestBody Admin admin, @PathVariable String adminId) {
		admin.setId(adminId);
		adminService.update(admin);
		return new Result(true, StatusCode.OK, "更新成功");
	}
	
	/**
	 * 根据adminId 查询Admin实体信息
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value = "/{adminId}", method = RequestMethod.GET)
	public Result findById(@PathVariable String adminId) {
		return new Result(true, StatusCode.OK, "查询成功", adminService.findById(adminId));
	}
	
	/**
	 * 根据adminId 删除Admin 信息
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value = "/{adminId}", method = RequestMethod.DELETE)
	public Result deleterById(@PathVariable String adminId) {
		adminService.deleteById(adminId);
		return new Result(true, StatusCode.OK, "删除成功");
	}
}
