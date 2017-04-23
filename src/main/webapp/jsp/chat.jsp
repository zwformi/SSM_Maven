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
	
	<!--Header-part-->
	<div id="header">
	  <h1><a href="dashboard.html">Matrix Admin</a></h1>
	</div>
	<!--close-Header-part--> 
	
	
		<!--top-Header-menu-->
	<div id="user-nav" class="navbar navbar-inverse">
	  <ul class="nav">
	    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">Welcome User</span><b class="caret"></b></a>
	      <ul class="dropdown-menu">
	        <li><a href="#"><i class="icon-user"></i> My Profile</a></li>
	        <li class="divider"></li>
	        <li><a href="#"><i class="icon-check"></i> My Tasks</a></li>
	        <li class="divider"></li>
	        <li><a href="login.html"><i class="icon-key"></i> Log Out</a></li>
	      </ul>
	    </li>
	    <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Messages</span> <span class="label label-important">5</span> <b class="caret"></b></a>
	      <ul class="dropdown-menu">
	        <li><a class="sAdd" title="" href="#"><i class="icon-plus"></i> new message</a></li>
	        <li class="divider"></li>
	        <li><a class="sInbox" title="" href="#"><i class="icon-envelope"></i> inbox</a></li>
	        <li class="divider"></li>
	        <li><a class="sOutbox" title="" href="#"><i class="icon-arrow-up"></i> outbox</a></li>
	        <li class="divider"></li>
	        <li><a class="sTrash" title="" href="#"><i class="icon-trash"></i> trash</a></li>
	      </ul>
	    </li>
	    <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li>
	    <li class=""><a title="" href="<%=basePath%>login.jsp"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
	  </ul>
	</div>
	<!--close-top-Header-menu-->
	<!--start-top-serch-->
	<div id="search">
	  <input type="text" placeholder="Search here..."/>
	  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
	</div>
	<!--close-top-serch-->

	<!--sidebar-menu-->
	<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
	  <ul>
	    <li class="active"><a href="index.jsp"><i class="icon icon-home"></i> <span>Dashboard</span></a> </li>
	    <li> <a href="<%=basePath%>jsp/charts.jsp"><i class="icon icon-signal"></i> <span>Charts &amp; graphs</span></a> </li>
	    <li> <a href="widgets.html"><i class="icon icon-inbox"></i> <span>Widgets</span></a> </li>
	    <li><a href="<%=basePath%>jsp/tables.jsp"><i class="icon icon-th"></i> <span>Tables</span></a></li>
	    <li><a href="grid.html"><i class="icon icon-fullscreen"></i> <span>Full width</span></a></li>
	    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>Forms</span> <span class="label label-important">3</span></a>
	      <ul>
	        <li><a href="form-common.html">Basic Form</a></li>
	        <li><a href="form-validation.html">Form with Validation</a></li>
	        <li><a href="<%=basePath%>jsp/form-wizard.jsp">Form with Wizard</a></li>
	      </ul>
	    </li>
	    <li><a href="buttons.html"><i class="icon icon-tint"></i> <span>Buttons &amp; icons</span></a></li>
	    <li><a href="interface.html"><i class="icon icon-pencil"></i> <span>Eelements</span></a></li>
	    <li class="submenu"> <a href="#"><i class="icon icon-file"></i> <span>Addons</span> <span class="label label-important">5</span></a>
	      <ul>
	        <li><a href="index2.html">Dashboard2</a></li>
	        <li><a href="gallery.html">Gallery</a></li>
	        <li><a href="calendar.html">Calendar</a></li>
	        <li><a href="invoice.html">Invoice</a></li>
	        <li><a href="<%=basePath%>jsp/chat.jsp">Chat option</a></li>
	      </ul>
	    </li>
	    <li class="submenu"> <a href="#"><i class="icon icon-info-sign"></i> <span>Error</span> <span class="label label-important">4</span></a>
	      <ul>
	        <li><a href="error403.html">Error 403</a></li>
	        <li><a href="error404.html">Error 404</a></li>
	        <li><a href="error405.html">Error 405</a></li>
	        <li><a href="error500.html">Error 500</a></li>
	      </ul>
	    </li>
	    <li class="content"> <span>Monthly Bandwidth Transfer</span>
	      <div class="progress progress-mini progress-danger active progress-striped">
	        <div style="width: 77%;" class="bar"></div>
	      </div>
	      <span class="percent">77%</span>
	      <div class="stat">21419.94 / 14000 MB</div>
	    </li>
	    <li class="content"> <span>Disk Space Usage</span>
	      <div class="progress progress-mini active progress-striped">
	        <div style="width: 87%;" class="bar"></div>
	      </div>
	      <span class="percent">87%</span>
	      <div class="stat">604.44 / 4000 MB</div>
	    </li>
	  </ul>
	</div>
	<!--sidebar-menu-->
	
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
