<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
        <h2>Here are your ads</h2>
        <hr>
        <c:forEach var="ad" items="${ads}">
            <div>
                <h2>${ad.getTitle()}</h2>
                <p>${ad.getDescription()}</p>
                <form action="/adpage">
                    <input type="hidden" name="adid" value=${ad.getId()}>
                    <input type="hidden" name="userId" value=${ad.getUserId()}>
                    <input type="submit" value="View Details">
                </form>
                <hr>
            </div>
        </c:forEach>
    </div>

</body>
</html>
