<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserAdd</title>
</head>
<body>
<h1>Зарегистрируйтесь</h1>
    <form method="post" action="/signup" accept-charset="UTF-8">
        <table>
            <tr>
                <td><label for="FirstName">Имя</label></td>
                <td><input id="FirstName" type="text" name="firstName" required></td>
            </tr>
            <tr>
                <td><label for="LastName">Фамилия</label></td>
                <td><input id="LastName" type="text" name="lastName" required></td>
            </tr>
            <tr>
                <td><label for="Login">Логин</label></td>
                <td><input id="Login" type="text" name="login" required></td>
            </tr>
            <tr>
                <td><label for="Password" >Пароль</label></td>
                <td><input id="Password" type="password" name="password" required></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" value="Зарегистрироваться"></td>

            </tr>
        </table>
    </form>
    <form action="/home">
        <input type="submit" value="Отмена">
    </form>
</body>
</html>
