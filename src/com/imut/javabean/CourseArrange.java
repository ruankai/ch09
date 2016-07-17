package com.imut.javabean;
/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class CourseArrange {
	private String arrangeNo;//安排编号
	private Course course;//所要安排课程
	private ClassTbl classTbl;//所要安排班级
	private Teacher teacher;//任课教师
	private String studyRoom;//上课地点
	//无参构造方法
	public CourseArrange() {
		super();
	}
	//有参构造方法
	public CourseArrange(String arrangeNo, Course course, ClassTbl classTbl,
			Teacher teacher, String studyRoom) {
		super();
		this.arrangeNo = arrangeNo;
		this.course = course;
		this.classTbl = classTbl;
		this.teacher = teacher;
		this.studyRoom = studyRoom;
	}
	//各属性的getter、setter方法
	public String getArrangeNo() {
		return arrangeNo;
	}
	public void setArrangeNo(String arrangeNo) {
		this.arrangeNo = arrangeNo;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public ClassTbl getClassTbl() {
		return classTbl;
	}
	public void setClassTbl(ClassTbl classTbl) {
		this.classTbl = classTbl;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getStudyRoom() {
		return studyRoom;
	}
	public void setStudyRoom(String studyRoom) {
		this.studyRoom = studyRoom;
	}
}
