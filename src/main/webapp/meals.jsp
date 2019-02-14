<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .green{background: green;
               color: white;
        }
        .red{background: red}
    </style>
</head>
<body>

    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <br/>
    <table>
        <tr>
            <td>DateTime</td>
            <td>Description</td>
            <td>Calories</td>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <c:choose>
                <c:when test="${meal.excess}">
                    <tr class="red">
                </c:when>
                <c:otherwise>
                    <tr class="green">
                </c:otherwise>
            </c:choose>
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
        <tr>

        </tr>
    </table>
</body>
</html>
