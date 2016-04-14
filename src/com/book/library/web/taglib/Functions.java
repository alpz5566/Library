package com.book.library.web.taglib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import com.book.library.mapper.SysUserMapper;
import com.book.library.po.SysPermission;
import com.book.library.po.SysRole;
import com.book.library.service.SysPermissionService;
import com.book.library.service.SysRoleService;
import com.book.library.web.spring.SpringUtils;

public class Functions {
	
	public static boolean in(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

//    public static String roleName(Long roleId) {
//        Role role = getRoleService().findOne(roleId);
//        if(role == null) {
//            return "";
//        }
//        return role.getDescription();
//    }
    
    public static String roleName(String roleid){
//    	SysRole sysRole = getSysRoleService().findRoleById(roleid);
    	SysRole sysRole = getSysRoleService().findRoleById(roleid);
    	if(sysRole == null){
    		return "";
    	}
    	return sysRole.getName();
    }

//    public static String roleNames(Collection<Long> roleIds) {
//        if(CollectionUtils.isEmpty(roleIds)) {
//            return "";
//        }
//
//        StringBuilder s = new StringBuilder();
//        for(Long roleId : roleIds) {
//            Role role = getRoleService().findOne(roleId);
//            if(role == null) {
//                return "";
//            }
//            s.append(role.getDescription());
//            s.append(",");
//        }
//
//        if(s.length() > 0) {
//            s.deleteCharAt(s.length() - 1);
//        }
//
//        return s.toString();
//    }
    
    public static String roleNames(List<String> roleIds){
    	if(roleIds.isEmpty()){
    		return "";
    	}
    	StringBuilder s = new StringBuilder();
    	for(String roleId : roleIds){
    		SysRole sysRole = getSysRoleService().findRoleById(roleId);
    		if(sysRole == null){
    			return "";
    		}
    		s.append(sysRole.getName());
    		s.append(",");
    	}
    	if(s.length() > 0){
    		s.deleteCharAt(s.length() -1);
    	}
    	return s.toString();
    }
    
//    public static String resourceName(Long resourceId) {
//        Resource resource = getResourceService().findOne(resourceId);
//        if(resource == null) {
//            return "";
//        }
//        return resource.getName();
//    }
    
    
    public static String permissionName(Long permissionId){
    	SysPermission sysPermission = getSysPermissionService().findByPermissionId(permissionId);
    	if(sysPermission == null){
    		return "";
    	}
    	return sysPermission.getName();
    }
    
    
//    public static String resourceNames(Collection<Long> resourceIds) {
//        if(CollectionUtils.isEmpty(resourceIds)) {
//            return "";
//        }
//
//        StringBuilder s = new StringBuilder();
//        for(Long resourceId : resourceIds) {
//            Resource resource = getResourceService().findOne(resourceId);
//            if(resource == null) {
//                return "";
//            }
//            s.append(resource.getName());
//            s.append(",");
//        }
//
//        if(s.length() > 0) {
//            s.deleteCharAt(s.length() - 1);
//        }
//
//        return s.toString();
//    }
    
    public static String permissionNames(List<Long> permissionIds){
    	if(permissionIds.isEmpty()){
    		return "";
    	}
    	StringBuilder s = new StringBuilder();
    	for(Long permissionId : permissionIds){
    		SysPermission sysPermission = getSysPermissionService().findByPermissionId(permissionId);
    		if(sysPermission == null){
    			return "";
    		}
    		s.append(sysPermission.getName());
    		s.append(",");
    	}
    	if(s.length() > 0){
    		s.deleteCharAt(s.length() - 1);
    	}
    	return s.toString();
    }

    
    private static SysRoleService sysRoleService;
    private static SysPermissionService sysPermissionService;
    
    public static SysRoleService getSysRoleService(){
    	if(sysRoleService == null){
    		sysRoleService = SpringUtils.getBean(SysRoleService.class);
    	}
    	return sysRoleService;
    }
    
    public static SysPermissionService getSysPermissionService(){
    	if(sysPermissionService == null){
    		sysPermissionService = SpringUtils.getBean(SysPermissionService.class);
    	}
    	return sysPermissionService;
    }
    
}
