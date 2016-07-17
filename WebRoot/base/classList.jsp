<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.imut.javabean.*"%>
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
		<form  method="post" action="checkClassServlet" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>班级管理-->班级列表显示&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="addClass.jsp">添加班级</a></h5></td>
						
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
								<th align="center" class="line1" scope="col" colspan="3">
									<b>班级编号</b>
								</th>
								<%
									String classNo=(String)request.getAttribute("classNo");									
								%>
								<th align="center" scope="col" colspan="1">
									<input name="classNo" value="<%=classNo==null?"":classNo%>"/>
								</th>
								<th align="center" scope="col" colspan="3">
									<b>班级名称</b>
								</th>
								<%
									String className=(String)request.getAttribute("className");									
								%>
								<th align="center" scope="col" colspan="1">
									<input name="className" value="<%=className==null?"":className%>"/>
								</th>
								<th align="center" scope="col" colspan="3">
									<b>所属学院</b>
								</th>
								<%
									String college=(String)request.getAttribute("college");									
								%>
								<th align="center" scope="col" colspan="1">
									<input name="college" value="<%=college==null?"":college%>"/>
								</th>
							</tr>
							<tr>
								<th align="center" class="line1" scope="col" colspan="12">
									<input type="submit" value="查询"/>
								</th>								
							</tr>
							
							<tr>
								<th width="5%" align="center" class="line1" scope="col" colspan="3">
									<b>班级编号</b>
								</th>
								<th width="10%" align="center" scope="col" colspan="3">
									<b>班级名称</b>
								</th>
								<th width="15%" align="center" scope="col" colspan="3">
									<b>所属学院</b>
								</th>
								<th width="15%" align="center" colspan="3">
									<b>操作</b>
								</th>
							</tr>
							</thead>
							
							<tbody>
							<%
								List<ClassTbl> list=(List)request.getAttribute("list");
								if(list!=null){
									Iterator<ClassTbl> e=list.iterator();
									while(e.hasNext()){
										ClassTbl classTbl=e.next();
							%>												
							<tr>
								<td width="10%" align="center" colspan="3">
									<a href="showClassServlet?classNo=<%=classTbl.getClassNo()%>"><%=classTbl.getClassNo()%></a>
								</td>
								<td width="15%" align="center" colspan="3">
									<%=classTbl.getClassName()%>
								</td>
								<td width="10%" align="center" colspan="3">
									<%=classTbl.getCollege()%> 
								</td>
								<td width="15%" align="center" colspan="3">
									<a href="deleteClassServlet?classNo=<%=classTbl.getClassNo()%>"><font color=red>删除</font></a> | 
									<a href="showClassServlet?classNo=<%=classTbl.getClassNo()%>"><font color=red>查看/修改</font></a>
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
											<a href="listAllClassServlet?curPage=1"/>首页</a>
											<a href="listAllClassServlet?curPage=<%=curPage-1 %>"/>前一页</a>
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
											<a href="listAllClassServlet?curPage=<%=curPage+1 %>">下一页</a>
											<a href="listAllClassServlet?curPage=<%=pageCount%>"/>尾页</a>
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
