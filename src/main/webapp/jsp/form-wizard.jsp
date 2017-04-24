<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Form</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/matrix-style.css" />
	<link rel="stylesheet" href="<%=basePath%>css/matrix-media.css" />
	<link href="<%=basePath%>font-awesome/css/font-awesome.css" rel="stylesheet" />
	<script src="<%=basePath%>js/jquery.min.js"></script> 
	<script src="<%=basePath%>js/jquery.ui.custom.js"></script> 
	<script src="<%=basePath%>js/bootstrap.min.js"></script> 
	</head>
	<body>
	
	    <jsp:include page="header.jsp" flush="true"/><!--动态包含-->
	    <jsp:include page="sidebar.jsp" flush="true"/><!--动态包含-->
		<div id="content">
		  <div id="content-header">
		    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#">Form elements</a> <a href="#" class="current">Form wizard</a> </div>
		    <h1>Form wizard</h1>
		  </div>
		  <div class="container-fluid"><hr>
		    <div class="row-fluid">
		      <div class="span12">
		        <div class="widget-box">
		          <div class="widget-title"> <span class="icon"> <i class="icon-pencil"></i> </span>
		            <h5>Form wizard &amp; validation</h5>
		          </div>
		          <div class="widget-content nopadding">
		            <form id="form-wizard" class="form-horizontal" method="post" action="/file/dowmLoadOrd.do">
		              <div id="file" align="center">  
					        <h1><input type="text" name="title" class="textField" value="我的简历"/></h1>  
					        <hr/>  
					        <table>  
					            <tr>  
					                <td class="key">姓名：</td>  
					                <td><input type="text" name="name" class="textField"/></td>  
					                <td class="key">性别：</td>  
					                <td>  
					                    <input type="radio" name="gender" value="男" checked/>男  
					                    <input type="radio" name="gender" value="女" />女  
					                </td>  
					            </tr>  
					            <tr>  
					                <td class="key">联系电话：</td>  
					                <td><input type="text" name="tel" class="textField"/></td>  
					                <td class="key">家庭住址：</td>  
					                <td><input type="text" name="address" class="textField"/></td>  
					            </tr>  
					            <tr>  
					                <td colspan="4" class="key">个人简介：</td>  
					            </tr>  
					            <tr>  
					                <td colspan="4">  
					                    <textarea rows="10" cols="100" name="content" style="width:100%"></textarea>  
					                </td>  
					            </tr>  
					        </table>  
					    </div>  
					    <div align="center" style="margin-top:15px;">  
					        <input type="submit" value="保存Word文档" />  
					    </div>  
		            </form>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
	
<%-- 		<script src="<%=basePath%>js/jquery.validate.js"></script> 
		<script src="<%=basePath%>js/jquery.wizard.js"></script> 
		<script src="<%=basePath%>js/matrix.js"></script>  --%>
		<script src="<%=basePath%>js/matrix.wizard.js"></script>
	</body>
	</html>

