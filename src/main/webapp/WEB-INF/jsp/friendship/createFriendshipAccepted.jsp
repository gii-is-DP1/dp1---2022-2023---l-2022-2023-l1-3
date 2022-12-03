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
<body>
        <a style="position: absolute;" href="<spring:url value="/friends/pendingFriendships" htmlEscape="true"/>" class="previous"> < Regresar</a>

        Foto de perfil: <img src="${player.picProfile}" width="150" height="132">
        <h1>firstName: <c:out value = "${player.firstName}"/></h1>
        <h1>lastName: <c:out value = "${player.lastName}"/></h1>
        <h1>Username: <c:out value = "${player.user.username}"/></h1>
        <h2>Email: <c:out value = "${player.email}"/></h2>
    
        <form:form modelAttribute="friendship">
            
                <select name = "state">
                    <option value= "aceptado">Aceptar solicitud</option>
                    <option value= "rechazado">Rechazar solicitud</option>
                </select>

            <div class="form-group submit-buttons">
                <div class="col-sm-offset-2 col-sm-10">
                <button class="button" type="submit">Confirmar</button>
                </div>
            </div>

        </form:form>


</body>