package com.book.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysRoleMapper;
import com.book.library.mapper.SysUserMapper;
import com.book.library.mapper.SysUserRoleMapper;
import com.book.library.mapper.XtStudentMapper;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserRole;
import com.book.library.po.XtStudent;
import com.book.library.po.XtStudentExample;
import com.book.library.service.XtStudentService;
import com.book.library.utill.MD5;

@Component
@Transactional
public class XtStudentServiceImpl implements XtStudentService{

	private static String salt = "qwert";
	private static String roleName = "学生";
	
	@Autowired
	private XtStudentMapper studentMapper;
	
	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
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
	public void save(XtStudent student,SysUser sysUser) {
		String id = UUID.randomUUID().toString();
		MD5 md5 = new MD5();
		student.setId(id);
		sysUser.setId(id);
		sysUser.setUsername(student.getName());
		sysUser.setSalt(salt);
		String password = md5.getMD5ofStr(sysUser.getSalt() + sysUser.getPassword());
		sysUser.setPassword(password);
//		sysUser.setPassword(new MD5().getMD5ofStr(sysUser.getSalt() + sysUser.getPassword()));
		sysUser.setLocked(0);
		studentMapper.insert(student);
		userMapper.insert(sysUser);

		String roleid = sysRoleMapper.selectIdByName(roleName);
		SysUserRole sysUserRole = new SysUserRole(
				UUID.randomUUID().toString(),id, roleid);
		sysUserRoleMapper.insert(sysUserRole);
	}
	
}
