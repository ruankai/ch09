package com.imut.servlet.arrange;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.imut.javabean.ClassDBAccess;
import com.imut.javabean.ClassTbl;
import com.imut.javabean.Course;
import com.imut.javabean.CourseArrange;
import com.imut.javabean.CourseArrangeImpl;
import com.imut.javabean.CourseImpl;
import com.imut.javabean.ICourse;
import com.imut.javabean.Teacher;
import com.imut.javabean.TeacherDBAccess;

public class AddArrangeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ClassDBAccess db1=new ClassDBAccess();
		ICourse db2=new CourseImpl();
		TeacherDBAccess db3=new TeacherDBAccess();
		String arrangeNo=request.getParameter("arrangeNo");
		String courseNo=request.getParameter("courseNo");
		String classNo=request.getParameter("classNo");
		String teacherNo=request.getParameter("teacherNo");
		String studyRoom=request.getParameter("studyRoom");
		Course course=db2.findCourseByCourseNo(courseNo);
		ClassTbl classTbl=db1.findClassTblByClassNo(classNo);
		Teacher teacher=db3.findTeacherByTeacherNo(teacherNo);
		
		CourseArrangeImpl dbAccess=new CourseArrangeImpl();		
		if(dbAccess.findCourseArrangeByArrangeNo(arrangeNo)!=null){
			session.setAttribute("message", "所要添加的的安排已存在！");
			request.getRequestDispatcher("/base/addArrange.jsp").forward(request, response);
		}else{
			CourseArrange arrange=new CourseArrange(arrangeNo,course,classTbl,teacher,studyRoom);
			dbAccess.addCourseArrange(arrange);			
	  		session.setAttribute("message", "课程安排信息添加成功！");
	  		request.getRequestDispatcher("/arrange/listAllArrangeServlet").forward(request, response);
		}
	}

}
