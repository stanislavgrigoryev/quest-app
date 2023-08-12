<%--
  Created by IntelliJ IDEA.
  User: setka
  Date: 08.07.2023
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quest</title>
</head>
<body>
<h1>${question.question}</h1>
<form method="post" action="/quest">
    <input type="radio" name="answer" value="1"/>${question.answerOne}<br>
    <input type="radio" name="answer" value="2"/>${question.answerTwo}<br>
    <input type="submit" value="Ответить"/>
</form>
<footer>
    Статистика:<br>
    IP address: ${statistic.ipAddress}<br>
    Имя в игре: ${statistic.name}<br>
    Колличество игр: ${statistic.count}<br>
</footer>

</body>
</html>
