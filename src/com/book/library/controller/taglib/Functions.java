package com.book.library.controller.taglib;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.book.library.po.SysRole;
import com.book.library.service.SysPermissionService;
import com.book.library.service.SysRoleService;

@Controller
public class Functions {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	public boolean in(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }
	
	public String roleName(String roleid){
//    	SysRole sysRole = getSysRoleService().findRoleById(roleid);
    	SysRole sysRole = sysRoleService.findRoleById(roleid);
    	if(sysRole == null){
    		return "";
    	}
    	return sysRole.getName();
    }
	
	@Test
    public void testfindRoleById(){
    	List<String> roleIds = new ArrayList<String>();
    	roleIds.add("ebc8a441-c6f9-11e4-b137-0adc305c");
    	roleIds.add("ebc9d647-c6f9-11e4-b137-0adc305c");
//    	roleNames(roleIds);
    	System.out.println(roleName("ebc9d647-c6f9-11e4-b137-0adc305c"));
    }
}
