<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    body {
        background-color: black;
    }
</style>

<xtreme:board board="${board}">
    <c:forEach items="${board.pieces}" var="piece">
        <xtreme:piece size="100" piece="${piece}"/>            	
    </c:forEach> 
</xtreme:board>
