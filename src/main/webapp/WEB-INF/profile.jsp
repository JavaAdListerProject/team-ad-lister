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


        <div class="row">

            <div class="col-md-4">
                <h2>User Profile Settings</h2>
                <hr>

                <jsp:include page="partials/validation.jsp" />



                <form method="post" action="/profile" >
                <div class="form-group">
                    <label for="username">Username</label>
                    <input id="username" name="username" class="form-control" type="text" value="${user.username}">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" name="email" class="form-control" type="text" value="${user.email}">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input id="password" name="password" class="form-control" type="password">
                </div>
                <div class="form-group">
                    <label for="confirm_password">Confirm Password</label>
                    <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                </div>
                    <input type="submit" class="btn btn-primary btn-block">
                </form>
            </div>

            <div class="col-md-8">
                <h2>Your Posted Ads </h2>
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

         </div>




    </div>

</body>
</html>
