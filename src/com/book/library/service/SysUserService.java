package com.book.library.service;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;

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
	    
}
