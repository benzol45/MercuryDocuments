var stompClient = null;

function doProcessing() {
    connect();
    window.location.href = '/processAllDocumets';
}

function connect() {
    var match = document.cookie.match(new RegExp('(^| )notificationID=([^;]+)'));
    var notificationID = '';
    if (match) {
        notificationID = match[2];
        var socket = new SockJS('/storm-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/notification/' + notificationID, function (greeting) {
                showNotification(JSON.parse(greeting.body).content);
            });
        });
    }
}

function showNotification(message) {
    document.getElementById("notification").innerHTML = "<p>" + message + "</p>";
}
