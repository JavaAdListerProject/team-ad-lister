<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty validate}" >


    <c:forEach items="${validate.results}" var="result">
        <div class="alert alert-danger" role="alert">

            <strong><c:out value="${result.item}"/></strong>
            <c:out value="${result.error}"/>
        </div>
    </c:forEach>

</c:if>


<c:if test="${updated == true}" >
    <div class="alert alert-success" role="alert">
        Successfully updated!
    </div>
</c:if>