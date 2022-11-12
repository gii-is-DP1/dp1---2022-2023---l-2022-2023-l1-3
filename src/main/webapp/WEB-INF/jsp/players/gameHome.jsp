<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<head>
    <title>Xtreme Parchis&Oca</title>
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/createPlayer.css">

</head>
<body>
    <h1 style="margin-top: 0.5em; margin-bottom: 0.10em;" class="title">Xtreme Parchis&Oca</h1>
    <a class="button" href="<spring:url value="/players/createGame"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
    licorice-64px">Crear partida</div></a>
    <a class="button" href = "<spring:url value="/players/joinGame"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Unirse a partida</div></a>
    
    <c:if test="${authoriti}">New </c:if> Owner
    

<canvas id="frame"></canvas>
</body>
