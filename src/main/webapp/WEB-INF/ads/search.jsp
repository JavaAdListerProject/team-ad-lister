<%--
  Created by IntelliJ IDEA.
  User: johnathoncook
  Date: 6/12/18
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<br><br><br><br><br><br>
<form method="post" name="frm" action="search">
    <table border="0" width="300" align="center" bgcolor="#CDFFFF">
        <tr>
            <td colspan=2 style="font-size:12pt;color:blue;" align="center">
            <h3>Search Ads</h3>
            </td>
        </tr>
        <tr>
            <td>
                <b>Titles</b>
            </td>
            <td>
                <c:forEach var="ads" items="${ads}">
                    <c:if test="${ads.id == ads.catId}">
                        <jsp:include page="/WEB-INF/partials/validation.jsp">
                            <jsp:param name="title" value="${ads.title}"/>
                            <jsp:param name="description" value="${ads.description}"/>
                            <jsp:param name="categories" value="${ads.categories}"/>
                        </jsp:include>
                    </c:if>
                </c:forEach>
                <input  type="text" name="emp_name" id="emp_name">
            </td>
            </tr>

            <td colspan=2 align="center">
            <input  type="submit" name="submit" value="Search">
            </td>
        </tr>
    </table>
</form>
</body>
</html>