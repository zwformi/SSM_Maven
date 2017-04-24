<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Chat</title>
    
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
	    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#">Sample pages</a> <a href="#" class="current">Chat option</a> </div>
	    <h1>Chat option</h1>
	  </div>
	  <div class="container-fluid"><hr>
	    <div class="row-fluid">
	      <div class="span12">
	        <div class="widget-box widget-chat">
	          <div class="widget-title"> <span class="icon"> <i class="icon-comment"></i> </span>
	            <h5>Let's do a chat</h5>
	          </div>
	          <div class="widget-content nopadding">
	            <div class="chat-users panel-right2">
	              <div class="panel-title">
	                <h5>Online Users</h5>
	              </div>
	              <div class="panel-content nopadding">
	                  <ul class="contact-list">
	                  <li id="user-Alex" class="online"><a href="#"><img alt="" src="img/demo/av1.jpg" /> <span>Alex</span></a></li>
	                  <li id="user-Linda"><a href="#"><img alt="" src="img/demo/av2.jpg" /> <span>Linda</span></a></li>
	                  <li id="user-John" class="online new"><a href="#"><img alt="" src="img/demo/av3.jpg" /> <span>John</span></a><span class="msg-count badge badge-info">3</span></li>
	                  <li id="user-Mark" class="online"><a href="#"><img alt="" src="img/demo/av4.jpg" /> <span>Mark</span></a></li>
	                  <li id="user-Maxi" class="online"><a href="#"><img alt="" src="img/demo/av5.jpg" /> <span>Maxi</span></a></li>
	                </ul>
	              </div>
	            </div>
	            <div class="chat-content panel-left2">
	              <div class="chat-messages" id="chat-messages">
	                <div id="chat-messages-inner"></div>
	              </div>
	              <div class="chat-message well">
	                <button class="btn btn-success">Send</button>
	                <span class="input-box">
	                <input type="text" name="msg-box" id="msg-box" />
	                </span> </div>
	            </div>
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
	<script src="<%=basePath%>js/matrix.chat.js"></script>
	</body>
	</html>
