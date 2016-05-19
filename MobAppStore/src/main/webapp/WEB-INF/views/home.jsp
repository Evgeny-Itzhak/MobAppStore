<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Home - Applications</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">

</head>
<body>

<jsp:include page="mostpopular.jsp"/>
<jsp:include page="menu.jsp"/>

<div id="container">

    <div id="category">
        <c:forEach items="${categories}" var="category">
            <a href="showApplicationsByCategory?categoryId=${category.categoryId}">${category.categoryName}</a><br>
        </c:forEach>
    </div>

    <div id="application">
        <table>
            <c:forEach items="${applications}" end="10" var="curApplication">
                <tr>
                    <td>
                        <a href="/showApplication?applicationId=${curApplication.applicationId}">
                            <img src="${curApplication.applicationImage128 == null ? "../images/icon_default_128_128.png" : pageContext.request.contextPath += "/showImage128?applicationId=" += curApplication.applicationId}"/>
                        </a>
                    </td>

                    <td>
                        <h2>
                            <a href="/showApplication?applicationId=${curApplication.applicationId}">
                                <c:out value="${curApplication.applicationName}"/>
                            </a>
                        </h2>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>