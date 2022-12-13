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

<a style="position: relative;" href="<spring:url value="/users/home" htmlEscape="true"/>" class="previous"> < Regresar</a>
<h1>Tus amigos</h1>
<div>
<table class="minimalistBlack">
<thead>
<tr>
<th>Nombre</th>
<th>Apellido</th>
<th>Usuario</th>
<th>Perfil usuario</th>
<th>Online</th>

</tr>
</thead>
<tbody>
<c:forEach items="${myfriends}" var="friend">
    <tr>
        <td>
            <c:out value="${friend.firstName}"/>
        </td>
        <td>
            <c:out value="${friend.lastName}"/>
        </td>
        <td>
            <c:out value="${friend.user.username}"/>
        </td>
        <td>
            <a class="button" href = "<spring:url value="/players/${friend.user.username}"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
            licorice-64px">Ver perfil</div></a>
        </td>
        <td>
            <c:if test = "${friend.isOnline}">
            Online
            </c:if>
             <c:if test = "${friend.isOnline == false}">
            Offline
            </c:if>
        </td>
    </tr>
</c:forEach>
</tbody>
</table>
</div>
<h1>Solicitudes de amistad enviadas</h1>
<div>
    <table class="minimalistBlack">
    <thead>
    <tr>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Usuario</th>
    <th>Perfil usuario </th>
    
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${myfriendsPending}" var="friend">
        <tr>
            <td>
                <c:out value="${friend.firstName}"/>
            </td>
            <td>
                <c:out value="${friend.lastName}"/>
            </td>
            <td>
                <c:out value="${friend.user.username}"/>
            </td>
            <td>
                <a class="button" href = "<spring:url value="/players/${friend.user.username}"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                licorice-64px">Ver perfil</div></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
    </div>
    
    <div style = 'margin-bottom: 6vh; margin-top: 6vh; text-align: center'>

        <h2>Busca amigos nuevos</h2>
        <a class="button" href="<spring:url value="/friends/sendFriendship"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Ver lista de jugadores</div></a>
        <h2>Revisa tus solicitudes de amistad recibidas</h2>
        <a class="button" href="<spring:url value="/friends/pendingFriendships"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Ver solicitudes de amistad</div></a></div>
</body>
