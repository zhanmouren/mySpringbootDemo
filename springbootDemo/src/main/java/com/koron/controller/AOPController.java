package com.koron.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koron.authority.ShowFieldMethod;
import com.koron.bean.ResponseData;
import com.koron.bean.UserBean;
import com.koron.bean.securityBean.SysUser;
import com.koron.service.UserService;

@RestController
public class AOPController {
	
	@Autowired
	private UserService userService;
	
	 @ShowFieldMethod
	 @ResponseBody
	 @RequestMapping(value = "/getUserAOP.api", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	 public ResponseData getUserAOP(@RequestBody SysUser user){
		   List<SysUser> userList=new ArrayList<>();
		   userList=userService.getUser();	
		   if(userList!=null && userList.size()>0) {
	    		return ResponseData.success(userList, "aop查询用户表成功");
	    	}else {
	    		return ResponseData.faill("aop查询用户表失败");
	    	}
	  }
}
