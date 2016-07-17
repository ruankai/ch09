package com.imut.javabean;
public class ClassTbl {
	private String classNo;
	private String className;
	private String college;
	public ClassTbl() {
	}
	public ClassTbl(String classNo, String className, String college) {
		super();
		this.classNo = classNo;
		this.className = className;
		this.college = college;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}	
}
