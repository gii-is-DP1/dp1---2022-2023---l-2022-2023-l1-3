<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/form.css">
    <title>Xtreme Parchis&Oca</title>
    <link rel="apple-touch-icon" sizes="180x180" href="/resources/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/resources/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/resources/favicon/favicon-16x16.png">
    <link rel="manifest" href="/resources/favicon/site.webmanifest">

</head>
<body>
    <a style="position: absolute;" href="<spring:url value="/players/${player.user.username}" htmlEscape="true"/>" class="previous"> < Regresar</a>
    <c:if test = "${isCurrentUser}">
    <form:form modelAttribute="player" class="form-horizontal">
        <span class="help-inline"><c:out value="${message}"/></span>
        <xtreme:inputField name="picProfile" type="text" label="Pega la url de tu foto de perfil:" val = "${player.picProfile}"/>
        <xtreme:inputField name="firstName" type="text" label="Nombre" val = "${player.firstName}"/>
        <xtreme:inputField name="lastName" type="text" label="Apellido" val = "${player.lastName}"/>
        <xtreme:inputField name="user.username" type="text" label="Usuario" val = "${player.user.username}"/>
        <xtreme:inputField name="user.password" type="text" label="Contraseña" val = "${player.user.password}"/>
        <xtreme:inputField name="email" type="text" label="Email" val = "${player.email}"/>
        <div class="form-group submit-buttons">
            <div class="col-sm-offset-2 col-sm-10" >
               <button class="button" type="submit">Actualizar</button>
            </div>
        </div>
    </form:form>
    </c:if>
    <c:if test = "${isCurrentUser==false}">
    <h1>NO DEBERIAS ESTAR AQUI</h1>

    </c:if>

</body>