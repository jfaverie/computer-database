package com.excilys.cdb.model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db";
    private static final String USERNAME = "admincdb";
    private static final String PASSWORD = "qwerty1234";
    private static final String PARAMS = "?zeroDateTimeBehavior=convertToNull";
    // Instance singleton
    private static ConnectionMySQL instance = null;

    /**
     * Private constructor of ConnectionMySQL for the singleton pattern.
    */
   private ConnectionMySQL() {
   }

    /**
    * Return a JDBC connection to a MySQL database.
    * @return a connection to a database
    * @throws SQLException
    *             if a connection with a database can't be done
    */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * get an instance of the connection.
     * @return instance
     */
    public static ConnectionMySQL getInstance() {
        if (instance == null) {
            instance = new ConnectionMySQL();
        }
        return instance;
    }

}
