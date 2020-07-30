package com.koron.bean.securityBean;

public class Permission {


    private int id;
 
    //权限名称
    private String name;
 
    //权限描述
    private String descritpion;
 
    //授权链接
    private String url;
 
    //父节点id
    private int pid;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescritpion() {
		return descritpion;
	}

	public String getUrl() {
		return url;
	}

	public int getPid() {
		return pid;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", descritpion=" + descritpion + ", url=" + url + ", pid="
				+ pid + "]";
	}
    

}
