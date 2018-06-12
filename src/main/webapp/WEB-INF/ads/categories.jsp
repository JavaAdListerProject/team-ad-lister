<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Want something? Look here!" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>What are we looking for?</h1>

    <div class="d-flex flex-box flex-wrap">
    <c:forEach var="cat" items="${main}">
        <div class="col-12 col-sm-6 col-md-4">
            <h2 class="ad-cat" data-id="${cat.id}">${cat.title}</h2>
            <div class="sub-cat" data-id="${cat.id}">

                <c:forEach var="subCat" items="${sub}">
                    <c:if test="${cat.id == subCat.mainId}">
                        <div><a href="#">${subCat.title}</a></div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
    </div>
</div>




<jsp:include page="/WEB-INF/partials/footer.jsp" />
</body>
</html>
