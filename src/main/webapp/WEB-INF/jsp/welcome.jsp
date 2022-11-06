<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<!DOCTYPE html>
<html>
<head>
<style>
@import url("https://fonts.googleapis.com/css?family=VT323:400");
<%@include file="/WEB-INF/css/welcome.css"%>
</style>
</head>
<div class="welcome screen">
    <h1 class="title">Xtreme Parchis&Oca</h1>
    <a class="button" href="http://localhost:8080/login"><div class="large valign-text-middle vt323-normal-
        licorice-64px">Iniciar Sesion</div></a>
    <a class="button" href = "http://localhost:8080/players/create"><div class="large valign-text-middle vt323-normal-
            licorice-64px">Registrarme</div></a>

    </div>
</div>
</div>