package ru.charushnikov.studentmagazine.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/crud_servlet";
    private static final String JDBC_USERNAME = "aston";
    private static final String JDBC_PASSWORD = "crud";


    public static Connection toConnectDB() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return connection;
    }
}
