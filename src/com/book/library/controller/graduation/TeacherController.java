package com.book.library.controller.graduation;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.library.po.XtTeacher;
import com.book.library.service.XtTeacherService;
import com.book.library.utill.ItextManager;

@Controller
@RequestMapping(value="/teacher")
public class TeacherController {

	@Autowired
	private XtTeacherService teacherService;
	
//	@RequiresPermissions("teacher:viewList")
	@RequestMapping(value="/list",method={RequestMethod.GET})
	public String showList(Model model){
		List<XtTeacher> teachers = teacherService.findAll();
		model.addAttribute("teachers", teachers);
		return "teacher/list";
	}
	
//	@RequiresPermissions("subject:create")
	@RequestMapping(value="/save",method={RequestMethod.GET})
	public String toCreateTeacherPage(Model model){
//		setCommonData(model);
		model.addAttribute("subject", new XtTeacher());
		model.addAttribute("op", "新增");
		return "teacher/edit";
	}
	
//	@RequiresPermissions("subject:create")
	@RequestMapping(value="/save",method={RequestMethod.POST})
	public String CreateDiction(RedirectAttributes attributes,XtTeacher teacher){
		teacherService.save(teacher);
		attributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/teacher/list";
	}
	
//	@RequiresPermissions("subject:update")
	@RequestMapping(value="/update",method={RequestMethod.GET})
	public String toUpdateDiction(Model model,@RequestParam(required = true)String id){
//		setCommonData(model);
		XtTeacher teacher = teacherService.findTeacherById(id);
		model.addAttribute("teacher", teacher);
		model.addAttribute("op", "修改");
		return "teacher/edit";
	}
	
//	@RequiresPermissions("subject:update")
	@RequestMapping(value="/update",method={RequestMethod.POST})
	public String UpdateDiction(RedirectAttributes attributes,XtTeacher teacher){
		teacherService.update(teacher);
		attributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/teacher/list";
	}
	
//	@RequiresPermissions("subject:delete")
	@RequestMapping(value="delete",method={RequestMethod.GET})
	public String deleteDiction(RedirectAttributes attributes,
			@RequestParam(required=true)String id){
		teacherService.delete(id);
		attributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/teacher/list";
	}
	
	//导出教师数据
	@RequestMapping(value="/exportword")  
	public String exportWord(HttpServletRequest request,HttpServletResponse response) throws Exception{  
	    String type = request.getParameter("type");  
	    response.setContentType("application/octet-stream; charset=UTF-8");    
	    if("word".equals(type)){  
	        response.setHeader("content-disposition", "attachment;filename=" + new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date()) + ".doc");  
	    }else if("pdf".equals(type)){  
	        response.setHeader("content-disposition", "attachment;filename=" + new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date()) + ".pdf");  
	    }  
	      
	    OutputStream out = response.getOutputStream();  
	    ItextManager tm = ItextManager.getInstance();  
	    List<XtTeacher> teachers = teacherService.findAll();  
	      
	    tm.createRtfContextTeacher(teachers,out,type);  
	      
	    out.flush();  
	    out.close();  
	    return null;  
	}  
	
}
