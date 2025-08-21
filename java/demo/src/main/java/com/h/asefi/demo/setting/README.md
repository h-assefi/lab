# Setting Module

The `setting` module provides a simple way to store and manage application settings as key-value pairs in the database. It is useful for saving configuration values, feature flags, or other dynamic settings that may need to be updated at runtime without redeploying the application.

## Features

- **Setting Entity**:  
  Represents a setting as a key-value pair in the database.

- **CRUD Operations**:  
  Easily add, update, and retrieve settings using the provided service and repository.

- **Validation**:  
  Ensures keys are not blank and handles errors gracefully.

- **Centralized Access**:  
  Access and update settings from anywhere in your application via the `SettingService`.

## Components

- `Setting.java`: Entity class representing a setting record.
- `SettingRepository.java`: Spring Data repository for database operations.
- `SettingService.java` / `SettingServiceImpl.java`: Service layer for business logic and validation.
- `SettingKey.java`: Constants for commonly used setting keys.

## Usage

- Store configuration values that may change at runtime (e.g., maintenance mode, feature toggles).
- Retrieve settings by key using `SettingService.getSettingByKey(key)`.
- Add or update settings using `SettingService.addOrUpdate(key, value)`.

This module helps you manage application
