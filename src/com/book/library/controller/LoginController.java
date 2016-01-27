package com.book.library.controller;

import javax.servlet.ServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
		String exceptionClassName2 = request.getParameter("shiroLoginFailure");

		System.err.println("username:"+username);
		System.err.println("password:"+password);
		System.err.println("exceptionClassName:"+exceptionClassName);
		System.err.println("exceptionClassName2:"+exceptionClassName2);
		String error = "";
		//根据shiro返回的异常类路径判断，抛出指定异常信息
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				//最终会抛给异常处理器
				error = "账号不存在";
//				throw new CustomException("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				error = "用户名/密码错误";
//				throw new CustomException("用户名/密码错误");
			} else if("randomCodeError".equals(exceptionClassName)){
				error = "验证码错误";
//				throw new CustomException("验证码错误 ");
			}
			else {
				error = "登录出现未知错误";
//				throw new Exception();//最终在异常处理器生成未知错误
			}
		}
		//此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		//登陆失败还到login页面
//		System.err.println("LOGIN SUCCESS !");
		model.addAttribute("error", error);
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
