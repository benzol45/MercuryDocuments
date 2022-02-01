var stompClient = null;

function doProcessing() {
    connect();
    window.location.href = '/processAllDocumets';
}

function connect() {
    let sessionID = document.getElementById("test").innerText;
    var socket = new SockJS('/storm-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notification/'+sessionID, function (greeting) {
            showNotification(JSON.parse(greeting.body).content);
        });
    });
}

function showNotification(message) {
    document.getElementById("notification").innerHTML = "<p>" + message + "</p>";
}
