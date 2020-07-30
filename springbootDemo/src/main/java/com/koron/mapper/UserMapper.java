package com.koron.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import com.koron.bean.UserBean;
import com.koron.bean.killItem;
import com.koron.bean.securityBean.Permission;
import com.koron.bean.securityBean.SysUser;
import com.koron.bean.securityBean.sysUserVO;

import io.lettuce.core.dynamic.annotation.Param;


@Mapper
public interface UserMapper {
	public List<SysUser> getUser();

	public List<SysUser> getLoginUser(SysUser userBean);
	
	public List<Permission> findByAdminName(SysUser userBean);
	
	public List<SysUser> getAllUser();
	
	@Select("call testtmp1(#{username})")
	@Options(statementType= StatementType.CALLABLE )
	public List<sysUserVO>   getProcedure(@Param("username")String username);
	
	
	//public  HashMap getProcedurekill(killItem items);
	public  void getProcedurekill(Map<String,Object> items);
}
