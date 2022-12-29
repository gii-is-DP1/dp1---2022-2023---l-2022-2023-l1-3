<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<head>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/form.css">
    <title>Xtreme Parchis&Oca</title>
    <link rel="apple-touch-icon" sizes="180x180" href="/resources/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/resources/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/resources/favicon/favicon-16x16.png">
    <link rel="manifest" href="/resources/favicon/site.webmanifest">
</head>

<link rel="apple-touch-icon" type="image/png" href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png">
<meta name="apple-mobile-web-app-title" content="CodePen">

<link rel="shortcut icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico">
<link rel="mask-icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111">  


<body translate="no" class="vsc-initialized">
    <a href="<spring:url value="/home" htmlEscape="true" />" class="previous"> < Regresar</a>
	<xtreme:title title="CREATE GAME" x="0" y="100"></xtreme:title>
    <form:form modelAttribute="game" class="form-horizontal">
        <span class="help-inline"><c:out value="${message}"/></span>
		<xtreme:inputField name="gameName" type="text" label="Nombre de la partida"/>
		<div style="display: flex; justify-content: space-evenly;">
		<div>
		<label for="numPlayers">Número de jugadores:</label>
		<input type="number" name="numPlayers" value="2" step="1" min="2" max="4" required>
		</div>
		<div>
		<labe for="isPublic"> Partida publica</label>
		<input type="checkbox" name="isPublic" checked>
		</div>
		<select name="gameType">
			<option value="PARCHIS">Parchis</option>
			<option value="OCA" selected>Oca</option>
		</select>
		</div>
		<div class="form-group submit-buttons">
            <div class="col-sm-offset-2 col-sm-10">
               <button class="button" type="submit">Crear partida</button>
            </div>
        </div>
    </form:form>
</body>
