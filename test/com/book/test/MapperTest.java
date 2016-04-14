package com.book.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.codegen.ibatis2.dao.elements.UpdateByExampleParmsInnerclassGenerator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.book.library.mapper.SysRoleMapper;
import com.book.library.mapper.SysUserMapper;
import com.book.library.po.SysRole;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;
import com.book.library.service.SysRoleService;

public class MapperTest {
	
	private ClassPathXmlApplicationContext applicationContext;
	
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		sysUserMapper = (SysUserMapper) applicationContext.getBean("sysUserMapper");
		sysRoleMapper = (SysRoleMapper)applicationContext.getBean("sysRoleMapper");
	}
	
	private SysUserMapper mapper;
	
	private SysUserMapper sysUserMapper;
	
	private SysRoleMapper sysRoleMapper;
	
	
	
	@Test
	public void findRoleByIdForMapper(){
		String id = "ebc9d647-c6f9-11e4-b137-0adc305c";
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey("dsjii343");
		System.out.println(sysRole);
	}
	
//	@Test
//	public void findRoleByIdForService(){
//		String id = "ebc9d647-c6f9-11e4-b137-0adc305c";
//		SysRole sysRole = sysRoleService.findRoleById(id);
//		System.out.println(sysRole);
//	}
	
	@Test
	public void testListToSplit(){
		List<String> strs = new ArrayList<String>();
		strs.add("0adc305c");
		strs.add("ebc9d647");
		System.out.println(strs);
				//["ebc8a441-c6f9-11e4-b137-0adc305c", "ebc9d647-c6f9-11e4-b137-0adc305c"];
		
	}
	
	@Test
	public void testFindRoleByUserid(){
		List<String> roles = sysUserMapper.selectRoleByUserid("zhangsan");
		System.out.println(roles);
	}
	
	@Test
	public void updateSysUser(){
		SysUser sysUser = new SysUser("2", "sa", "duoshao", "ssss", "s", 1);
		sysUserMapper.updateByPrimaryKey(sysUser);
	}
	
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
		record.setLocked(1);
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
		SysUser record = new SysUser("3", "admin", "long", "long", "1", 1);
		sysUserMapper.updateByExample(record, example);

	}

}
