package com.book.library.controller.system;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.library.po.SysPermission;
import com.book.library.service.SysPermissionService;

@Controller
@RequestMapping(value="/syspermission")
public class SysPermissionController {
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@RequestMapping(value="/permissionlist",method={RequestMethod.GET})
	public String findAllPermission(Model model){
		List<SysPermission> sysPermissions = sysPermissionService.findAll();
		model.addAttribute("permissionList", sysPermissions);
		return "system/permission/list";
	}
	
	@RequestMapping(value="/{parentid}/appendChild",method={RequestMethod.GET})
	public String showAppendChildForm(@PathVariable("parentid")Long parentid,Model model){
		List<String> types = sysPermissionService.findAllType();
		model.addAttribute("types", types);
		SysPermission parent = sysPermissionService.findByPermissionId(parentid);
		model.addAttribute("parent", parent);
		SysPermission child = new SysPermission();
		child.setParentid(parentid);
		child.setParentids(parent.makeSelfAsParentIds());
		model.addAttribute("permission", child);
		model.addAttribute("op", "新增子节点");
		return "system/permission/edit";
	}
	
	@RequestMapping(value="/{parentid}/appendChild",method={RequestMethod.POST})
	public String create(SysPermission sysPermission , RedirectAttributes attributes){
		sysPermissionService.create(sysPermission);
		attributes.addFlashAttribute("msg","新增子节点成功");
		return "redirect:/syspermission/permissionlist";
	}
	
	@RequestMapping(value="/{id}/update",method={RequestMethod.GET})
	public String toUpdatePage(@PathVariable("id")Long id,Model model){
		List<String> types = sysPermissionService.findAllType();
		model.addAttribute("types", types);
		model.addAttribute("permission",sysPermissionService.findByPermissionId(id));
		model.addAttribute("op", "修改");
		return "system/permission/edit";
	}
	
	@RequestMapping(value="/{id}/update",method={RequestMethod.POST})
	public String updatePermission(RedirectAttributes redirectAttributes,
			SysPermission sysPermission){
		sysPermissionService.updatePermission(sysPermission);
		redirectAttributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/syspermission/permissionlist";
	}
	
	@RequestMapping(value="/{id}/delete",method={RequestMethod.GET})
	public String deletePermission(@PathVariable("id")Long id,
			RedirectAttributes redirectAttributes){
		sysPermissionService.deletePermission(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/syspermission/permissionlist";
	}
	
	
	
}
