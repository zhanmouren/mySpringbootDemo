package com.koron.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.koron.bean.securityBean.Permission;
import com.koron.bean.securityBean.SysUser;
import com.koron.service.UserService;

//这个实现类是spring secrtity的一个实现类
//UserDetailsService是spring security自带的一个类
public class SpringSecurityImpl implements UserDetailsService{
	@Autowired
	private UserService userService;

	/** 根据用户名获取用户 - 用户的角色、权限等信息   */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//获取用户信息
		SysUser sysUser=new SysUser();
		sysUser.setName(username);
		List<SysUser> userList=userService.getLoginUser(sysUser);
		 //GrantedAuthority也是spring security自带的一个bean
		 List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		  if(userList!=null && userList.size()>0) {
			  //获取用户权限
			  List<Permission>  permissionList= userService.findByAdminName(sysUser);
			  //声明授权文件
			  if(permissionList!=null && permissionList.size()>0) {
				  for(int i=0;i<permissionList.size();i++) {
				   //spring security必须包含ROLE_ 为前缀的，在这里加，或者数据库的数据有这个前缀
				   GrantedAuthority grantedAuthority =new SimpleGrantedAuthority("ROLE_"+permissionList.get(i).getName());
				   grantedAuthorities.add(grantedAuthority);
				  }
			  }
		  }
		  System.err.println("grantedAuthorities===============" + grantedAuthorities);
		  //下面这个User也是spring security自带的
		  return new User(userList.get(0).getName(), userList.get(0).getPassword(), grantedAuthorities);
	}

}
