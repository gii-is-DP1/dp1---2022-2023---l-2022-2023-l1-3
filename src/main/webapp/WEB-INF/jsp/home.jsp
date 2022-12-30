<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<head>
    <xtreme:head></xtreme:head>
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/form.css">
    <h3>
        <c:if test="${isAdmin}">Estas logueado como admin</c:if>
    </h3>
</head>
<body style = 'overflow-y: hidden;'>
    
    <a href="<spring:url value="/users/logout" htmlEscape="true"/>" class="previous"> < logout</a></logout>
    <h1 style="margin-top: 0.5em; margin-bottom: 0.10em;" class="title">Xtreme Parchis&Oca</h1>

        <c:if test="${isAdmin == false}">
            <div style = 'margin-bottom: 6vh; margin-top: 6vh'>
            <a class="button" href="<spring:url value="/games/createGame"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
            licorice-64px">Crear partida</div></a></div>
            <div style = 'margin-bottom: 6vh'>
            <a class="button" href = "<spring:url value="/games/joinGame"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
            licorice-64px">Unirse a partida</div></a>
            </div>
        </c:if>
    
        <c:if test="${isAdmin}">
                <div style = 'margin-bottom: 9vh; margin-top: 6vh'>
                    <a class="button" href = "<spring:url value="/players"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                    licorice-64px">Lista de jugadores</div></a>
                </div>
                <div style = 'margin-bottom: 9vh'>
                    <a class="button" href = "<spring:url value="/admins/create"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                    licorice-64px">Crear admin</div></a>
                </div>
                <div style = 'margin-bottom: 9vh'>
                    <a class="button" href = "<spring:url value="/admins"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                    licorice-64px">Lista admins</div></a>
                </div>
                <div style = 'margin-bottom: 9vh'>
                    <a class="button" href = "<spring:url value="/admins/listAllGames"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                    licorice-64px">Ver listado de partidas</div></a>
                </div>
        </c:if>

    <c:if test="${isAdmin == false}">
        <div style = 'margin-bottom: 6vh'>
        <a class="button" href = "<spring:url value="/friends"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Lista amigos</div></a>
        </div>
    </c:if>
    <c:if test="${isAdmin == false}">
        <div>
        <a class="button" href = "<spring:url value="/players/${username}"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">ver perfil</div></a>
        </div>
    </c:if>

</body>
