<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Enterprise</title>
</head>
<body>
    <h1>Добавление нового предприятия</h1>
    <form method="post" action="/enterprise/check">
        <p>
            <label for="name">Удобное вам название:</label>
            <input type="text" id="name" name="name" size="100" placeholder="Name">
        </p>
        <p>
            <label for="uuid">GUID (глобальный идентификатор предприятия):</label>
            <input type="text" id="uuid" name="uuidString" size="50"  placeholder="UUID">
        </p>
        <p>
            <label for="mercLogin">Имя пользователя ГИС Меркурий:</label>
            <input type="text" id="mercLogin" name="mercLogin" size="50"  placeholder="Пользователь Меркурий">
        </p>
        <p>
            <label for="vetisApiLogin">Login Ветис.API:</label>
            <input type="text" id="vetisApiLogin" name="vetisApiLogin" size="50"  placeholder="API Login">
        </p>
        <p>
            <label for="vetisApiPassword">Password Ветис.API:</label>
            <input type="text" id="vetisApiPassword" name="vetisApiPassword" size="50"  placeholder="API Password">
        </p>
        <p>
            <label for="vetisApiKey">APIKey Ветис.API:</label>
            <input type="text" id="vetisApiKey" name="vetisApiKey" size="50"  placeholder="API Key">
        </p>
        <p>
            <label for="vetisApiIssuerID">IssuerID Ветис.API:</label>
            <input type="text" id="vetisApiIssuerID" name="vetisApiIssuerID" size="50"  placeholder="API IssuerID">
        </p>

        <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden"/>
        <input type="submit" value="Проверить">

        <p>GUID можно найти в любом ВСД в информации о предприятии или на странице входа ГИС "Меркурий" при выборе предприятия</p>
        <p>Имя пользователя ГИС "Меркурий" то же, под которым вы заходите в веб-интерфейс</p>
        <p>Данные Ветис.API должны прийти вам в письме с темой "Предоставление доступа к Ветис.API"</p>
    </form>
</body>
</html>
