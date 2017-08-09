function WebsocketClient(topic){
    this.topic = topic;
    this.socket = new SockJS(topic);
    this.stompClient = Stomp.over(this.socket);
}
WebsocketClient.prototype.getSocket = function () {
    return this.socket;
};
WebsocketClient.prototype.getStompClient = function () {
    return this.stompClient;
}
WebsocketClient.prototype.disconnect= function() {
    this.stompClient.disconnect();
};
WebsocketClient.prototype.connect = function (connectCallback) {
    var self = this;
    this.stompClient.connect({},function (frame) {
        connectCallback(frame, self.stompClient);
    }, function (error) {
        console.error("STOMP protocol error " + error);
    });
};

