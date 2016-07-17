package com.imut.servlet.base;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.imut.javabean.Teacher;
import com.imut.javabean.TeacherDBAccess;
public class UpdateTeacherServlet extends HttpServlet {
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
		String teacherName=request.getParameter("teacherName");
		String password=request.getParameter("password");
		Integer professional=new Integer(request.getParameter("professional"));
		String education=request.getParameter("education");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String subject=request.getParameter("subject");
		
  		TeacherDBAccess dbAccess=new TeacherDBAccess();
  		Teacher teacher=dbAccess.findTeacherByTeacherNo(teacherNo);
  		teacher.setTeacherNo(teacherNo);
		teacher.setTeacherName(teacherName);
		teacher.setPassword(password);
		teacher.setProfessional(professional);
		teacher.setEducation(education);
		teacher.setAddress(address);
		teacher.setPhone(phone);
		teacher.setEmail(email);
		teacher.setSubject(subject);
  		dbAccess.updateTeacher(teacher);
  		session.setAttribute("message", "修改教师信息成功！");
  		request.getRequestDispatcher("/base/listAllTeacherServlet").forward(request, response);
	}
}
