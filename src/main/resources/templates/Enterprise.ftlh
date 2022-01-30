<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>${enterprise.name}</h1>
    <h4>${enterprise.uuid.toString()}</h4>
    <h4>${enterprise.mercuryName}</h4>

    <form method="post" action="/enterprise/${enterprise.uuid.toString()}/processAllDocs">
        <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden"/>
        <input type="submit" value="Погасить ВСЕ документы">
    </form>
    <br>

    <#if viewAllDocs>
    <a href="/enterprise/${enterprise.uuid.toString()}">Показать только непогашенный</a>
    <#else>
    <a href="/enterprise/${enterprise.uuid.toString()}/allDocs">Показать ВСЕ документы (в т.ч. погашенные)</a>
    </#if>
    <table border="1">
        <tr>
            <th align="center">Погашен</th>
            <th align="center">Дата</th>
            <th align="center">Товар по ВСД</th>
            <th align="center">Открыть ВСД</th>
        </tr>
        <#list documentList as document>
            <tr>
                <td align="center"><h1><#if document.processed>+<#else>-</#if></h1></td>
                <td>${document.date?date}</td>
                <td>${document.content}</td>
                <td><a href="https://t2-mercury.vetrf.ru/pub/operatorui?_action=findVetDocumentFormByUuid&uuid=${document.uuid.toString()}">открыть</a></td>
            </tr>
        </#list>
    </table>



</body>
</html>