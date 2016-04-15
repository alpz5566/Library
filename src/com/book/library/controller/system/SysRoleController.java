package com.book.library.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@SuppressWarnings("unused")
	private void setCommonData(Model model) {
		List<SysPermission> permissions = permissionService.findAll();
        model.addAttribute("permissionList", permissions);
    }
	
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
	
	@RequestMapping(value="save",method={RequestMethod.GET})
	public String toSaveRolePage(Model model){
		setCommonData(model);
		String permissionNames = "";
		model.addAttribute("permissionNames", permissionNames);
		model.addAttribute("role", new SysRole());
		model.addAttribute("op", "新增");
		return "system/role/edit";
	}
	
	@RequestMapping(value="save",method={RequestMethod.POST})
	public String saveRole(SysRole role ,Model model,RedirectAttributes redirectAttributes){
		roleService.save(role);
		redirectAttributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/sysrole/rolelist";
	}
	
	@RequestMapping(value="update",method={RequestMethod.GET})
	public String toUpdatePage(@RequestParam(required = true)String id
			,Model model){
		setCommonData(model);
		//准备修改回显的基本数据
		SysRole role = roleService.findRoleById(id);
		
		//准备修改回显的权限信息数据
		List<SysPermission> permissions = new ArrayList<SysPermission>();
		List<String> permissionListStr = new ArrayList<String>();
		List<Long> permissionIds = new ArrayList<Long>();
		String permissionNames = "";
		permissions = permissionService.selectPermissionByRoleId(role.getId());
		if(permissions.size() != 0){
			for(int i = 0;i < permissions.size();i++){
				permissionIds.add(permissions.get(i).getId());
			}
			role.setPermissionIds(permissionIds);
			permissionNames = getPermissionNames(permissionIds);
		}
		
		model.addAttribute("permissionNames", permissionNames);
		model.addAttribute("role", role);
		model.addAttribute("op", "修改");
		return "system/role/edit";
	}
	
	@RequestMapping(value="update",method={RequestMethod.POST})
	public String updateRole(RedirectAttributes redirectAttributes,SysRole sysRole){
		System.out.println(sysRole.getId());
		roleService.updateEntity(sysRole);
		redirectAttributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/sysrole/rolelist";
	}
	
	@RequestMapping(value="delete",method={RequestMethod.GET})
	public String deleteRole(@RequestParam(required = true)String id,
			RedirectAttributes redirectAttributes){
		roleService.deleteRole(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/sysrole/rolelist";
	}
	
	public String getPermissionName(Long permissionId){
		SysPermission sysPermission = permissionService.findByPermissionId(permissionId);
		if(sysPermission == null){
			return "";
		}
		return sysPermission.getName();
	}
	
	public String getPermissionNames(List<Long> permissionIds){
		if(permissionIds.isEmpty()){
			return "";
		}
		StringBuilder s = new StringBuilder();
    	for(Long permissionId : permissionIds){
    		SysPermission sysPermission = permissionService.findByPermissionId(permissionId);
    		if(sysPermission == null){
    			return "";
    		}
    		s.append(sysPermission.getName());
    		s.append(",");
    	}
    	if(s.length() > 0){
    		s.deleteCharAt(s.length() - 1);
    	}
    	return s.toString();
	}
	
	public boolean isin(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }
	
}
