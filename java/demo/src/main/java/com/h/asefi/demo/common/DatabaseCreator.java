package com.h.asefi.demo.common;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
     * Ensures that the target database exists in the PostgreSQL server. If the
     * target database does not exist, it will be created.
     *
     * Required PostgreSQL permissions:
     * - The user specified by 'app.bootstrap.user' must have the 'CREATEDB'
     * privilege
     * to be able to create new databases.
     * - The user must also have CONNECT privilege on the PostgreSQL server.
     *
     * Example PostgreSQL commands to grant these permissions:
     * -- Grant CREATEDB privilege to the bootstrap user
     * ALTER USER your_bootstrap_user CREATEDB;
     * -- Grant CONNECT privilege on the server (usually granted by default)
     * -- To explicitly grant on a specific database:
     * GRANT CONNECT ON DATABASE postgres TO your_bootstrap_user;
     */
    private void createDatabaseIfNotExists() {
        try (Connection connection = DriverManager.getConnection(bootstrapUrl, bootstrapUser, bootstrapPassword);
                PreparedStatement stmt = connection.prepareStatement("SELECT 1 FROM pg_database WHERE datname = ?")) {

            stmt.setString(1, targetDatabase);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                try (PreparedStatement createStmt = connection.prepareStatement("CREATE DATABASE ?")) {
                    createStmt.setString(1, targetDatabase);
                    createStmt.execute();
                    System.out.println("✅ Database created: " + targetDatabase);
                }
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
     * the schema that is specified in the application configuration and is the
     * schema that the
     * application will use to store its data.
     *
     * Required PostgreSQL permissions:
     * - The user specified by 'spring.datasource.username' must have the 'CREATE'
     * privilege
     * on the connected database to create new schemas.
     * - The user must also have CONNECT privilege on the database.
     *
     * Example PostgreSQL commands to grant these permissions:
     * -- Grant CREATE privilege on the database to the application user
     * GRANT CREATE ON DATABASE your_database TO your_app_user;
     * -- Grant CONNECT privilege (usually granted by default)
     * GRANT CONNECT ON DATABASE your_database TO your_app_user;
     */
    private void createSchemaIfNotExists() {
        try (Connection conn = DriverManager.getConnection(appJdbcUrl, appDbUser, appDbPassword);
                Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE SCHEMA IF NOT EXISTS public");
            System.out.println("✅ Schema ensured: public");

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to create schema: public", e);
        }
    }
}
