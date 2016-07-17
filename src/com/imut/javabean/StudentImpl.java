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

/**
 *@author: Lilx
 *@date: Feb 18, 2011
 *@company: cstd
 *@Email:llxhappy@126.com
 */
public class StudentImpl implements IStudent {
	private ClassDBAccess db=new ClassDBAccess();
	//添加一个学生
	public void addStudent(Student student){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();//调用ConnectionFactory组件的getConnection()方法来获得数据库连接
			String sql="insert into Student values(?,?,?,?,?,?,?)"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, student.getStudentNo());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getPassword());
			pstmt.setString(4, student.getAddress());
			pstmt.setString(5, student.getPhone());
			pstmt.setString(6, student.getEmail());
			pstmt.setString(7, student.getClassTbl().getClassNo());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);//调用ResourceClose组件的close方法来关闭数据库资源
		}
	}
	//根据学生学号删除一个学生
	public void delStudent(String studentNo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="delete from Student where studentNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, studentNo);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//修改一个学生的信息
	public void updateStudent(Student student){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="update Student set studentNo=?,name=?,password=?,address=?,phone=?,email=?,classNo=? where studentNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, student.getStudentNo());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getPassword());
			pstmt.setString(4, student.getAddress());
			pstmt.setString(5, student.getPhone());
			pstmt.setString(6, student.getEmail());
			pstmt.setString(7, student.getClassTbl().getClassNo());
			pstmt.setString(8, student.getStudentNo());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//根据学号查找学生
	public Student findStudentByStudentNo(String studentNo){
		Student student=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from Student where studentNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, studentNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//将学生记录转化为学生对象
				student=new Student();
				student.setStudentNo(rs.getString(1));
				student.setName(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setAddress(rs.getString(4));
				student.setPhone(rs.getString(5));
				student.setEmail(rs.getString(6));
				student.setClassTbl(db.findClassTblByClassNo(rs.getString(7)));//根据数据库查询到的班级编号来获得班级对象，然后赋给学生对象的班级属性
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return student;
	}
	//列表显示所有学生列表--分页
	public Map findAllStudent(int curPage){
		Student student=null;
		ArrayList list=new ArrayList();//声明一个List对象，用于存放所要显示学生对象
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		
		ClassTbl classTbl=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from Student order by studentNo"; 
			pstmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=pstmt.executeQuery(sql);
			pa=new Page();//声明分页类对象
			pa.setPageSize(5);//设置每页所要显示记录个数
			pa.setPageCount(rs);//根据结果集设置所有要显示的记录总数
			pa.setCurPage(curPage);//设置所要显示页
			r=pa.setRs(rs);//设置要显示的结果集，此时记录指针处于第一条记录上
			r.previous();//记录指针前移，使之处于第一条记录之前的位置
			//利用循环操作将查询到的结果集放入List对象中
			for(int i=0;i<pa.getPageSize();i++){
				if(r.next()){
					//将学生记录转化为学生对象
					student=new Student();
					student.setStudentNo(rs.getString(1));
					student.setName(rs.getString(2));
					student.setPassword(rs.getString(3));
					student.setAddress(rs.getString(4));
					student.setPhone(rs.getString(5));
					student.setEmail(rs.getString(6));
					student.setClassTbl(db.findClassTblByClassNo(rs.getString(7)));//根据数据库查询到的班级编号来获得班级对象，然后赋给学生对象的班级属性
					list.add(student);
				}else{
					break;
				}
			}
			map=new HashMap();//声明一个Map对象
			map.put("list",list);//将list对象放入Map对象
			map.put("pa",pa);//将Page对象放入Map对象
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
			ResourceClose.close(r, null, null);
		}
		return map;
	}
	//多条件查询学生
	public List findAllStudentByMostCon(String studentNo,String name,String classNo){
		Student student=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//构造多条件查询的SQL语句（实现单一条件或者多个条件的灵活查询）
		String sql="select * from Student where 1=1 ";
		//精确查询
		/*
		if(studentNo!=null&&!studentNo.equals("")){
			sql+=" and studentNo='"+studentNo+"'";
		}
		if(name!=null&&!name.equals("")){
			sql+=" and name='"+name+"'";
		}
		if(classNo!=null&&!classNo.equals("")){
			sql+=" and classNo='"+classNo+"'";
		}
		sql+=" order by studentNo";
		*/
		//模糊查询
		if(studentNo!=null&&!studentNo.equals("")){
			sql+=" and studentNo like '%"+studentNo+"%'";
		}
		if(name!=null&&!name.equals("")){
			sql+=" and name like '%"+name+"%'";
		}
		if(classNo!=null&&!classNo.equals("")){
			sql+=" and classNo like '%"+classNo+"%'";
		}
		sql+=" order by studentNo";
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//将学生记录转化为学生对象
				student=new Student();
				student.setStudentNo(rs.getString(1));
				student.setName(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setAddress(rs.getString(4));
				student.setPhone(rs.getString(5));
				student.setEmail(rs.getString(6));
				student.setClassTbl(db.findClassTblByClassNo(rs.getString(7)));//根据数据库查询到的班级编号来获得班级对象，然后赋给学生对象的班级属性
				list.add(student);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//根据班级查询学生
	public List findAllStudentByClassTbl(String classNo){
		Student student=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from Student where classNo=?";
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, classNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//将学生记录转化为学生对象
				student=new Student();
				student.setStudentNo(rs.getString(1));
				student.setName(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setAddress(rs.getString(4));
				student.setPhone(rs.getString(5));
				student.setEmail(rs.getString(6));
				student.setClassTbl(db.findClassTblByClassNo(rs.getString(7)));//根据数据库查询到的班级编号来获得班级对象，然后赋给学生对象的班级属性
				list.add(student);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//学生登录验证方法
	public Student login(String studentNo,String password){
		Student student=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from Student where studentNo=? and password=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, studentNo);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//将学生记录转化为学生对象
				student=new Student();
				student.setStudentNo(rs.getString(1));
				student.setName(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setAddress(rs.getString(4));
				student.setPhone(rs.getString(5));
				student.setEmail(rs.getString(6));
				student.setClassTbl(db.findClassTblByClassNo(rs.getString(7)));//根据数据库查询到的班级编号来获得班级对象，然后赋给学生对象的班级属性
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return student;
	}
}
