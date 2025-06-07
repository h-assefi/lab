# Elastic Stack Project

This project sets up an Elastic Stack environment using Docker Compose. The Elastic Stack is a collection of open-source software products developed by Elastic, including **Elasticsearch**, **Kibana**, **Logstash**, and **Beats**.

## Files and Directories

- `.env`: Environment variable file containing configuration settings for the Elastic Stack.
- `kibana.yml`: Configuration file for Kibana.
- `logstash.conf`: Configuration file for Logstash.
- `docker-compose.yml`: Docker Compose file defining the services and their configurations.

## Overview

This project uses Docker Compose to create a containerized environment for the Elastic Stack. The services defined in the `docker-compose.yml` file include:

- **Elasticsearch** (`es01`)
- **Kibana**
- **Logstash** (`logstash01`)

The `.env` file contains environment variables that are used to configure the services. These variables include settings such as passwords, ports, and version numbers.

The `kibana.yml` file configures Kibana to connect to the Elasticsearch instance and defines the Fleet Server settings.

The `logstash.conf` file configures Logstash to read data from a file input and output it to Elasticsearch.

## Relationship Between Files

The files in this project are related as follows:

- The `docker-compose.yml` file defines the services and their configurations, including the environment variables defined in the `.env` file.
- The `kibana.yml` file is used to configure Kibana, which is defined as a service in the `docker-compose.yml` file.
- The `logstash.conf` file is used to configure Logstash, which is also defined as a service in the `docker-compose.yml` file.
- The `.env` file contains environment variables that are used to configure the services defined in the `docker-compose.yml` file.

## Usage

To use this project, follow these steps:

1. Create a new directory for the project and navigate to it in your terminal.
2. Copy the `.env`, `kibana.yml`, `logstash.conf`, and `docker-compose.yml` files into the directory.
3. Update the environment variables in the `.env` file as needed.
4. Run `docker-compose up` to start the services.
5. Access Kibana at [http://localhost:5601](http://localhost:5601).

> **Note:** This is a basic setup, and you may need to modify the configuration files to suit your specific use case.