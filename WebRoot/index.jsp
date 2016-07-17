<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.imut.javabean.*,com.imut.listener.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>学生成绩管理首页</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<script src="js/main.js" type="text/javascript"></script>
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
      <h1>学生成绩管理</h1></div>
    <div id="mainnav">
      <ul>
      <li><a href="index.jsp">首页</a></li>
      <li><a href="base/listAllTeacherServlet">基本数据管理</a></li>
      <li><a href="arrange/listAllArrangeServlet">课程安排管理</a></li>
      <li><a href="#">学生成绩管理</a></li>
      <li><a href="person/showPersonServlet">个人信息管理</a></li>
      </ul>
     <span id="right">
     	<%
     		User user=(User)session.getAttribute("user");
     		if(user!=null){
     			if(user.getUserType()==0){
     	%>
     			<font color=red>状态：已登录&nbsp;&nbsp;用户类别：管理员&nbsp;&nbsp;用户名：<%=user.getName() %>
     			&nbsp;&nbsp;当前在线人数:<%=CountListener.getLinedNumber() %>
     	<%
     			}else if(user.getUserType()==1){
     	%>
     			<font color=red>状态：已登录&nbsp;&nbsp;用户类别：教师&nbsp;&nbsp;用户名：<%=user.getName() %>
     			&nbsp;&nbsp;当前在线人数:<%=CountListener.getLinedNumber() %>
     	<%
     			}     		
     		}
     	%>     	
     </span></div>
   </div>
  <div id="content">
     <div id="left">
        <div id="tabs0">
             <ul class="menu0" id="menu0">
              <li onmouseover="setTab(0)" class="lisovers">图书A简介</li>
              <li onmouseover="setTab(1)">图书B简介</li>
              <li onmouseover="setTab(2)">图书C简介</li>
              <li onmouseover="setTab(3)">图书D简介</li>
             </ul>
         <div class="main0" id="main0">
          <div class="block">
                      <img src="images/net.jpg" />

            <p>
                   1999年,盖茨撰写了《未来时速:数字神经系统和商务新思维》（
                   Business @ the Speed of Thought: Using a Digital Nervous System）一书，
                   向人们展示了计算机技术是如何以崭新的方式来解决商业问题的。这本书在超过60个国家以25种语言出版。
            </p>

          </div>
          <div>
                       <img src="images/php.jpg" />

             <p>
除了对计算机和软件的热爱之外，盖茨对生物技术也很有兴趣。他是ICOS公司董事会的一员，这是一家专注于蛋白质基体及小分子疗法的公司。他也是很多其它生物技术公司的投资人             </p>

          </div>
          <div>
                      <img src="images/net.jpg" />

            <p>
对于盖茨来说，慈善事业也是非常重要的。他和他的妻子Melinda已经捐赠了近 58亿美元建立了一个基金，支持在全球医疗健康和教育领域的慈善事业，希望随着人类进入21世纪，这些关键领域的科技进步能使全人类都受益            </p>
          </div>
          <div>
                       <img src="images/php.jpg" />

             <p>
到今天为止，盖茨和他的妻子Melinda Gates建立的基金已经将25多亿美元用于了全球的健康事业，将14亿多美元用于改善人们的学习条件，其中包括为盖茨图书馆购置计算机设备、为美国和加拿大的低收入社区的公共图书馆提供互联网培训和互联网访问服务             </p>
          </div>
          </div>
          <div class="clear"></div>
        </div>
        
        <div id="hots">
          <h2>我的操作</h2>
          <ul>
          <li>
           <div>
            <img src="images/a.gif" />
            <a href="login.jsp">我要登录</a>
            <p>登录后可以使用系统进行操作（所有用户均可）</p>
           </div>
          </li>
          <li>
           <div>
            <img src="images/b.gif" />
            <a href="base/listAllTeacherServlet">基础数据管理</a>
            <p>可以对系统数据进行维护（仅管理员可用）</p>
           </div>
          </li>
          <li>
           <div>
            <img src="images/c.gif" />
            <a href="#">学生成绩管理</a>
            <p>可以进行成绩的录入、修改、查询（管理员、教师可用）</p>
           </div>
          </li>
          <li>
           <div>
            <img src="images/d.gif" />
            <a href="person/showPersonServlet">个人信息管理</a>
            <p>可以进行个人信息修改、查询（所有用户均可用）</p>
           </div>
          </li>
          </ul>
        </div> 
         
     </div>
     
     <div id="right">
        <div id="demo">
        <div id="indemo">
        <div id="demo1">
        <a href="#"><img src="images/1.gif" border="0" width="90" height="80"/></a>
        <a href="#"><img src="images/2.gif" border="0" width="90" height="80"/></a>
        <a href="#"><img src="images/3.gif" border="0" width="90" height="80"/></a>
        <a href="#"><img src="images/4.gif" border="0" width="90" height="80" /></a>
        <a href="#"><img src="images/1.gif" border="0" width="90" height="80" /></a>
        <a href="#"><img src="images/2.gif" border="0" width="90" height="80" /></a>
        <a href="#"><img src="images/3.gif" border="0" width="90" height="80"/></a>
        <a href="#"><img src="images/4.gif" border="0" width="90" height="80" /></a>
        </div>
        <div id="demo2"></div>
        </div>
        </div>
      <h2> 校内新闻 </h2>
      <ul>
        <li><a href="#">关于2011年全国硕士研究生入学考试期间四教和科学楼全封闭管理的通知 <font color="red">[2011-01-13]</font></a></li>
        <li><a href="#">关于发放家庭经济困难学生价格临时生活补贴资金的通知  <font color="red">[2011-01-10]</font> </a></li>
        <li><a href="#">中秋佳节慰问学生座谈会举行  <font color="red">[2010-09-25]</font> </a></li>
        <li><a href="#">我校举行2010年度白梅奖学金颁奖大会  <font color="red">[2010-08-19]</font> </a></li>
        <li><a href="#">关于发放2010年第一批生源地贷款的通知  <font color="red">[2010-07-15]</font></a></li>
        <li><a href="#">我校在第35届ACM/ICPC亚洲区预赛中获得佳绩  <font color="red">[2010-07-08]</font></a></li>
        <li><a href="#"> 信息工程学院本科毕业生座谈会举行  <font color="red">[2010-07-05]</font> </a></li>
      </ul>
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
