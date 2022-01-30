<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body>
    <h1>Регистрация нового пользователя</h1>
    <#if RequestParameters.error??><p style="color: red">Введённое имя пользователя уже используется</p></#if>
    <form action="/registration" method="post">
        <label> Full Name : <input type="text" name="fullName"/> </label>
        <label> User Name : <input type="text" name="username"/> </label>
        <label> Password: <input type="password" name="password"/> </label>
        <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden"/>
        <input type="submit" value="Register"/>
    </form>
</body>
</html>