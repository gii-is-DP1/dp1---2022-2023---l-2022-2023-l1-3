<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<xtreme:layout pageName="ocaGames">

    <h2>Player Information</h2>


    <table class="table table-striped">

        
        <tr>
            <th>Nombre de partida:</th>
            <td><c:out value="${game.gameName}"/></td>
        </tr>
        <tr>
            <th>Tablero</th>
            <td><img class="card-img-top" src="/resources/img/boards/oca/ocaboard.jpeg"
							alt="Dubai"></td>
        </tr>
         <tr>0
         <th>DICE</th>
            <td><c:out value="${turn.dice}"/></td>
     
          </tr>
                 <tr>
            <th>TURN</th>
            <td><c:out value="${turn.turn}"/></td>
        </tr>
        <tr>
         <th>PLAYER</th>
            <td><c:out value="${turn.player.user.username}"/></td>
     
          </tr>
           <tr>
         <th>Your Position</th>
            <td><c:out value="${piece.position}"/></td>
          </tr>
          
          
    </table>
    <div class="col-md-3">
           <c:choose>
                    <c:when test="${player.id==turn.player.id}">
                        <spring:url value="/turn/${game.id}/${player.id}" var="joinUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                          <spring:param name="playerId" value="${player.id}"/>
                    </spring:url>
                    <a class="btn btn-primary" href="${fn:escapeXml(joinUrl)}">Tira el dado</a>
                    </c:when>
                    <c:otherwise>
                        <p> NO ES TU TURNO </p> 
                    </c:otherwise>
                </c:choose>
        </div>
      

      </xtreme:layout>
