package com.book.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.XtStudentMapper;
import com.book.library.po.XtStudent;
import com.book.library.po.XtStudentExample;
import com.book.library.service.XtStudentService;

@Component
@Transactional
public class XtStudentServiceImpl implements XtStudentService{

	@Autowired
	private XtStudentMapper studentMapper;
	
	@Override
	public List<XtStudent> findAll() {
		XtStudentExample example = new XtStudentExample();
		XtStudentExample.Criteria criteria = example.createCriteria();
		List<XtStudent> students = studentMapper.selectByExample(example);
		return students;
	}

	@Override
	public XtStudent findStuById(String id) {
		XtStudent student = studentMapper.selectByPrimaryKey(id);
		return student;
	}

	@Override
	public void update(XtStudent student) {
		studentMapper.updateByPrimaryKey(student);
	}

	@Override
	public void delete(String id) {
		studentMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void save(XtStudent student) {
		studentMapper.insert(student);
	}
	
}
