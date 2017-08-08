function QuoteModel(webSocketClient) {
    var self = this;

    self.symbol = ko.observable();

    self.connect = function () {
        var stompClient = webSocketClient.getStompClient();
        webSocketClient.connect(self.connectCallback);
    }

    self.connectCallback = function (frame, stompClient) {
        //todo? real time data
        stompClient.subscribe("/app/positions", function (message) {
            self.portfolio().loadPositions(JSON.parse(message.body));
        });

    }
}