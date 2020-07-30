package com.koron.bean.securityBean;

import java.util.List;

import com.koron.authority.ShowField;

public class SysUser {
	
        //数据库标识
	    private String dataBaseCode;

	    private Integer id;

	    //用户名
	    private String name;

	    //密码
	    @ShowField("ADMIN")
	    private String password;
	 
	    //角色列表
	    private List<SysRole> roles;
	    
	    

		public String getDataBaseCode() {
			return dataBaseCode;
		}

		public void setDataBaseCode(String dataBaseCode) {
			this.dataBaseCode = dataBaseCode;
		}

		public Integer getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getPassword() {
			return password;
		}

		public List<SysRole> getRoles() {
			return roles;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setRoles(List<SysRole> roles) {
			this.roles = roles;
		}


}
