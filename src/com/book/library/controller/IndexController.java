package com.book.library.controller;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.library.po.ActiveUser;
import com.book.library.po.SysUser;
import com.book.library.service.SysService;
import com.book.library.utill.MD5;
import com.book.library.web.exception.CustomException;

@Controller
@RequestMapping(value="index")
public class IndexController {

	@Autowired
	private SysService sysService;
	
	/**
	 * 跳转主页
	 * @return
	 */
//	@RequiresPermissions("item:query111")
	@RequestMapping(value="/home")
	public String toIndexPage(){
		return "index";
	}
	
	/**
	 * 跳转修改密码页面
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/toUpdatePasswordPage")
	public String toUpdatePassword(){
		Subject currentUser = SecurityUtils.getSubject(); //获取当前用户
		System.out.println(currentUser.getPrincipal().getClass().getSimpleName());
//		String userCode = (String)currentUser.getPrincipal();
		System.out.println(currentUser.getPrincipals().getRealmNames());
		System.out.println(currentUser.getPrincipals());
		ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject() ;
		System.out.println(activeUser.getUsercode());
		String id = activeUser.getUserid();
//		System.out.println(userCode);
		return "updatePassword";
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/updatePassword")
	public String UpdatePassword(@RequestParam(required=true,value="oldpassword")String oldpassword,
			@RequestParam(value="newpassword",required=true)String newpassword,
			ServletRequest request) throws Exception{
		ActiveUser activeUser = new ActiveUser();
		String id = activeUser.getUserid();
		SysUser sysUser = sysService.findSysUserByUserId(id);
		String password_db = sysUser.getPassword();
		String password_input_md5 = new MD5().getMD5ofStr(oldpassword);
		if(!password_input_md5.equalsIgnoreCase(password_db)){
			throw new CustomException("原密码错误");
		}else{
			
		}
		return "redirect:/index/home";
	}
	
}
