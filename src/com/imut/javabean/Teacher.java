package com.imut.javabean;
public class Teacher {
	private String teacherNo;//教师编号
	private String teacherName;//教师姓名
	private String password;//登录密码
	private int professional;//职称，0表示教授或正高工，1表示副教授或副高工，2表示讲师，3表示助教
	private String education;//最后学历
	private String address;//家庭住址
	private String phone;//联系电话
	private String email;//电子邮箱
	private String subject;//研究方向
	//无参构造方法
	public Teacher() {
	}
	//有参构造方法
	public Teacher(String teacherNo, String teacherName, String password,
			int professional, String education, String address,
			String phone, String email, String subject) {
		super();
		this.teacherNo = teacherNo;
		this.teacherName = teacherName;
		this.password = password;
		this.professional = professional;
		this.education = education;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.subject = subject;
	}
	//getter、setter方法
	public String getTeacherNo() {
		return teacherNo;
	}
	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getProfessional() {
		return professional;
	}
	public void setProfessional(int professional) {
		this.professional = professional;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}	
}
