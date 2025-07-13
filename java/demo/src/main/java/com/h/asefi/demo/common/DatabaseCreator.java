package com.h.asefi.demo.common;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class DatabaseCreator {
    @Value("${app.bootstrap.url}")
    private String bootstrapUrl;

    @Value("${app.bootstrap.user}")
    private String bootstrapUser;

    @Value("${app.bootstrap.password}")
    private String bootstrapPassword;

    @Value("${app.database.name}")
    private String targetDatabase;

    @Value("${spring.datasource.url}")
    private String appJdbcUrl;

    @Value("${spring.datasource.username}")
    private String appDbUser;

    @Value("${spring.datasource.password}")
    private String appDbPassword;

    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private String schemaName;

    @PostConstruct
    public void initializeDatabaseRequirements() {
        createDatabaseIfNotExists();
        createSchemaIfNotExists();
    }

    private void createDatabaseIfNotExists() {
        try (Connection connection = DriverManager.getConnection(bootstrapUrl, bootstrapUser, bootstrapPassword);
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + targetDatabase + "'");
            if (!rs.next()) {
                stmt.execute("CREATE DATABASE " + targetDatabase);
                System.out.println("✅ Database created: " + targetDatabase);
            } else {
                System.out.println("✅ Database already exists: " + targetDatabase);
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to check or create database: " + targetDatabase, e);
        }
    }

    private void createSchemaIfNotExists() {
        try (Connection conn = DriverManager.getConnection(appJdbcUrl, appDbUser, appDbPassword);
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);
            System.out.println("✅ Schema ensured: " + schemaName);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to create schema: " + schemaName, e);
        }
    }
}
