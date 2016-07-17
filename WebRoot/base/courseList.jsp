<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.imut.javabean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>课程管理</title>
<link rel="stylesheet" type="text/css" id="css" href="../style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="../style/style1.css" />
<link rel="stylesheet" type="text/css" id="css" href="../style/style.css" />

<style type="text/css">
<!--
table{border-spacing:1px; border:1px solid #A2C0DA;}
td, th{padding:2px 5px;border-collapse:collapse;text-align:left; font-weight:normal;}
thead tr th{height:50px;background:#B0D1FC;border:1px solid white;}
thead tr th.line1{background:#D3E5FD;}
thead tr th.line4{background:#C6C6C6;}
tbody tr td{height:35px;background:#CBE2FB;border:1px solid white; vertical-align:middle;}
tbody tr td.line4{background:#D5D6D8;}
tbody tr th{height:35px;background: #DFEDFF;border:1px solid white; vertical-align:middle;}
tfoot tr td{height:35px;background:#FFFFFF;border:1px solid white; vertical-align:middle;}
-->
</style>

<script type="text/javascript" src="../js/common.js" ></script>
</head>
<%
	String message=(String)session.getAttribute("message");
	if(message!=null){
%>
	<script type="text/javascript">
		alert("<%=message %>");
	</script>
<%
		session.removeAttribute("message");
	}
%>
<body>
<div id="btm">
<div id="main">

  <div id="header">
    <div id="top"></div>
    <div id="logo">
      <h1>课程管理</h1></div>
    <div id="mainnav">
      <ul>
      <li><a href="../index.jsp">首页</a></li>
      <li><a href="listAllTeacherServlet">基本数据管理</a></li>
      <li><a href="../arrange/listAllArrangeServlet">课程安排管理</a></li>
      <li><a href="#">学生成绩管理</a></li>
      <li><a href="../person/showPersonServlet">个人信息管理</a></li>		  
      </ul>
      <span>
      </span>
    </div>
   </div>
<div id="tabsJ">
  <ul>
    <li><a href="#" title="管理员维护"><span>管理员维护</span></a></li>
    <li><a href="listAllStudentServlet" title="学生信息维护"><span>学生信息维护</span></a></li>
    <li><a href="listAllTeacherServlet" title="教师信息维护"><span>教师信息维护</span></a></li>
    <li><a href="listAllClassServlet" title="班级信息维护"><span>班级信息维护</span></a></li>
    <li><a href="listAllCourseServlet" title="课程信息维护"><span>课程信息维护</span></a></li>
  </ul>
</div>
  <div id="content" align="center"> 
     <div id="center">
	 <BR /><BR />
		<form  method="post" action="checkCourseServlet" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>课程管理-->课程列表显示&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="addCourse.jsp">添加课程</a></h5></td>
						
					</tr>
					
				</thead>
				
				<tr>
					<td colspan="2" width="100%">
						<table width="100%">
							<thead>
							
							<tr>
								<th align="center" class="line1" scope="col" colspan="12">
									<b><font color="red">请输入查询统计条件：</font></b>
								</th>								
							</tr>
							<tr>
								<th width="15%" align="center" class="line1" scope="col" colspan="4">
									<b>课程编号</b>
								  </th>
								<th  align="center" scope="col" colspan="2">
									<%
										String courseNo=(String)request.getAttribute("courseNo");										
									%>
									<input name="courseNo" value="<%=courseNo==null?"":courseNo%>"/>
								</th>
								<th width="15%" align="center" scope="col" colspan="1">
									<b>课程名称</b>
								</th>
								<th  colspan="1" align="center" scope="col">
									<%
										String courseName=(String)request.getAttribute("courseName");										
									%>
									<input name="courseName" value="<%=courseName==null?"":courseName%>"/>
								</th>
								<th align="center" scope="col" colspan="2">
									<b>学时</b>
								</th>
								<th align="center" scope="col" colspan="2">
									<%
										Integer studyTime=(Integer)request.getAttribute("studyTime");										
									%>
									<input name="studyTime" value="<%=studyTime==null?"":studyTime%>"/>
								</th>
							</tr>
							<tr>
								<th align="center" class="line1" scope="col" colspan="4">
									<b>学分</b>
								</th>
								<th align="center" scope="col" colspan="2">
									<%
										Integer grade=(Integer)request.getAttribute("grade");										
									%>
									<input name="grade" value="<%=grade==null?"":grade%>"/>
								</th>
								<th align="center" scope="col" colspan="1">
									<b>课程类别</b>
								</th>
								<th align="center" scope="col" colspan="1">
									<select name="courseType">
										<%
											Integer courseType=(Integer)request.getAttribute("courseType");
											if(courseType!=null&&courseType==0){ 
										%>										
											<option value="">请选择课程类别...</option>
											<option value="0" selected>必修课</option>
											<option value="1">专业必修课</option>
											<option value="2">专业基础课</option>
											<option value="3">选修课</option>
											<option value="4">专业选修课</option>
										<%
											}else if(courseType!=null&&courseType==1){
										%>										
											<option value="">请选择课程类别...</option>
											<option value="0">必修课</option>
											<option value="1" selected>专业必修课</option>
											<option value="2">专业基础课</option>
											<option value="3">选修课</option>
											<option value="4">专业选修课</option>
										<%
											}else if(courseType!=null&&courseType==2){
										%>
											<option value="">请选择课程类别...</option>
											<option value="0">必修课</option>
											<option value="1">专业必修课</option>
											<option value="2" selected>专业基础课</option>
											<option value="3">选修课</option>
											<option value="4">专业选修课</option>
										<%
											}else if(courseType!=null&&courseType==3){
										%>
											<option value="">请选择课程类别...</option>
											<option value="0">必修课</option>
											<option value="1">专业必修课</option>
											<option value="2">专业基础课</option>
											<option value="3" selected>选修课</option>
											<option value="4">专业选修课</option>
										<%
											}else if(courseType!=null&&courseType==4){
										%>
											<option value="">请选择课程类别...</option>
											<option value="0">必修课</option>
											<option value="1">专业必修课</option>
											<option value="2">专业基础课</option>
											<option value="3">选修课</option>
											<option value="4" selected>专业选修课</option>
										<%
											}else if(courseType==null){
										%>
											<option value="" selected>请选择课程类别...</option>
											<option value="0">必修课</option>
											<option value="1">专业必修课</option>
											<option value="2">专业基础课</option>
											<option value="3">选修课</option>
											<option value="4">专业选修课</option>
										<%
											}
										%>
									</select>
								</th>
								<th align="center" scope="col" colspan="2">
									<b>开课学期</b>
								</th>
								<th align="center" scope="col" colspan="2">
									<%
										Integer term=(Integer)request.getAttribute("term");										
									%>
									<input name="term" value="<%=term==null?"":term%>"/>
								</th>
							</tr>
							<tr>
								<th align="center" class="line1" scope="col" colspan="12">
									<input type="submit" value="查询"/>
								</th>								
							</tr>
							
							<tr>
								<th width="20%" align="center" class="line1" scope="col" colspan="3">
									<b>课程编号</b>
								</th>
								<th width="20%" align="center" scope="col" colspan="2">
									<b>课程名称</b>
								</th>
								<th width="8%"  align="center" scope="col" colspan="1">
									<b>学时</b>
								</th>
								<th width="12%"  align="center" scope="col" colspan="1">
									<b>学分</b>
								</th>
								<th width="15%" align="center" scope="col" colspan="2">
									<b>课程类别</b>
								</th>
								<th width="10%" align="center" scope="col" colspan="1">
									<b>开课学期</b>
								</th>
								<th width="15%" align="center" colspan="2">
									<b>操作</b>
								</th>
							</tr>
							</thead>
							
							<tbody>	
							<%
								List<Course> list=(List)request.getAttribute("list");
								Iterator<Course> e=list.iterator();
								while(e.hasNext()){
									Course course=e.next();
									if(course!=null){
							%>					
							<tr>
								<td align="center" colspan="3">
									<a href="showCourseServlet?courseNo=<%=course.getCourseNo()%>"><%=course.getCourseNo()%></a>
								</td>
								<td align="center" colspan="2">
									<%=course.getCourseName()%>
								</td>
								<td width="17%" align="center" colspan="1">
									<%=course.getStudyTime()%>
								</td>
								<td width="10%" align="center" colspan="1">
									<%=course.getGrade()%>
								</td>
								<td align="center" colspan="2">
									<%if(course.getCourseType()==0) {%>必修课<%} %>
									<%if(course.getCourseType()==1) {%>专业必修课<%} %>
									<%if(course.getCourseType()==2) {%>专业基础课<%} %>
									<%if(course.getCourseType()==3) {%>选修课<%} %>
									<%if(course.getCourseType()==4) {%>专业选修课<%} %>									
								</td>
								<td width="9%" align="center" colspan="1">
									<%=course.getTerm()%>
								</td>
								<td width="17%" align="center" colspan="2">
									<a href="deleteCourseServlet?courseNo=<%=course.getCourseNo()%>"><font color=red>删除</font></a> | 
									<a href="showCourseServlet?courseNo=<%=course.getCourseNo()%>"><font color=red>查看/修改</font></a>
								</td>
							</tr>
							<%
									}
								}
							%>							
							
							</tbody>						
							<tbody>	
							<tr>
								<td align="center" colspan="12">
								<%
									Integer pageCount=(Integer)request.getAttribute("pageCount");
									Integer curPage=(Integer)request.getAttribute("curPage");
									if(pageCount!=null&&curPage!=null){
										if(pageCount!=0&&curPage!=1){
								%>
											<a href="listAllCourseServlet?curPage=1"/>首页</a>
											<a href="listAllCourseServlet?curPage=<%=curPage-1 %>"/>前一页</a>
								<%						
										}
								%>
								<%
										if(pageCount==0||curPage==1){
								%>
											首页	 前一页
								<%						
										}
								%>
								<%
										if(pageCount!=0&&curPage!=pageCount){
								%>
											<a href="listAllCourseServlet?curPage=<%=curPage+1 %>">下一页</a>
											<a href="listAllCourseServlet?curPage=<%=pageCount%>"/>尾页</a>
								<%
										}
								%>	
								<%
										if(pageCount==0||curPage==pageCount){
								%>
											下一页 尾页
								<%
										}
								%>	
										&nbsp;
										第<%=curPage %>页/共<%=pageCount%>页
										&nbsp;
								<%
									
									}
								%>
				               </td>
							</tr>
							</tbody>	
							
					  </table>
					</td>
				</tr>
			</table>
		</form>
     </div>
  </div>
  
  <div id="footer">
    <div id="copyright">
       <div id="copy">
       <p align="center">CopyRight&copy;2010</p>
       <p>内蒙古工业大学信息工程学院软件工程系 </p>
        </div>
    </div>
    <div id="bgbottom"></div>
  </div>
  
</div>
</div>
</body>
</html>
