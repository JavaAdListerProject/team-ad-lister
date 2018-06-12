
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

<c:forEach var="cat" items="${selectad.categories}">

    <span class="mr-2">${cat.title}</span>

</c:forEach>
    </div>
</body>
</html>
