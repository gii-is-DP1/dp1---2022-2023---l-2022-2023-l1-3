<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <xtreme:head></xtreme:head>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/list.css">
</head>
<body>

<a style="position: relative;" href="<spring:url value="/home" htmlEscape="true"/>" class="previous"> < Regresar</a>

<table class="minimalistBlack">
<thead>
<tr>
<th>Nombre</th>
<th>Apellido</th>
<th>Usuario</th>
<th>Email</th>
</tr>
</thead>
<tbody>
<c:forEach items="${admins}" var="admin">
    <tr>
        <td>
            <c:out value="${admin.firstName}"/>
        </td>
        <td>
            <c:out value="${admin.lastName}"/>
        </td>
        <td>
            <c:out value="${admin.user.username}"/>    
        </td>
        <td>
            <c:out value="${admin.email}"/>        
        </td>
    </tr>
</c:forEach>
</tbody>
</table>
</body>
