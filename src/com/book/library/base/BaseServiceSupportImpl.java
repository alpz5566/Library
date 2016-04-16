package com.book.library.base;

import java.lang.reflect.ParameterizedType;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysPermissionMapper;
import com.book.library.mapper.SysRoleMapper;
import com.book.library.mapper.SysUserMapper;
import com.book.library.mapper.XtStudentMapper;
import com.book.library.mapper.XtSubjectMapper;
import com.book.library.mapper.XtTeacherMapper;
import com.book.library.service.SysDictionaryService;

@Transactional
public abstract class BaseServiceSupportImpl<T> implements BaseServiceSupport<T>{
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	//-------------注入所需要的mapper----------//
	@Autowired
	private SysDictionaryService dictionaryService;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private XtStudentMapper xtStudentMapper;
	@Autowired
	private XtTeacherMapper xtTeacherMapper;
	@Autowired
	private XtSubjectMapper xtSubjectMapper;
	//--------------注入-----------------------//
	
	protected T model;
	
	@SuppressWarnings("unchecked")
	public BaseServiceSupportImpl(){
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取当前可用Session
	 * @return
	 */
	protected SqlSession getSqlSession(){
		return sessionFactory.openSession();
	}

}
