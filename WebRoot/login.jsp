<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>学生成绩管理-用户登录</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />

<script src="js/main.js" type="text/javascript"></script>
<style type="text/css">
<!--
.STYLE1 {color: #CC6600}
-->
</style>
<script type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
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
		if(isEmpty(document.myForm.loginName.value)){
			alert("登录名不能为空！");
			document.myForm.loginName.focus();
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

</head>
<body onload="MM_preloadImages('images/login-21.gif')">
<div id="btm">
<div id="main">

  <div id="header">
    <div id="top"></div>
    <div id="logo"><h1>用户登录</h1></div>
    <div id="mainnav"><span></span> </div>
   </div>
  
  <div id="content">
     
     <div id="left">
      
       <div id="ltd" align="center">
         <h2>用户登录系统</h2>	     
         <form name="myForm" action="loginServlet" method="post" >
			<table align="center">
				<tr>
					<td width="30%" align="right">
						用户账号：
					</td>
					<td width="70%" align="left" >
						<input type="text" id="loginName" name="loginName" style="width:150px"/>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right">
						用户密码：
					</td>
					<td width="70%" align="left">
						<input type="password" id="password" name="password" style="width:150px"/>
					</td>
				</tr>
				<tr>
					<td width="30%" align="right">
						&nbsp;&nbsp;
					</td>
					<td width="70%" align="left">
						&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  <td colspan="2" align="center"><input type="submit" value="登录" onclick="return check();"/><input type="reset" value="重置"/></td>
				</tr>
				<tr>
					<td width="30%" align="right">
						&nbsp;&nbsp;
					</td>					
				</tr>
				<tr>
					<td width="30%" align="right">
						&nbsp;&nbsp;
					</td>					
				</tr>
				<tr>
					<td width="30%" align="right">
						&nbsp;&nbsp;
					</td>					
				</tr>
				<tr>
					<td width="30%" align="right">
						&nbsp;&nbsp;
					</td>					
				</tr>
				<tr>
					<td width="30%" align="right">
						&nbsp;&nbsp;
					</td>					
				</tr>
			</table>
		  </form> 
       </div>
              
     </div>
     
     
     <div class="clear"></div>
     
  </div>
  
  <div id="footer">
    <div id="copyright">
       <div id="copy">
       <p>CopyRight&copy;2010</p>
       <p>内蒙古工业大学信息工程学院软件工程系</p>
        </div>
    </div>
    <div id="bgbottom"></div>
  </div>
  
</div>
</div>
</body>
</html>
