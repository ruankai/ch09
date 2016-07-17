<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.imut.javabean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理员管理</title>
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
			alert("姓名不能为空！");
			document.myForm.name.focus();
			return false;
		}
		if(isEmpty(document.myForm.password.value)){
			alert("密码不能为空！");
			document.myForm.password.focus();
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
      <h1>etu管理员管理</h1></div>
    <div id="mainnav">
      <ul>
      <li><a href="../index.jsp">首页</a></li>
      <li><a href="../base/listAllTeacherServlet">基本数据管理</a></li>
      <li><a href="../arrange/listAllArrangeServlet">课程安排管理</a></li>
      <li><a href="#">学生成绩管理</a></li>
      <li><a href="showPersonServlet">个人信息管理</a></li>			  
      </ul>
      <span>
      
      </span>
    </div>
   </div>
   <div id="content"  align="center">
   	 <div id="center">	     
	 <BR /><div align="center"><br /></div>
		<form action="#" method="post" name="myForm" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>管理员个人信息管理-->管理员个人信息查看/修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5></td>						
					</tr>					
				</thead>
				
				<tr>
					<td colspan="2" width="100%">
						<table width="100%">
							<%
								Admin admin=(Admin)request.getAttribute("admin");
								if(admin!=null){
							%>
							<tbody>
							<tr>
								<td align="right">管理员信息</td>
								<td>注：带<font color="red"> * </font>号的必须填写</td>
							</tr>
							<tr>
								<td align="right">管理员登录名:</td>
								<%
									if(admin.getLoginName()==null){
										admin.setLoginName("");
									}									
								%>
								<td><input disabled="disabled" name="loginName" value="<%=admin.getLoginName()%>"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">姓名:</td>
								<%
									if(admin.getName()==null){
										admin.setName("");
									}									
								%>
								<td><input id="name" name="name"  value="<%=admin.getName()%>"/><font color="red"> * </font></td>
							</tr>
							<tr>
								<td align="right">密码:</td>
								<%
									if(admin.getPassword()==null){
										admin.setPassword("");
									}									
								%>
								<td><input id="password" type="password" name="password"  value="<%=admin.getPassword()%>"/><font color="red"> * </font></td>
							</tr>
							</tbody>							
							<tr>
								<td align="center" colspan="2">
									<input type="hidden" name="loginName" value="<%=admin.getLoginName()%>" style="width:0;height:0" />
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
       <p>内蒙古工业大学信息工程学院软件工程系 </p>
        </div>
    </div>
    <div id="bgbottom"></div>
  </div>  
</div>
</div>
</body>
</html>
