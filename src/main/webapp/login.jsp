<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	    <title>Login</title><meta charset="UTF-8" />
	    <META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
		<META HTTP-EQUIV="expires" CONTENT="0">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath%>css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="<%=basePath%>css/matrix-login.css" />
        <link href="<%=basePath%>font-awesome/css/font-awesome.css" rel="stylesheet" />

    </head>
    <body onLoad="javascript:document.loginform.reset()">
        <div id="loginbox">            
            <form id="loginform"  name="loginform" class="form-vertical" action="/login.do" method="post">
				  <div class="control-group normal_text"> <h3><img src="img/logo.png" alt="Logo" /></h3></div>
	                <div class="control-group">
	                    <div class="controls">
	                        <div class="main_input_box">
	                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input name="name" type="text" placeholder="Username" />
	                        </div>
	                    </div>
	                </div>
	                <div class="control-group">
	                    <div class="controls">
	                        <div class="main_input_box">
	                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input name="paw" type="password" placeholder="Password" />
	                        </div>
	                    </div>
	                </div>
	                <div style="text-align: center;">
                       <span style="color:red;margin:0 auto">${msg }</span>
                    </div>
	                <div class="form-actions">
	                    <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">Lost password?</a></span>
	                    <span class="pull-right"><input type="submit" role="button" value="Login" class="btn btn-success" /></span>
	                </div>
            </form>
<!--             <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a password.</p>
				
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div>
               
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; Back to login</a></span>
                    <span class="pull-right"><a class="btn btn-info"/>Reecover</a></span>
                </div>
            </form> -->
        </div>
        
        <script src="<%=basePath%>js/jquery.min.js"></script>  
        <script src="<%=basePath%>js/matrix.login.js"></script> 
    </body>
</html>
