package com.imut.javabean;
/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class Student {
	private String studentNo;
	private String name;
	private String password;
	private String address;
	private String phone;
	private String email;
	private ClassTbl classTbl;
	
	public Student() {
		super();
	}
	public Student(String studentNo, String name, String password,
			String address, String phone, String email, ClassTbl classTbl) {
		super();
		this.studentNo = studentNo;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.classTbl = classTbl;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public ClassTbl getClassTbl() {
		return classTbl;
	}
	public void setClassTbl(ClassTbl classTbl) {
		this.classTbl = classTbl;
	}
	
}
