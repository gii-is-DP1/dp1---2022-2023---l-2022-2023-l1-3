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
    <a style="position: relative;" href="<spring:url value="/friends" htmlEscape="true"/>" class="previous"> < Regresar</a>

<div>
    <table id="tabla" class="minimalistBlack">
    <thead>
    <tr>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Usuario</th>
    <th>Solicitud amistad</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${players}" var="player">
        <tr>
            <td>
                <c:out value="${player.firstName}"/>
            </td>
            <td>
                <c:out value="${player.lastName}"/>
            </td>
            <td>
                <c:out value="${player.user.username}"/>
            </td>
            <td>
                <a class="button" href = "<spring:url value="/friends/${player.user.username}"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
                licorice-64px">Enviar solicitud</div></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot></tfoot>
    </table>
</div>
</body>