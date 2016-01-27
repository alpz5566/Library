package com.book.library.service.impl;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysUserMapper;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;
import com.book.library.service.SysUserService;
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



	
}
