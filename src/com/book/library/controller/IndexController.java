package com.book.library.controller;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.library.po.ActiveUser;
import com.book.library.po.SysUser;
import com.book.library.service.SysService;
import com.book.library.service.SysUserService;
import com.book.library.utill.MD5;
import com.book.library.web.exception.CustomException;

@Controller
@RequestMapping(value="index")
public class IndexController {

	@Autowired
	private SysService sysService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 跳转主页
	 * @return
	 */
//	@RequiresPermissions("item:query111")
	@RequestMapping(value="/home")
	public String toIndexPage(ServletRequest request,Model model){
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
		String username = activeUser.getUsername();
		System.err.println("用户名为：" + username);
		model.addAttribute("activeUser", activeUser);
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
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
		System.out.println(activeUser.getUsercode());
		System.out.println(activeUser.getUserid());
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
			ServletRequest request,Model model) throws Exception{
		//获取当前用户
		String error = "";
		Subject currentUser = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) currentUser.getPrincipal();
		String id = activeUser.getUserid();
		SysUser sysUser = sysService.findSysUserByUserId(id);
		String salt = sysUser.getSalt();
		String password_db = sysUser.getPassword();
		String password_input_md5 = new MD5().getMD5ofStr(salt + oldpassword);
		if(!password_input_md5.equalsIgnoreCase(password_db)){
			error = "原密码错误";
			model.addAttribute("error", error);
			return "updatePassword";
		}else{
			String password_md5 = new MD5().getMD5ofStr(salt + newpassword);
			sysUserService.updatePassword(id,password_md5);
			System.out.println("密码修改成功");
			return "redirect:/index/home";
		}
	}
	
}
