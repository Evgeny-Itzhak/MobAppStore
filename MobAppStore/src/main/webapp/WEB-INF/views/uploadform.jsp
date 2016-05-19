<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Application</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">

    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>

</head>
<body>

<jsp:include page="menu.jsp"/>

<div class="container">

    <h2>UPLOAD YOUR APPLICATION</h2>

    <div class="content">

        <form:form method="POST" enctype="multipart/form-data" action="/uploadApplication" modelAttribute="application">
            <form:errors path="*" cssClass="errorblock" element="div"/>

            <table>
                <tr>
                    <td>Application name:</td>
                    <td><form:input path="applicationName"/></td>
                    <td><form:errors path="applicationName" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>Application Description:</td>
                    <td><form:textarea path="applicationDescription" rows="5" cols="55" maxlength="250"/></td>
                    <td><form:errors path="applicationDescription" cssClass="error"/></td>
                </tr>

                <tr>
                    <td>Category :</td>
                    <td>
                        <select name="categoryId" required>
                            <option selected value="">--- Select category ---</option>
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.categoryId}">${category.categoryName}</option><br>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>

            <br/>
            <p><strong>Specify Application (*.zip) to Post:</strong><br/></p>
            <p><input type="file" name="applicationZipMultipart" id="zipfile" accept="zip/*" required/></p>
            <%--<p><form:input path="applicationZip" id=""/></p>--%>
            <hr/>
            <br/>

            <%--<form:errors path="file"/>--%>
            <p><strong>Specify image (512*512) to Post:<br/>(optional)</strong><br/></p>
            <p><input type="file" name="image512" id="image1" accept="image/*"/></p>
            <hr/>
            <br/>

            <%--<form:errors path="file"/>--%>
            <p><strong>Specify image (128*128) to Post:<br/>(optional)</strong><br/></p>
            <p><input type="file" name="image128" id="image2" accept="image/*"/></p>
            <hr/>
            <br/>

            <p><input type="submit" value="Upload Application" name="SubmitButton" id="SubmitButton"/>
                <input type="reset" value="Reset Form"/></p>

        </form:form>
    </div>

</div>

</body>
</html>