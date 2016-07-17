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
public class CourseImpl implements ICourse{
	//添加课程方法
	public void addCourse(Course course){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="insert into Course values(?,?,?,?,?,?)"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, course.getCourseNo());
			pstmt.setString(2, course.getCourseName());
			pstmt.setInt(3, course.getStudyTime());
			pstmt.setInt(4, course.getGrade());
			pstmt.setInt(5, course.getCourseType());
			pstmt.setInt(6, course.getTerm());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//删除课程方法
	public void delCourse(String courseNo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="delete from Course where courseNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, courseNo);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//修改课程信息
	public void updateCourse(Course course){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="update Course set courseNo=?,courseName=?,studyTime=?,grade=?,courseType=?,term=? where courseNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, course.getCourseNo());
			pstmt.setString(2, course.getCourseName());
			pstmt.setInt(3, course.getStudyTime());
			pstmt.setInt(4, course.getGrade());
			pstmt.setInt(5, course.getCourseType());
			pstmt.setInt(6, course.getTerm());
			pstmt.setString(7, course.getCourseNo());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//根据课程编号查找课程
	public Course findCourseByCourseNo(String courseNo){
		Course course=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from Course where courseNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, courseNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				course=new Course();
				course.setCourseNo(rs.getString(1));
				course.setCourseName(rs.getString(2));
				course.setStudyTime(rs.getInt(3));
				course.setGrade(rs.getInt(4));
				course.setCourseType(rs.getInt(5));
				course.setTerm(rs.getInt(6));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return course;
	}
	//列表显示所有课程列表--分页
	public Map findAllCourse(int curPage){
		Course course=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from Course order by courseNo"; 
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
					course=new Course();
					course.setCourseNo(r.getString(1));
					course.setCourseName(r.getString(2));
					course.setStudyTime(r.getInt(3));
					course.setGrade(r.getInt(4));
					course.setCourseType(r.getInt(5));
					course.setTerm(r.getInt(6));
					list.add(course);
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
	//列表显示所有课程列表
	public List findAllCourse(){
		Course course=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from Course order by courseNo"; 
			pstmt=conn.createStatement();
			rs=pstmt.executeQuery(sql);
			while(rs.next()){
				course=new Course();
				course.setCourseNo(rs.getString(1));
				course.setCourseName(rs.getString(2));
				course.setStudyTime(rs.getInt(3));
				course.setGrade(rs.getInt(4));
				course.setCourseType(rs.getInt(5));
				course.setTerm(rs.getInt(6));
				list.add(course);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//多条件查询课程
	public List findAllCourseByMostCon(String courseNo,String courseName,Integer studyTime,
			Integer grade,Integer courseType,Integer term){
		Course course=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//构造多条件查询的SQL语句
		String sql="select * from Course where 1=1 ";		
		//模糊查询
		if(courseNo!=null&&!courseNo.equals("")){
			sql+=" and courseNo like '%"+courseNo+"%'";
		}
		if(courseName!=null&&!courseName.equals("")){
			sql+=" and courseName like '%"+courseName+"%'";
		}
		if(studyTime!=null&&!studyTime.equals("")){
			sql+=" and studyTime="+studyTime;
		}
		if(grade!=null&&!grade.equals("")){
			sql+=" and grade="+grade;
		}
		if(courseType!=null&&!courseType.equals("")){
			sql+=" and courseType="+courseType;
		}
		if(term!=null&&!term.equals("")){
			sql+=" and term="+term;
		}
		sql+=" order by courseNo";		
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				course=new Course();
				course.setCourseNo(rs.getString(1));
				course.setCourseName(rs.getString(2));
				course.setStudyTime(rs.getInt(3));
				course.setGrade(rs.getInt(4));
				course.setCourseType(rs.getInt(5));
				course.setTerm(rs.getInt(6));
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
