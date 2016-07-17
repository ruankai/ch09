package com.imut.servlet;
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
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();//获得一个Session对象，用于存放一些提示信息返回到前台
		request.setCharacterEncoding("UTF-8");//设置请求的编码方式
		response.setCharacterEncoding("UTF-8");//设置响应的编码方式
		//通过request对象从前台登录页面表单获得登录名和密码数据
  		String loginName=request.getParameter("loginName");
  		String password=request.getParameter("password");
  		//声明AdminDBAccess、TeacherDBAccess的对象，调用其中的登录方法
  		AdminDBAccess db1=new AdminDBAccess();
  		TeacherDBAccess db2=new TeacherDBAccess();  		
  		Admin admin=db1.login(loginName,password);
  		Teacher teacher=db2.login(loginName, password);  		
  		User user=null;
  		//根据登陆方法返回的值判断用户是哪种类型用户，并将教师或者管理员对象转化为User对象并设置对应的登录名、真实姓名、用户类型属性值
  		if(admin!=null||teacher!=null){
  			if(admin!=null){
  				user=new User();
  				user.setLoginName(admin.getLoginName());
  				user.setName(admin.getName());
  				user.setUserType(0);
  			}else if(teacher!=null){
	  			user=new User();
	  			user.setLoginName(teacher.getTeacherNo());
	  			user.setName(teacher.getTeacherName());
	  			user.setUserType(1);	  			
  			}
  			//如果登录成功将获得的User对象存在session对象中（十分重要，程序以后要使用）
			session.setAttribute("user",user);
			//如果登录成功跳转系统主页面index.jsp
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{
			//如果登录失败将登录失败提示信息放入session对象
			session.setAttribute("message","登录信息有误，请重新登录！！！");
			//如果登录失败跳转系统登录页面login.jsp，要求用户重新登录
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}
}
