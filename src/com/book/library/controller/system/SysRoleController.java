package com.book.library.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.library.po.SysPermission;
import com.book.library.po.SysRole;
import com.book.library.service.SysPermissionService;
import com.book.library.service.SysRoleService;

@Controller
@RequestMapping(value="/sysrole")
public class SysRoleController {
	
	@Autowired
	private SysRoleService roleService;
	
	@Autowired
	private SysPermissionService permissionService;
	
	@RequestMapping(value="/rolelist",method={RequestMethod.GET})
	public String findAllRoles(Model model){
		List<SysRole> roles = null;
		try {
			roles = roleService.findAll();
			List<SysPermission> permissions = new ArrayList<SysPermission>();
			for(SysRole role : roles){
				List<String> permissionListStr = new ArrayList<String>();
				permissions = permissionService.selectPermissionByRoleId(role.getId());
				if(permissions.size() != 0){
					for(int i = 0;i < permissions.size();i++){
						permissionListStr.add(permissions.get(i).getName());
					}					
					role.setPermissionListStr(permissionListStr);
				}
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("roleList", roles);
		return "system/role/list";
	}
	
	@RequestMapping(value="")
	public String toSaveRolePage(){
		return "";
	}
	
	
}
