<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<!DOCTYPE html>
<html>
<head>
    <style>
        @import url("https://fonts.googleapis.com/css?family=VT323:400");
        <%@include file="/WEB-INF/css/player.css"%>
    </style>
</head>

    <h2 class="title">
        <c:if test="${player['new']}">New </c:if> Player
    </h2>
    <form:form modelAttribute="player" class="form-horizontal">
        <div class="form-group has-feedback">
            <div class="form-element">
                <label for="fname">First name:</label><br>
                <input type="text" id="fname" name="firstName"><br>
            </div>
            <div class="form-element">
                <label for="sname">Last name:</label><br>
                <input type="text" id="sname" name="lastName"><br>
            </div>
            <div class="form-element">
                <label for="fname">Username:</label><br>
                <input type="text" id="fname" name="username"><br>
            </div>
            <div class="form-element">
                <label for="fname">Password:</label><br>
                <input type="text" id="fname" name="password"><br>
            </div>
            <div class="form-element">
                <label for="fname">Email:</label><br>
                <input type="text" id="fname" name="email"><br>
            </div>
            
        </div>
        <div class="form-group submit-buttons">
            <div class="col-sm-offset-2 col-sm-10">
               <input type="hidden" name="id" value="${card.id}"/>
               <button class="button" type="submit">Registrarme</button>
            </div>
        </div>

    </form:form>
</html>
