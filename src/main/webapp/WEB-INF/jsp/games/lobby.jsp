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
        <a style="position: absolute;" href="<spring:url value="/games/createGame" htmlEscape="true"/>" class="previous"> < Regresar</a>

       
        <h1>Nombre partida: <c:out value = "${game.gameName}"/></h1>
        <h2>Numero de jugadores: <c:out value = "${game.numPlayers}"/></h2>
        <h1>Tipo de juego: <c:out value = "${game.typeGame}"/></h1>
        <h1>Host: <c:out value = "${game.creatorPlayer.user.username}"/></h1>
        <c:if test = "${game.isPublic}">
            <h2>Privacidad: publica</h2>
        </c:if>
        <c:if test = "${game.isPublic == false}">
            <h2>Privacidad: privada</h2>
        </c:if>
        <h2>
            Lista jugadores:<c:forEach items = "${game.players}" var = "player"> 
                <c:out value = "${player.user.username}"/>&nbsp;
            </c:forEach>
        </h2>
        <c:if test = "${(numActualPlayers >= 2 && numActualPlayers <= 4) && isHost}">
            <div class="form-group submit-buttons">
                <div class="col-sm-offset-2 col-sm-10">
                <button class="button" type="submit">Empezar partida</button>
                </div>
            </div>
        </c:if>

            <form:form modelAttribute="game" class="form-horizontal">

                <div class="form-group submit-buttons">
                    <div class="col-sm-offset-2 col-sm-10">
                    <button class="button" type="submit">Salir</button>
                    </div>
                </div>
    
            </form:form>
       


</body>