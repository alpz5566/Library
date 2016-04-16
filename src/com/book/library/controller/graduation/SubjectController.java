package com.book.library.controller.graduation;

import java.util.List;

import javax.security.auth.Subject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.library.po.SysDictionary;
import com.book.library.po.XtSubject;
import com.book.library.service.SysDictionaryService;
import com.book.library.service.XtSubjectService;

/**
 * 选题控制器
 * @author liaopingzhu
 *
 */
@Controller
@RequestMapping(value="/subject")
public class SubjectController {

	@Autowired
	private XtSubjectService subjectService;
	
	@Autowired
	private SysDictionaryService dictionaryService;
	
//	@RequiresPermissions("subject:viewList")
	@RequestMapping(value="/list",method={RequestMethod.GET})
	public String showList(Model model){
		List<XtSubject> subjects = subjectService.findAll();
		model.addAttribute("subjects", subjects);
		return "subject/list";
	}
	
//	@RequiresPermissions("subject:create")
	@RequestMapping(value="/save",method={RequestMethod.GET})
	public String toCreateDicPage(Model model){
		List<SysDictionary> difficults = dictionaryService.findByType("difficult");
		List<SysDictionary> directions = dictionaryService.findByType("direction");
		model.addAttribute("difficults", difficults);
		model.addAttribute("directions", directions);
		model.addAttribute("subject", new XtSubject());
		model.addAttribute("op", "新增");
		return "subject/edit";
	}
	
//	@RequiresPermissions("subject:create")
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public String CreateDiction(RedirectAttributes attributes,XtSubject subject){
		subjectService.save(subject);
		attributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/subject/list";
	}
	
//	@RequiresPermissions("subject:update")
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String toUpdateDiction(Model model,@RequestParam(required = true)String id){
		XtSubject subject = subjectService.findSubjectById(id);
		model.addAttribute("subject", subject);
		model.addAttribute("op", "修改");
		return "subject/edit";
	}
	
//	@RequiresPermissions("subject:update")
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String UpdateDiction(RedirectAttributes attributes,XtSubject subject){
		subjectService.update(subject);
		attributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/subject/list";
	}
	
//	@RequiresPermissions("subject:delete")
	@RequestMapping(value="delete",method={RequestMethod.GET})
	public String deleteDiction(RedirectAttributes attributes,
			@RequestParam(required=true)String id){
		subjectService.delete(id);
		attributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/subject/list";
	}
	
}
