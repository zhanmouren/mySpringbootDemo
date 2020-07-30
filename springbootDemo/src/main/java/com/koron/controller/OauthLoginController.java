package com.koron.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koron.utils.HttpRequest;



@RequestMapping(value = "/oauthLoginManager")
@Controller
public class OauthLoginController {
	
	/*
	 * 根据授权码code获取Token和refreshToken
	 */
//	public String getData(String code) { 
//		if(!"".equals(code)) {
//			//创建参数
//			String url="http://localhost:8081/oauth/token";
//			Map<String, String> parameterMap=new HashMap<>();
//			parameterMap.put("code",code);
//			parameterMap.put("client_id", "oauth_client");
//			parameterMap.put("grant_type", "authorization_code,refresh_token");
//			parameterMap.put("redirect_uri", "https://segmentfault.com/a/1190000022503621"); 
//			Map<String, String> headers=new HashMap<>();
//			headers.put("Content-Type", "application/json"); 
//			try {
//				String data=HttpRequest.doPost(url, parameterMap, headers);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

}
