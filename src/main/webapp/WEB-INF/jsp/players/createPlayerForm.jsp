<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>




    <h2>
        <c:if test="${player['new']}">New </c:if> Player
    </h2>
    <form:form modelAttribute="player" class="form-horizontal">
        <div class="form-group has-feedback">
            <label for="fname">First name:</label><br>
            <input type="text" id="fname" name="firstName"><br>
            <label for="sname">Last name:</label><br>
            <input type="text" id="sname" name="lastName"><br>
            <label for="fname">Username:</label><br>
            <input type="text" id="fname" name="username"><br>
            <label for="fname">Password:</label><br>
            <input type="text" id="fname" name="password"><br>
            <label for="fname">Email:</label><br>
            <input type="text" id="fname" name="email"><br>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <input type="hidden" name="id" value="${card.id}"/>
               <button class="btn btn-default" type="submit">Registrarme</button>
            </div>
        </div>

    </form:form>

