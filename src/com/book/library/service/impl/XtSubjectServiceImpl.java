package com.book.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysDictionaryMapper;
import com.book.library.mapper.XtSubjectMapper;
import com.book.library.po.XtSubject;
import com.book.library.po.XtSubjectExample;
import com.book.library.service.XtSubjectService;

@Component
@Transactional
public class XtSubjectServiceImpl implements XtSubjectService{
	
	@Autowired
	private XtSubjectMapper xtSubjectMapper;
	
	@Autowired
	private SysDictionaryMapper dictionaryMapper;

	@Override
	public List<XtSubject> findAll() {
		XtSubjectExample example = new XtSubjectExample();
		List<XtSubject> subjects = xtSubjectMapper.selectByExample(example);
		return subjects;
	}

	@Override
	public void save(XtSubject subject) {
		xtSubjectMapper.insert(subject);
	}

	@Override
	public XtSubject findSubjectById(String id) {
		// TODO Auto-generated method stub
		XtSubject subject = xtSubjectMapper.selectByPrimaryKey(id);
		return subject;
	}

	@Override
	public void update(XtSubject subject) {
		xtSubjectMapper.updateByPrimaryKey(subject);
	}

	@Override
	public void delete(String id) {
		xtSubjectMapper.deleteByPrimaryKey(id);
	}
	
}