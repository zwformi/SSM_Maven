<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Table</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/uniform.css" />
	<link rel="stylesheet" href="<%=basePath%>css/select2.css" />
	<link rel="stylesheet" href="<%=basePath%>css/matrix-style.css" />
	<link rel="stylesheet" href="<%=basePath%>css/matrix-media.css" />
	<link href="<%=basePath%>font-awesome/css/font-awesome.css" rel="stylesheet" />
	</head>
	<body>
	
	    <jsp:include page="header.jsp" flush="true"/><!--动态包含-->
	    <jsp:include page="sidebar.jsp" flush="true"/><!--动态包含-->
	    
		<div id="content">
		  <div id="content-header">
		    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Tables</a> </div>
		    <h1>Tables</h1>
		  </div>
		  <div class="container-fluid">
		    <hr>
		    <div class="row-fluid">
		      <div class="span12">

		        <div class="widget-box">
		          <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
		            <h5>Data table</h5>
		            <span class="exportExl" style="padding: 10px;line-height: 12px;">
		             <button class="btn btn-success">导出excle</button>
		            </span>
		          </div>
		          <div class="widget-content nopadding">
		            <table class="table table-bordered data-table">
		              <thead>
		                <tr>
		                  <th><input type="checkbox" /></th>
		                  <th>用户id</th>
		                  <th>用户名</th>
		                  <th>用户密码</th>
		                  <th>用户年龄</th>
		                </tr>
		              </thead>
		              <tbody id="div_tbody">
			              <script id="UserList" type="text/html">
                          {{each userlist as value i}}
		                   <tr class="gradeX"> 
		                    <td><input type="checkbox" /></td>
		                    <td>{{value.id}}</td> 
		                    <td>{{value.userName}}</td>
		                    <td>{{value.password}}</td>
		                    <td class="center">{{value.age}}</td>
		                   </tr>
                          {{/each}}
                          </script>
		              </tbody>
		            </table>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
	
		<script src="<%=basePath%>js/jquery.min.js"></script> 
		<script src="<%=basePath%>js/jquery.ui.custom.js"></script> 
		<script src="<%=basePath%>js/bootstrap.min.js"></script> 
		<script src="<%=basePath%>js/jquery.uniform.js"></script> 
		<script src="<%=basePath%>js/select2.min.js"></script> 
		<script src="<%=basePath%>js/template.js"></script>
		<script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
		<script src="<%=basePath%>js/matrix.tables.js"></script>		
		</body>
	</html>