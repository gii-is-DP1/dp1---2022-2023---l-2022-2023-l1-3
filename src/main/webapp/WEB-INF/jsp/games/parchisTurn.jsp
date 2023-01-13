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
<div><img class="card-img-top" src="/resources/img/boards/parchis/parchis.jpg" width="600" height="600">
</div>
Dice: <c:out value="${game.dice}"/><br>
Turno del jugador: <c:out value="${game.i+1}"/><br><br>

Player : <c:out value="${player.user.username}"/><br>
Posicion de las fichas: <br><br>
<c:forEach items="${pieces}" var="piece">
    <tr>      
        <th>(${piece.name}</th>
        <c:choose>
        <c:when test="${piece.inBase}">
        <td>IN BASE)</td>
        </c:when>
        <c:otherwise>
        <td><c:out value="IN ${piece.cell.position})"/></td>
        </c:otherwise>
        </c:choose> 
    </tr>
</c:forEach><br><br>

<c:forEach items="${pieces}" var="piece">
<a class="button" href = "<spring:url value="/parchis/move/${game.id}/${player.id}/${piece.id}"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
licorice-64px">Mover <c:out value="${piece.name}"/></div></a>
</c:forEach>


      </xtreme:layout>