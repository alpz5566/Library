package com.book.library.controller.system;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.library.po.ActiveUser;
import com.book.library.po.SysRole;
import com.book.library.po.SysUser;
import com.book.library.service.SysRoleService;
import com.book.library.service.SysUserService;
import com.book.library.utill.PageForm;

@Controller
@RequestMapping(value="/sysuser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService roleService;
	
	@RequestMapping(value="/userlist")
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
	
	@RequestMapping(value="/delete",method={RequestMethod.POST})
	public String  deleteSysUser(@RequestParam(required = false)String id){
		sysUserService.deleteUserById(id);
		return "redirect:/sysuser/userlist";
	}
	
	@RequestMapping(value="/editpage")
	public String toUpdatePage(@RequestParam(required = false)String id
			,Model model){
		setCommonData(model);
		SysUser sysUser = sysUserService.findUserById(id);
		model.addAttribute("sysuser",sysUser);
		model.addAttribute("op", "修改");
		return "system/user/edit";
	}
	
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String updateSysUser(SysUser sysUser,Model model){
		sysUserService.updateEntity(sysUser);
		return "redirect:/sysuser/userlist";
	}
	
	@RequestMapping(value="/save",method={RequestMethod.GET})
	public String toSavePage(Model model){
		setCommonData(model);
		model.addAttribute("user", new SysUser());
		model.addAttribute("op", "新增");
		return "sysuser/user/edit";
	}
	
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public String saveSysUser(SysUser sysUser,Model model,
			RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("msg","新增成功");
		model.addAttribute("op", "新增");
		return "redirect:/sysuser/userlist";
	}
	
	/**
	 * 将角色全部查询出来供使用
	 * @param model
	 */
	private void setCommonData(Model model) {
		List<SysRole> roles = roleService.findAll();
        model.addAttribute("roleList", roles);
    }
}
