# lab

Scientists learn from books, but real learning happens in the lab.

This is where I try things out by building them: backend services, storage and messaging setups, and smaller exercises. Some projects are experiments; others grew into more complete demos. Either way, I learn more when I can make something work.

## What's on the bench

| Project                                                           | What I explored                                                                                                                                                                      |
| ----------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| [Java / Spring Boot demo](./java/demo/README.md)                  | A modular REST application with PostgreSQL, Liquibase migrations, caching, exception handling, and tests.                                                                            |
| [Node.js API Gateway](./nodejs/api-gateway/README.md)             | A TypeScript and Express gateway that routes requests to backend services and handles authorization, CSRF, and response formatting.                                                  |
| [MinIO + Spring Boot](./minio/minio-springboot-demo/README.md)    | Connecting a Spring application to S3-compatible object storage for file operations. There is also a [standalone MinIO setup](./minio/single-node-single-drive/docker-compose.yaml). |
| [Elastic Stack](./elk-stack/README.md)                            | Running Elasticsearch, Kibana, and Logstash with Docker Compose to ingest and explore logs.                                                                                          |
| [RabbitMQ exercise](./rabbitMQ/rabbitmq-practical-exam/README.md) | A small Python and Pika exercise with AMQPS, exchange and queue bindings, and publisher confirms.                                                                                    |
| [Python calculator](./python/calculator/README.md)                | A command-line calculator, with arithmetic kept separate from the interactive part and covered by unit tests.                                                                        |

## Practical Backend Engineering Projects

### Event-Driven Order Processing Platform

**Repository:** [event-driven-order-platform](https://github.com/h-assefi/event-driven-order-platform)

A Java 21 and Spring Boot microservices project demonstrating event-driven architecture using RabbitMQ.

**Key engineering topics:**

- Asynchronous communication using RabbitMQ and Spring AMQP.
- Exchange types, routing keys, queues and message acknowledgment.
- Retry mechanisms and Dead Letter Exchanges.
- Idempotent message consumers.
- Transactional Outbox Pattern.
- Integration testing with Testcontainers.
- Docker-based local infrastructure.

**Status:** In development.

Most projects have their own README with setup details. Have a look around; the small experiments are part of the learning too.
