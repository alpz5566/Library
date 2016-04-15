package com.book.library.controller.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.library.po.SysDictionary;
import com.book.library.service.SysDictionaryService;

/**
 * 数据字典
 * @author liaopingzhu
 *
 */
@Controller
@RequestMapping(value="/dictionary")
public class SysDictionaryController {

	@Autowired
	private SysDictionaryService sysDictionaryService;

	@RequiresPermissions("dictionary:view")
	@RequestMapping(value="/list",method={RequestMethod.GET})
	public String showDicList(Model model){
		List<SysDictionary> dictionaries = sysDictionaryService.findAll();
		model.addAttribute("dictionaries", dictionaries); 
		return "system/dictionary/list";
	}
	
	@RequiresPermissions("dictionary:create")
	@RequestMapping(value="/save",method={RequestMethod.GET})
	public String toCreateDicPage(Model model){
		model.addAttribute("dictionary", new SysDictionary());
		model.addAttribute("op", "新增");
		return "system/dictionary/edit";
	}
	
	@RequiresPermissions("dictionary:create")
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public String CreateDiction(RedirectAttributes attributes,SysDictionary sysDictionary){
		sysDictionaryService.save(sysDictionary);
		attributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/dictionary/list";
	}
	
	@RequiresPermissions("dictionary:update")
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String toUpdateDiction(Model model,@RequestParam(required = true)String id){
		SysDictionary sysDictionary = sysDictionaryService.findDictionaryById(id);
		model.addAttribute("dictionary", sysDictionary);
		model.addAttribute("op", "修改");
		return "system/dictionary/edit";
	}
	
	@RequiresPermissions("dictionary:update")
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String UpdateDiction(RedirectAttributes attributes,SysDictionary sysDictionary){
		System.out.println(sysDictionary.getId());
		sysDictionaryService.update(sysDictionary);
		attributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/dictionary/list";
	}
	
	@RequiresPermissions("dictionary:delete")
	@RequestMapping(value="delete",method={RequestMethod.GET})
	public String deleteDiction(RedirectAttributes attributes,
			@RequestParam(required=true)String id){
		sysDictionaryService.delete(id);
		attributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/dictionary/list";
	}
	
}
