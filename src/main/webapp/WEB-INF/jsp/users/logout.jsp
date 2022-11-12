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
	<link rel="stylesheet" href="/resources/css/form.css">
</head>
<body>
    <h2>Se ha cerrado su sesion correctamente</h2>
    <a class="button" href="<spring:url value="/"  htmlEscape="true"/>"><div class="large valign-text-middle vt323-normal-
    licorice-64px">Volver a pantalla de inicio</div></a>
</body>