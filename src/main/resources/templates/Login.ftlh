<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body>
    <h1>Авторизация</h1>
    <#if RequestParameters.error??><p style="color: red">Неверный логин или пароль</p></#if>
    <form action="/login" method="post">
        <label> User Name : <input type="text" name="username"/> </label>
        <label> Password: <input type="password" name="password"/> </label>
        <label><input type="checkbox" name="rememberMe"/> запомнить</label>
        <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden"/>
        <input type="submit" value="Login"/>
    </form>

    <a href="/registration">Регистрация нового пользователя</a>
</body>
</html>