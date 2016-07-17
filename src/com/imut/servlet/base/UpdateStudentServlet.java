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
import com.imut.javabean.Student;
import com.imut.javabean.StudentImpl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class UpdateStudentServlet extends HttpServlet {

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
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String classNo=request.getParameter("classNo");
		ClassDBAccess db=new ClassDBAccess();
		ClassTbl classTbl=db.findClassTblByClassNo(classNo);
  		StudentImpl dbAccess=new StudentImpl();
  		Student student=dbAccess.findStudentByStudentNo(studentNo);
  		student.setStudentNo(studentNo);
  		student.setName(name);
  		student.setPassword(password);
  		student.setAddress(address);
  		student.setPhone(phone);
  		student.setEmail(email);
  		student.setClassTbl(classTbl);
  		dbAccess.updateStudent(student);
  		session.setAttribute("message", "修改学生信息成功！");
  		request.getRequestDispatcher("/base/listAllStudentServlet").forward(request, response);
	}

}
