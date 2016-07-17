package com.imut.javabean;
public class User {
	private String loginName;//用户登录名，教师登录名为教师编号，学生登录名为学号
	private String name;//用户真实姓名
	private int userType;//用户类别，管理员类别为0，教师类别为1，学生类别为2
	//无参构造方法
	public User() {
		super();
	}
	//有参构造方法
	public User(String loginName, String name, int userType) {
		super();
		this.loginName = loginName;
		this.name = name;
		this.userType = userType;
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
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}	
}
