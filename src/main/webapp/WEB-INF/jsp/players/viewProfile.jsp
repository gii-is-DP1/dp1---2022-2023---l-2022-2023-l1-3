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
    
    Foto de perfil: <img src="${player.picProfile}" width="150" height="132">
    <h1>firstName: <c:out value = "${player.firstName}"/></h1>
    <h1>lastName: <c:out value = "${player.lastName}"/></h1>
    <h1>Username: <c:out value = "${player.user.username}"/></h1>
    <h2>Email: <c:out value = "${player.email}"/></h2>

    <c:if test = "${isCurrentUser == false}">
        <a class="previous" onClick="history.go(-1);" htmlEscape="true"/> < Regresar</a>

    </c:if>
    <c:if test = "${isCurrentUser}">
        <a style="position: absolute;" href="<spring:url value="/home" htmlEscape="true"/>" class="previous"> < Regresar</a>
        <div class="large valign-text-middle vt323-normal-licorice-64px">
        <a class="button" href="<spring:url value="/players/${player.user.username}/edit"  htmlEscape="true"/>">Editar perfil</a>

        <a class="button" href="<spring:url value="/players/${player.user.username}/myMatchPlayed"  htmlEscape="true"/>">Ver historial de partidas</a>
        </div>

    </c:if>
    <c:if test = "${isAdmin}">
        <a style="position: absolute;" href="<spring:url value="/players" htmlEscape="true"/>" class="previous"> < Regresar</a>


    <form:form modelAttribute="player" class="form-horizontal">
        <select name = "enabled">
			<option value= "activado">Activo</option>
			<option value= "baneado">Baneado</option>
		  </select>
        <div class="form-group submit-buttons">
            <div class="col-sm-offset-2 col-sm-10">
               <button class="button" type="submit">Guardar cambios</button>
            </div>
        </div>
    </form:form>
        <a class="button" href="<spring:url value="/players/${player.user.username}/edit"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Editar perfil</div></a>

    </c:if>

</body>