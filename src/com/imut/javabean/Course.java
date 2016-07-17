package com.imut.javabean;
public class Course {
	private String courseNo;
	private String courseName;
	private int studyTime;
	private int grade;
	private int courseType;
	private int term;	
	public Course() {
	}
	public Course(String courseNo, String courseName, int studyTime, int grade,
			int courseType, int term) {
		super();
		this.courseNo = courseNo;
		this.courseName = courseName;
		this.studyTime = studyTime;
		this.grade = grade;
		this.courseType = courseType;
		this.term = term;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(int studyTime) {
		this.studyTime = studyTime;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getCourseType() {
		return courseType;
	}
	public void setCourseType(int courseType) {
		this.courseType = courseType;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}	
}
