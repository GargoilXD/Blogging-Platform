package org.blogging.platform.DatabaseInitializer;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class JDBCDatabaseInitializer {
    public static void initializeDatabase() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("POSTGRESQL_USERNAME");
        String password = dotenv.get("POSTGRESQL_PASSWORD");
        try (
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM pg_database WHERE datname = 'blog'")
            ) {
            // Check if database exists
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) return;
            }
            // Database does not exist, create it
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE DATABASE blog");
                System.out.println("JDBC Database Created.");
            }
        }
    }
}
