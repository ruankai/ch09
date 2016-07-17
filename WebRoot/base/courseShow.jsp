<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.imut.javabean.*" %>
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

<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/common.js" ></script>
<script type="text/javascript" src="../js/calendar.js" ></script>
<script type="text/JavaScript">
	/*判断是否为数字*/
	function isNumber(str) {
		var Letters = "1234567890";
		for (var i = 0; i < str.length; i = i + 1) {
			var CheckChar = str.charAt(i);
			if (Letters.indexOf(CheckChar) == -1) {
				return false;
			}
		}
		return true;
	}
	/*判断是否为Email*/
	function isEmail(str) {
		var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
		if (myReg.test(str)) {
			return true;
		}
		return false;
	}
	/*判断是否为空*/
	function isEmpty(value) {
		return /^\s*$/.test(value);
	}
	function check(){
		if(isEmpty(document.myForm.courseName.value)){
			alert("课程名称不能为空！");
			document.myForm.courseName.focus();
			return false;
		}
		if(isEmpty(document.myForm.studyTime.value)){
			alert("课程学时不能为空！");
			document.myForm.studyTime.focus();
			return false;
		}
		if(!isNumber(document.myForm.studyTime.value)){
			alert("课程学时必须是数字！");
			document.myForm.studyTime.focus();
			return false;
		}
		if(isEmpty(document.myForm.grade.value)){
			alert("课程学分不能为空！");
			document.myForm.grade.focus();
			return false;
		}
		if(!isNumber(document.myForm.grade.value)){
			alert("课程学分必须是数字！");
			document.myForm.grade.focus();
			return false;
		}
		if(document.myForm.courseType.value==""){
			alert("请选择课程类别！");
			document.myForm.courseType.focus();
			return false;
		}
		if(isEmpty(document.myForm.term.value)){
			alert("开课学期不能为空！");
			document.myForm.term.focus();
			return false;
		}
		if(!isNumber(document.myForm.term.value)){
			alert("开课学期必须是数字！");
			document.myForm.term.focus();
			return false;
		}
		return true;
	}	
</script>
</head>
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
		<form action="updateCourseServlet" method="post" name="myForm" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>课程管理-->课程信息查看/修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="listAllCourseServlet">&nbsp;&nbsp;&nbsp;返回</a></h5></td>
						
					</tr>
					
				</thead>
				
				<tr>
					<td colspan="2" width="100%">
						<table width="100%">
							<%
								Course course=(Course)request.getAttribute("course");
								if(course!=null){
							%>
							<tbody>
							<tr>
								<td align="right">课程信息</td>
								<td>注：带<font color="red"> * </font>号的必须填写</td>
							</tr>
							<tr>
								<td align="right">课程编号:</td>
								<td><input disabled="disabled" name="courseNo"  value="<%=course.getCourseNo()%>"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">课程名称:</td>
								<td><input id="courseName" name="courseName"  value="<%=course.getCourseName()%>"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">学时:</td>
								<td><input id="studyTime" name="studyTime"  value="<%=course.getStudyTime()%>"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">学分:</td>
								<td><input id="grade" name="grade"  value="<%=course.getGrade()%>"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">课程类别:</td>
								<td>
								<select id="courseType" name="courseType" >
									<%
										Integer courseType=course.getCourseType();
										if(courseType==0){
									%>									
											<option value="">请选择课程类别...</option>
											<option value="0" selected>必修课</option>
											<option value="1">专业必修课</option>
											<option value="2">专业基础课</option>
											<option value="3">选修课</option>
											<option value="4">专业选修课</option>
									<%
										}else if(courseType==1){
									%>										
											<option value="">请选择课程类别...</option>
											<option value="0">必修课</option>
											<option value="1" selected>专业必修课</option>
											<option value="2">专业基础课</option>
											<option value="3">选修课</option>
											<option value="4">专业选修课</option>
									<%
										}else if(courseType==2){
									%>
											<option value="">请选择课程类别...</option>
											<option value="0">必修课</option>
											<option value="1">专业必修课</option>
											<option value="2" selected>专业基础课</option>
											<option value="3">选修课</option>
											<option value="4">专业选修课</option>
									<%
										}else if(courseType==3){
									%>
											<option value="">请选择课程类别...</option>
											<option value="0">必修课</option>
											<option value="1">专业必修课</option>
											<option value="2">专业基础课</option>
											<option value="3" selected>选修课</option>
											<option value="4">专业选修课</option>
									<%
										}else if(courseType==4){
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
								<font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">开课学期:</td>
								<td><input id="term" name="term"  value="<%=course.getTerm()%>"/><font color="red"> * </font></td>
							</tr>
							</tbody>						
							
							
							<tr>
								<td align="center" colspan="2">
									<input type="hidden" name="courseNo" value="<%=course.getCourseNo()%>" style="width:0;height:0" />
									<input type="submit" value="提交" onclick="return check();"/>
									<input type="reset" value="重置"/>
				                </td>
							</tr>
							<%
								}
							%>
								
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
       <p>内蒙古工业大学信息工程学院软件系 </p>
        </div>
    </div>
    <div id="bgbottom"></div>
  </div>
  
</div>
</div>
</body>
</html>
