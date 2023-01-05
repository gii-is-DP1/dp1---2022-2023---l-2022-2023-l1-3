<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<xtreme:layout pageName="ocaGames">
<head>
    <xtreme:head></xtreme:head>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/form.css">
</head>
<div>
Nombre de la partida:<c:out value="${game.gameName}"/><br>
</div>
<div><img class="card-img-top" src="/resources/img/boards/oca/ocaboard.jpeg" width="600" height="600">
</div>
Dice: <c:out value="${game.dice}"/><br>
Turno del jugador: <c:out value="${game.i+1}"/><br><br>

Player 1: <c:out value="${player0.user.username}"/><br>
Posicion de la ficha: <c:out value="${piece0.position}"/><br><br>

Player 2: <c:out value="${player1.user.username}"/><br>
Posicion de la ficha: <c:out value="${piece1.position}"/><br><br>
<c:if test="${player2 != null}">
Player 3: <c:out value="${player2.user.username}"/><br>
Posicion de la ficha: <c:out value="${piece2.position}"/><br><br>
</c:if>
<c:if test="${player3 != null}">
Player 4: <c:out value="${player3.user.username}"/><br>
Posicion de la ficha: <c:out value="${piece3.position}"/><br>
</c:if>

<c:if test="${isViewer == false}">
    <div class="col-md-3">
        <c:if test="${isUserEquals}">
            <spring:url value="/turn/${game.id}/${player.id}" var="joinUrl">
                <spring:param name="gameId" value="${game.id}"/>
                <spring:param name="playerId" value="${player.id}"/>
            </spring:url>
            <a class="button" href = "<spring:url value="/games/${game.id}/${player.id}"  htmlEscape="true"/>">Tira el dado</a></c:if>
                    
            <c:if test="${!isUserEquals}">
                <h2>No es tu turno</h2>
            </c:if>
    </div>

    <div style = 'margin-bottom: 9vh; margin-top: 6vh'>
          <a class="button"  href="<spring:url value="/games/lobby/${game.id}/chat"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
          licorice-64px">Chat</div></a>
    </div>
</c:if>
<c:if test="${isViewer == true}">
    <h2>Estas en modo espectador</h2>
</c:if>
      </xtreme:layout>
