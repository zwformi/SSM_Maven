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
		            <form id="form-wizard" class="form-horizontal" method="post">
		              <div id="form-wizard-1" class="step">
		                <div class="control-group">
		                  <label class="control-label">Username</label>
		                  <div class="controls">
		                    <input id="username" type="text" name="username" />
		                  </div>
		                </div>
		                <div class="control-group">
		                  <label class="control-label">Password</label>
		                  <div class="controls">
		                    <input id="password" type="password" name="password" />
		                  </div>
		                </div>
		                <div class="control-group">
		                  <label class="control-label">Confirm Password</label>
		                  <div class="controls">
		                    <input id="password2" type="password" name="password2" />
		                  </div>
		                </div>
		              </div>
		              <div id="form-wizard-2" class="step">
		                <div class="control-group">
		                  <label class="control-label">Email</label>
		                  <div class="controls">
		                    <input id="email" type="text" name="email" />
		                  </div>
		                </div>
		                <div class="control-group">
		                  <label class="control-label">EULA</label>
		                  <div class="controls">
		                    <input id="eula" type="checkbox" name="eula" />
		                  </div>
		                </div>
		              </div>
		              <div class="form-actions">
		                <input id="back" class="btn btn-primary" type="reset" value="Back" />
		                <input id="next" class="btn btn-primary" type="submit" value="Next" />
		                <div id="status"></div>
		              </div>
		              <div id="submitted"></div>
		            </form>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
	
		<script src="<%=basePath%>js/jquery.min.js"></script> 
		<script src="<%=basePath%>js/jquery.ui.custom.js"></script> 
		<script src="<%=basePath%>js/bootstrap.min.js"></script> 
		<script src="<%=basePath%>js/jquery.validate.js"></script> 
		<script src="<%=basePath%>js/jquery.wizard.js"></script> 
		<script src="<%=basePath%>js/matrix.js"></script> 
		<script src="<%=basePath%>js/matrix.wizard.js"></script>
	</body>
	</html>

