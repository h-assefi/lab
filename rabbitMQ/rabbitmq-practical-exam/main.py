import os
import pika

QUEUE_NAME = "exam"
EXCHANGE_NAME = "exchange.c5eaef6a-b96a-44cc-9b20-e728ff3842ac"
ROUTING_KEY ="c5eaef6a-b96a-44cc-9b20-e728ff3842ac"
MESSAGE = "Hi CloudAMQP, this was fun!"

connection = None
channel = None

exchange_created = False
binding_created = False

try:
    # 1. Connect to RabbitMQ using AMQPS
    # rabbitmq_url = os.environ["RABBITMQ_URL"]
    rabbitmq_url = "amqps://url"

    parameters = pika.URLParameters(rabbitmq_url)
    connection = pika.BlockingConnection(parameters)
    channel = connection.channel()

    print("Connected to RabbitMQ!")

    # 2. Declare a new direct exchange
    channel.exchange_declare(
        exchange=EXCHANGE_NAME,
        exchange_type="direct",
        durable=True
    )
    exchange_created = True

    print(f"Exchange '{EXCHANGE_NAME}' created!")

    # 3. Bind the existing exam queue to the exchange
    channel.queue_bind(
        queue=QUEUE_NAME,
        exchange=EXCHANGE_NAME,
        routing_key=ROUTING_KEY
    )

    binding_created = True
    print(f"Queue '{QUEUE_NAME}' bound to exchange '{EXCHANGE_NAME}' with routing key '{ROUTING_KEY}'!")

    # 4. Enable publisher confirmations
    channel.confirm_delivery()

    # 5. Publish a persistent message
    channel.basic_publish(
        exchange=EXCHANGE_NAME,
        routing_key=ROUTING_KEY,
        body=MESSAGE,
        properties=pika.BasicProperties(
            delivery_mode=2  # Make message persistent
        ),
        mandatory=True
    )

    print("Message successfully published!")
    print(f"Payload: {MESSAGE}")

except Exception as e:
    print(f"An error occurred: {e}")
    raise

finally:
    # 6. Remove the binding
    if channel and channel.is_open:
        if binding_created:
            try:
                channel.queue_unbind(
                    queue=QUEUE_NAME,
                    exchange=EXCHANGE_NAME,
                    routing_key=ROUTING_KEY
                )
                print(f"Binding between queue '{QUEUE_NAME}' and exchange '{EXCHANGE_NAME}' removed!")
            except Exception as e:
                print(f"Binding cleanup failed: {e}")

        # 7. Delete the exchange
        if exchange_created:
            try:
                channel.exchange_delete(exchange=EXCHANGE_NAME)
                print(f"Exchange '{EXCHANGE_NAME}' deleted!")
            except Exception as e:
                print(f"Exchange cleanup failed: {e}")

    # 8. Close the connection
    if connection and connection.is_open:
        connection.close()
        print("Connection to RabbitMQ closed!")