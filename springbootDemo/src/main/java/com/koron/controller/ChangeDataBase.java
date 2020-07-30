package com.koron.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koron.bean.ResponseData;
import com.koron.bean.securityBean.SysUser;
import com.koron.datasource.DBIdentifier;
import com.koron.service.UserService;

@RestController
public class ChangeDataBase {
	@Autowired
	private UserService userService;
	
	@ResponseBody
    @RequestMapping(value = "/getAllUserByDataBase.api", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	public ResponseData getAllUserByDataBase(@RequestBody SysUser user) {
		DBIdentifier.setProjectCode(user.getDataBaseCode());
		String code=DBIdentifier.getProjectCode();
		List<SysUser> userList=new ArrayList<>();
		if("data".equals(code)) {
			userList=userService.getAllUser();
		}else {
		  userList=userService.getUser();	
		}
    	if(userList!=null && userList.size()>0) {
    		return ResponseData.success(userList, "切换查询成功");
    	}else {
    		return ResponseData.faill("切换数据库失败");
    	}
	}

}
