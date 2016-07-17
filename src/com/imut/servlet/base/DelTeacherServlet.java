package com.imut.servlet.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imut.javabean.AdminDBAccess;
import com.imut.javabean.CourseArrangeImpl;
import com.imut.javabean.ICourseArrange;
import com.imut.javabean.TeacherDBAccess;

public class DelTeacherServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String teacherNo=request.getParameter("teacherNo");
		
		//删除教师信息前判断从表中是否存在关联记录，若不存在关联进行删除，否则禁止删除
		ICourseArrange cdb=new CourseArrangeImpl();
		List list=cdb.findAllCourseArrangeByNo(null, null, teacherNo);
		if(list!=null&&list.size()>0){
			session.setAttribute("message", "所要删除记录存在关联项，禁止删除教师信息！");
			request.getRequestDispatcher("/base/listAllTeacherServlet").forward(request, response);
		}else{
			TeacherDBAccess dbAccess=new TeacherDBAccess();
	  		dbAccess.delTeacher(teacherNo);
	  		session.setAttribute("message", "删除教师信息成功！");
	  		request.getRequestDispatcher("/base/listAllTeacherServlet").forward(request, response);
		}
	}
}
