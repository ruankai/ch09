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

<script type="text/javascript" src="../js/common.js" ></script>
</head>
<c:if test="${!(empty sessionScope.message)}">
	<script type="text/javascript">
		alert('<c:out value="${sessionScope.message}"/>');
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
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
		<form  method="post" action="checkArrangeServlet" >
			<table width="800" align="center" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td width="70%"><h5>课程安排管理-->课程安排列表显示&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="addArrange.jsp">添加课程安排</a></h5></td>
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
									<b>安排编号</b>
								</th>
								<th align="center" scope="col" colspan="2">
									<input name="arrangeNo" value="${requestScope.fg }"/>
								</th>
								<th align="center" scope="col" colspan="2">
									<b>安排课程</b>
								</th>
								<th align="center" scope="col" colspan="2">
									<select name="courseNo">
										<option value="">请选择所要安排课程...</option>
										<c:forEach items="${requestScope.courses}" var="course">
											<c:choose>
												<c:when test="${requestScope.courseNo==course.courseNo}">
						          					<option value="${course.courseNo}" selected>${course.courseName}</option>         				
						          				</c:when>
						          				<c:otherwise>
						          					<option value="${course.courseNo}">${course.courseName}</option>
						          				</c:otherwise> 
					          				</c:choose> 
										</c:forEach>
									</select>
								</th>
								<th align="center" scope="col" colspan="2">
									<b>上课班级</b>
								</th>
								<th align="center" scope="col" colspan="2">
									<select name="classNo">
										<option value="">请选择上课班级...</option>
										<c:forEach items="${requestScope.classtbls}" var="classTbl">
											<c:choose>
												<c:when test="${requestScope.classNo==classTbl.classNo}">
						          					<option value="${classTbl.classNo}" selected>${classTbl.className}</option>         				
						          				</c:when>
						          				<c:otherwise>
						          					<option value="${classTbl.classNo}">${classTbl.className}</option>
						          				</c:otherwise> 
					          				</c:choose> 
										</c:forEach>
									</select>
								</th>
							</tr>
							<tr>
								<th align="center" scope="col" colspan="2">
									<b>任课教师</b>
								</th>
								<th align="center" scope="col" colspan="2">
									<select name="teacherNo">
										<option value="">请选择任课教师...</option>
										<c:forEach items="${requestScope.teachers}" var="teacher">
											<c:choose>
												<c:when test="${requestScope.teacherNo==teacher.teacherNo}">
						          					<option value="${teacher.teacherNo}" selected>${teacher.teacherName}</option>         				
						          				</c:when>
						          				<c:otherwise>
						          					<option value="${teacher.teacherNo}">${teacher.teacherName}</option>
						          				</c:otherwise> 
					          				</c:choose> 
										</c:forEach>
									</select>
								</th>
							
								<th align="center" class="line1" scope="col" colspan="8">
									<input type="submit" value="查询"/>
								</th>								
							</tr>
							
							<tr>
								<th width="10%" align="center" class="line1" scope="col" colspan="2">
									<b>安排编号</b>
								</th>
								<th width="10%" align="center" scope="col" colspan="2">
									<b>所上课程</b>
								</th>
								<th width="15%" align="center" scope="col" colspan="2">
									<b>上课班级</b>
								</th>
								<th width="15%" align="center" scope="col" colspan="2">
									<b>任课教师</b>
								</th>
								<th width="15%" align="center" scope="col" colspan="2">
									<b>上课地点</b>
								</th>
								<th width="15%" align="center" colspan="2">
									<b>操作</b>
								</th>
							</tr>
							</thead>
							<tbody>		
							<c:forEach items="${requestScope.list}" var="arrange">						
							<tr>
								<td width="10%" align="center" colspan="2">
									<a href='<c:url value="showArrangeServlet?arrangeNo=${arrange.arrangeNo }"></c:url>'>${arrange.arrangeNo }</a>
								</td>
								<td width="10%" align="center" colspan="2">
									${arrange.course.courseName }
								</td>
								<td width="15%" align="center" colspan="2">
									${arrange.classTbl.className }
								</td>
								<td width="15%" align="center" colspan="2">
									${arrange.teacher.teacherName }
								</td>
								<td width="10%" align="center" colspan="2">
									${arrange.studyRoom }
								</td>
								<td width="15%" align="center" colspan="2">
									<a href='<c:url value="deleteArrangeServlet?arrangeNo=${arrange.arrangeNo }"></c:url>'><font color=red>删除</font></a> | 
									<a href='<c:url value="showArrangeServlet?arrangeNo=${arrange.arrangeNo }"></c:url>'><font color=red>查看/修改</font></a>
								</td>
							</tr>
							</c:forEach>							
							
							</tbody>						
							<tbody>	
							<tr>
								<td align="center" colspan="12">
								<c:if test="${requestScope.pageCount!=0&&requestScope.curPage!=1}">
									<a href='<c:url value="listAllArrangeServlet?curPage=1"/>'>首页</a>
									<a href='<c:url value="listAllArrangeServlet?curPage=${requestScope.curPage-1}"/>'>前一页</a>
								</c:if>
								<c:if test="${requestScope.pageCount==0||requestScope.curPage==1}">首页 前一页</c:if>
								<c:if test="${requestScope.pageCount!=0&&requestScope.curPage!=requestScope.pageCount}">
									<a href='<c:url value="listAllArrangeServlet?curPage=${requestScope.curPage+1}"/>'>下一页</a>
									<a href='<c:url value="listAllArrangeServlet?curPage=${requestScope.pageCount}"/>'>尾页</a>
								</c:if>
								<c:if test="${requestScope.pageCount==0||requestScope.curPage==requestScope.pageCount}">下一页 尾页</c:if>
								&nbsp;
								第${requestScope.curPage}页/共${requestScope.pageCount}页
								&nbsp;
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
