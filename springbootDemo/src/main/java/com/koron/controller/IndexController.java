package com.koron.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koron.bean.ResponseData;
import com.koron.bean.UserBean;
import com.koron.bean.securityBean.SysUser;
import com.koron.service.UserService;



@RestController
public class IndexController {
	
	@Autowired
	private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUserTest.api", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	public ResponseData getUserTest(@RequestBody SysUser user) {
    	if(user.getPassword()==null || "".equals(user.getPassword())) {
    		return ResponseData.faill("姓名不能为空");
    	}
    	List<SysUser> userList=userService.getUser();
    	if(userList!=null && userList.size()>0) {
    		return ResponseData.success(userList, "chengg");
    	}else {
    		return ResponseData.faill("不能为");
    	}
    	
	}
    @ResponseBody
    @RequestMapping(value = "/getUserByName.api", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	public ResponseData getUserByName(@RequestBody SysUser user) {
    	if(user.getName()==null || "".equals(user.getName())) {
    		return ResponseData.faill("姓名不能为空");
    	}
    	List<SysUser> userList=userService.getLoginUser(user);
    	if(userList!=null && userList.size()>0) {
    		return ResponseData.success(userList, "成功");
    	}else {
    		return ResponseData.faill("不能为");
    	}
    	
	}
}
