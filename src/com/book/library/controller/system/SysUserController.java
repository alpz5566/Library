package com.book.library.controller.system;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.library.po.ActiveUser;
import com.book.library.po.SysUser;
import com.book.library.service.SysUserService;
import com.book.library.utill.PageForm;

@Controller
@RequestMapping(value="/sysuser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/userlist")
	public String findAllSysUser(@RequestParam(required = false,value="page",defaultValue = "1") int page,
            @RequestParam(required = false,value="pagesize",defaultValue = "2") int limit,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String dir,
			ServletRequest request,PageForm pageForm,Model model){
		List<SysUser> users = sysUserService.findAllUser(pageForm.toPageBounds());
		List<SysUser> users1 = sysUserService.findAllUser();
		List<ActiveUser> activeUsers = null;
		for(SysUser user : users1){
			//查询用户所属角色
//			List<String> roles = sysUserService.findRolesByUserid(user.getId());
		}
		model.addAttribute("users", users1);
		return "system/user/list2";
	}
}
