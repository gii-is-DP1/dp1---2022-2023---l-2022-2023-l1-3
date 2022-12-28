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

<body translate="no" class="vsc-initialized">
    <a href="<spring:url value="/" htmlEscape="true"/>" class="previous"> < Regresar</a>
	<xtreme:title title="LOGIN" x="150" y="100"></xtreme:title>

    <form:form modelAttribute="user" class="form-horizontal">
		<c:if test="${not empty param.error}">
            <p style="color:red">
                Your login attempt was not
                successful, try again.
            </p>
        </c:if>
            <xtreme:inputField name="username" type="text" label="Usuario"/>
			<xtreme:inputField name="password" type="password" label="Contraseña"/>
            <div class="form-group submit-buttons">
				<div class="col-sm-offset-2 col-sm-10">
				   <button class="button" type="submit">Iniciar sesión</button>
				</div>
			</div>
    </form:form>
</body>
