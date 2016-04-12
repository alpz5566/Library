package com.book.library.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.library.service.SysUserService;

@Controller
@RequestMapping(value="/sysuser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/userlist")
	public String findAllSysUser(){
		return "system/user/list";
	}
}
