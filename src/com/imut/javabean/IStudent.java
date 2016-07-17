package com.imut.javabean;

import java.util.List;
import java.util.Map;

public interface IStudent {
	//添加一个学生
	public void addStudent(Student student);
	//根据学生学号删除一个学生
	public void delStudent(String studentNo);
	//修改一个学生的信息
	public void updateStudent(Student student);
	//根据学号查找学生
	public Student findStudentByStudentNo(String studentNo);
	//列表显示所有学生列表--分页
	public Map findAllStudent(int curPage);
	//多条件查询学生
	public List findAllStudentByMostCon(String studentNo,String name,String classNo);
	//根据班级查询学生
	public List findAllStudentByClassTbl(String classNo);
	//学生登录验证方法
	public Student login(String studentNo,String password);
}
