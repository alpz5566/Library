package com.book.library.service;

import java.util.List;

import com.book.library.po.XtSubject;

public interface XtSubjectService {

	List<XtSubject> findAll();

	void save(XtSubject subject);

	XtSubject findSubjectById(String id);

	void update(XtSubject subject);

	void delete(String id);

}
