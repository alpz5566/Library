package com.book.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysRoleMapper;
import com.book.library.po.SysRole;
import com.book.library.po.SysRoleExample;
import com.book.library.service.SysRoleService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Component
@Transactional
public class SysRoleServiceImpl implements SysRoleService{

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public List<SysRole> findAllRoles(PageBounds pageBounds) throws Exception {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
//		List<SysRole> roles = sysRoleMapper.selectByExample(example,pageBounds);
		return null;
	}

	/**
	 * 新增，修改用户时所需要的rolelist
	 */
	@Override
	public List<SysRole> findAll() {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		List<SysRole> roles = sysRoleMapper.selectByExample(example);
		return roles;
	}

}
