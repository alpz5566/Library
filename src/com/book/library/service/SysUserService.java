package com.book.library.service;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.book.library.po.SysRole;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 
 * 定义的接口
 * @author L------F
 *
 */
public interface SysUserService {

	List<SysUser> findAllUser();
	
	void insert();
	
	void delete();
	
	void update();
	
	SysUser login(String username,String password)throws Exception;

	List<SysUser> findAllUser(PageBounds pageBounds);

	void updatePassword(String id, String newpassword);

	void deleteUserById(String id);

	SysUser findUserById(String id);

	void updateEntity(SysUser sysUser);

	List<String> findRolesByUserid(String id);
	    
}
