<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <xtreme:head></xtreme:head>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/form.css">
</head>
<body>
    <a style="position: absolute;" href="<spring:url value="/home" htmlEscape="true"/>" class="previous"> < Regresar</a>

    <h1>HA GANADO LA PARTIDA</h1>
    Foto de perfil: <img src="${player.picProfile}" width="150" height="132">
    <h1>firstName: <c:out value = "${player.firstName}"/></h1>
    <h1>lastName: <c:out value = "${player.lastName}"/></h1>
    <h1>Username: <c:out value = "${player.user.username}"/></h1>
    <h2>Email: <c:out value = "${player.email}"/></h2>
</body>