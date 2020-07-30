package com.koron.authority.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koron.authority.ShowField;
import com.koron.bean.ResponseData;

//使用注解+AOP+反射来实现将返回的对象中的敏感字段设置为null, https://blog.csdn.net/qq_37170583/article/details/90674103
@Configuration
@Aspect
public class FiledAspect {
	
	 @Pointcut("@annotation(com.koron.authority.ShowFieldMethod)")
	  public void field() {
		 
	 }
	 //getFields()只能获取public的字段，包括父类的。　
	 //而getDeclaredFields()只能获取自己声明的各种字段，包括public，protected，private。
	 @AfterReturning(returning = "obj", pointcut = "field()")
	 public void doAfterReturning(ResponseData obj) throws Throwable {
	     //   filterField(obj)
       // System.out.println("方法的返回值 : " + obj.getDescription());
       //  System.out.println("方法的返回值 : " + obj.getData()); 
		 //Java将任意类型的Object对象转换为相应的实体对象
//		 ObjectMapper objectMapper = new ObjectMapper();
//		 ResponseData responseData =objectMapper.convertValue(obj,ResponseData.class);
		 System.out.print("返回");
		 //返回的ResponseData直接处理
		 Field[] fields1 = obj.getClass().getDeclaredFields();
		 //返回的ResponseData 获取data处理
		 Field[] fields = obj.getData().getClass().getDeclaredFields();	
		 String aa=obj.getData().toString();
		 for(Field field: fields1) {
			 field.setAccessible(true);//如果为private ,则需要这样
			 
			 System.out.println("类型：" + field.getGenericType());
			 if (field.getName().endsWith("data")) {
				 System.out.println("获取对象类型：[com.koron.bean.securityBean.SysUser@34bccadc, com.koron.bean.securityBean.SysUser@b860537]" + field.get(obj));
				 Object o=field.get(obj);
				 List<Object> objList=(List<Object>)o;
				 System.out.println("单个对象的某个键的值==反射==" + objList.get(0).getClass());
				 Field[] fields2=objList.get(0).getClass().getFields();
				 Field[] fields3=objList.get(0).getClass().getDeclaredFields();
                 System.out.println("单个对象的某个键的值==反射==" + field.get(obj).getClass().getName());
                 System.out.println("返回 java.util.ArrayList" + field.get(obj).getClass().getCanonicalName());
                 System.out.println("返回  java.util.ArrayList" + field.get(obj).getClass().getDeclaredFields());
                 System.out.println("单个对象的某个键的值==反射==" + field.get(obj).getClass().getGenericSuperclass());
                 for(Field fieldfinal: fields3) {
                	 fieldfinal.setAccessible(true);//如果为private ,则需要这样
                	 ShowField showField = fieldfinal.getAnnotation(ShowField.class);
                	  if (showField != null) {
                		  //需要做的操作是检查登录用户的角色,假设只能ADMIN有password权限
                		  //假设user的role是 simpleRole
                		  String userrole="simpleRole";
                		  //获取字段注解上的名称
                		  String showfieldval=showField.value();
                		  if(showfieldval.equals(userrole)) {
                			  
                		  }else {
                			  //遍历objList(把所有的List<Object> 里面的对象中注解的字段，设置为null)
                			  for(int k=0;k<objList.size();k++) {
                				  //updatefield 代表要更新的字段
                				  for(Field updatefield:objList.get(k).getClass().getDeclaredFields()) {
                					  updatefield.setAccessible(true);
                					  if(updatefield.getName().equals(fieldfinal.getName())) {
                						  //设置为null
                						  updatefield.set(objList.get(k), null);
                					  }
                				  }
                			  }             			 
                			  
                			  
                		  }
                		 
                      }
                	 
                 }
                
             }
			 
			
		 }
		 /*
		  * 下面这for循环没做操作，只是为了参考方法 里面有哪些新方法
		  */
		 for(Field field: fields) {
			 if(List.class.isAssignableFrom(field.getType())) {
				 Type t=field.getGenericType();
				 if (t instanceof ParameterizedType) {
					 ParameterizedType pt = (ParameterizedType) t;
				     Class clz = (Class) pt.getActualTypeArguments()[0];//得到对象list中实例的类型
				     Class clazz = field.get("data").getClass();//获取到属性的值的Class对象
				     Method m= clazz.getDeclaredMethod("size");
				     int size = (Integer) m.invoke(field.get("data"));
				     for (int i = 0; i < size; i++) {//遍历list，调用get方法，获取list中的对象实例
				            Method getM= clazz.getDeclaredMethod("get", int.class);
				            if(!getM.isAccessible()){
				               getM.setAccessible(true);
				            }
				           Object oo=getM.invoke(field.get("data"), i);//加入到Ormlite数据库中
				        }
				 }
			 }
		 }
		 System.out.print(fields);
	 }
	  

}
