package com.koron.bean;

public class UserBean {
	private String name;
	private String password;
	private String loginName;
	private String workNo;
	
	public String getLoginName() {
		return loginName;
	}
	public String getWorkNo() {
		return workNo;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
