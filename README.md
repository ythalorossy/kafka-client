# Kafka Client

A Spring Boot application demonstrating **Apache Kafka** integration with WebSocket support. Shows how to produce and consume messages, configure serializers/deserializers, and push real-time updates via WebSocket.

## Features

- **Kafka Producer** — publish custom messages to Kafka topics
- **Kafka Consumer** — subscribe and process messages from topics
- **Custom Serializers** — `YCustomMessageSerializer` / `YCustomMessageDeserializer`
- **WebSocket Integration** — real-time message delivery via WebSocket
- **Configuration Properties** — externalized Kafka settings via `application.yml`

## Tech Stack

| Component | Technology |
|---|---|
| Framework | Spring Boot 3.2.1 |
| Messaging | Apache Kafka |
| Serialization | Custom Kafka serializers |
| Real-time | WebSocket (STOMP) |
| Build | Maven |
| Container | Docker |

## Project Structure

```
src/main/java/com/ythalorossy/kafkaclient/
├── KafkaClientAPI.java                      # Main entry point
├── YCustomMessage.java                      # Custom message model
├── configurations/
│   ├── KafkaConfiguration.java             # Producer & Consumer factories
│   ├── KafkaConfigurationProperties.java   # External config binding
│   └── Producer.java                        # Kafka producer setup
├── websocket/
│   ├── KafkaWebSocket.java                 # WebSocket handler (sends Kafka msgs)
│   ├── WebSocketConfig.java                # WebSocket configuration
│   ├── Greeting.java                        # Response model
│   └── HelloMessage.java                   # Incoming message model
└── serializations/
    ├── YCustomMessageSerializer.java        # JSON serializer for Kafka
    └── YCustomMessageDeserializer.java       # JSON deserializer for Kafka
```

## Getting Started

### Prerequisites
- Java 17+
- Docker (for Kafka broker)

### Run with Docker

```bash
docker run -p 9092:9092 -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 apache/kafka:latest
```

### Run Locally

```bash
./mvnw spring-boot:run
```

### Build

```bash
./mvnw package -DskipTests
```

## Configuration

Kafka settings are externalized via `application.yml` (or environment variables):

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: my-group
      auto-offset-reset: earliest
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
```

## WebSocket

The app exposes a WebSocket endpoint that integrates with Kafka:
- Connect to `ws://localhost:8080/ws`
- Send messages via WebSocket → routed to Kafka topic
- Consume Kafka messages → push to connected WebSocket clients

## Docker

```bash
docker build -t kafka-client .
docker run kafka-client
```

---

**YRoss** · [LinkedIn](https://www.linkedin.com/in/ythalorossy/) · [GitHub Profile](https://github.com/ythalorossy)