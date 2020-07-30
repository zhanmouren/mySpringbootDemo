package com.koron.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koron.bean.UserBean;
import com.koron.bean.killItem;
import com.koron.bean.securityBean.Permission;
import com.koron.bean.securityBean.SysUser;
import com.koron.bean.securityBean.sysUserVO;
import com.koron.mapper.UserMapper;
import com.koron.service.UserService;

@Service
public class UserServicelmpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<SysUser> getUser() {
		return userMapper.getUser();
	}

	@Override
	public List<SysUser> getLoginUser(SysUser userBean) {
		return userMapper.getLoginUser(userBean);
	}

	//根据名字获取用户授权权限
	@Override
	public List<Permission> findByAdminName(SysUser userBean) {
		// TODO Auto-generated method stub
		return userMapper.findByAdminName(userBean);
	}

	@Override
	public List<SysUser> getAllUser() {
		// TODO Auto-generated method stub
		return userMapper.getAllUser();
	}

	@Override
	public List<sysUserVO>   getProcedure(String username) {
		return userMapper.getProcedure(username);
	}
	
	public List<killItem> getProcedurekill() {
		Map<String,Object> map=new HashMap<>();
		map.put("mobile","111");
		map.put("downum", 30);
		map.put("proid", 1);
	//	map.put("r_result", 100);
		userMapper.getProcedurekill(map);
		map.get("r_result");
		//List<killItem> addres=(List<killItem>)userMapper.getProcedurekill(map);	
		System.out.print(map.get("r_result"));
		System.out.print(map.get("str_result"));
		return null;
		
//		killItem kill=new killItem();
//		kill.setMobile("11111");
//		kill.setDownnum(30);
//		kill.setProid(1);
//		List<killItem> addres=userMapper.getProcedurekill(map);		
//		return addres;
		
	}

}
