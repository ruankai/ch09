package com.imut.servlet.base;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.imut.commmon.Page;
import com.imut.javabean.TeacherDBAccess;
public class ListAllTeacherServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8");
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		TeacherDBAccess dbAccess=new TeacherDBAccess();
		Map map=dbAccess.findAllTeacher(curPage);
		ArrayList list=(ArrayList) map.get("list");
		Page pa=(Page) map.get("pa");
  		request.setAttribute("curPage", pa.getCurPage());//向显示页传递当前页页码
  		request.setAttribute("pageCount",pa.getPageCount());//向显示页传递总页数
  		request.setAttribute("list", list);//向显示页传递结果集
  		request.getRequestDispatcher("/base/teacherList.jsp").forward(request, response);
	}
}
