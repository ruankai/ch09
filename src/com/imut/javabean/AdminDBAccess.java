package com.imut.javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imut.commmon.ConnectionFactory;
import com.imut.commmon.Page;
import com.imut.commmon.ResourceClose;

public class AdminDBAccess {
	//添加管理员方法
	public void addAdmin(Admin admin){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="insert into admin values(?,?,?)"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, admin.getLoginName());
			pstmt.setString(2, admin.getName());
			pstmt.setString(3, admin.getPassword());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//删除管理员方法
	public void delAdmin(String loginName){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="delete from admin where loginName=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginName);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//修改管理员信息
	public void updateAdmin(Admin admin){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="update admin set loginName=?,name=?,password=? where loginName=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, admin.getLoginName());
			pstmt.setString(2, admin.getName());
			pstmt.setString(3, admin.getPassword());
			pstmt.setString(4, admin.getLoginName());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//根据登录名查找管理员
	public Admin findAdminByLoginName(String loginName){
		Admin admin=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from admin where loginName=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginName);
			rs=pstmt.executeQuery();
			while(rs.next()){
				admin=new Admin();
				admin.setLoginName(rs.getString(1));
				admin.setName(rs.getString(2));
				admin.setPassword(rs.getString(3));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return admin;
	}
	//列表显示所有管理员列表
	public Map findAllAdmin(int curPage){
		Admin admin=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from admin order by loginName"; 
			pstmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=pstmt.executeQuery(sql);
			pa=new Page();//声明分页类对象
			pa.setPageSize(5);
			pa.setPageCount(rs);
			pa.setCurPage(curPage);
			r=pa.setRs(rs);
			r.previous();
			for(int i=0;i<pa.getPageSize();i++){
				if(r.next()){
					admin=new Admin();
					admin.setLoginName(r.getString(1));
					admin.setName(r.getString(2));
					admin.setPassword(r.getString(3));
					list.add(admin);
				}else{
					break;
				}
			}
			map=new HashMap();
			map.put("list",list);
			map.put("pa",pa);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
			ResourceClose.close(r, null, null);
		}
		return map;
	}
	//多条件查询管理员
	public List findAllAdminByMostCon(String loginName,String name,String password){
		Admin admin=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//构造多条件查询的SQL语句
		String sql="select * from admin where 1=1 ";
		//精确查询
		/*
		if(loginName!=null&&!loginName.equals("")){
			sql+=" and loginName='"+loginName+"'";
		}
		if(name!=null&&!name.equals("")){
			sql+=" and name='"+name+"'";
		}
		if(password!=null&&!password.equals("")){
			sql+=" and password='"+password+"'";
		}
		sql+=" order by loginName";
		*/
		//模糊查询
		if(loginName!=null&&!loginName.equals("")){
			sql+=" and loginName like '%"+loginName+"%'";
		}
		if(name!=null&&!name.equals("")){
			sql+=" and name like '%"+name+"%'";
		}
		if(password!=null&&!password.equals("")){
			sql+=" and password like '%"+password+"%'";
		}
		sql+=" order by loginName";
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				admin=new Admin();
				admin.setLoginName(rs.getString(1));
				admin.setName(rs.getString(2));
				admin.setPassword(rs.getString(3));
				list.add(admin);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//用户登录验证方法
	public Admin login(String loginName,String password){
		Admin admin=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from admin where loginName=? and password=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginName);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			while(rs.next()){
				admin=new Admin();
				admin.setLoginName(rs.getString(1));
				admin.setName(rs.getString(2));
				admin.setPassword(rs.getString(3));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return admin;
	}
}
