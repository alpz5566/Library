package com.book.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysRoleMapper;
import com.book.library.mapper.SysUserMapper;
import com.book.library.mapper.SysUserRoleMapper;
import com.book.library.mapper.XtTeacherMapper;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserRole;
import com.book.library.po.XtTeacher;
import com.book.library.po.XtTeacherExample;
import com.book.library.service.XtTeacherService;
import com.book.library.utill.MD5;

@Component
@Transactional
public class XtTeacherServiceImpl implements XtTeacherService{
	
	private static String salt = "qwert";
	private static String roleName = "导师";
	
	@Autowired
	private XtTeacherMapper teacherMapper;
	
	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public List<XtTeacher> findAll() {
		XtTeacherExample example = new XtTeacherExample();
		XtTeacherExample.Criteria criteria = example.createCriteria();
		List<XtTeacher> teachers = teacherMapper.selectByExample(example);
		return teachers;
	}

	@Override
	public void save(XtTeacher teacher,SysUser sysUser) {
		String id = UUID.randomUUID().toString();
		MD5 md5 = new MD5();
		teacher.setId(id);
		sysUser.setId(id);
		sysUser.setUsername(teacher.getName());
		sysUser.setSalt(salt);
		String password = md5.getMD5ofStr(sysUser.getSalt() + sysUser.getPassword());
		sysUser.setPassword(password);
//		sysUser.setPassword(new MD5().getMD5ofStr(sysUser.getSalt() + sysUser.getPassword()));
		sysUser.setLocked(0);
		teacherMapper.insert(teacher);
		userMapper.insert(sysUser);
		
		//设置中间表 7de34596-35cb-485b-9f16-17a0d5b65958代表教师信息id
		String roleid = sysRoleMapper.selectIdByName(roleName);
		SysUserRole sysUserRole = new SysUserRole(
				UUID.randomUUID().toString(),id, roleid);
		sysUserRoleMapper.insert(sysUserRole);
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

	@Override
	public List<XtTeacher> getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
