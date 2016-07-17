package com.imut.servlet.base;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.imut.javabean.StudentImpl;

public class DelStudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String studentNo=request.getParameter("studentNo");			
		StudentImpl dbAccess=new StudentImpl();
	  	dbAccess.delStudent(studentNo);
	  	session.setAttribute("message", "删除学生信息成功！");
	  	request.getRequestDispatcher("/base/listAllStudentServlet").forward(request, response);		
	}
}
