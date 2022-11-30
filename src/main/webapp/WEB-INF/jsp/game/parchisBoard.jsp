<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="xtreme" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-md-12">
        <xtreme:board board="${board}">
        <c:forEach items="${board.pieces}" var="piece">
            <xtreme:piece size="100" piece="${piece}"/>            	
        </c:forEach> 
        </xtreme:board>
    </div>
</div>
