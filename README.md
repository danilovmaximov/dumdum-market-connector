# dumdum market connector

Self-study project: a system that connects to cryptocurrency exchanges and collects information about what's happening in their markets.

## Features

Here's a list of features included in this project:

### Stage 1 - REST API integration

Connect to Binance using ordinary HTTP requests.

Retrieve:
- [ ] Current BTC price.
- [ ] Historical candlestick data.
- [ ] 24-hour trading statistics.
- [ ] Current order book snapshot.

### Stage 2 - WebSocket integration

Connect to Binance and subscribe to:
- [ ] BTC/USDT trades.
- [ ] BTC/USDT price updates.
- [ ] BTC/USDT candlestick updates.

### Stage 3 - Data persistence

Store received information in PostgreSQL. Details TBD.

### Stage 4 - Order book processing

Start maintaining a local copy of the exchange's order book.
- [ ] Receive initial snapshot.
- [ ] Apply subsequent updates in the correct sequence.
- [ ] Display data in a readable way.

## Building & Running

To build or run the project, use one of the following tasks:

| Task              | Description       |
|-------------------|-------------------|
| `./gradlew test`  | Run the tests     |
| `./gradlew build` | Build the project |
| `./gradlew run`   | Run the server    |
