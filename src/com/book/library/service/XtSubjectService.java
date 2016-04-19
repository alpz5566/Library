package com.book.library.service;

import java.util.List;

import com.book.library.po.XtSubject;

public interface XtSubjectService {

	List<XtSubject> findAll();

	void save(XtSubject subject);

	XtSubject findSubjectById(String id);

	void update(XtSubject subject);

	void delete(String id);

	//根据teacherid查询选题列表
	List<XtSubject> findSubjectByTeacherId(String teacherid);

	String findSubjectIdByUserId(String userid);

}
