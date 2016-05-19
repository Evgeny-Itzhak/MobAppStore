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

    <div id="application">
        <form:form method="POST">
            <table class="applicationTable">
                <tr>
                    <td>
                        <h1>
                            <c:out value="${application.applicationName}"/>
                        </h1>
                    </td>
                    <td rowspan="2">
                        <h2>Application Description</h2> </br>
                        <c:out value="${application.applicationDescription}"/> </br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <img src="${application.applicationImage512 == null ? "../images/icon_default_512_512.png" : pageContext.request.contextPath += "/showImage512?applicationId=" += application.applicationId}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="buttonDownloadSector" align="right">
                            <h2><a href="/downloadApplication?applicationId=${application.applicationId}">DOWNLOAD</a></h2>
                        </div>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>

</body>
</html>