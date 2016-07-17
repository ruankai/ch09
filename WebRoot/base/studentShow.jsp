<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.imut.javabean.*,java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>学生管理</title>
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
		if(isEmpty(document.myForm.name.value)){
			alert("学生姓名不能为空！");
			document.myForm.name.focus();
			return false;
		}
		if(isEmpty(document.myForm.password.value)){
			alert("密码不能为空！");
			document.myForm.password.focus();
			return false;
		}
		if(isEmpty(document.myForm.phone.value)){
			alert("电话不能为空！");
			document.myForm.phone.focus();
			return false;
		}
		if(isEmpty(document.myForm.address.value)){
			alert("家庭住址不能为空！");
			document.myForm.address.focus();
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
		if(document.myForm.classNo.value==""){
			alert("请选择学生所在班级！");
			document.myForm.classNo.focus();
			return false;
		}
		return true;
	}	
</script>
</head>
<%
	//获得班级列表（在下拉框中使用）
	ClassDBAccess dbAccess=new ClassDBAccess();
	List classtbls=dbAccess.findAllClassTbl();
	request.setAttribute("classtbls",classtbls);
%>
<body>
<div id="btm">
<div id="main">
  <div id="header">
    <div id="top"></div>
    <div id="logo">
      <h1>学生管理</h1></div>
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
		<form action="updateStudentServlet" method="post" name="myForm" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>学生管理-->学生信息查看/修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="listAllStudentServlet">&nbsp;&nbsp;&nbsp;返回</a></h5></td>
					</tr>
				</thead>				
				<tr>
					<td colspan="2" width="100%">
						<table width="100%">
							<tbody>
							<tr>
								<td align="right">学生信息</td>
								<td>注：带<font color="red"> * </font>号的必须填写</td>
							</tr>
							<tr>
								<td align="right">学号:</td>
								<td><input disabled="disabled" name="studentNo" value="${requestScope.student.studentNo }"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">姓名:</td>
								<td><input id="name" name="name"  value="${requestScope.student.name }"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">密码:</td>
								<td><input id="password" type="password" name="password"  value="${requestScope.student.password }"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">电话:</td>
								<td><input id="phone" name="phone" value="${requestScope.student.phone }" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">家庭住址:</td>
								<td><input id="address" name="address" value="${requestScope.student.address }" size=60/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">EMAIL:</td>
								<td><input id="email" name="email" value="${requestScope.student.email }" /><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">所在班级:</td>
								<td>
									<select id="classNo" name="classNo">
										<option value="">请选择所在班级..</option>										
										<%											
											List<ClassTbl> list1=(List)request.getAttribute("classtbls");
											Student student=(Student)request.getAttribute("student");
											String classNo=student.getClassTbl().getClassNo();											
											if(list1!=null){
												Iterator<ClassTbl> it=list1.iterator();
												while(it.hasNext()){												
													ClassTbl classtbl=it.next();													
													request.setAttribute("classtbl", classtbl);
													if(classtbl.getClassNo().equals(classNo)){														
										%>
														<option value="${classtbl.classNo }" selected="selected">${classtbl.className }</option>
										<%
													}else{													
										%>
														<option value="${classtbl.classNo }">${classtbl.className }</option>
										<%
													}
												}
											}
										%>
										
									</select>
								<font color="red"> * </font></td>
							</tr>
							</tbody>						
							<tr>
								<td align="center" colspan="2">
									<input type="hidden" name="studentNo" value=${requestScope.student.studentNo } style="width:0;height:0" />
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
