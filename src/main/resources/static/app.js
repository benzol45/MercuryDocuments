var stompClient = null;

function connect() {
    var socket = new SockJS('/storm-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notification', function (greeting) {
            showNotification(JSON.parse(greeting.body).content);
        });
    });
}

function showNotification(message) {
    document.getElementById("notification").innerHTML = "<p>" + message + "</p>";
}
