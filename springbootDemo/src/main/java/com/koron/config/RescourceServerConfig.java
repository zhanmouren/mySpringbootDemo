package com.koron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

//@Configuration 
//@EnableResourceServer //资源管理器,校验是否传了token,token是否正确
public class RescourceServerConfig extends ResourceServerConfigurerAdapter {
	
	/** 
	* kenServices 我们配置了一个 RemoteTokenServices 的实例， 
	* 这是因为资源服务器和授权服务器是分开的，资源服务器和授权服务器是放在一起的，就不需要配置 RemoteTokenServices 了 
	* RemoteTokenServices 中我们配置了 access_token 的校验地址、client_id、client_secret 这三个信息， 
	* 当用户来资源服务器请求资源时，会携带上一个 access_token， 
	* 通过这里的配置，就能够校验出 token 是否正确等 
	*/ 
	
	@Bean 
	RemoteTokenServices remoteTokenServices() { 
	  RemoteTokenServices remoteTokenServices = new RemoteTokenServices(); 
	  remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token"); 
	  remoteTokenServices.setClientId("client_code"); 
	  remoteTokenServices.setClientSecret("secret"); 
	  return remoteTokenServices; 
	} 
	
	@Override 
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception { 
	   resources.resourceId("res1").tokenServices(remoteTokenServices()); 
	} 

	/*
    spring security 先通过ResourceSecurityConfiguration的配置先执行
    ，再执行webSecurityConfiguration的配置，所以要规划好不同断点对应的过滤链不冲突，需要清晰规划好。
    
    Spring Security 中的ResourceServerConfigurerAdapter配置会覆盖WebSecurityConfigurerAdapter
    protected void configure(HttpSecurity http) 中的配置会以ResourceServerConfigurerAdapter为准。?
	*/
//	@Override 
//	public void configure(HttpSecurity http) throws Exception { 
////		http.authorizeRequests() 		
////	    .antMatchers("/admin/**").hasRole("ADMIN")
////	    .antMatchers("/oauth/**", 
////                "/open/**").permitAll() // 不需要认证
////	   //验证所有请求
////	   //  .anyRequest().authenticated(); 
////	    .anyRequest().permitAll();	
////		http.csrf().disable();		
//   	 } 

}
