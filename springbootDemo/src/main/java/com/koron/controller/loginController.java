package com.koron.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.koron.bean.LoginBean;
import com.koron.bean.ResponseData;
import com.koron.bean.UserBean;
import com.koron.bean.securityBean.SysUser;
import com.koron.service.UserService;
import com.koron.utils.RedisUtil;

@RequestMapping(value = "/loginManager")
@Controller
public class loginController {
	

	@Autowired
	private UserService userService;
	
	 @Autowired
	 private RedisUtil redisUtil;
	
	//重定向到登录页面(其他系统跳转到我方的登录系统的登录界面)，或者cookie 查询有没有token
	@GetMapping("/toLogin")
	public String login(@RequestParam(required=false,defaultValue="") String target,HttpSession session,
			            @RequestParam(required=false,defaultValue="") String token) {
		if("".equals(target) ) {
			target="https://www.baidu.com/";
		}
		//如果是已经登录的用户再次登录系统
		if(!"".equals(token)) {
			//获取用户信息
			 //根据token取出值
	    	 String json = JSONObject.toJSONString(redisUtil.get("USER"+":"+token));
	    	 if("null".equals(json) && "".equals(json)) {
	    			return "login";
	    	 }else {
	    		 //更新redis过期时间
	    		 redisUtil.expire("USER"+":"+token, 1000*60*30);
	    		 System.out.print(redisUtil.getExpire("USER"+":"+token));	 
	    		 return "success";
	    	 }		
		}
		//重定向地址,返回的地址,设置在
		session.setAttribute("target", target);
		return "login";		
	}
	
	//校验用户密码
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8" })
	 @ResponseBody
	 public Object doLogin(@RequestBody SysUser user,HttpSession session,HttpServletResponse  response) {
		//从session中获取target
	     String target=(String)session.getAttribute("target");
	     if("".equals(target)) {
	    	 return ResponseData.faill("登录失败");
	     }
	     List<SysUser> userList=userService.getLoginUser(user);
	     if(userList!=null && userList.size()>0) {	    
	     String token = UUID.randomUUID().toString();
	    	 //将用户信息写到redis
	     redisUtil.set("USER"+":"+token, JSONObject.toJSON(userList.get(0)),1000*60*30);
	     LoginBean loginBean=new LoginBean();
	     loginBean.setName(userList.get(0).getName());
	     loginBean.setToken(token);	     
	     loginBean.setTargetUrl(target);
	     //或者写到Cookie 
	     // Cookie cookie=new Cookie("token",token);
	     // cookie.setDomain("donmin.com");
	     // response.addCookie(cookie);
	     return ResponseData.success(loginBean, "登录成功");
	     }else {
	    	 return ResponseData.faill("登录失败");
	     }
	     
	 }
	 
	 //根据token查询是否是正确的token
	 @PostMapping("/checkToken")
	 @ResponseBody
	 public Object checkToken(String token) {
		 if("".equals(token)) {
	    	 return ResponseData.faill("token不能为空");
	     }else {
	    	 //根据token取出值
	    	 String json = JSONObject.toJSONString(redisUtil.get("USER"+":"+token));
	    	 if("".equals(json)) {
	    		 return ResponseData.faill("过期"); 
	    	 }else {
	    		 //更新redis过期时间
	    		 redisUtil.expire("USER"+":"+token, 1000*60*30);
	    		 System.out.print(redisUtil.getExpire("USER"+":"+token));
	    		 return ResponseData.success(json, "登录成功");
	    	 }
	     }
	 }
	 
}
