package com.book.library.service.impl;

import java.util.List;
import java.util.UUID;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysRoleMapper;
import com.book.library.mapper.SysRolePermissionMapper;
import com.book.library.mapper.SysUserRoleMapper;
import com.book.library.po.SysRole;
import com.book.library.po.SysRoleExample;
import com.book.library.po.SysRolePermission;
import com.book.library.service.SysRoleService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Component
@Transactional
public class SysRoleServiceImpl implements SysRoleService{

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
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

	@Override
	public void save(SysRole role) {
		UUID uuid = UUID.randomUUID();
		String roleid = uuid.toString();
		role.setId(roleid);
		role.setAvailable("1");
		//保存中间表
		List<Long> permissionIds = role.getPermissionIds();
		for(Long permissionid : permissionIds){
			SysRolePermission record = new SysRolePermission(UUID.randomUUID().toString(),
					roleid, permissionid);
			sysRolePermissionMapper.insert(record);
		}
		//保存角色基本信息
		sysRoleMapper.insert(role);
	}

	@Override
	public SysRole findRoleById(String id) {
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
		return sysRole;
	}

	@Override
	public void updateEntity(SysRole sysRole) {
//		String roleid = sysRole.getId().substring(0, 36);
		String[] roleids = sysRole.getId().split(",");
		String roleid = roleids[0];
		sysRole.setId(roleid);
		//先删除有关联的中间表，再添加
		sysRolePermissionMapper.deleteConnByRoleId(roleid);
		List<Long> permissionIds = sysRole.getPermissionIds();
		for(Long permissionid : permissionIds){
			SysRolePermission record = new SysRolePermission(UUID.randomUUID().toString()
					, roleid, permissionid);
			sysRolePermissionMapper.insert(record);
		}
		sysRoleMapper.updateByPrimaryKey(sysRole);
	}

	@Override
	public void deleteRole(String id) {
		//先根据roleid删除中间表，在删除本表
		sysUserRoleMapper.deleteConnByRoleId(id);
		sysRolePermissionMapper.deleteConnByRoleId(id);
		sysRoleMapper.deleteByPrimaryKey(id);
	}

}
