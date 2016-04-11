package com.book.library.service;

import java.util.List;

import com.book.library.po.SysRole;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface SysRoleService {
	
	List<SysRole> findAllRoles(PageBounds pageBounds) throws Exception;
	
}
