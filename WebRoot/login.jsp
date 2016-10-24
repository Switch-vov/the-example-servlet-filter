<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
	</head>
	<body>
		<div class="container-fluid">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
		<div class="container" style="width:100%;height:460px;background:url('${pageContext.request.contextPath}/img/login_bg.jpg');">
			<div class="row">
				<div class="col-md-7">
				</div>
				<div class="col-md-5">
					<div style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
						<font>会员登录</font>USER LOGIN
						<div>&nbsp;</div>
						<%--登录表单start --%>
						
						<form action="${pageContext.request.contextPath}/UserLoginServlet" method="get" class="form-horizontal">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
								</div>
								<c:if test="${!empty msg }">
									<span class="label label-success">${msg }</span>
								</c:if>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-6">
									<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
								</div>
							</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="checkbox">
										<label> 
											<input type="checkbox" name="autoLogin"> 自动登录
										</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										<label> 
											<input type="checkbox" name="rememberme"> 记住用户名
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" width="100" value="登录"
										style="background: url('${pageContext.request.contextPath}/img/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height:35px;width:100px;color:white;">
								</div>
							</div>
						</form>
						<%--登录表单end --%>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>