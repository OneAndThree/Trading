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
            console.group("/app/positions");
            console.info(message.body);
            console.groupEnd("/app/positions");
            self.portfolio().loadPositions(JSON.parse(message.body));
        });
        stompClient.subscribe("/topic/price.stock.*", function (message) {
            console.log(JSON.parse(message.body));
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
        if (rowLookup.hasOwnProperty(quote.s)) {
            rowLookup[quote.s].updatePrice(quote.p);
        }
    };

    self.updatePosition = function (position) {
        rowLookup[position.s].shares(position.shares);
    };
};

function PortfolioRow(data) {
    var self = this;
    /*self.symbol = data.s;
    self.open =  ko.observable(data.o);
    self.bid =  ko.observable(data.b);
    self.ask = ko.observable(data.a);
    self.price = ko.observable(data.p);
    self.lastTradePrice = ko.observable(data.l1);
    self.avgVolume = ko.observable(data.a2);
    self.dividenYield = ko.observable(data.y);
    self.peRatio = ko.observable(data.r);
    self.dayRange = ko.observable(data.m);
*/
    self.company = ko.observable(data.company);
    self.ticker = data.ticker;
    self.price = ko.observable(data.p);


    // self.formattedPrice = ko.computed(function () {
    //     return "$" + self.price().toFixed(2);
    // });
    self.change = ko.observable(0);
    self.arrow = ko.observable();
    self.shares = ko.observable(0);
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

function TradeModel(stompClient) {
    var self = this;

    self.action = ko.observable();
    self.sharesToTrade = ko.observable(0);
    self.currentRow = ko.observable({});
    self.error = ko.observable('');
    self.tradeType = ko.observableArray(['Mrkt', 'IOC', 'FOK', 'GTC']);
    self.selectedType = ko.observable();

    self.showDetails = function (row) {
        console.group("showDetails");
        console.warn(row);
        console.groupEnd("showDetails");
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

        stompClient.send("/app/tradeOrderDetail", {}, JSON.stringify(trade));
        $('#trade-dialog').modal('hide');
    }
}
var QuoteModal = function (data) {
    var self = this;
    self.previous_close = ko.observable();
    self.open = ko.observable();
    self.close = ko.observable();
    self.bid = ko.observable();
    self.ask = ko.observable();
    self.volume = ko.observable();
    self.lowest = ko.observable();
    self.highest = ko.observable();
    self.scale = ko.observable();
    self.gmtoffset = ko.observable();
    self.instrumentType = ko.observable();
    self.change = ko.observable();
    self.change_percent = ko.observable();

    self.splitData = function () {
        var result = JSON.parse(data.result);
        var quote = result[0];
        self.previous_close(quote.meta.previousClose);
        self.gmtoffset(quote.meta.gmtoffset);
        self.scale(quote.meta.scale);
        self.instrumentType(quote.meta.instrumentType);
        self.highest(quote.indicators.quote[0].high.pop());
        self.lowest(quote.indicators.quote[0].low.pop());
        self.open(quote.indicators.quote[0].open.pop());
        self.close(quote.indicators.quote[0].close.pop());
        self.volume(quote.indicators.quote[0].volume.pop());

        console.log(quote);
    }
};