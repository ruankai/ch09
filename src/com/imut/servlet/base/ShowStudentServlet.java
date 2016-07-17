package com.imut.servlet.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imut.javabean.Student;
import com.imut.javabean.StudentImpl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class ShowStudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String studentNo=request.getParameter("studentNo");
		StudentImpl dbAccess=new StudentImpl();
		Student student=dbAccess.findStudentByStudentNo(studentNo);
		request.setAttribute("student",student);
		request.getRequestDispatcher("/base/studentShow.jsp").forward(request, response);
	}
}
