<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.imut.javabean.*" %>
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
	<form  method="post" action="checkTeacherServlet" >
	<table width="800" align="center" cellpadding="0" cellspacing="0">
		<thead>
		<tr>
			<td width="70%"><h5>教师管理-->教师列表显示&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="addTeacher.jsp">添加教师</a></h5></td>						
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
						<b>教师编号</b>
					</th>
					<%
						String teacherNo=(String)request.getAttribute("teacherNo");						
					%>
					<th align="center" scope="col" colspan="1">
						<input name="teacherNo" value="<%=teacherNo==null?"":teacherNo%>"/>
					</th> 									
					<th align="center" scope="col" colspan="3">
						<b>教师姓名</b>
					</th>
					<%
						String teacherName=(String)request.getAttribute("teacherName");						
					%>
					<th align="center" scope="col" colspan="1">
						<input name="teacherName" value="<%=teacherName==null?"":teacherName%>"/>
					</th>									
					<th align="center" scope="col" colspan="3">
						<b>职称</b>
					</th>								
					<th align="center" scope="col" colspan="1">	
						<select name="professional">
						<%
							Integer professional=(Integer)request.getAttribute("professional");
							if(professional!=null&&professional==0){ 
						%>
								<option value="">请选择职称...</option>
								<option value="0" selected>教授</option>
								<option value="1">副教授</option>
								<option value="2">讲师</option>
								<option value="3">助教</option>
								<option value="4">其他</option>
						<%
							}else if(professional!=null&&professional==1){ 
						%>
								<option value="">请选择职称...</option>
								<option value="0">教授</option>
								<option value="1" selected>副教授</option>
								<option value="2">讲师</option>
								<option value="3">助教</option>
								<option value="4">其他</option>
						<%
							}else if(professional!=null&&professional==2){ 
						%>
								<option value="">请选择职称...</option>
								<option value="0">教授</option>
								<option value="1">副教授</option>
								<option value="2" selected>讲师</option>
								<option value="3">助教</option>
								<option value="4">其他</option>
						<%
							}else if(professional!=null&&professional==3){ 
						%>
								<option value="">请选择职称...</option>
								<option value="0">教授</option>
								<option value="1">副教授</option>
								<option value="2">讲师</option>
								<option value="3" selected>助教</option>
								<option value="4">其他</option>
						<%
							}else if(professional!=null&&professional==4){ 
						%>
								<option value="">请选择职称...</option>
								<option value="0">教授</option>
								<option value="1">副教授</option>
								<option value="2">讲师</option>
								<option value="3">助教</option>
								<option value="4" selected>其他</option>
						<%
							}else if(professional==null){ 
						%>
								<option value="" selected>请选择职称...</option>
								<option value="0">教授</option>
								<option value="1">副教授</option>
								<option value="2">讲师</option>
								<option value="3">助教</option>
								<option value="4">其他</option>
						<%
								}
						%> 	
						</select>	
						</th>
					</tr>
					<tr>
						<th align="center" class="line1" scope="col" colspan="3">
							<b>联系电话</b>
						</th>
						<%
							String phone=(String)request.getAttribute("phone");							
						%>
						<th align="center" scope="col" colspan="1">
							<input name="phone" value="<%=phone==null?"":phone%>"/>
						</th>
						<th align="center" scope="col" colspan="3">
							<b>研究方向</b>
						</th>
						<%
							String subject=(String)request.getAttribute("subject");							
						%>
						<th align="center" scope="col" colspan="1">
							<input name="subject" value="<%=subject==null?"":subject%>"/>
						</th>
						<th align="center" class="line1" scope="col" colspan="4">
							<input type="submit" value="查询"/>
						</th>
					</tr>
					<tr>
						<th width="15%" align="center" class="line1" scope="col" colspan="2">
							<b>教师姓名</b>
						</th>
						<th width="15%" align="center" scope="col" colspan="2">
							<b>教师职称</b>
						</th>
						<th width="15%" align="center" scope="col" colspan="2">
							<b>联系电话</b>
						</th>
						<th width="15%" align="center" scope="col" colspan="2">
							<b>学历</b>
						</th>
						<th width="15%" align="center" scope="col" colspan="2">
							<b>Email</b>
						</th>
						<th width="15%" align="center" colspan="2">
							<b>操作</b>
						</th>
					</tr>
				</thead>
			<tbody>	
				<%
					ArrayList list=(ArrayList)request.getAttribute("list");
					if(list!=null){
						Iterator e=list.iterator();
						while(e.hasNext()){
							Teacher teacher=(Teacher)e.next();
									
				%>
						<tr>
							<td width="15%" align="center" colspan="2">
								<a href="showTeacherServlet?teacherNo=<%=teacher.getTeacherNo() %>"><%=teacher.getTeacherName() %></a>
							</td>
							<td width="15%" align="center" colspan="2">
								<%	if(teacher.getProfessional()==0)%>教授
								<%	if(teacher.getProfessional()==1)%>副教授
								<%	if(teacher.getProfessional()==2)%>讲师
								<%	if(teacher.getProfessional()==3)%>助教
								<%	if(teacher.getProfessional()==4)%>其他									
							</td>
							<td width="15%" align="center" colspan="2">
								<%=teacher.getPhone() %>
							</td>
							<td width="15%" align="center" colspan="2">
								<%=teacher.getEducation() %>
							</td>
							<td width="15%" align="center" colspan="2">
								<%=teacher.getEmail() %>
							</td>
							<td width="15%" align="center" colspan="2">
								<a href="deleteTeacherServlet?teacherNo=<%=teacher.getTeacherNo()%>"><font color=red>删除</font></a> | 
								<a href="showTeacherServlet?teacherNo=<%=teacher.getTeacherNo()%>"><font color=red>查看/修改</font></a>
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
								<a href="listAllTeacherServlet?curPage=1"/>首页</a>
								<a href="listAllTeacherServlet?curPage=<%=curPage-1 %>"/>前一页</a>
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
								<a href="listAllTeacherServlet?curPage=<%=curPage+1 %>">下一页</a>
								<a href="listAllTeacherServlet?curPage=<%=pageCount%>"/>尾页</a>
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