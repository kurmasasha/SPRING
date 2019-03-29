<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>Все пользователи</h1>
    <table>
        <tr>
            <td>ID</td>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Логин</td>
            <td>Роль</td>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.login}</td>
                <c:forEach items="#{user.roles}" var="role">
                    <td>${role.authority}</td>
                </c:forEach>

                <td>
                    <a href="/admin/edit?id=${user.id}">Редактировать</a>
                </td>
                <td>
                    <a href="/admin/delete?id=${user.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
<div>
    <form method="get">
        <a href="/home">Домой</a>
    </form>

</div>
</body>
</html>
