<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<head>
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/welcome.css">
    <title>Xtreme Parchis&Oca</title>
    <link rel="apple-touch-icon" sizes="180x180" href="/resources/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/resources/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/resources/favicon/favicon-16x16.png">
    <link rel="manifest" href="/resources/favicon/site.webmanifest">
</head>
<div class="welcome">
    <img src="/resources/img/Xtreme_Oca_2006.jpeg" alt="Logo Xtremo" class = "logo">
    <h1 style="margin-top: 0.5em; margin-bottom: 0.10em;" class="title"><span class="text">Xtreme</span> Parchis&Oca</h1>
    
    <a class="button" href="<spring:url value="/users/home"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Iniciar Sesión</div></a>
    <a class="button" href = "<spring:url value="/players/create"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
            licorice-64px">Registrarme</div></a>
</div>
<canvas id="frame"></canvas>

<script src="/resources/js/fire.js"></script>
