package com.imut.servlet.base;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.imut.javabean.TeacherDBAccess;
public class CheckTeacherServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String teacherNo=request.getParameter("teacherNo");
		String teacherName=request.getParameter("teacherName");
		Integer professional=null;
		if(request.getParameter("professional")!=null&&!request.getParameter("professional").equals("")){
			professional=new Integer(request.getParameter("professional"));
		}
		String phone=request.getParameter("phone");
		String subject=request.getParameter("subject");		
		request.setAttribute("teacherNo", teacherNo);
		request.setAttribute("teacherName", teacherName);
		request.setAttribute("professional", professional);
		request.setAttribute("phone", phone);
		request.setAttribute("subject", subject);
		TeacherDBAccess dbAccess=new TeacherDBAccess();
  		List list=dbAccess.findAllTeacherByMostCon(teacherNo, teacherName, professional,phone,subject);
  		request.setAttribute("list", list);
  		request.getRequestDispatcher("/base/teacherList.jsp").forward(request, response);
	}

}
