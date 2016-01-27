package com.book.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysPermissionCustomMapper;
import com.book.library.mapper.SysUserMapper;
import com.book.library.po.ActiveUser;
import com.book.library.po.SysPermission;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;
import com.book.library.service.SysService;
import com.book.library.web.exception.CustomException;
import com.book.library.utill.MD5;

@Component
@Transactional
public class SysServiceImpl implements SysService{

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysPermissionCustomMapper permissionCustomMapper;
	
	@Override
	public ActiveUser authenticat(String userCode, String password) throws Exception {
		SysUser sysUser = this.findSysUserByUserCode(userCode);
		if(sysUser == null){
			throw new CustomException("用户账号不存在");
		}
		//数据库密码 (md5密码 )
		String password_db = sysUser.getPassword();
		
		//对输入的密码 和数据库密码 进行比对，如果一致，认证通过
		//对页面输入的密码 进行md5加密 
		String password_input_md5 = new MD5().getMD5ofStr(password);
		if(!password_input_md5.equalsIgnoreCase(password_db)){
			//抛出异常
			throw new CustomException("用户名或密码 错误");
		}
		//得到用户id
		String userid = sysUser.getId();
		//根据用户id查询菜单 
		List<SysPermission> menus =this.findMenuListByUserId(userid);
		
		//根据用户id查询权限url
		List<SysPermission> permissions = this.findPermissionListByUserId(userid);
		
		//认证通过，返回用户身份信息
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(sysUser.getId());
		activeUser.setUsercode(userCode);
		activeUser.setUsername(sysUser.getUsername());//用户名称
		
		//放入权限范围的菜单和url
		activeUser.setMenus(menus);
		activeUser.setPermissions(permissions);
		
		return activeUser;
	}

	@Override
	public SysUser findSysUserByUserCode(String userCode) throws Exception {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andUsercodeEqualTo(userCode);
		
		List<SysUser> list = sysUserMapper.selectByExample(sysUserExample);
		
		if(list!=null && list.size()==1){
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public List<SysPermission> findMenuListByUserId(String userid) throws Exception {
		return permissionCustomMapper.findMenuListByUserId(userid);
	}

	@Override
	public List<SysPermission> findPermissionListByUserId(String userid) throws Exception {
		return permissionCustomMapper.findPermissionListByUserId(userid);
	}

}
