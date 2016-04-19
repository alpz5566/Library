package com.book.library.controller.graduation;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.library.po.ActiveUser;
import com.book.library.po.SysDictionary;
import com.book.library.po.SysRole;
import com.book.library.po.XtStudent;
import com.book.library.po.XtSubject;
import com.book.library.po.XtTeacher;
import com.book.library.service.SysDictionaryService;
import com.book.library.service.SysRoleService;
import com.book.library.service.XtStudentService;
import com.book.library.service.XtSubjectService;
import com.book.library.service.XtTeacherService;

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
	
	@Autowired
	private XtTeacherService teacherService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private XtStudentService studentService;
	
//	@RequiresPermissions("subject:viewList")
	@RequestMapping(value="/list",method={RequestMethod.GET})
	public String showList(Model model){
		List<XtSubject> subjects = subjectService.findAll();
		for(XtSubject subject : subjects){
			XtTeacher teacher = teacherService.findTeacherById(subject.getTid());
			subject.setTeacher(teacher);
		}
		model.addAttribute("subjects", subjects);
		return "subject/list";
	}
	
//	@RequiresPermissions("subject:create")
	@RequestMapping(value="/save",method={RequestMethod.GET})
	public String toCreateDicPage(Model model){
//		List<SysDictionary> difficults = dictionaryService.findByType("difficult");
//		List<SysDictionary> directions = dictionaryService.findByType("direction");
//		model.addAttribute("difficults", difficults);
//		model.addAttribute("directions", directions);
		setCommonData(model);
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
		setCommonData(model);
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
	
	/**
	 * 查询出所需要的字典表
	 * @param model
	 */
	@SuppressWarnings("unused")
	private void setCommonData(Model model) {
		List<SysDictionary> difficults = dictionaryService.findByType("difficult");
		List<SysDictionary> directions = dictionaryService.findByType("direction");
		List<XtTeacher> teachers = teacherService.findAll();
		model.addAttribute("teachers", teachers);
		model.addAttribute("difficults", difficults);
		model.addAttribute("directions", directions);
    }
	
	/**
	 * 我要选题 (学生功能)
	 * @return
	 */
	@RequestMapping(value="/iwantselect")
	public String IWantSelect(@RequestParam(required = true)String subjectid){
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
		String userid = activeUser.getUserid();
		XtStudent student = studentService.findStuById(userid);
		String old_subid = student.getSubjectid();
		student.setSubjectid(subjectid);
		studentService.update(student);
		//判断学生是否有选课
		String selectsb = subjectService.findSubjectIdByUserId(userid); 
		if(!selectsb.equals("0")){
			//已经选课，修改选课题
			//先删除之前选课的被选状态
			XtSubject xtSubject = subjectService.findSubjectById(old_subid);
			xtSubject.setIsselect(0);
			subjectService.update(xtSubject);
		}
		//再添加新的被选状态
		XtSubject xtSubject = subjectService.findSubjectById(subjectid);
		xtSubject.setIsselect(1);
		subjectService.update(xtSubject);
		return "redirect:/subject/list";
		
	}
	
	/**
	 * 查询我的选题
	 * @return
	 */
	@RequestMapping(value="/mysubject")
	public String findMySubject(Model model){
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
		String teacherid = activeUser.getUserid();
		List<XtSubject> subjects = subjectService.findSubjectByTeacherId(teacherid);
		model.addAttribute("subjects", subjects);
		return "/subject/mysubject";
	}
}
