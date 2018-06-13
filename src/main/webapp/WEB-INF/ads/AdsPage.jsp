<%--
  Created by IntelliJ IDEA.
  User: johnathoncook
  Date: 6/11/18
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.

--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Results" />
    </jsp:include>
</head>
<body>


<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">


    <h1>Search through the ads.</h1>
    <form action="/ads/search" method="get">
        <div class="form-group">
            <label for="term">Term</label>
            <input id="term" name="term" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="ads">Ads </label>
            <input id="ads" name="ads" class="form-control" type="text">
        </div>

        <input type="submit" class="btn btn-primary btn-block">
    </form>

        <hr>

    <br>
    <br>
    <br>
    <hr>
    <h1 style="text-align: center">Results for: <e:forHtmlContent value="${ads}"/></h1>
    <div class="code:forHtmlContentmargin-top: 75px">
        <div>

                <div class="col-md-6">
                    <div>
                    <c:forEach var="ad" items="${ads}">
                        <div>
                            <p href="/ads/page?id=${ad.id}">
                                <div>
                                    <div>
                                        <h3 style="font-family: 'Jua', sans-serif" >${ad.title}</h3>
                                        <p>${ad.description}</p>
                                        <form action="/delete" method="post">

                                        </form>
                                    </div>
                                </div>
                            </p>
                        </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>