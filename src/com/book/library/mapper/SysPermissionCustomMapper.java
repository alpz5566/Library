package com.book.library.mapper;

import java.util.List;

import com.book.library.po.SysPermission;

public interface SysPermissionCustomMapper {

	//根据用户id查询菜单
	public List<SysPermission> findMenuListByUserId(String userid)throws Exception;
	//根据用户id查询权限url
	public List<SysPermission> findPermissionListByUserId(String userid)throws Exception;

}
