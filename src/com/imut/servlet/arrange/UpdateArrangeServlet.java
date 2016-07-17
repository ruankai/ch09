package com.imut.servlet.arrange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imut.javabean.ClassDBAccess;
import com.imut.javabean.CourseArrange;
import com.imut.javabean.CourseArrangeImpl;
import com.imut.javabean.CourseImpl;
import com.imut.javabean.ICourse;
import com.imut.javabean.TeacherDBAccess;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class UpdateArrangeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ClassDBAccess db1=new ClassDBAccess();
		ICourse db2=new CourseImpl();
		TeacherDBAccess db3=new TeacherDBAccess();
		String arrangeNo=request.getParameter("arrangeNo");
		String courseNo=request.getParameter("courseNo");
		String classNo=request.getParameter("classNo");
		String teacherNo=request.getParameter("teacherNo");
		String studyRoom=request.getParameter("studyRoom");		
		CourseArrangeImpl dbAccess=new CourseArrangeImpl();
				CourseArrange arrange=dbAccess.findCourseArrangeByArrangeNo(arrangeNo);
		arrange.setArrangeNo(arrangeNo);
		arrange.setCourse(db2.findCourseByCourseNo(courseNo));
		arrange.setClassTbl(db1.findClassTblByClassNo(classNo));
		arrange.setTeacher(db3.findTeacherByTeacherNo(teacherNo));
		arrange.setStudyRoom(studyRoom);
  		dbAccess.updateCourseArrange(arrange);  		
  		session.setAttribute("message", "修改课程安排信息成功！");
  		request.getRequestDispatcher("/arrange/listAllArrangeServlet").forward(request, response);
	}

}
