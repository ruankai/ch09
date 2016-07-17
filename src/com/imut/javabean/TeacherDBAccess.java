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
public class TeacherDBAccess {
	//添加教师方法
	public void addTeacher(Teacher teacher){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="insert into Teacher values(?,?,?,?,?,?,?,?,?)"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherNo());
			pstmt.setString(2, teacher.getTeacherName());
			pstmt.setString(3, teacher.getPassword());
			pstmt.setInt(4, teacher.getProfessional());
			pstmt.setString(5, teacher.getEducation());
			pstmt.setString(6, teacher.getAddress());
			pstmt.setString(7, teacher.getPhone());
			pstmt.setString(8, teacher.getEmail());
			pstmt.setString(9, teacher.getSubject());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//删除教师方法
	public void delTeacher(String teacherNo){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="delete from Teacher where teacherNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherNo);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//修改教师信息
	public void updateTeacher(Teacher teacher){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="update Teacher set teacherNo=?,teacherName=?,password=?,professional=?,"+
				"education=?,address=?,phone=?,email=?,subject=? where teacherNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getTeacherNo());
			pstmt.setString(2, teacher.getTeacherName());
			pstmt.setString(3, teacher.getPassword());
			pstmt.setInt(4, teacher.getProfessional());
			pstmt.setString(5, teacher.getEducation());
			pstmt.setString(6, teacher.getAddress());
			pstmt.setString(7, teacher.getPhone());
			pstmt.setString(8, teacher.getEmail());
			pstmt.setString(9, teacher.getSubject());
			pstmt.setString(10, teacher.getTeacherNo());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
	}
	//根据教师编号查找教师
	public Teacher findTeacherByTeacherNo(String teacherNo){
		Teacher teacher=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from Teacher where teacherNo=?"; 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacherNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				teacher=new Teacher();
				teacher.setTeacherNo(rs.getString(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setPassword(rs.getString(3));
				teacher.setProfessional(rs.getInt(4));
				teacher.setEducation(rs.getString(5));
				teacher.setAddress(rs.getString(6));
				teacher.setPhone(rs.getString(7));
				teacher.setEmail(rs.getString(8));
				teacher.setSubject(rs.getString(9));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return teacher;
	}
	//列表显示所有教师列表--分页
	public Map findAllTeacher(int curPage){
		Teacher teacher=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs=null;
		ResultSet r=null;
		Map map=null;
		Page pa=null;
		try{
			conn=ConnectionFactory.getConnection();
			String sql="select * from Teacher order by teacherNo"; 
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
					teacher=new Teacher();
					teacher.setTeacherNo(r.getString(1));
					teacher.setTeacherName(r.getString(2));
					teacher.setPassword(rs.getString(3));
					teacher.setProfessional(r.getInt(4));
					teacher.setEducation(r.getString(5));
					teacher.setAddress(r.getString(6));
					teacher.setPhone(r.getString(7));
					teacher.setEmail(r.getString(8));
					teacher.setSubject(r.getString(9));
					list.add(teacher);
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
	//列表显示所有教师列表
		public List findAllTeacher(){
			Teacher teacher=null;
			ArrayList list=new ArrayList();
			Connection conn=null;
			Statement pstmt=null;
			ResultSet rs=null;
			try{
				conn=ConnectionFactory.getConnection();
				String sql="select * from Teacher order by teacherNo"; 
				pstmt=conn.createStatement();
				rs=pstmt.executeQuery(sql);
				while(rs.next()){
					teacher=new Teacher();
					teacher.setTeacherNo(rs.getString(1));
					teacher.setTeacherName(rs.getString(2));
					teacher.setPassword(rs.getString(3));
					teacher.setProfessional(rs.getInt(4));
					teacher.setEducation(rs.getString(5));
					teacher.setAddress(rs.getString(6));
					teacher.setPhone(rs.getString(7));
					teacher.setEmail(rs.getString(8));
					teacher.setSubject(rs.getString(9));
					list.add(teacher);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ResourceClose.close(rs, pstmt, conn);
			}
			return list;
		}
	//多条件查询教师
	public List findAllTeacherByMostCon(String teacherNo,String teacherName,
			Integer professional,String phone,String subject){
		Teacher teacher=null;
		ArrayList list=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//构造多条件查询的SQL语句
		String sql="select * from Teacher where 1=1 ";		
		//模糊查询
		if(teacherNo!=null&&!teacherNo.equals("")){
			sql+=" and teacherNo like '%"+teacherNo+"%'";
		}
		if(teacherName!=null&&!teacherName.equals("")){
			sql+=" and teacherName like '%"+teacherName+"%'";
		}
		if(phone!=null&&!phone.equals("")){
			sql+=" and phone like '%"+phone+"%'";
		}
		if(subject!=null&&!subject.equals("")){
			sql+=" and subject like '%"+subject+"%'";
		}
		if(professional!=null&&!professional.equals("")){
			sql+=" and professional="+professional;
		}		
		sql+=" order by teacherNo";
		try{
			conn=ConnectionFactory.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				teacher=new Teacher();
				teacher.setTeacherNo(rs.getString(1));
				teacher.setTeacherName(rs.getString(2));
				teacher.setPassword(rs.getString(3));
				teacher.setProfessional(rs.getInt(4));
				teacher.setEducation(rs.getString(5));
				teacher.setAddress(rs.getString(6));
				teacher.setPhone(rs.getString(7));
				teacher.setEmail(rs.getString(8));
				teacher.setSubject(rs.getString(9));
				list.add(teacher);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ResourceClose.close(rs, pstmt, conn);
		}
		return list;
	}
	//教师登录验证方法
		public Teacher login(String teacherNo,String password){
			Teacher teacher=null;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try{
				conn=ConnectionFactory.getConnection();
				String sql="select * from Teacher where teacherNo=? and password=?"; 
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, teacherNo);
				pstmt.setString(2, password);
				rs=pstmt.executeQuery();
				while(rs.next()){
					teacher=new Teacher();
					teacher.setTeacherNo(rs.getString(1));
					teacher.setTeacherName(rs.getString(2));
					teacher.setPassword(rs.getString(3));
					teacher.setProfessional(rs.getInt(4));
					teacher.setEducation(rs.getString(5));
					teacher.setAddress(rs.getString(6));
					teacher.setPhone(rs.getString(7));
					teacher.setEmail(rs.getString(8));
					teacher.setSubject(rs.getString(9));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				ResourceClose.close(rs, pstmt, conn);
			}
			return teacher;
		}
}
