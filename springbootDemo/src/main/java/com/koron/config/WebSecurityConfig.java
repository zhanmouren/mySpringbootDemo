package com.koron.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.koron.service.impl.SpringSecurityImpl;

//spring security 配置类  (创建用户和角色)
@Configuration
@EnableWebSecurity
@Order(10)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
//	@Autowired
//    private UserDetailsService userDetailsService;
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new SpringSecurityImpl();
	}

	  //代表密码采用加密方式
	  @Bean
	  public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	  }


	/*
	 * 配置拦截保护请求
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()//禁用了 csrf 功能（scrf作用：防止CSRF（跨站请求伪造）配置）
        .authorizeRequests()//限定签名成功的请求
     //   .antMatchers("/decision/**","/govern/**").hasAnyRole("USER","ADMIN")//对decision和govern 下的接口 需要 USER 或者 ADMIN 权限
        .antMatchers("/loginManager/**").permitAll()///admin/login 不限定
        .antMatchers("/admin/**").hasRole("ADMIN")//对admin下的接口 需要ADMIN权限
        .antMatchers("/oauth/**","/login/**").permitAll()//不拦截 oauth 开放的资源     
        .anyRequest().permitAll()//其他没有限定的请求，允许访问
        .and().anonymous()//对于没有配置权限的其他请求允许匿名访问
        .and().formLogin()//使用 spring security 默认登录页面
        .and().httpBasic();//启用http 基础验证
    }
    /*
             * 用于构建用户具体权限控制(校验用户)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService())
    	    .passwordEncoder(passwordEncoder());
    	 
    }
     /*
      * 配置用户签名服务 (过滤链，默认对所有的范文url进行过滤，意味着打开这个网站的任何链接，都弹出授权页面)
      */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/check_token");
        super.configure(web);
    }
    

   
}
