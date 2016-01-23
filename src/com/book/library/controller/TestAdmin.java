package com.book.library.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.library.po.SysUser;
import com.book.library.service.SysUserService;
import com.book.library.utill.AjaxResult;



/**
 * 测试
 * @author L------F
 *
 */
@Controller  //申明这是个控制器
@RequestMapping(value="/user")
public class TestAdmin {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value="/admin")
	public String test(){
		System.out.println("哈哈");
		return "test/test";
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public Map findAllUser(){
		List<SysUser> users = sysUserService.findAllUser();
		return AjaxResult.ajaxResult("全部用户", users);
	}
	
	
	

}
