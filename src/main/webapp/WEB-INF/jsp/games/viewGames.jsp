<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<head>
    <link rel="stylesheet" href="/resources/css/list.css">
    <title>Xtreme Parchis&Oca</title>
    <link rel="apple-touch-icon" sizes="180x180" href="/resources/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/resources/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/resources/favicon/favicon-16x16.png">
    <link rel="manifest" href="/resources/favicon/site.webmanifest">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.csss">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
        $('#tabla').DataTable();
        $('.dataTables_length').addClass('bs-select');
  });
    </script>
    
</head>
<body>

<div>
<table id="tabla" class="minimalistBlack">
<thead>
<tr>
<th>Nombre partida</th>
<th>Numero de jugadores</th>
<th>Tipo de juego</th>
<th>Host de la partida</th>
</tr>
</thead>
<tbody>
<c:forEach items="${games}" var="game">
    <tr>
        <td>
            <c:out value="${game.gameName}"/>
        </td>
        <td>
            <c:out value="${game.numPlayers}"/>
        </td>
        <td>
            <c:out value="${game.typeGame}"/>    
        </td>
        <td>
            <c:out value="${game.creatorPlayer.user.username}"/>        
        </td>
    </tr>
</c:forEach>
</tbody>
<tfoot></tfoot>
</table>
</div>
<div>
    <a style="position: relative;" href="<spring:url value="/users/home" htmlEscape="true"/>" class="regresar"> < Regresar</a>
</div>
</body>

