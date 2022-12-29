<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <xtreme:head></xtreme:head>
    <link rel="stylesheet" href="/resources/css/base.css">
    <link rel="stylesheet" href="/resources/css/form.css">
</head>
<body>
    <a href="<spring:url value="/games/lobby/${id}" htmlEscape="true" />" class="previous"> < Regresar</a>

        <h1>XTREME CHAT</h1>
        <h2>
            <c:forEach items = "${chat}" var = "mensaje">
                <br> 
                <c:out value = "${mensaje.player.user.username}"/>: &nbsp;<c:out value = "${mensaje.text}"/>
            </c:forEach>
        </h2>


        <form:form modelAttribute="mensaje" class="form-horizontal">
            <input name="text" type="text" label=" "/>
            <div class="form-group submit-buttons">
                <div class="col-sm-offset-2 col-sm-10">
                   <button class="button" type="submit">Enviar</button>
                </div>
            </div>
        
        </form:form>



</body>
