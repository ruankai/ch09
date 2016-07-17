package com.imut.servlet.base;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.imut.javabean.Teacher;
import com.imut.javabean.TeacherDBAccess;
public class ShowTeacherServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String teacherNo=request.getParameter("teacherNo");
		TeacherDBAccess dbAccess=new TeacherDBAccess();
		Teacher teacher=dbAccess.findTeacherByTeacherNo(teacherNo);
		request.setAttribute("teacher",teacher);
		request.getRequestDispatcher("/base/teacherShow.jsp").forward(request, response);
	}

}
