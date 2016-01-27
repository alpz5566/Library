package com.book.library.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="index")
public class IndexController {

	/**
	 * 测试用例
	 * @return
	 */
	@RequiresPermissions("item:query111")
	@RequestMapping(value="/home")
	public String toIndexPage(){
		return "index";
	}
}
