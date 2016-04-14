package com.book.library.po;

import java.io.Serializable;
import java.util.List;

public class SysRole implements Serializable{
	
	private static final long serialVersionUID = -6120905474716069753L;

	private String id;

    private String name;

    private String available;

    private List<String> permissionListStr;
    
    private List<String> permissionIds;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }

	public List<String> getPermissionListStr() {
		return permissionListStr;
	}

	public void setPermissionListStr(List<String> permissionListStr) {
		this.permissionListStr = permissionListStr;
	}

	public List<String> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<String> permissionIds) {
		this.permissionIds = permissionIds;
	}

	
    
}