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
    <form method="post" action="/enterprise/add">
        <p>
            <label for="uuid">UUID:</label>
            <input type="text" readonly="readonly" id="uuid" name="uuidString" size="50" value="${uuid}">
        </p>
        <p>
            <label for="name">Name:</label>
            <input type="text" readonly="readonly" id="name" name="name" size="150" value="${enterprise.name}">
        </p>
        <p>
            <input type="hidden" name="verived" value=<#if isVerifed>true<#else>false</#if>>
            <b>Статус:  <#if isVerifed><font color="#006400">Корректен</font><#else><font color="#a52a2a">Некорректен</font></#if></b>
        </p>
        <p>
            <label for="mercuryName">Name in GIS Mercury:</label>
            <input type="text" readonly="readonly" id="mercuryName" name="mercuryName" size="255" value="${mercuryName}">
        </p>
        <p>
            <label for="vetInspection">Контроль входящих партий осуществляется ветврачом</label>
            <input type="checkbox" id="vetInspection" name="vetInspection">
        </p>
        <p>
            <label for="autoProcessing">Автоматически гасить поступающие ВСД</label>
            <input type="checkbox" id="autoProcessing" name="autoProcessing">
        </p>

        <input type="hidden" name="mercLogin" value="${enterprise.mercLogin}"/>
        <input type="hidden" name="vetisApiLogin" value="${enterprise.vetisApiLogin}"/>
        <input type="hidden" name="vetisApiPassword" value="${enterprise.vetisApiPassword}"/>
        <input type="hidden" name="vetisApiKey" value="${enterprise.vetisApiKey}"/>
        <input type="hidden" name="vetisApiIssuerID" value="${enterprise.vetisApiIssuerID}"/>

        <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden"/>
        <input type="submit" value="Add" <#if isVerifed==false>disabled</#if>>
    </form>
</body>
</html>