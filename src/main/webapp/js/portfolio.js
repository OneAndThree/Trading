function ApplicationModel(webSocketClient) {
    var self = this;

    self.username = ko.observable();
    self.portfolio = ko.observable(new PortfolioModel());
    self.trade = ko.observable(new TradeModel(webSocketClient.getStompClient()));
    self.notifications = ko.observableArray();

    self.connect = function () {
        var stompClient = webSocketClient.getStompClient();
        webSocketClient.connect(self.connectCallback);
    }

    self.connectCallback = function (frame, stompClient) {
        self.username(frame.headers['user-name']);
        stompClient.subscribe("/app/positions", function (message) {
            console.log(message.body);
            self.portfolio().loadPositions(JSON.parse(message.body));
        });
        stompClient.subscribe("/topic/price.stock.*", function (message) {
            self.portfolio().processQuote(JSON.parse(message.body));
        });
        stompClient.subscribe("/user/queue/position-updates", function (message) {
            self.pushNotification("Position update " + message.body);
            self.portfolio().updatePosition(JSON.parse(message.body));
        });
        stompClient.subscribe("/user/queue/errors", function (message) {
            self.pushNotification("Error " + message.body);
        });
    }

    self.pushNotification = function (text) {
        self.notifications.push({notification: text});
        if (self.notifications().length > 5) {
            self.notifications.shift();
        }
    }

    self.logout = function () {
        webSocketClient.disconnect();
        window.location.href = "/logout";
    }
}

function PortfolioModel() {
    var self = this;

    self.rows = ko.observableArray();

    self.totalShares = ko.computed(function () {
        var result = 0;
        for (var i = 0; i < self.rows().length; i++) {
            result += self.rows()[i].shares();
        }
        return result;
    });

    self.totalValue = ko.computed(function () {
        var result = 0;
        for (var i = 0; i < self.rows().length; i++) {
            result += self.rows()[i].value();
        }
        return "$" + result.toFixed(2);
    });

    var rowLookup = {};

    self.loadPositions = function (positions) {
        for (var i = 0; i < positions.length; i++) {
            var row = new PortfolioRow(positions[i]);
            self.rows.push(row);
            rowLookup[row.ticker] = row;
        }
    };

    self.processQuote = function (quote) {
        if (rowLookup.hasOwnProperty(quote.ticker)) {
            rowLookup[quote.ticker].updatePrice(quote.price);
        }
    };

    self.updatePosition = function (position) {
        rowLookup[position.ticker].shares(position.shares);
    };
};

function PortfolioRow(data) {
    var self = this;

    self.company = data.company;
    self.ticker = data.ticker;
    self.price = ko.observable(data.price);
    self.formattedPrice = ko.computed(function () {
        return "$" + self.price().toFixed(2);
    });
    self.change = ko.observable(0);
    self.arrow = ko.observable();
    self.shares = ko.observable(data.shares);
    self.value = ko.computed(function () {
        return (self.price() * self.shares());
    });
    self.formattedValue = ko.computed(function () {
        return "$" + self.value().toFixed(2);
    });

    self.updatePrice = function (newPrice) {
        var delta = (newPrice - self.price()).toFixed(2);
        self.arrow((delta < 0) ? '<i class="glyphicon glyphicon-arrow-down"></i>' : '<i class="glyphicon glyphicon-arrow-up"></i>');
        self.change((delta / self.price() * 100).toFixed(2));
        self.price(newPrice);
    };
};

function MarketDataRow(data) {
    var self = this;
//{"r2":"N/A","t8":"62.63","l1":"65.40","l2":"N/A","j1":"N/A","j3":"N/A","b2":"N/A","b3":"N/A","a":"N/A","b":"N/A","m2":"N/A","k1":"N/A","k2":"N/A","k3":"64839805","l":"4:02pm - <b>65.40</b>","i5":"N/A","m":"65.35 - 66.88","o":"66.82","p":"66.89","a2":"7346880","q":"6/8/2017","r":"N/A","s":"RAI","v":"159452032","w":"43.38 - 67.81","y":"0.00","r1":"7/3/2017"}

    self.symbol = data.s;

    self.price = ko.observable(data.l1);
    self.formattedPrice = ko.computed(function () {
        return "$" + self.price().toFixed(2);
    });
    self.change = ko.observable(data.k2);

}

function TradeModel(stompClient) {
    var self = this;

    self.action = ko.observable();
    self.sharesToTrade = ko.observable(0);
    self.currentRow = ko.observable({});
    self.error = ko.observable('');
    self.tradeType = ko.observableArray(['Mrkt', 'IOC', 'FOK', 'GTC']);
    self.selectedType = ko.observable();

    self.showDetails = function (row) {
        window.location.href = "/quote?symbol=" + row.company;
    }
    self.showBuy = function (row) {
        self.showModal('Buy', row)
    }
    self.showSell = function (row) {
        self.showModal('Sell', row)
    }

    self.showModal = function (action, row) {
        self.action(action);
        self.sharesToTrade(0);
        self.currentRow(row);
        self.error('');
        $('#trade-dialog').modal();
    }

    $('#trade-dialog').on('shown', function () {
        var input = $('#trade-dialog input');
        input.focus();
        input.select();
    })

    var validateShares = function () {
        if (isNaN(self.sharesToTrade()) || (self.sharesToTrade() < 1)) {
            self.error('Invalid number');
            return false;
        }
        if ((self.action() === 'Sell') && (self.sharesToTrade() > self.currentRow().shares())) {
            self.error('Not enough shares');
            return false;
        }
        return true;
    }

    self.executeTrade = function () {
        if (!validateShares()) {
            return;
        }
        var trade = {
            "action": self.action(),
            "ticker": self.currentRow().ticker,
            "shares": self.sharesToTrade(),
            "type": self.selectedType()
        };

        // console.log(trade);

        // stompClient.send("/app/trade", {}, JSON.stringify(trade));
        $('#trade-dialog').modal('hide');
    }
}
