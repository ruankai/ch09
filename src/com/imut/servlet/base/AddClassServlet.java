package com.imut.servlet.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class AddClassServlet extends HttpServlet {

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
		if(dbAccess.findClassTblByClassNo(classNo)!=null){
			session.setAttribute("message", "所要添加的班级已存在！");
			request.getRequestDispatcher("/base/addClass.jsp").forward(request, response);
		}else{
			ClassTbl classTbl=new ClassTbl(classNo,className,college);
			dbAccess.addClass(classTbl);
	  		session.setAttribute("message", "班级信息添加成功！");
	  		request.getRequestDispatcher("/base/listAllClassServlet").forward(request, response);
		}
	}

}
