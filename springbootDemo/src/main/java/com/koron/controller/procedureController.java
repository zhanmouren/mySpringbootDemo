package com.koron.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koron.bean.ResponseData;
import com.koron.bean.killItem;
import com.koron.bean.securityBean.SysUser;
import com.koron.bean.securityBean.sysUserVO;
import com.koron.service.UserService;

//调用存储过程
@RestController
public class procedureController {

	@Autowired
	private UserService userService;
	
	 //在mapper.java 调用
	 @ResponseBody
	 @RequestMapping(value = "/getProcedure.api", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	 public ResponseData getProcedure(@RequestBody SysUser user){
		   String username="ADMIN";
		   List<sysUserVO>   userList=userService.getProcedure(username);
		   return ResponseData.success(userList, "存储过程查询数据成功");
	  }
	 // 在mapper.xml调用
	 @ResponseBody 
	 @RequestMapping(value = "/getProcedurekill.api", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	 public ResponseData getProcedurekill(){ 
		   List<killItem> addres=userService.getProcedurekill();
		   return ResponseData.success(addres, "存储过程查询数据成功");
	  }
}
