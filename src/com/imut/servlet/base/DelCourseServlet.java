package com.imut.servlet.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imut.javabean.CourseArrangeImpl;
import com.imut.javabean.CourseImpl;
import com.imut.javabean.ICourse;
import com.imut.javabean.ICourseArrange;

public class DelCourseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String courseNo=request.getParameter("courseNo");
		//删除课程信息前判断从表中是否存在关联记录，若不存在关联进行删除，否则禁止删除
		ICourseArrange cdb=new CourseArrangeImpl();
		List list=cdb.findAllCourseArrangeByNo(courseNo, null, null);
		if(list!=null&&list.size()>0){
			session.setAttribute("message", "所要删除记录存在关联项，禁止删除课程信息！");
			request.getRequestDispatcher("/base/listAllCourseServlet").forward(request, response);
		}else{
			ICourse dbAccess=new CourseImpl();
	  		dbAccess.delCourse(courseNo);
	  		session.setAttribute("message", "删除课程信息成功！");
	  		request.getRequestDispatcher("/base/listAllCourseServlet").forward(request, response);
		}
	}

}
