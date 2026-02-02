package com.JDBC.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DEFAULT_URL = "jdbc:mysql://127.0.0.1:3306/college_program";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "mysql";

    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL Driver");
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || url.isEmpty())
            url = DEFAULT_URL;
        if (username == null || username.isEmpty())
            username = DEFAULT_USERNAME;
        if (password == null || password.isEmpty())
            password = DEFAULT_PASSWORD;

        return DriverManager.getConnection(url, username, password);
    }
}
