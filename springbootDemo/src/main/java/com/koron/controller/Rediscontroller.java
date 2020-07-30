package com.koron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koron.bean.UserBean;
import com.koron.utils.RedisUtil;


@RestController
public class Rediscontroller {
	 @Autowired
	 private RedisUtil redisUtil;
	
	 @ResponseBody
	 @RequestMapping(value = "/redisset.api", method = RequestMethod.POST)
	    public boolean redisset(String key, String value){
		    UserBean user=new UserBean();
	        user.setName("niu");
	        user.setPassword("bi");
	        return redisUtil.set(key,value);
	    }
	 
	 @RequestMapping(value = "/redisget.api", method = RequestMethod.POST)
	    public Object redisget(String key){
	        return redisUtil.get(key);
	    }
}
