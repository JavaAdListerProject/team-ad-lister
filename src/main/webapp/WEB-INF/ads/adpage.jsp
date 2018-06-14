
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adpage</title>
</head>
<body>
    <h1>Now showing ad #${adid}</h1>
    <h2>${selectad.title}</h2>
    <h3>By user ${user.username}</h3>
    <h2>${selectad.description}</h2>


    <div class="categories">
    <h3>Categories:</h3>
<c:forEach var="cat" items="${cats}">

    <span class="mr-2">${cat.title}</span><br>
    <input type="submit" class="button" >

</c:forEach>
    </div>

<c:forEach var="ad" items="${ads}">
    <div>
        <h3>${ad.title}</h3>
            <p>${ad.description}</p>
            <form action="/ads">
                <input type="hidden" name="title" value="${ad.title}">
                <input type="hidden" name="userId" value="${ad.getUserId()}">
                <input type="hidden" name="addescription" value="${ad.description}">
                <input type="submit">
        </form>

    </div>


</c:forEach>
</body>
</html>
