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
        <a style="position: absolute;" href="<spring:url value="/games/inviteFriends" htmlEscape="true"/>" class="previous"> < Regresar</a>

        Foto de perfil: <img src="${player.picProfile}" width="150" height="132">
        <h1>firstName: <c:out value = "${player.firstName}"/></h1>
        <h1>lastName: <c:out value = "${player.lastName}"/></h1>
        <h1>Username: <c:out value = "${player.user.username}"/></h1>
        <h2>Email: <c:out value = "${player.email}"/></h2>
    
        <form:form modelAttribute="friendship" class="form-horizontal">

        <select name = "state">
            <option value= "player">Jugador</option>
            <option value= "viewer">Espectador</option>
        </select>

            <div class="form-group submit-buttons">
                <div class="col-sm-offset-2 col-sm-10">
                <button class="button" type="submit">Invitar a la partida</button>
                </div>
            </div>

        </form:form>


</body>