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
public class ClassDBAccess {
	//添加班级方法
	public void addClass(ClassTbl classTbl){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="insert into ClassTbl values(?,?,?)"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, classTbl.getClassNo());
			pstmt.setString(2, classTbl.getClassName());
			pstmt.setString(3, classTbl.getCollege());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//删除班级方法
	public void delClassTbl(String classNo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="delete from ClassTbl where classNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, classNo);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//修改班级信息
	public void updateClassTbl(ClassTbl classTbl){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="update ClassTbl set classNo=?,className=?,college=? where classNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, classTbl.getClassNo());
			pstmt.setString(2, classTbl.getClassName());
			pstmt.setString(3, classTbl.getCollege());
			pstmt.setString(4, classTbl.getClassNo());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//根据班级编号查找班级
	public ClassTbl findClassTblByClassNo(String classNo){
		ClassTbl classTbl=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from ClassTbl where classNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, classNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				classTbl=new ClassTbl();
				classTbl.setClassNo(rs.getString(1));
				classTbl.setClassName(rs.getString(2));
				classTbl.setCollege(rs.getString(3));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return classTbl;
	}
	//列表显示所有班级列表-分页
	public Map findAllClassTbl(int curPage){
		ClassTbl classTbl=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from ClassTbl order by classNo"; 
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
					classTbl=new ClassTbl();
					classTbl.setClassNo(r.getString(1));
					classTbl.setClassName(r.getString(2));
					classTbl.setCollege(r.getString(3));
					list.add(classTbl);
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
	public List findAllClassTbl(){
		ClassTbl classTbl=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from ClassTbl order by classNo"; 
			pstmt=conn.createStatement();
			rs=pstmt.executeQuery(sql);
			while(rs.next()){
				classTbl=new ClassTbl();
				classTbl.setClassNo(rs.getString(1));
				classTbl.setClassName(rs.getString(2));
				classTbl.setCollege(rs.getString(3));
				list.add(classTbl);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//多条件查询班级
	public List findAllClassTblByMostCon(String classNo,String className,String college){
		ClassTbl classTbl=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;		
		//构造多条件查询的SQL语句
		String sql="select * from ClassTbl where 1=1 ";		
		//模糊查询
		if(classNo!=null&&!classNo.equals("")){
			sql+=" and classNo like '%"+classNo+"%'";
		}
		if(className!=null&&!className.equals("")){
			sql+=" and className like '%"+className+"%'";
		}
		if(college!=null&&!college.equals("")){
			sql+=" and college like '%"+college+"%'";
		}
		sql+=" order by classNo";
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				classTbl=new ClassTbl();
				classTbl.setClassNo(rs.getString(1));
				classTbl.setClassName(rs.getString(2));
				classTbl.setCollege(rs.getString(3));
				list.add(classTbl);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}	
}
