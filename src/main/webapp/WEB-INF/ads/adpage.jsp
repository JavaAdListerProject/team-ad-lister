<%--
  Created by IntelliJ IDEA.
  User: danielconner
  Date: 6/11/18
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
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
</body>
</html>
