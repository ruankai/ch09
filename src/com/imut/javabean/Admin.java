package com.imut.javabean;
public class Admin {
	private String loginName;//用户登录名
	private String name;//用户真实姓名
	private String password;//用户登录密码
	//无参构造方法
	public Admin() {
	}
	//有参构造方法
	public Admin(String loginName, String name, String password) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.password = password;
	}
	//getter、setter方法
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
