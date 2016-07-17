package com.imut.servlet.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imut.javabean.Course;
import com.imut.javabean.CourseImpl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class UpdateCourseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String courseNo=request.getParameter("courseNo");
		String courseName=request.getParameter("courseName");
		Integer studyTime=new Integer(request.getParameter("studyTime"));
		Integer grade=new Integer(request.getParameter("grade"));
		Integer courseType=new Integer(request.getParameter("courseType"));
		Integer term=new Integer(request.getParameter("term"));
		
  		CourseImpl dbAccess=new CourseImpl();
  		Course course=dbAccess.findCourseByCourseNo(courseNo);
  		course.setCourseNo(courseNo);
  		course.setCourseName(courseName);
  		course.setStudyTime(studyTime);
  		course.setGrade(grade);
  		course.setCourseType(courseType);
  		course.setTerm(term);
  		dbAccess.updateCourse(course);
  		session.setAttribute("message", "修改课程信息成功！");
  		request.getRequestDispatcher("/base/listAllCourseServlet").forward(request, response);
	}

}
