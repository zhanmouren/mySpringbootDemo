package com.koron.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.koron.service.impl.SpringSecurityImpl;


@Configuration
@EnableAuthorizationServer   //这个注解告诉spring 这个应用是OAuth2的授权服务器
//scope：客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all 
//https://segmentfault.com/a/1190000022503621

public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	 @Autowired
     private UserDetailsService userDetailsService;
	 @Autowired
	 private DataSource dataSource;
	 
	
	  @Bean
	  public ClientDetailsService clientDetails() {
	     return new JdbcClientDetailsService(dataSource);
	 }
	
	 @Bean
	 public TokenStore tokenStore() {
	        return new JdbcTokenStore(dataSource);
	 }
	 /**/
	 
	 @Bean
	 public ApprovalStore approvalStore() {
	      return new JdbcApprovalStore(dataSource);
	 }
	 
	 @Bean
	 public AuthorizationCodeServices authorizationCodeServices() {
	        return new JdbcAuthorizationCodeServices(dataSource);
	 }

 
	/** 
	* 授权服务器要做两方面的检验，一方面是校验客户端，另一方面则是校验用户， 
	* 校验用户，SpringSecurity已经配置了，这里就是配置校验客户端。 
	* 客户端的信息我们可以存在数据库中，这其实也是比较容易的，和用户信息存到数据库中类似， 
	* 但是这里为了简化代码，我还是将客户端信息存在内存中， 
	* 这里我们分别配置了客户端的 id，secret、资源 id、授权类型、授权范围以及重定向 uri。 
	* 授权类型四种，四种之中不包含 refresh_token 这种类型， 
	* 但是在实际操作中，refresh_token 也被算作一种。 
	*/ 
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//采用jdbc方式
		clients.withClientDetails(clientDetails());
		//如果采用的内存中配置，则需要在这配置
	}
	
	/** 
	* AuthorizationServerSecurityConfigurer 用来配置令牌端点的安全约束， 
	* 也就是这个端点谁能访问，谁不能访问。checkTokenAccess 是指一个 Token 校验的端点， 
	* 这个端点我们设置为可以直接访问 
	* （在后面，当资源服务器收到 Token 之后，需要去校验 Token 的合法性，就会访问这个端点）。
	*/ 
	 @Override
	 public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		 security.tokenKeyAccess("permitAll()")
		         .checkTokenAccess("permitAll()")
		      //   .checkTokenAccess("isAuthenticated()")
		         .allowFormAuthenticationForClients(); //允许表单登录
	 }
	 
	 /** 
	 * AuthorizationServerEndpointsConfigurer 这里用来配置令牌的访问端点和令牌服务。 
	 * authorizationCodeServices用来配置授权码的存储，这里我们是存在在内存中， 还是数据库
	 * tokenServices 用来配置令牌的存储，即 access_token 的存储位置，这里我们也先存储在内存中。 
	   *    授权码是用来获取令牌的，使用一次就失效，令牌则是用来获取资源的 
	 */ 
	 @Override 
	 public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception { 
		 endpoints
         .approvalStore(approvalStore())
         .authorizationCodeServices(authorizationCodeServices())  //授权码存储
         .tokenStore(tokenStore())
         .userDetailsService(userDetailsService)
         // 2018-4-3 增加配置，允许 GET、POST 请求获取 token，即访问端点：oauth/token
         .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
       
	 }
	 
	 
	 /*
	  * tokenServices 这个 Bean 主要用来配置 Token 的一些基本信息， 
	       * 例如 Token 是否支持刷新、Token 的存储位置、Token 的有效期以及刷新 Token 的有效期等等。
      * Token 有效期这个好理解，刷新 Token 的有效期我说一下，当 Token 快要过期的时候，我们需要获取一个新的 Token， 
                  * 在获取新的 Token 时候，需要有一个凭证信息，这个凭证信息不是旧的 Token，而是另外一个 refresh_token，这个 refresh_token 也是有有效期的。 
	  */
	 
//	 @Bean 
//	  public AuthorizationServerTokenServices authorizationServerTokenServices() { 
//	    DefaultTokenServices defaultTokenServices = new DefaultTokenServices(); 
//	    defaultTokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3); //设置refresh_token有效期
//	    defaultTokenServices.setAccessTokenValiditySeconds(60 * 60 * 12); //设置token有效期 12小时
//	    defaultTokenServices.setSupportRefreshToken(true); //是否支持token更新
//	    defaultTokenServices.setTokenStore(tokenStore()); 
//	    defaultTokenServices.setClientDetailsService(clientDetails()); 
//	    return defaultTokenServices; 
//	 } 
}
