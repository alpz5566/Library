package com.book.library.service;

import java.util.List;

import com.book.library.po.ActiveUser;
import com.book.library.po.SysPermission;
import com.book.library.po.SysUser;

public interface SysService {
	//根据用户的身份和密码 进行认证，如果认证通过，返回用户身份信息
	public ActiveUser authenticat(String userCode,String password) throws Exception;
	
	//根据用户账号查询用户信息
	public SysUser findSysUserByUserCode(String userCode)throws Exception;
	
	//根据用户id查询权限范围的菜单
	public List<SysPermission> findMenuListByUserId(String userid) throws Exception;
	
	//根据用户id查询权限范围的url
	public List<SysPermission> findPermissionListByUserId(String userid) throws Exception;

	//根据id查询用户信息
	public SysUser findSysUserByUserId(String id) throws Exception;
}
