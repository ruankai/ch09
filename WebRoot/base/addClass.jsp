<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>班级管理</title>
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
<script type="text/javascript" src="../js/calendar.js" ></script>
<script type="text/javascript" src="../js/ajax.js" ></script>
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
		if(isEmpty(document.myForm.classNo.value)){
			alert("班级编号不能为空！");
			document.myForm.classNo.focus();
			return false;
		}
		if(isEmpty(document.myForm.className.value)){
			alert("班级名称不能为空！");
			document.myForm.className.focus();
			return false;
		}
		if(isEmpty(document.myForm.college.value)){
			alert("所属学院不能为空！");
			document.myForm.college.focus();
			return false;
		}
		return true;
	}	
</script>
</head>
<%
	String message=(String)session.getAttribute("message");
	if(message!=null){
%>
		<script type="text/javascript">
			alert("<%=message%>");
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
      <h1>班级管理</h1></div>
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
		<form name="myForm" action="addClassServlet" method="post" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>班级管理-->添加班级</h5></td>						
					</tr>
					
				</thead>
				
				<tr>
					<td colspan="2" width="100%">
						<table width="100%">
							<tbody>
							<tr>
								<td align="right">班级信息录入</td>
								<td>注：带<font color="red"> * </font>号的必须填写</td>
							</tr>
							<tr>
								<td align="right">班级编号:</td>
								<td><input id="classNo" name="classNo" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">班级名称:</td>
								<td><input id="className" name="className" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">所属学院:</td>
								<td><input id="college" name="college" /><font color="red"> * </font></td>
							</tr>
							
							</tbody>						
							
							
							<tr>
								<td align="center" colspan="2">
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
