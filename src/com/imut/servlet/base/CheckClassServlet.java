package com.imut.servlet.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imut.javabean.AdminDBAccess;
import com.imut.javabean.ClassDBAccess;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class CheckClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String classNo=request.getParameter("classNo");
		String className=request.getParameter("className");
		String college=request.getParameter("college");
		
		request.setAttribute("classNo", classNo);
		request.setAttribute("className", className);
		request.setAttribute("college", college);
  		ClassDBAccess dbAccess=new ClassDBAccess();
  		List list=dbAccess.findAllClassTblByMostCon(classNo, className, college);
  		request.setAttribute("list", list);
  		request.getRequestDispatcher("/base/classList.jsp").forward(request, response);
	}

}
