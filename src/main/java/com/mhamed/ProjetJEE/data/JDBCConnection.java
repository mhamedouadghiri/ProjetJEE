package com.mhamed.ProjetJEE.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {
    private static Connection connection;

    private static String DRIVER_CLASS;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        try {
            InputStream in = JDBCConnection.class.getClassLoader().getResourceAsStream("application.properties");

            Properties properties = new Properties();
            properties.load(in);

            DRIVER_CLASS = properties.getProperty("jdbc.driverClass");
            URL = properties.getProperty("jdbc.url");
            USERNAME = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");

            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JDBCConnection() {
    }

    private static void loadConnection() {
        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            loadConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
