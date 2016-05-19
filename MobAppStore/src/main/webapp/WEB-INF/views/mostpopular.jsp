<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Home - Applications</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">

</head>
<body>

<div id="container">
    <div id="gallery">
        <h1>Most popular applications</h1></br>
        <c:forEach items="${applicationsTop5}" end="4" var="applicationInTop">
            <div class="slide">
                <a href="showApplication?applicationId=${applicationInTop.applicationId}">
                    <img src="${pageContext.request.contextPath}/showImage128?applicationId=${applicationInTop.applicationId}"/>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>