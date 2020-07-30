package com.koron.bean.securityBean;

public class sysUserVO {
	//
	private Integer userid;
	//用户名
	private String username;
   //角色名
	private String rolename;
	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	

}
