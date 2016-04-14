package com.book.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysPermissionMapper;
import com.book.library.po.SysPermission;
import com.book.library.service.SysPermissionService;

@Component
@Transactional
public class SysPermissionServiceImpl implements SysPermissionService{

	@Autowired
	private SysPermissionMapper permissionMapper;
	
	@Override
	public List<SysPermission> selectPermissionByRoleId(String id) {
		List<SysPermission> permissions = permissionMapper.selectPermissionByRoleId(id);
		return permissions;
	}

}
