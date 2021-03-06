<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>教师管理</title>
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
		if(isEmpty(document.myForm.teacherNo.value)){
			alert("教师编号不能为空！");
			document.myForm.teacherNo.focus();
			return false;
		}
		if(isEmpty(document.myForm.teacherName.value)){
			alert("教师姓名不能为空！");
			document.myForm.teacherName.focus();
			return false;
		}
		if(isEmpty(document.myForm.password.value)){
			alert("密码不能为空！");
			document.myForm.password.focus();
			return false;
		}
		if(document.myForm.professional.value==""){
			alert("请选择教师职称！");
			document.myForm.professional.focus();
			return false;
		}
		if(isEmpty(document.myForm.education.value)){
			alert("学历不能为空！");
			document.myForm.education.focus();
			return false;
		}
		if(isEmpty(document.myForm.address.value)){
			alert("家庭住址不能为空！");
			document.myForm.address.focus();
			return false;
		}
		if(isEmpty(document.myForm.phone.value)){
			alert("电话不能为空！");
			document.myForm.phone.focus();
			return false;
		}
		if(isEmpty(document.myForm.email.value)){
			alert("email不能为空！");
			document.myForm.email.focus();
			return false;
		}
		if(!isEmail(document.myForm.email.value)){
			alert("email格式不正确！");
			document.myForm.email.focus();
			return false;
		}
		if(isEmpty(document.myForm.subject.value)){
			alert("研究方向不能为空！");
			document.myForm.subject.focus();
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
      <h1>教师管理</h1></div>    
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
		<form name="myForm" action="addTeacherServlet" method="post" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>教师管理-->添加教师</h5></td>						
					</tr>
				</thead>
				<tr>
					<td colspan="2" width="100%">
						<table width="100%">
							<tbody>
							<tr>
								<td align="right">教师信息录入</td>
								<td>注：带<font color="red"> * </font>号的必须填写</td>
							</tr>
							<tr>
								<td align="right">教师编号:</td>
								<td><input id="teacherNo" name="teacherNo" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">教师姓名:</td>
								<td><input id="teacherName" name="teacherName" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">登录密码:</td>
								<td><input id="password" type="password" name="password" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">职称:</td>
								<td>
								<select id="professional" name="professional" >
									<option value="">请选择职称...</option>
									<option value="0">教授</option>
									<option value="1">副教授</option>
									<option value="2">讲师</option>
									<option value="3">助教</option>
									<option value="4">其他</option>
								</select>
								<font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">学历:</td>
								<td><input id="education" name="education" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">家庭住址:</td>
								<td><input id="address" name="address" size=60/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">电话:</td>
								<td><input id="phone" name="phone" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">EMAIL:</td>
								<td><input id="email" name="email" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">研究方向:</td>
								<td><input id="subject" name="subject"  size=60/><font color="red"> * </font></td>
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
