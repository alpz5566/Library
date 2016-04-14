package com.book.library.service;

import java.util.List;

import com.book.library.po.SysPermission;

public interface SysPermissionService {

	List<SysPermission> selectPermissionByRoleId(String id);

	List<SysPermission> findAll();

	SysPermission findByPermissionId(Long permissionId);

	void create(SysPermission sysPermission);

	void updatePermission(SysPermission sysPermission);

	void deletePermission(Long id);

	List<String> findAllType();

}
