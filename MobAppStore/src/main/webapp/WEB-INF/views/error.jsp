<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <style>
        #errorMessage{
            background-color: #ffd3cd;
            border: #ff0e15;
            text-line-through-color: #3338ff;
            padding: 3%;
        }

        #errorMessage p{
            font-family: "Comic Sans MS";
            font-size: 450%;
            color: #ff0e15;
        }
    </style>
</head>
<body>

<jsp:include page="menu.jsp"/>

<div class="container">
    <div id="errorMessage" align="center">
        <p>
            <c:out value="${errorMessage}"/>
        </p>
    </div>
</div>

</body>
</html>