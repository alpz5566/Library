package com.book.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.XtTeacherMapper;
import com.book.library.po.XtTeacher;
import com.book.library.po.XtTeacherExample;
import com.book.library.service.XtTeacherService;

@Component
@Transactional
public class XtTeacherServiceImpl implements XtTeacherService{
	
	@Autowired
	private XtTeacherMapper teacherMapper;

	@Override
	public List<XtTeacher> findAll() {
		XtTeacherExample example = new XtTeacherExample();
		XtTeacherExample.Criteria criteria = example.createCriteria();
		List<XtTeacher> teachers = teacherMapper.selectByExample(example);
		return teachers;
	}

	@Override
	public void save(XtTeacher teacher) {
		teacherMapper.insert(teacher);
	}

	@Override
	public XtTeacher findTeacherById(String id) {
		XtTeacher teacher = teacherMapper.selectByPrimaryKey(id);
		return teacher;
	}

	@Override
	public void delete(String id) {
		teacherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(XtTeacher teacher) {
		teacherMapper.updateByPrimaryKey(teacher);
	}
	
	
	
	
}
