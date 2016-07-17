package com.imut.servlet.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imut.javabean.ClassDBAccess;
import com.imut.javabean.ClassTbl;
import com.imut.javabean.StudentImpl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class CheckStudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String studentNo=request.getParameter("studentNo");
		String name=request.getParameter("name");
		String classNo=request.getParameter("classNo");
		
		request.setAttribute("studentNo", studentNo);
		request.setAttribute("name", name);
		request.setAttribute("classNo", classNo);
  		StudentImpl dbAccess=new StudentImpl();
  		List list=dbAccess.findAllStudentByMostCon(studentNo, name, classNo);
  		request.setAttribute("list", list);
  		request.getRequestDispatcher("/base/studentList.jsp").forward(request, response);
	}

}
