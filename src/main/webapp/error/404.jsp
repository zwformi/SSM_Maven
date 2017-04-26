<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv='Refresh' content='3;URL=/index.jsp'>
	<title>404 错误</title>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/matrix-style.css" />
	<link rel="stylesheet" href="<%=basePath%>css/matrix-media.css" />
	<link href="<%=basePath%>font-awesome/css/font-awesome.css" rel="stylesheet" />

</head>

<body class="gray-bg">
   <div id="content" style="margin:0;padding-bottom: 30px;">
	  <div id="content-header">
	    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#">Sample pages</a> <a href="#" class="current">Error</a> </div>
	    <h1>Error 404</h1>
	  </div>
	  <div class="container-fluid">
	    <div class="row-fluid">
	      <div class="span12">
	        <div class="widget-box">
	          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
	            <h5>Error 404</h5>
	          </div>
	          <div class="widget-content">
	            <div class="error_ex">
	              <h1>404</h1>
	              <h3>Opps, You're lost.</h3>
	              <p>We can not find the page you're looking for.</p>
	              <a class="btn btn-warning btn-big"  href="/index.jsp">Back to Home</a> </div>
	              <p>系统将在 <span style="color:red;" id="seconds">3</span> 秒后跳转到首页，或者直接点击 <a href="javascript:history.back()">返回</a></p>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
    
    <script src="<%=basePath%>js/jquery.min.js"></script> 
	<script src="<%=basePath%>js/jquery.ui.custom.js"></script> 
	<script src="<%=basePath%>js/bootstrap.min.js"></script> 
	<script src="<%=basePath%>js/matrix.js"></script>
	<script type="text/javascript">
	var limitTime = $("#seconds").html();
	var siv=self.setInterval("settime()",1000);
	function settime(){
		  if(limitTime==0){
		  clearInterval(siv);
		  return;
	  }
	  $("#seconds").html(--limitTime);
	}
	</script>
</body>
</html>
