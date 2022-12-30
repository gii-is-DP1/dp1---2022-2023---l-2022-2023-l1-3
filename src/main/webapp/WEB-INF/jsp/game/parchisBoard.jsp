<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    body {
        background-color: black;
        top: 0;
        left: 0;
        overflow: hidden;
    }
    #board{
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);

    }
</style>
<div id = "board">
<xtreme:board board="${board}">
    <c:forEach items="${board.pieces}" var="piece">
        <xtreme:piece size="100" piece="${piece}"/>            	
    </c:forEach> 
</xtreme:board>
</div>