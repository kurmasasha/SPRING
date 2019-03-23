<%--
  Created by IntelliJ IDEA.
  User: sankur
  Date: 2019-03-10
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Введите логин и пароль</h1>
    <form method="post" action="/signin" accept-charset="UTF-8">
        <table>
            <tr>
                <td><label for="login">Login</label></td>
                <td><input id="login" type="text" name="login"></td>
            </tr>
            <tr>
                <td><label for="password">Password</label></td>
                <td><input id="password" type="password" name="password"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" value="Войти"></td>
            </tr>
        </table>
        <a href="/signup">Зарегистрироваться</a>
    </form>

</body>
</html>
