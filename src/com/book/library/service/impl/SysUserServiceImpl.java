package com.book.library.service.impl;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysUserMapper;
import com.book.library.mapper.SysUserRoleMapper;
import com.book.library.po.SysRole;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;
import com.book.library.po.SysUserRole;
import com.book.library.po.SysUserRoleExample;
import com.book.library.service.SysUserService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.sun.istack.internal.Nullable;
/**
 * 
 * 接口实现类
 * @author L------F
 *
 */
@Component
@Transactional
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public List<SysUser> findAllUser() {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
//		select *from sys_user where id like 'lisi';
//		criteria.andIdEqualTo("lisi");
		List<SysUser> users = sysUserMapper.selectByExample(example);
		return users;
	}
	
	@Override
	public List<SysUser> findAllUser(PageBounds pageBounds) {
//		SysUserExample example = new SysUserExample();
//		SysUserExample.Criteria criteria = example.createCriteria();
//		select *from sys_user where id like 'lisi';
//		criteria.andIdEqualTo("lisi");
//		List<SysUser> users = sysUserMapper.selectByExample(example,pageBounds);
		List<SysUser> users = sysUserMapper.findAllUser(pageBounds);
		return users;
	}
	

	@Override
	public void insert() {
		SysUser recode = new SysUser();
		recode.setId("12");
		recode.setUsercode("2");
		recode.setUsername("admins");
		recode.setPassword("123");
		recode.setSalt("456");
		recode.setLocked("0");
		sysUserMapper.insert(recode);
	}

	@Override
	public void delete() {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsercodeEqualTo("2");
		sysUserMapper.deleteByExample(example);
	}

	@Override
	public void update() {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo("3");
		SysUser recode = new SysUser("adnmin", "admin", "long", "long", null, "1");
		sysUserMapper.updateByExample(recode, example);
		
	}

	@Override
	public SysUser login(String username, String password) throws Exception {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo("admin");
		SysUser user = new SysUser("long", "long");
		return user;
	}

	//修改密码方法
	@Override
	public void updatePassword(String id, String newpassword) {
		sysUserMapper.updatePassword(id,newpassword);
	}

	//根据userid删除用户，并且删除对应sys_user_role中间表信息
	@Override
	@Nullable
	public void deleteUserById(String id) {
		//先删除关联表
		SysUserRoleExample example = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andSysUserIdEqualTo(id);
		List<SysUserRole> sysUserRoles = null;
		try {
			sysUserRoles = sysUserRoleMapper.selectByExample(example);
			if(sysUserRoles != null && sysUserRoles.size() != 0){
				sysUserRoleMapper.deleteConnByUserId(id);			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//再删除user表
		sysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public SysUser findUserById(String id) {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		return sysUser;
	}

	@Override
	public void updateEntity(SysUser sysUser) {
//		SysUserExample example = new SysUserExample();
//		SysUserExample.Criteria criteria = example.createCriteria();
		sysUserMapper.updateByPrimaryKey(sysUser);
	}

	//根据用户id查询所属角色，多对多
	@Override
	public List<String> findRolesByUserid(String id) {
		List<String> roles = sysUserMapper.selectRoleByUserid(id);
		return roles;
	}

	
	



	
}
