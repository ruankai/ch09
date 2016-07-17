package com.imut.javabean;
import java.util.List;
import java.util.Map;
public interface ICourse {
	//添加课程方法
	public void addCourse(Course course);
	//删除课程方法
	public void delCourse(String courseNo);
	//修改课程信息
	public void updateCourse(Course course);
	//根据课程编号查找课程
	public Course findCourseByCourseNo(String courseNo);
	//列表显示所有课程列表--分页
	public Map findAllCourse(int curPage);
	//列表显示所有课程列表
	public List findAllCourse();
	//多条件查询课程
	public List findAllCourseByMostCon(String courseNo,String courseName,Integer studyTime,
				Integer grade,Integer courseType,Integer term);
}