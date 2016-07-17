package com.imut.servlet.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imut.javabean.Admin;
import com.imut.javabean.AdminDBAccess;
import com.imut.javabean.ClassDBAccess;
import com.imut.javabean.ClassTbl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class ShowClassServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String classNo=request.getParameter("classNo");
		ClassDBAccess dbAccess=new ClassDBAccess();
		ClassTbl classTbl=dbAccess.findClassTblByClassNo(classNo);
		request.setAttribute("classTbl",classTbl);
		request.getRequestDispatcher("/base/classShow.jsp").forward(request, response);
	}

}
