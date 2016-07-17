package com.imut.servlet.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imut.javabean.AdminDBAccess;
import com.imut.javabean.ClassDBAccess;
import com.imut.javabean.CourseArrangeImpl;
import com.imut.javabean.ICourseArrange;
import com.imut.javabean.IStudent;
import com.imut.javabean.StudentImpl;

public class DelClassServlet extends HttpServlet {

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
		//删除班级信息前判断从表中是否存在关联记录，若不存在关联进行删除，否则禁止删除
		ICourseArrange cdb=new CourseArrangeImpl();
		IStudent sdb=new StudentImpl();
		List list1=sdb.findAllStudentByClassTbl(classNo);
		List list2=cdb.findAllCourseArrangeByNo(null, classNo, null);
		if((list1!=null&&list1.size()>0)||(list2!=null&&list2.size()>0)){
			session.setAttribute("message", "所要删除记录存在关联项，禁止删除班级信息！");
			request.getRequestDispatcher("/base/listAllClassServlet").forward(request, response);
		}else{
	  		ClassDBAccess dbAccess=new ClassDBAccess();
	  		dbAccess.delClassTbl(classNo);
	  		session.setAttribute("message", "删除班级信息成功！");
	  		request.getRequestDispatcher("/base/listAllClassServlet").forward(request, response);
		}
	}

}
