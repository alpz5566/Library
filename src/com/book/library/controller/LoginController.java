package com.book.library.controller;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登陆页面 shiro处理
 * @author liaopingzhu
 *
 */
@Controller
@RequestMapping(value="/user")
public class LoginController {
	
	@RequestMapping(value="/login")
	public String login(ServletRequest request,Model model) throws Exception{
		return "login";
	}
	
	/**
	 * 刷新验证码
	 * @return
	 */
	@RequestMapping("/refreshRandomCode")
	public String refreshRandomCode(){
		return "validatecode";
	}
	
}
