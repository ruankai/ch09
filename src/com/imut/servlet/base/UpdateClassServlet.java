package com.imut.servlet.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imut.javabean.ClassDBAccess;
import com.imut.javabean.ClassTbl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class UpdateClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String classNo=request.getParameter("classNo");
		String className=request.getParameter("className");
		String college=request.getParameter("college");
  		ClassDBAccess dbAccess=new ClassDBAccess();
  		ClassTbl classTbl=dbAccess.findClassTblByClassNo(classNo);
  		classTbl.setClassNo(classNo);
  		classTbl.setClassName(className);
  		classTbl.setCollege(college);
  		dbAccess.updateClassTbl(classTbl);
  		session.setAttribute("message", "修改班级信息成功！");
  		request.getRequestDispatcher("/base/listAllClassServlet").forward(request, response);
	}

}
