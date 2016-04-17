package com.book.library.service;

import java.util.List;

import com.book.library.po.SysUser;
import com.book.library.po.XtTeacher;

public interface XtTeacherService {

	List<XtTeacher> findAll();

	void save(XtTeacher teacher,SysUser sysUser);

	XtTeacher findTeacherById(String id);

	void delete(String id);

	void update(XtTeacher teacher);

	List<XtTeacher> getData();

}
