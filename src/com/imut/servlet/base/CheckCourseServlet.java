package com.imut.servlet.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imut.javabean.CourseImpl;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class CheckCourseServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String courseNo=request.getParameter("courseNo");
		String courseName=request.getParameter("courseName");
		Integer studyTime=null;
		if(request.getParameter("studyTime")!=null&&!request.getParameter("studyTime").equals(""))
			studyTime=new Integer(request.getParameter("studyTime"));
		Integer grade=null;
		if(request.getParameter("grade")!=null&&!request.getParameter("grade").equals(""))
			grade=new Integer(request.getParameter("grade"));
		Integer courseType=null;
		if(request.getParameter("courseType")!=null&&!request.getParameter("courseType").equals(""))
			courseType=new Integer(request.getParameter("courseType"));
		Integer term=null;
		if(request.getParameter("term")!=null&&!request.getParameter("term").equals(""))
			term=new Integer(request.getParameter("term"));
		
		request.setAttribute("courseNo", courseNo);
		request.setAttribute("courseName", courseName);
		request.setAttribute("studyTime", studyTime);
		request.setAttribute("grade", grade);
		request.setAttribute("courseType", courseType);
		request.setAttribute("term", term);
  		CourseImpl dbAccess=new CourseImpl();
  		List list=dbAccess.findAllCourseByMostCon(courseNo, courseName, studyTime,grade,courseType,term);
  		request.setAttribute("list", list);
  		request.getRequestDispatcher("/base/courseList.jsp").forward(request, response);
	}

}
