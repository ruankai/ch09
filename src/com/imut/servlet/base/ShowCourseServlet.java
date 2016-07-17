package com.imut.servlet.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imut.javabean.Course;
import com.imut.javabean.CourseImpl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class ShowCourseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String courseNo=request.getParameter("courseNo");
		CourseImpl dbAccess=new CourseImpl();
		Course course=dbAccess.findCourseByCourseNo(courseNo);
		request.setAttribute("course",course);
		request.getRequestDispatcher("/base/courseShow.jsp").forward(request, response);
	}

}
