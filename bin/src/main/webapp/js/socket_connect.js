function socketConnect() {
    var socket = new SockJS('/portfolio');
    var stompClient = Stomp.over(socket);

    stompClient.subscribe('/user/app/realTime', function (messages) {
       //TODO? realTime data
    });
}
