package com.book.library.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.library.po.SysPermission;
import com.book.library.service.SysPermissionService;

@Controller
@RequestMapping(value="/syspermission")
public class SysPermissionController {
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
//	@ModelAttribute("types")
//	private SysPermission.permissionType[] permissionTypes(){
//		return SysPermission.permissionType.values();
//	}
	
	@RequestMapping(value="/permissionlist",method={RequestMethod.GET})
	public String findAllPermission(Model model){
		List<SysPermission> sysPermissions = sysPermissionService.findAll();
		model.addAttribute("permissionList", sysPermissions);
		return "system/permission/list";
	}
	
	
	
	
	
}
