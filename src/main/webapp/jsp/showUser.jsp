<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table col>
	    <tr>
	        <th>用户id</th>
	        <th>用户名</th>
	        <th>用户密码</th>
	        <th>用户年龄</th>
	    </tr>
	    <c:forEach var="user" items="${userList}">
		    <tr>
		      <td><c:out value="${user.id}"/></td>
		      <td><c:out value="${user.userName}"/></td>
		      <td><c:out value="${user.password}" /></td>		      
		      <td><c:out value="${user.age}"/></td>
		    </tr>
		</c:forEach>
	</table>
  </body>
</html>
