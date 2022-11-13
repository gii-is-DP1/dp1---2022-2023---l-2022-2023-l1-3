<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<head>
    <title>Xtreme Parchis&Oca</title>
    <link rel="apple-touch-icon" sizes="180x180" href="/resources/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/resources/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/resources/favicon/favicon-16x16.png">
    <link rel="manifest" href="/resources/favicon/site.webmanifest">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/form.css">
    <a href="<spring:url value="/users/logout" htmlEscape="true"/>" class="previous"> < logout</a>
    <h3>
        <c:if test="${esAdmin}">Estas logueado como admin</c:if>
    </h3>
</head>
<body>
    <h1 style="margin-top: 0.5em; margin-bottom: 0.10em;" class="title">Xtreme Parchis&Oca</h1>
    <a class="button" href="<spring:url value="/players/createGame"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
    licorice-64px">Crear partida</div></a>
    <a class="button" href = "<spring:url value="/players/joinGame"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Unirse a partida</div></a>
    
    
        <c:if test="${esAdmin}">
            <div>    
                <a class="button" href = "<spring:url value="/players"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                licorice-64px">Lista de jugadores</div></a>
                <a class="button" href = "<spring:url value="/admins/create"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                licorice-64px">Crear admin</div></a>
                <a class="button" href = "<spring:url value="/admins"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                licorice-64px">Lista admins</div></a>
            </div>
        </c:if>

    <c:if test="${esAdmin == false}">
        <a class="button" href = "<spring:url value="/players/friends"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Lista amigos</div></a>
    </c:if>

<canvas id="frame"></canvas>

</body>
