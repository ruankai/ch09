package com.imut.javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.imut.commmon.ConnectionFactory;
import com.imut.commmon.Page;
import com.imut.commmon.ResourceClose;

public class CourseArrangeImpl implements ICourseArrange {
	private ClassDBAccess db1=new ClassDBAccess();
	private ICourse db2=new CourseImpl();
	private TeacherDBAccess db3=new TeacherDBAccess();
	
	
	//添加课程安排方法
	public void addCourseArrange(CourseArrange courseArrange){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="insert into CourseArrange values(?,?,?,?,?)"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, courseArrange.getArrangeNo());
			pstmt.setString(2, courseArrange.getCourse().getCourseNo());
			pstmt.setString(3, courseArrange.getClassTbl().getClassNo());
			pstmt.setString(4, courseArrange.getTeacher().getTeacherNo());
			pstmt.setString(5, courseArrange.getStudyRoom());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//删除课程安排方法
	public void delCourseArrange(String arrangeNo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="delete from CourseArrange where arrangeNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, arrangeNo);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//修改课程安排信息
	public void updateCourseArrange(CourseArrange courseArrange){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="update CourseArrange set arrangeNo=?,courseNo=?,classNo=?,teacherNo=?,studyRoom=? where arrangeNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, courseArrange.getArrangeNo());
			pstmt.setString(2, courseArrange.getCourse().getCourseNo());
			pstmt.setString(3, courseArrange.getClassTbl().getClassNo());
			pstmt.setString(4, courseArrange.getTeacher().getTeacherNo());
			pstmt.setString(5, courseArrange.getStudyRoom());
			pstmt.setString(6, courseArrange.getArrangeNo());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//根据课程安排编号查找课程安排
	public CourseArrange findCourseArrangeByArrangeNo(String arrangeNo){
		CourseArrange courseArrange=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from CourseArrange where arrangeNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, arrangeNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//将表记录转化为CourseArrange对象
				courseArrange=new CourseArrange();
				courseArrange.setArrangeNo(rs.getString(1));
				courseArrange.setCourse(db2.findCourseByCourseNo(rs.getString(2)));
				courseArrange.setClassTbl(db1.findClassTblByClassNo(rs.getString(3)));
				courseArrange.setTeacher(db3.findTeacherByTeacherNo(rs.getString(4)));
				courseArrange.setStudyRoom(rs.getString(5));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return courseArrange;
	}
	//列表显示所有课程安排列表
	public Map findAllCourseArrange(int curPage){
		CourseArrange courseArrange=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from CourseArrange order by arrangeNo"; 
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
					//将表记录转化为CourseArrange对象
					courseArrange=new CourseArrange();
					courseArrange.setArrangeNo(rs.getString(1));
					courseArrange.setCourse(db2.findCourseByCourseNo(rs.getString(2)));
					courseArrange.setClassTbl(db1.findClassTblByClassNo(rs.getString(3)));
					courseArrange.setTeacher(db3.findTeacherByTeacherNo(rs.getString(4)));
					courseArrange.setStudyRoom(rs.getString(5));
					list.add(courseArrange);
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
	//多条件查询课程安排1
	public List findAllCourseArrangeByMostCon(String arrangeNo,String courseNo,String classNo,String teacherNo){
		CourseArrange courseArrange=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//构造多条件查询的SQL语句
		String sql="select * from CourseArrange where 1=1 ";
		//精确查询
		
		//模糊查询
		if(arrangeNo!=null&&!arrangeNo.equals("")){
			sql+=" and arrangeNo like '%"+arrangeNo+"%'";
		}
		if(courseNo!=null&&!courseNo.equals("")){
			sql+=" and courseNo like '%"+courseNo+"%'";
		}
		if(classNo!=null&&!classNo.equals("")){
			sql+=" and classNo like '%"+classNo+"%'";
		}
		if(teacherNo!=null&&!teacherNo.equals("")){
			sql+=" and teacherNo like '%"+teacherNo+"%'";
		}
		sql+=" order by arrangeNo";
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//将表记录转化为CourseArrange对象
				courseArrange=new CourseArrange();
				courseArrange.setArrangeNo(rs.getString(1));
				courseArrange.setCourse(db2.findCourseByCourseNo(rs.getString(2)));
				courseArrange.setClassTbl(db1.findClassTblByClassNo(rs.getString(3)));
				courseArrange.setTeacher(db3.findTeacherByTeacherNo(rs.getString(4)));
				courseArrange.setStudyRoom(rs.getString(5));
				list.add(courseArrange);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//多条件查询课程安排2
	public List findAllCourseArrangeByNo(String courseNo,String classNo,String teacherNo){
		CourseArrange courseArrange=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//构造多条件查询的SQL语句
		String sql="select * from CourseArrange where 1=1 ";
		if(courseNo!=null&&!courseNo.equals("")){
			sql+=" and courseNo='"+courseNo+"'";
		}
		if(classNo!=null&&!classNo.equals("")){
			sql+=" and classNo='"+classNo+"'";
		}
		if(teacherNo!=null&&!teacherNo.equals("")){
			sql+=" and teacherNo='"+teacherNo+"'";
		}
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				//将表记录转化为CourseArrange对象
				courseArrange=new CourseArrange();
				courseArrange.setArrangeNo(rs.getString(1));
				courseArrange.setCourse(db2.findCourseByCourseNo(rs.getString(2)));
				courseArrange.setClassTbl(db1.findClassTblByClassNo(rs.getString(3)));
				courseArrange.setTeacher(db3.findTeacherByTeacherNo(rs.getString(4)));
				courseArrange.setStudyRoom(rs.getString(5));
				list.add(courseArrange);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//查询某一教师所带班级
	public List findAllClassTblsByTeacherNo(String teacherNo){
		ClassTbl classTbl=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select distinct(classNo) from CourseArrange where teacherNo=? order by classNo";
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				list.add(db1.findClassTblByClassNo(rs.getString(1)));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//查询某一教师所带课程
	public List findAllCoursesByTeacherNo(String teacherNo){
		Course course=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select distinct(courseNo) from CourseArrange where teacherNo=? order by courseNo";
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				course=db2.findCourseByCourseNo(rs.getString(1));
				list.add(course);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
}
