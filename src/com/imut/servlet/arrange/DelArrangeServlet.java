package com.imut.servlet.arrange;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imut.javabean.CourseArrangeImpl;

public class DelArrangeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String arrangeNo=request.getParameter("arrangeNo");
		CourseArrangeImpl dbAccess=new CourseArrangeImpl();
	  	dbAccess.delCourseArrange(arrangeNo);
	  	session.setAttribute("message", "删除课程安排信息成功！");
	  	request.getRequestDispatcher("/arrange/listAllArrangeServlet").forward(request, response);
	}
}
