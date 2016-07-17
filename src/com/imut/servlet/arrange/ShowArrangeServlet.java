package com.imut.servlet.arrange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imut.javabean.CourseArrange;
import com.imut.javabean.CourseArrangeImpl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class ShowArrangeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String arrangeNo=request.getParameter("arrangeNo");
		CourseArrangeImpl dbAccess=new CourseArrangeImpl();
		CourseArrange arrange=dbAccess.findCourseArrangeByArrangeNo(arrangeNo);
		request.setAttribute("arrange",arrange);
		request.getRequestDispatcher("/arrange/arrangeShow.jsp").forward(request, response);
	}

}
