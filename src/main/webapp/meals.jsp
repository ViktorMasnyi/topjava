<%--
  Created by IntelliJ IDEA.
  User: viktorma
  Date: 6/11/20
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User meals</title>
</head>
<body>
    <h1>User meals</h1>
    <table>
        <c:forEach var="meal" items="${data}">
            <c:if test="${meal.isExcess() == true}">
                <tr style="color: red">
            </c:if>
            <c:if test="${meal.isExcess() == false}">
                <tr style="color: black">
            </c:if>
                <td style="border: solid 1px gray">
                    ${meal.getDateTime().toLocalDate()} ${meal.getDateTime().toLocalTime()}
                </td>
                <td style="border: solid 1px gray">
                    "${meal.getDescription()}"
                </td>
                <td style="border: solid 1px gray">
                    "${meal.getCalories()}"
                </td>
                <td style="border: solid 1px gray">
                    "${meal.isExcess()}"
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
