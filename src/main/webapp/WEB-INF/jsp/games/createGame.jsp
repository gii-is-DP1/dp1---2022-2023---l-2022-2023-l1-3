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
    <a href="<spring:url value="/users/home" htmlEscape="true" />" class="previous"> < Regresar</a>
  <svg version="1.1" id="Ebene_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="600px" height="100px" viewBox="0 0 600 100">
	<defs>

		<filter id="filter">
		    <feFlood flood-color="black" result="black"></feFlood>
		    <feFlood flood-color="red" result="flood1"></feFlood>
		    <feFlood flood-color="limegreen" result="flood2"></feFlood>
			<feOffset in="SourceGraphic" dx="3" dy="0" result="off1a"></feOffset>
			<feOffset in="SourceGraphic" dx="2" dy="0" result="off1b"></feOffset>
			<feOffset in="SourceGraphic" dx="-3" dy="0" result="off2a"></feOffset>
			<feOffset in="SourceGraphic" dx="-2" dy="0" result="off2b"></feOffset>
		    <feComposite in="flood1" in2="off1a" operator="in" result="comp1"></feComposite>
		    <feComposite in="flood2" in2="off2a" operator="in" result="comp2"></feComposite>

 		  	<feMerge x="0" width="100%" result="merge1">
				<feMergeNode in="black"></feMergeNode>
				<feMergeNode in="comp1"></feMergeNode>
				<feMergeNode in="off1b"></feMergeNode>

				<animate attributeName="y" id="y" dur="4s" values="104px; 104px; 30px; 105px; 30px; 2px; 2px; 50px; 40px; 105px; 105px; 20px; 6ßpx; 40px; 104px; 40px; 70px; 10px; 30px; 104px; 102px" keyTimes="0; 0.362; 0.368; 0.421; 0.440; 0.477; 0.518; 0.564; 0.593; 0.613; 0.644; 0.693; 0.721; 0.736; 0.772; 0.818; 0.844; 0.894; 0.925; 0.939; 1" repeatCount="indefinite"></animate>
 
				<animate attributeName="height" id="h" dur="4s" values="10px; 0px; 10px; 30px; 50px; 0px; 10px; 0px; 0px; 0px; 10px; 50px; 40px; 0px; 0px; 0px; 40px; 30px; 10px; 0px; 50px" keyTimes="0; 0.362; 0.368; 0.421; 0.440; 0.477; 0.518; 0.564; 0.593; 0.613; 0.644; 0.693; 0.721; 0.736; 0.772; 0.818; 0.844; 0.894; 0.925; 0.939; 1" repeatCount="indefinite"></animate>
		    </feMerge>
 			

 			<feMerge x="0" width="100%" y="60px" height="65px" result="merge2">
				<feMergeNode in="black"></feMergeNode>
				<feMergeNode in="comp2"></feMergeNode>
				<feMergeNode in="off2b"></feMergeNode>

				<animate attributeName="y" id="y" dur="4s" values="103px; 104px; 69px; 53px; 42px; 104px; 78px; 89px; 96px; 100px; 67px; 50px; 96px; 66px; 88px; 42px; 13px; 100px; 100px; 104px;" keyTimes="0; 0.055; 0.100; 0.125; 0.159; 0.182; 0.202; 0.236; 0.268; 0.326; 0.357; 0.400; 0.408; 0.461; 0.493; 0.513; 0.548; 0.577; 0.613; 1" repeatCount="indefinite"></animate>
 
				<animate attributeName="height" id="h" dur="4s" values="0px; 0px; 0px; 16px; 16px; 12px; 12px; 0px; 0px; 5px; 10px; 22px; 33px; 11px; 0px; 0px; 10px" keyTimes="0; 0.055; 0.100; 0.125; 0.159; 0.182; 0.202; 0.236; 0.268; 0.326; 0.357; 0.400; 0.408; 0.461; 0.493; 0.513;  1" repeatCount="indefinite"></animate>
		    </feMerge>
			
		 	<feMerge>
 				<feMergeNode in="SourceGraphic"></feMergeNode>	

				<feMergeNode in="merge1"></feMergeNode> 
 			<feMergeNode in="merge2"></feMergeNode>

		    </feMerge>
	    </filter>

	</defs>

<g>
	<text x="0" y="100">CREATE GAME</text>
</g>
</svg>
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
		<select name="typeGame">
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
