<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/app.js"></script>
    <!--<script>$(document).ready ( connect());-->
    </script>
</head>
<body>
    <noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
        enabled. Please enable
        Javascript and reload this page!</h2></noscript>
    <h1>${fullName}</h1>
    <form action="/logout" method="post">
        <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden"/>
        <input type="submit" value="Logout"/>
    </form>
    <br>
    <br>

    <form action="/getAllDocumets" method="get">
        <input type="submit" value="Проверить новые документы"/>
    </form>
    <br>
    <button onclick="doProcessing()">Выполнить гашение документов</button>
    <!--<form action="/processAllDocumets" method="get">
        <input type="submit" value="Выполнить гашение документов"/>
    </form>-->
    <br>
    <div style="color: #0000FFFF" id="notification"></div>
    <br>

    <a href="/enterprise/add">Добавить предприятие</a>
    <br>
    <table border="1">
        <tr>
            <th hidden="hidden">uuid</th>
            <th align="center">Наименование предприятия</th>
            <th align="center">Непогашенных документов</th>
            <th align="center">Подробнее</th>
        </tr>
        <#list enterpriseDocumentList as enterpriseDocument>
            <tr>
                <td hidden="hidden">${enterpriseDocument.enterprise.uuid}</td>
                <td>${enterpriseDocument.enterprise.name}</td>
                <td align="center">${enterpriseDocument.documentCounter}</td>
                <td><a href="/enterprise/${enterpriseDocument.enterprise.uuid}">перейти</a></td>
            </tr>
        </#list>
    </table>
    <div id="test" hidden>${session}</div>
</body>
</html>