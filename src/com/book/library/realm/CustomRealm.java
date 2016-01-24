package com.book.library.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.book.library.po.ActiveUser;
import com.book.library.po.SysPermission;
import com.book.library.po.SysUser;
import com.book.library.service.SysService;


/**
 * 用户校验授权realm
 * @author lpz
 *
 */
public class CustomRealm extends AuthorizingRealm{

	@Autowired
	private SysService sysService;
	
	@Override 
	public void setName(String name){
		super.setName("customRealm");
	}
	
	//用于认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userCode = (String)token.getPrincipal();
//		String username = (String)token.getPrincipal();
		
		//从数据库里面查询
		SysUser sysUser = null;
		try {
			sysUser = sysService.findSysUserByUserCode(userCode);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(sysUser == null){
			return null;
		}
		String password = sysUser.getPassword();
		String salt = sysUser.getSalt();
		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(sysUser.getId());
		
		activeUser.setUsercode(sysUser.getUsercode());
		activeUser.setUsername(sysUser.getUsername());
		
		//根据用户id取出菜单
		List<SysPermission> menus = null;
		try {
			menus = sysService.findMenuListByUserId(sysUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		activeUser.setMenus(menus);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				activeUser,password,ByteSource.Util.bytes(salt),this.getName());
		return authenticationInfo;
	}
	
	//用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//从 principals获取主身份信息
		//将getPrimaryPrincipal方法返回值转为真实身份类型
		//（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		ActiveUser activeUser = (ActiveUser)principals.getPrimaryPrincipal();
		
		//根据身份信息获取权限信息
		//从数据库获取到权限数据
		List<SysPermission> permissionList = null;
		try {
			permissionList = sysService.findPermissionListByUserId(activeUser.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//单独定义一个权限对象
		List<String> permissions = new ArrayList<String>();
		if(permissionList!=null){
			for(SysPermission gzwPermission:permissionList){
				permissions.add(gzwPermission.getPercode());
			}
		}
		
		//查看到权限数据，返回授权信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);
		System.err.println("从数据库中查出权限===================");
		return simpleAuthorizationInfo;
	}

	//清除缓存 
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	
}
