# RabbitMQ Practical Exam

## Purpose

This project is a practical RabbitMQ exercise that demonstrates how to:

- Connect to a RabbitMQ broker over AMQPS.
- Create a durable direct exchange.
- Bind an existing queue to the exchange with a routing key.
- Enable publisher confirmations.
- Publish a persistent message.
- Remove the binding and delete the exchange during cleanup.
- Close the RabbitMQ connection safely.

The application uses the `exam` queue and publishes the message `Hi CloudAMQP, this was fun!` to an exchange named `exchange.c5eaef6a-b96a-44cc-9b20-e728ff3842ac`.

## Tech Stack

- **Language:** Python
- **Message broker:** RabbitMQ
- **Messaging protocol:** AMQP over TLS (AMQPS)
- **Python client:** Pika
- **Environment:** Python virtual environment (`.venv`)
- **Package manager:** pip

## Prerequisites

- Python 3
- A RabbitMQ or CloudAMQP broker
- Pika installed in the Python environment

Install Pika with:

```bash
pip install pika
```

## Configuration

Set the broker AMQPS URL in `main.py` before running the project:

```python
rabbitmq_url = "amqps://your-rabbitmq-url"
```

The broker credentials and URL should be kept private and should not be committed to source control.

## Running the Project

From the project directory, run:

```bash
python main.py
```
