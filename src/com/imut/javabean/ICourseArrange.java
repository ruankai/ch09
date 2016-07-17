package com.imut.javabean;

import java.util.List;
import java.util.Map;

public interface ICourseArrange {
	//添加课程安排方法
	public void addCourseArrange(CourseArrange courseArrange);
	//删除课程安排方法
	public void delCourseArrange(String arrangeNo);
	//修改课程安排信息
	public void updateCourseArrange(CourseArrange courseArrange);
	//根据课程安排编号查找课程安排
	public CourseArrange findCourseArrangeByArrangeNo(String arrangeNo);
	//列表显示所有课程安排列表
	public Map findAllCourseArrange(int curPage);
	//多条件查询课程安排1
	public List findAllCourseArrangeByMostCon(String arrangeNo,String courseNo,String classNo,String teacherNo);
	//多条件查询课程安排2
	public List findAllCourseArrangeByNo(String courseNo,String classNo,String teacherNo);
	//查询某一教师所带班级
	public List findAllClassTblsByTeacherNo(String teacherNo);
	//查询某一教师所带课程
	public List findAllCoursesByTeacherNo(String teacherNo);
}
