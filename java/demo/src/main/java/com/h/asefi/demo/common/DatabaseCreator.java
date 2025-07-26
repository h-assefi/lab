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

    /**
     * This method is invoked after the constructor and after dependency injection
     * is completed to
     * initialize the database requirements. It will create the database if it does
     * not exist and
     * create the schema if it does not exist.
     */
    @PostConstruct
    public void initializeDatabaseRequirements() {
        createDatabaseIfNotExists();
        createSchemaIfNotExists();
    }

    /**
     * Checks if the target database exists and creates it if it doesn't. The target
     * database is the
     * database that is specified in the application configuration and is the
     * database that the
     * application will use to store its data.
     *
     * Required PostgreSQL permissions:
     * - The user specified by 'app.bootstrap.user' must have the 'CREATEDB'
     * privilege
     * to be able to create new databases.
     * - The user must also have CONNECT privilege on the PostgreSQL server.
     */
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

    /**
     * Ensures that the target schema exists in the database. If the schema does not
     * exist, it will be created.
     * If the schema already exists, no action will be taken. The target schema is
     * the schema that is
     * specified in the application configuration and is the schema that the
     * application will use to
     * store its data.
     *
     * Required PostgreSQL permissions:
     * - The user specified by 'spring.datasource.username' must have the 'CREATE'
     * privilege
     * on the connected database to create new schemas.
     * - The user must also have CONNECT privilege on the database.
     */
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
