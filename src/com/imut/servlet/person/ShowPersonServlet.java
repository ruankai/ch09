package com.imut.servlet.person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.imut.javabean.Admin;
import com.imut.javabean.AdminDBAccess;
import com.imut.javabean.Teacher;
import com.imut.javabean.TeacherDBAccess;
import com.imut.javabean.User;
public class ShowPersonServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();//获得一个Session对象，用于存放一些提示信息返回到前台
		request.setCharacterEncoding("UTF-8");//设置请求的编码方式
		response.setCharacterEncoding("UTF-8");//设置响应的编码方式
		//从session中取出user对象（该对象在登录成功是放入session）
		User user=(User)session.getAttribute("user");
		//session过期将失效，其中的对象都将失效
		if(user==null){
			session.setAttribute("message", "登录用户已失效，请重新访问！");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		//判断用户类别来调用不同显示页面，供用户查看/修改
		if(user.getUserType()==0){
			String loginName=user.getLoginName();
			AdminDBAccess dbAccess=new AdminDBAccess();
			Admin admin=dbAccess.findAdminByLoginName(loginName);
			request.setAttribute("admin",admin);
			request.getRequestDispatcher("/person/adminShow.jsp").forward(request, response);
		}else {
			String teacherNo=user.getLoginName();
			TeacherDBAccess dbAccess=new TeacherDBAccess();
			Teacher teacher=dbAccess.findTeacherByTeacherNo(teacherNo);
			request.setAttribute("teacher",teacher);
			request.getRequestDispatcher("/person/teacherShow.jsp").forward(request, response);
		}
	}
}
