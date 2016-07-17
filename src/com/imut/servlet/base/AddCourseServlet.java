package com.imut.servlet.base;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.imut.javabean.Course;
import com.imut.javabean.CourseImpl;
public class AddCourseServlet extends HttpServlet {
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
		String courseName=request.getParameter("courseName");
		Integer studyTime=new Integer(request.getParameter("studyTime"));
		Integer grade=new Integer(request.getParameter("grade"));
		Integer courseType=new Integer(request.getParameter("courseType"));
		Integer term=new Integer(request.getParameter("term"));
		CourseImpl dbAccess=new CourseImpl();
		if(dbAccess.findCourseByCourseNo(courseNo)!=null){
			session.setAttribute("message", "所要添加的课程已存在！");
			request.getRequestDispatcher("/base/addCourse.jsp").forward(request, response);
		}else{
			Course course=new Course(courseNo,courseName,studyTime,grade,courseType,term);
			dbAccess.addCourse(course);
	  		session.setAttribute("message", "课程信息添加成功！");
	  		request.getRequestDispatcher("/base/listAllCourseServlet").forward(request, response);
		}
	}
}
