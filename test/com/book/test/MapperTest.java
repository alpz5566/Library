package com.book.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByExampleParmsInnerclassGenerator;
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
		// select *from sys_user where id like 'lisi';
		 criteria.andIdEqualTo("lisi");
		 List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
		 System.out.println(sysUsers.get(0).getPassword());
	}
	
	@Test
	public  void insert(){
		SysUser record = new SysUser();
		record.setId("3");
		record.setUsername("long");
		record.setPassword("123");
		record.setUsercode("4");
		record.setLocked("1");
		sysUserMapper.insert(record);
		
	}
	
	@Test
	public void delete(){
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo("long");
		sysUserMapper.deleteByExample(example); 
	}
	
	@Test
	public void update(){
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo("3");
		SysUser record = new SysUser("3", "admin", "long", "long", "1", "1");
		sysUserMapper.updateByExample(record, example);

	}

}
