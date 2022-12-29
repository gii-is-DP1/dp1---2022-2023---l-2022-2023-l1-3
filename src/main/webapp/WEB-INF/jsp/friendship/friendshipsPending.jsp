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

    <a style="position: relative;" href="<spring:url value="/friends" htmlEscape="true"/>" class="previous"> < Regresar</a>

<h1>Solicitudes de amistad recibidas</h1>
<div>
    <table class="minimalistBlack">
    <thead>
    <tr>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Usuario</th>
    <th>Aceptar/Rechazar</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${friendsPending}" var="friend">
        <tr>
            <td>
                <c:out value="${friend.firstName}"/>
            </td>
            <td>
                <c:out value="${friend.lastName}"/>
            </td>
            <td>
                <c:out value="${friend.user.username}"/>
            </td>
            <td>
                <a class="button" href = "<spring:url value="/friends/pendingFriendships/${friend.user.username}"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                licorice-64px">Ver usuario</div></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
    </div>
</body>