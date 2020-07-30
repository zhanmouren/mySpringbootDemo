package com.koron.service;

import java.util.HashMap;
import java.util.List;

import com.koron.bean.killItem;
import com.koron.bean.securityBean.Permission;
import com.koron.bean.securityBean.SysUser;
import com.koron.bean.securityBean.sysUserVO;

public interface UserService {
	public List<SysUser> getUser();
	
	public List<SysUser> getLoginUser(SysUser userBean);
	
    //获取用户授权
	 List<Permission> findByAdminName(SysUser userBean);
	 
	 public List<SysUser> getAllUser();
	
	 //调用存储过程 mapper.java
	 public List<sysUserVO>   getProcedure(String username);
	 
	 //调用存储过程 mapper.xml
	 public List<killItem> getProcedurekill();
}
