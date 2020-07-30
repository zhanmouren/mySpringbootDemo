package com.koron.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koron.bean.ResponseData;
import com.koron.bean.securityBean.SysUser;

@RestController
@RequestMapping("admin")
public class mainController {
	 @RequestMapping(value = "/adminMsg", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	 @ResponseBody
	 public Object adminMsg() {
		 String target="dsdsd";
		 if("".equals(target)) {
	    	 return ResponseData.faill("测试失败");
	     }else {
	    	 return ResponseData.faill("测试成功");
	     }

	 }
}
