<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.imut.javabean.*,java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>课程安排管理</title>
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
		if(document.myForm.courseNo.value==""){
			alert("请选择所要安排的课程！");
			document.myForm.courseNo.focus();
			return false;
		}
		if(document.myForm.classNo.value==""){
			alert("请选择所要安排的班级！");
			document.myForm.classNo.focus();
			return false;
		}
		if(document.myForm.teacherNo.value==""){
			alert("请选择任课教师！");
			document.myForm.teacherNo.focus();
			return false;
		}
		if(isEmpty(document.myForm.studyRoom.value)){
			alert("上课教室不能为空！");
			document.myForm.studyRoom.focus();
			return false;
		}
		return true;
	}	
</script>
</head>
<%
	//获得班级列表（在下拉框中使用）
	ClassDBAccess db1=new ClassDBAccess();
	ICourse db2=new CourseImpl();
	TeacherDBAccess db3=new TeacherDBAccess();
	List classtbls=db1.findAllClassTbl();
	List courses=db2.findAllCourse();
	List teachers=db3.findAllTeacher();
	request.setAttribute("classtbls",classtbls);
	request.setAttribute("courses",courses);
	request.setAttribute("teachers",teachers);
%>
<body>
<div id="btm">
<div id="main">
  <div id="header">
    <div id="top"></div>
    <div id="logo">
      <h1>课程安排管理</h1></div>
    <div id="mainnav">
      <ul>
      <li><a href="../index.jsp">首页</a></li>
      <li><a href="../base/listAllTeacherServlet">基本数据管理</a></li>
      <li><a href="listAllArrangeServlet">课程安排管理</a></li>
      <li><a href="#">学生成绩管理</a></li>
      <li><a href="../person/showPersonServlet">个人信息管理</a></li>
      </ul>
      <span>      
      </span>
    </div>
   </div>
  <div id="content" align="center"> 
     <div id="center">
	 <BR /><BR />
		<form action="updateArrangeServlet" method="post" name="myForm" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>课程安排管理-->课程安排信息查看/修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="listAllArrangeServlet">&nbsp;&nbsp;&nbsp;返回</a></h5></td>
					</tr>
				</thead>
				<tr>
					<td colspan="2" width="100%">
						<table width="100%">
							<tbody>
							<tr>
								<td align="right">课程安排信息</td>
								<td>注：带<font color="red"> * </font>号的必须填写</td>
							</tr>
							<tr>
								<td align="right">安排编号:</td>
								<td><input disabled="disabled" name="arrangeNo" value="${requestScope.arrange.arrangeNo }"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">所上课程:</td>
								<td>
									<select id="courseNo" name="courseNo">
										<option value="">请选择所上课程..</option>
										<c:forEach items="${requestScope.courses}" var="course">
											<c:choose>
												<c:when test="${course.courseNo==requestScope.arrange.course.courseNo}">
						          					<option value="${course.courseNo}" selected>${course.courseName}</option>
						          				</c:when>
						          				<c:otherwise>
						          					<option value="${course.courseNo}">${course.courseName}</option>
						          				</c:otherwise> 
					          				</c:choose>
										</c:forEach>
									</select>
								<font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">上课班级:</td>
								<td>
									<select id="classNo" name="classNo">
										<option value="">请选择上课班级..</option>
										<c:forEach items="${requestScope.classtbls}" var="clazz">
											<c:choose>
												<c:when test="${clazz.classNo==requestScope.arrange.classTbl.classNo}">
						          					<option value="${clazz.classNo}" selected>${clazz.className}</option>         				
						          				</c:when>
						          				<c:otherwise>
						          					<option value="${clazz.classNo}">${clazz.className}</option>
						          				</c:otherwise>
					          				</c:choose>
										</c:forEach>
									</select>
								<font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">任课教师:</td>
								<td>
									<select id="teacherNo" name="teacherNo">
										<option value="">请选择任课教师..</option>
										<c:forEach items="${requestScope.teachers}" var="teacher">
											<c:choose>
												<c:when test="${teacher.teacherNo==requestScope.arrange.teacher.teacherNo}">
						          					<option value="${teacher.teacherNo}" selected>${teacher.teacherName}</option>
						          				</c:when>
						          				<c:otherwise>
						          					<option value="${teacher.teacherNo}">${teacher.teacherName}</option>
						          				</c:otherwise>
					          				</c:choose>
										</c:forEach>
									</select>
								<font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">上课地点:</td>
								<td><input id="studyRoom" name="studyRoom"  value="${requestScope.arrange.studyRoom }"/><font color="red"> * </font></td>
							</tr>
							</tbody>
							<tr>
								<td align="center" colspan="2">
									<input type="hidden" name="arrangeNo" value=${requestScope.arrange.arrangeNo } style="width:0;height:0" />
									<input type="submit" value="提交" onclick="return check();"/>
									<input type="reset" value="重置"/>
				                </td>
							</tr>
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
