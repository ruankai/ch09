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
import com.imut.javabean.Admin;
import com.imut.javabean.AdminDBAccess;
import com.imut.javabean.ClassDBAccess;

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class ListAllClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int curPage=1;
		String temp=request.getParameter("curPage");
		if(temp!=null){
			curPage=Integer.parseInt(request.getParameter("curPage"));
		}
		ClassDBAccess dbAccess=new ClassDBAccess();
		Map map=dbAccess.findAllClassTbl(curPage);
		ArrayList list=(ArrayList) map.get("list");
		Page pa=(Page) map.get("pa");
  		request.setAttribute("curPage", pa.getCurPage());//向显示页传递当前页页码
  		request.setAttribute("pageCount",pa.getPageCount());//向显示页传递总页数
  		request.setAttribute("list", list);//向显示页传递结果集
  		request.getRequestDispatcher("/base/classList.jsp").forward(request, response);
	}

}
