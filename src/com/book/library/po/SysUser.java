package com.book.library.po;

import java.io.Serializable;
import java.util.List;

public class SysUser implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7459728688662264984L;

	private String id;

    private String usercode;

    private String username;

    private String password;

    private String salt;

    private int locked;
    
    private List<SysRole> roles; //拥有的角色列表
    
    private List<String> roleListStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

	public SysUser(String id, String usercode, String username,
			String password, String salt, int locked) {
		super();
		this.id = id;
		this.usercode = usercode;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.locked = locked;
	}

	public SysUser() {
		super();
	}

	public SysUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public List<String> getRoleListStr() {
		return roleListStr;
	}

	public void setRoleListStr(List<String> roleListStr) {
		this.roleListStr = roleListStr;
	}

	
	
	
    
}