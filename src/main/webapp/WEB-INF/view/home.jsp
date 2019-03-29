<%--
  Created by IntelliJ IDEA.
  User: sankur
  Date: 2019-03-14
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Привет</h1>

<sec:authorize access="hasAuthority('admin')">
    <a href="/admin/users">Все пользователи</a>
</sec:authorize>


<a href="/logout">Выйти</a>
</body>
</html>
