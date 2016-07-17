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

<script type="text/javascript" src="../js/common.js" ></script>
</head>
<%
	String message=(String)session.getAttribute("message");
	if(message!=null){
%>
	<script type="text/javascript">
		alert("${sessionScope.message}");
	</script>
<%
		session.removeAttribute("message");
	}
%>
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
      <li><a href="listAllAdminServlet">基本数据管理</a></li>
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
<form  method="post" action="checkStudentServlet" >
	<table width="800" align="center" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<td width="70%"><h5>学生管理-->学生列表显示&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="addStudent.jsp">添加学生</a></h5></td>
				
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
						<th align="center" class="line1" scope="col" colspan="2">
							<b>学号</b>
						</th>
						<th align="center" scope="col" colspan="2">
							<input name="studentNo" value="${requestScope.studentNo }"/>
						</th>
						<th align="center" scope="col" colspan="2">
							<b>姓名</b>
						</th>
						<th align="center" scope="col" colspan="2">
							<input name="name" value="${requestScope.name }"/>
						</th>
						<th align="center" scope="col" colspan="2">
							<b>所在班级</b>
						</th>
						<th align="center" scope="col" colspan="2">
							<select name="classNo">
								<option value="">请选择所在班级...</option>
								<%
									List<ClassTbl> list1=(List)request.getAttribute("classtbls");
									String classNo=(String)request.getAttribute("classNo");
									if(list1!=null){
										Iterator<ClassTbl> it=list1.iterator();
										while(it.hasNext()){
											ClassTbl classtbl=it.next();
											if(classNo==classtbl.getClassNo()){
												request.setAttribute("classtbl", classtbl);
								%>
												<option value="${classtbl.classNo}" selected>${classtbl.className}</option> 
								<%
											}else{
												request.setAttribute("classtbl", classtbl);
								%>
												<option value="${classtbl.classNo}">${classtbl.className}</option>
								<%
											}
										}
									}
								%>
								
							</select>
						</th>
					</tr>
					<tr>
						<th align="center" class="line1" scope="col" colspan="12">
							<input type="submit" value="查询"/>
						</th>								
					</tr>
					
					<tr>
						<th width="10%" align="center" class="line1" scope="col" colspan="2">
							<b>学号</b>
						</th>
						<th width="10%" align="center" scope="col" colspan="2">
							<b>姓名</b>
						</th>
						<th width="15%" align="center" scope="col" colspan="2">
							<b>联系电话</b>
						</th>
						<th width="15%" align="center" scope="col" colspan="2">
							<b>Email</b>
						</th>
						<th width="15%" align="center" scope="col" colspan="2">
							<b>所在班级</b>
						</th>
						<th width="15%" align="center" colspan="2">
							<b>操作</b>
						</th>
					</tr>
					</thead>
					<tbody>	
					<%
						List<Student> list2=(List)request.getAttribute("list");
						if(list2!=null){
							Iterator<Student> it=list2.iterator();
							while(it.hasNext()){
								Student student=it.next();
								request.setAttribute("student", student);
					%>
					<tr>
						<td width="10%" align="center" colspan="2">
							<a href="showStudentServlet?studentNo=${student.studentNo }">${student.studentNo }</a>
						</td>
						<td width="10%" align="center" colspan="2">
							${student.name }
						</td>
						<td width="15%" align="center" colspan="2">
							${student.phone }
						</td>
						<td width="15%" align="center" colspan="2">
							${student.email }
						</td>
						<td width="10%" align="center" colspan="2">
							${student.classTbl.className }
						</td>
						<td width="15%" align="center" colspan="2">
							<a href="deleteStudentServlet?studentNo=${student.studentNo }"><font color=red>删除</font></a> | 
							<a href="showStudentServlet?studentNo=${student.studentNo }"><font color=red>查看/修改</font></a>
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
							if((Integer)request.getAttribute("pageCount")!=null&&(Integer)request.getAttribute("curPage")!=null){
								if((Integer)request.getAttribute("pageCount")!=0&&(Integer)request.getAttribute("curPage")!=1){
						%>
									<a href="listAllStudentServlet?curPage=1">首页</a>
									<a href="listAllStudentServlet?curPage=${requestScope.curPage-1}">前一页</a>
						<%
								}
								if((Integer)request.getAttribute("pageCount")==0||(Integer)request.getAttribute("curPage")==1){
						%>
									首页 前一页
						<%
								}
								if((Integer)request.getAttribute("pageCount")!=0&&(Integer)request.getAttribute("curPage")!=(Integer)request.getAttribute("pageCount")){
						%>
									<a href="listAllStudentServlet?curPage=${requestScope.curPage+1}">下一页</a>
									<a href="listAllStudentServlet?curPage=${requestScope.pageCount}">尾页</a>
						<%
								}if((Integer)request.getAttribute("pageCount")==0||(Integer)request.getAttribute("curPage")==(Integer)request.getAttribute("pageCount")){
						%>
									下一页 尾页
						<%
								}
						%>
							&nbsp;
							第${requestScope.curPage}页/共${requestScope.pageCount}页
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
       <p>内蒙古工业大学信息工程学院计算机系 </p>
        </div>
    </div>
    <div id="bgbottom"></div>
  </div>
  
</div>
</div>
</body>
</html>
