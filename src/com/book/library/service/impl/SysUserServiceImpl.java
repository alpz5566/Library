package com.book.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysUserMapper;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;
import com.book.library.service.SysUserService;

@Component
@Transactional
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public List<SysUser> findAllUser() {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
//		select *from sys_user where id like 'lisi';
//		criteria.andIdEqualTo("lisi");
		List<SysUser> users = sysUserMapper.selectByExample(example);
		return users;
	}



	
}
