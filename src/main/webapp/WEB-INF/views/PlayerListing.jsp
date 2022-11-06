<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>	Players</title>
</head>
<body>
	<h2>Players:</h2>
	<div class="container">
		<br />
		<c:if test="${message != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close">�</a>
		</div>
		</c:if>
	</div>
	<a href="/boards/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Create Board</a>
	<table class="table table-striped">
		<tr>
			<th>Username</th>
			<th>Password</th>			
			<th>Email</th>
		</tr>
		<c:forEach items="${players}" var="player">
			<tr>
				<td><c:out value="${player.username}"/></td>
				<td><c:out value="${player.password}"/></td>
				<td><c:out value="${player.email}"/></td>					
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>