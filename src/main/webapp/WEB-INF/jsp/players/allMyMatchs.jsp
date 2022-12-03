<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<head>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/list.css">
    <title>Xtreme Parchis&Oca</title>
    <link rel="apple-touch-icon" sizes="180x180" href="/resources/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/resources/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/resources/favicon/favicon-16x16.png">
    <link rel="manifest" href="/resources/favicon/site.webmanifest">

</head>
<body>
    <a style="position: relative;" href="<spring:url value="/players/${player.user.username}" htmlEscape="true"/>" class="previous"> < Regresar</a>

<div>
    <h2>Historial de partidas</h2>
    <table id="tabla" class="minimalistBlack">
    <thead>
    <tr>
    <th>Nombre partida</th>
    <th>Juego</th>
    <th>Numero de jugadores</th>
    <th>Host</th>
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
                <c:out value="${game.playerWinner.user.username}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot></tfoot>
    </table>
</div>
<h2>Mis partidas ganadas</h2>
<div>
    <table id="tabla" class="minimalistBlack">
    <thead>
    <tr>
    <th>Nombre partida</th>
    <th>Juego</th>
    <th>Numero de jugadores</th>
    <th>Host</th>
    <th>Ganador</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${gamesWinned}" var="game">
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
                <c:out value="${game.playerWinner.user.username}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot></tfoot>
    </table>
</div>
</body>
