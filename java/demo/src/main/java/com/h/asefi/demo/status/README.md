# Status Module

The `status` module provides a simple health check and maintenance mode management for your application.

## Features

- **Health Check Endpoint**:  
  The `/status` endpoint returns the current availability of the system. It responds with `OK` if the service is running normally, or `MAINTENANCE` if the service is in maintenance mode.

- **Maintenance Mode Control**:  
  The `/status/maintainable/{status}` endpoint allows you to enable or disable maintenance mode via a POST request.

## Components

- `StatusController.java`: Exposes REST endpoints for health check and maintenance mode.
- `StatusService.java` / `StatusServiceImpl.java`: Business logic for status and maintenance mode, including caching for efficient status checks.
- `dto/Status.java`: Enum representing possible system statuses (`OK`, `MAINTENANCE`).
- `dto/StatusResponseDTO.java`: DTO for status responses.

## Usage

- Use `/status` as a health check endpoint for load balancers or monitoring tools.
- Use `/status/maintainable/{status}` to toggle maintenance mode (e.g., for deployments or scheduled downtime).

This module helps you monitor and control your application's availability in
