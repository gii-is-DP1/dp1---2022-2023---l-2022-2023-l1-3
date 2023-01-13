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

Player 1: <c:out value="${player0.user.username}"/>&nbsp;&nbsp;&nbsp;Color: Amarillo&nbsp;&nbsp;&nbsp;Casilla meta: 76<br>
Posicion de las fichas: <br><br>
<c:forEach items="${player0.parchisPieces}" var="piece0">
    <tr>      
        <th>(${piece0.name}</th>
        <c:choose>
        <c:when test="${piece0.inBase}">
        <td>IN BASE)</td>
        </c:when>
        <c:otherwise>
        <td><c:out value="IN ${piece0.cell.position})"/></td>
        </c:otherwise>
        </c:choose> 
    </tr>
</c:forEach><br><br>

Player 2: <c:out value="${player1.user.username}"/>&nbsp;&nbsp;&nbsp;Color: Verde&nbsp;&nbsp;&nbsp;Casilla meta: 84<br>
Posicion de las fichas: <br><br>
<c:forEach items="${player1.parchisPieces}" var="piece1">
    <tr>      
        <th>(${piece1.name}</th>
        <c:choose>
        <c:when test="${piece1.inBase}">
        <td>IN BASE)</td>
        </c:when>
        <c:otherwise>
        <td><c:out value="IN ${piece1.cell.position})"/></td>
        </c:otherwise>
        </c:choose> 
    </tr>
</c:forEach><br><br>

<c:if test="${player2 != null}">
Player 3: <c:out value="${player2.user.username}"/>&nbsp;&nbsp;&nbsp;Color: Rojo&nbsp;&nbsp;&nbsp;Casilla meta: 92<br>
Posicion de las fichas: <br><br>
<c:forEach items="${player2.parchisPieces}" var="piece2">
    <tr>      
        <th>(${piece2.name}</th>
        <c:choose>
        <c:when test="${piece2.inBase}">
        <td>IN BASE)</td>
        </c:when>
        <c:otherwise>
        <td><c:out value="IN ${piece2.cell.position})"/></td>
        </c:otherwise>
        </c:choose> 
    </tr>
</c:forEach>
</c:if> <br><br>

<c:if test="${player3 != null}">
Player 4: <c:out value="${player3.user.username}"/>&nbsp;&nbsp;&nbsp;Color: Azul&nbsp;&nbsp;&nbsp;Casilla meta: 100<br>
Posicion de las fichas: <br><br>
<c:forEach items="${player3.parchisPieces}" var="piece3">
    <tr>      
        <th>(${piece3.name}</th>
        <c:choose>
        <c:when test="${piece3.inBase}">
        <td>IN BASE)</td>
        </c:when>
        <c:otherwise>
        <td><c:out value="IN ${piece3.cell.position})"/></td>
        </c:otherwise>
        </c:choose> 
    </tr>
</c:forEach>
</c:if> <br><br>

    <div class="col-md-3">
                    <c:if test="${isUserEquals}">

                    <a class="button" href = "<spring:url value="/parchis/${game.id}/${player.id}"  htmlEscape="true"/>">Tira el dado</a></c:if>
                    
                    <c:if test="${!isUserEquals}">
                        <h2>No es tu turno</h2></c:if>
        </div>
        <div style = 'margin-bottom: 9vh; margin-top: 6vh'>
          <a class="button"  href="<spring:url value="/games/lobby/${game.id}/chat"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
          licorice-64px">Chat</div></a></div>


      </xtreme:layout>