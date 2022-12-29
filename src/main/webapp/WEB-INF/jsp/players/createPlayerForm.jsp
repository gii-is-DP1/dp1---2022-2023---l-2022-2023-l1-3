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

<link rel="apple-touch-icon" type="image/png" href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png">
<meta name="apple-mobile-web-app-title" content="CodePen">

<link rel="shortcut icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico">
<link rel="mask-icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111">  


<body translate="no" class="vsc-initialized">
    <a href="<spring:url value="/" htmlEscape="true" />" class="previous"> < Regresar</a>
	<xtreme:title title="REGISTRO" x="75" y="100"></xtreme:title>
    <form:form modelAttribute="player" class="form-horizontal">
        <span class="help-inline"><c:out value="${message}"/></span>
        <xtreme:inputField name="firstName" type="text" label="Nombre"/>
        <xtreme:inputField name="lastName" type="text" label="Apellido"/>
        <xtreme:inputField name="user.username" type="text" label="Usuario"/>
        <xtreme:inputField name="user.password" type="text" label="Contraseña"/>
        <xtreme:inputField name="email" type="text" label="Email"/>
        <div class="form-group submit-buttons">
            <div class="col-sm-offset-2 col-sm-10">
               <button class="button" type="submit">Registrarme</button>
            </div>
        </div>
    </form:form>
</body>
