package com.book.library.controller.graduation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.library.po.SysDictionary;
import com.book.library.po.SysUser;
import com.book.library.po.XtStudent;
import com.book.library.po.XtSubject;
import com.book.library.po.XtTeacher;
import com.book.library.service.SysDictionaryService;
import com.book.library.service.XtStudentService;
import com.book.library.service.XtSubjectService;
import com.book.library.service.XtTeacherService;

@Controller
@RequestMapping(value="/student")
public class StudentController {

	@Autowired
	private XtStudentService studentService;
	
	@Autowired
	private SysDictionaryService dictionaryService;
	
	@Autowired
	private XtTeacherService teacherService;
	
	@Autowired
	private XtSubjectService subjectService; 
	

//	@RequiresPermissions("subject:viewList")
	@RequestMapping(value="/list",method={RequestMethod.GET})
	public String showList(Model model){
		setCommonData(model);
		List<XtStudent> students = studentService.findAll();
		for(XtStudent student : students){
			XtSubject subject = subjectService.findSubjectById(student.getSubjectid());
			XtTeacher teacher = teacherService.findTeacherById(student.getTeacherid());
			student.setTeacher(teacher);
			student.setSubject(subject);
		}
		model.addAttribute("students", students);
		return "student/list";
	}
	
//	@RequiresPermissions("subject:create")
	@RequestMapping(value="/save",method={RequestMethod.GET})
	public String toCreateStuPage(Model model){
		setCommonData(model);
		model.addAttribute("student", new XtStudent());
		model.addAttribute("op", "新增");
		return "student/edit";
	}
	
//	@RequiresPermissions("subject:create")
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public String CreateDiction(RedirectAttributes attributes,XtStudent student,SysUser sysUser){
		studentService.save(student,sysUser);
		attributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/student/list";
	}
	
//	@RequiresPermissions("subject:update")
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String toUpdateDiction(Model model,@RequestParam(required = true)String id){
//		setCommonData(model);
		XtStudent student = studentService.findStuById(id);
		model.addAttribute("student", student);
		model.addAttribute("op", "修改");
		return "student/edit";
	}
	
//	@RequiresPermissions("subject:update")
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String UpdateDiction(RedirectAttributes attributes,XtStudent student){
		studentService.update(student);
		attributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/student/list";
	}
	
//	@RequiresPermissions("subject:delete")
	@RequestMapping(value="delete",method={RequestMethod.GET})
	public String deleteDiction(RedirectAttributes attributes,
			@RequestParam(required=true)String id){
		studentService.delete(id);
		attributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/student/list";
	}
	
	/**
	 * 查询出所需要的字典表
	 * @param model
	 */
	@SuppressWarnings("unused")
	private void setCommonData(Model model) {
		List<SysDictionary> majors = dictionaryService.findByType("major");
		model.addAttribute("majors", majors);
    }
}
