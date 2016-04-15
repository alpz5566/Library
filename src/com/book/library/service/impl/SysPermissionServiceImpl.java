package com.book.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysPermissionMapper;
import com.book.library.mapper.SysRolePermissionMapper;
import com.book.library.po.SysPermission;
import com.book.library.po.SysPermissionExample;
import com.book.library.service.SysPermissionService;

@Component
@Transactional
public class SysPermissionServiceImpl implements SysPermissionService{

	@Autowired
	private SysPermissionMapper permissionMapper;
	
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	
	@Override
	public List<SysPermission> selectPermissionByRoleId(String id) {
		List<SysPermission> permissions = permissionMapper.selectPermissionByRoleId(id);
		return permissions;
	}

	@Override
	public List<SysPermission> findAll() {
		SysPermissionExample example = new SysPermissionExample();
//		SysPermissionExample.Criteria criteria = example.createCriteria();
		List<SysPermission> sysPermissions = permissionMapper.selectByExample(example);
		return sysPermissions;
	}

	@Override
	public SysPermission findByPermissionId(Long permissionId) {
		SysPermission sysPermission = permissionMapper.selectByPrimaryKey(permissionId);
		return sysPermission;
	}

	@Override
	public void create(SysPermission sysPermission) {
		permissionMapper.insert(sysPermission);
	}

	@Override
	public void updatePermission(SysPermission sysPermission) {
		permissionMapper.updateByPrimaryKey(sysPermission);
	}

	@Override
	public void deletePermission(Long id) {
		//先删除中间表，再删除子节点，再删本身
		sysRolePermissionMapper.deleteConnByPermissionId(id);
		permissionMapper.deleteChildByPermissionId(id);
		permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<String> findAllType() {
		List<String> types = permissionMapper.findAllType();
		return types;
	}

}
