package com.book.library.po;

import java.io.Serializable;
import java.util.List;

public class ActiveUser implements Serializable{

	private static final long serialVersionUID = -8212390535832246162L;
	
	private String userid;//用户id（主键）
	private String usercode;// 用户账号
	private String username;// 用户名称

	private List<SysPermission> menus;// 菜单
	private List<SysPermission> permissions;// 权限

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<SysPermission> getMenus() {
		return menus;
	}

	public void setMenus(List<SysPermission> menus) {
		this.menus = menus;
	}

	public List<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}
	
}
