<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <style>.error{color:red;margin-left:auto;margin-right:auto;}</style>

    <title>图书管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/sbadmin/sb-admin/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${ctx}/sbadmin/sb-admin/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctx}/sbadmin/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${ctx}/sbadmin/sb-admin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">图书管理系统</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" id="loginform" action="${ctx}/user/login.action" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input id="username" name="username" class="form-control" placeholder="用户名" name="email" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input id="password" name="password" class="form-control" placeholder="密码" name="password" type="password" value="">
                                </div>
                                <<div class="form-group">
                                    <!-- <input class="form-control" placeholder="验证码" name="randcode" type="" value=""> -->
                                    <input id="randomcode" name="randomcode" placeholder="验证码" size="8" />
                                </div> 
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">记住我
                                    </label>
                                    <imgid="randomcode_img" src="${ctx}/validatecode.jsp" title="看不清可单击图片刷新" alt=""
								onclick="this.src='${ctx}/validatecode.jsp?id='+Math.random();" width="56" height="20" align='absMiddle' />
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <div class="error">${error}</div>
                                <a href="index.html" class="btn btn-lg btn-success btn-block" onclick="login()">登陆</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="${ctx}/sbadmin/sb-admin/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/sbadmin/sb-admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${ctx}/sbadmin/sb-admin/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${ctx}/sbadmin/sb-admin/dist/js/sb-admin-2.js"></script>
	<script type="text/javascript">
	
	function loginsubmit(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		
		alert(username+password);
		document.getElementById("loginform").submit();
		
	}
	
	function login(){
		var username = $("#username").val();
		$("#loginform").submit();
	}
	
	</script>
</body>
</html>