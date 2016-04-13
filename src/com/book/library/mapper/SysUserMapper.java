package com.book.library.mapper;

import com.book.library.po.SysUser;
import com.book.library.po.SysUserExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	List<SysUser> selectByExample(SysUserExample example, PageBounds pageBounds);

	List<SysUser> findAllUser(PageBounds pageBounds);

	void updatePassword(String id, String newpassword);

	List<String> selectRoleByUserid(String id);

	//添加sys_user_role
	void setConnUserRole(UUID uuid,String userid, String role);
    
    
}