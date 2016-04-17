package com.book.library.service;

import java.util.List;

import com.book.library.po.XtStudent;

public interface XtStudentService {

	List<XtStudent> findAll();

	XtStudent findStuById(String id);

	void update(XtStudent student);

	void delete(String id);

	void save(XtStudent student);

}
