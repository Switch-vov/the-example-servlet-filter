<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>首页</title>
	</head>
	<body>
		<h2>
			<c:choose>
				<c:when test="${!empty loginUser }">
					<h2>恭喜您登录成功！！！</h2>
				</c:when>
				<c:otherwise>
					<a href="login.jsp">登录</a>
				</c:otherwise>
			</c:choose>
		</h2>
	</body>
</html>