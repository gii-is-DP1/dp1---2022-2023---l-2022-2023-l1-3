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

<div>
    <table id="tabla" class="minimalistBlack">
    <thead>
    <tr>
    <th>Nombre partida</th>
    <th>Juego</th>
    <th>Numero de jugadores</th>
    <th>Host</th>
    <th>Privacidad</th>
    <th>Estado de la partida</th>
    <th>Jugadores</th>
    <th>Ganador</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${games}" var="game">
        <tr>
            <td>
                <c:out value="${game.gameName}"/>
            </td>
            <td>
                <c:out value="${game.typeGame}"/>
            </td>
            <td>
                <c:out value="${game.numPlayers}"/>
            </td>
            <td>
                <c:out value="${game.creatorPlayer.user.username}"/>
            </td>
            <td>
                <c:if test="${game.isPublic}">Publica</c:if>
                <c:if test="${game.isPublic == false}">Privada</c:if>
            </td>
            <td>
                <c:out value="${game.stateGame}"/>
            </td>
            <td>
                <c:forEach items = "${game.players}" var = "player"> 
                    <c:out value = "${player.user.username}"/>&nbsp;
                </c:forEach>
            </td>
            <td>
                <c:if test="${game.playerWinner == null}">Partida en juego...</c:if>
                <c:if test="${game.playerWinner != null}">
                    <c:out value="${game.playerWinner.user.username}"/>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot></tfoot>
    </table>
</div>
</body>