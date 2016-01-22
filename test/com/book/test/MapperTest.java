package com.book.test;

import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.book.library.mapper.SysUserMapper;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;

public class MapperTest {
	
	private ClassPathXmlApplicationContext applicationContext;
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		sysUserMapper = (SysUserMapper) applicationContext.getBean("sysUserMapper");
	}
	
	private SysUserMapper mapper;
	
	private SysUserMapper sysUserMapper;
	
	@Test
	public void findById(){
		 SysUserExample example = new SysUserExample();
		 SysUserExample.Criteria criteria = example.createCriteria();
		 criteria.andIdEqualTo("lisi");
		 List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
		 System.out.println(sysUsers.get(0).getUsername());
	}

}
