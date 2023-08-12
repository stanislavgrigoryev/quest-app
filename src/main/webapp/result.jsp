<%--
  Created by IntelliJ IDEA.
  User: setka
  Date: 15.07.2023
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quest</title>
</head>
<body>
${result.text}
<br>
<c:choose>
    <c:when test="${result.win}">
        <b>Победа</b>
    </c:when>
    <c:otherwise>
        <b>Поражение</b>
    </c:otherwise>
</c:choose>
<br>
<form method="get" action="/quest">
    <input type="submit" value="Начать заново"/>
</form>
</body>
</html>
