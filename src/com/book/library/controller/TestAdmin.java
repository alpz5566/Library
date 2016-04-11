package com.book.library.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.library.po.SysUser;
import com.book.library.service.SysUserService;
import com.book.library.utill.AjaxResult;
import com.book.library.utill.PageForm;

/**
 * 页面测试
 * @author L------F
 *
 */
@Controller  //申明这是个控制器
@RequestMapping(value="/user1")
public class TestAdmin {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value="/admin")
	public String test(){
		System.out.println("哈哈");
		return "test/test";
	}
	
	@RequiresPermissions("item:query")
	@RequestMapping(value="/list")
	@ResponseBody
	public Map findAllUser(){
		List<SysUser> users = sysUserService.findAllUser();
		return AjaxResult.ajaxResult("全部用户", users);
	}
	
	@RequestMapping(value="/userlist")
	@ResponseBody
	public List<SysUser> findAllSysUser(@RequestParam(required = false,value="page",defaultValue = "1") int page,
            @RequestParam(required = false,value="pagesize",defaultValue = "2") int limit,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String dir,
			ServletRequest request,PageForm pageForm){
		pageForm.setPage(page);
		pageForm.setLimit(limit);
		List<SysUser> users = sysUserService.findAllUser(pageForm.toPageBounds());
		return users;
	}
	
	@RequestMapping(value="/insert")
	public String insert(){
		sysUserService.insert();
		System.out.println("chenggong");
		return "test/test";
		
	}
	
	@RequestMapping(value="/delete")
	public void delete(){
		sysUserService.delete();
		System.out.println("hello world");
	}
	
	@RequestMapping(value="/update")
	public void update(){
		sysUserService.update();
		System.out.println("success");
	}
	
	@RequestMapping(value="/login")
	public SysUser login(String username,String password){
	 SysUser user= null;
	try {
		user = sysUserService.login(username, password);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(user.getUsername()+" "+user.getPassword());
		return user;
	}

}
