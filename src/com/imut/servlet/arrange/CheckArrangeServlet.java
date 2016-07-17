package com.imut.servlet.arrange;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imut.javabean.CourseArrangeImpl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class CheckArrangeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String arrangeNo=request.getParameter("arrangeNo");
		String courseNo=request.getParameter("courseNo");
		String classNo=request.getParameter("classNo");
		String teacherNo=request.getParameter("teacherNo");
		request.setAttribute("arrangeNo", arrangeNo);
		request.setAttribute("courseNo", courseNo);
		request.setAttribute("classNo", classNo);
		request.setAttribute("teacherNo", teacherNo);
		
  		CourseArrangeImpl dbAccess=new CourseArrangeImpl();
  		List list=dbAccess.findAllCourseArrangeByMostCon(arrangeNo, courseNo, classNo,teacherNo);
  		request.setAttribute("list", list);
  		request.getRequestDispatcher("/arrange/arrangeList.jsp").forward(request, response);
	}

}
