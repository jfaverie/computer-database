package com.excilys.cdb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMySQL {
	
	private final static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
	private final static String USERNAME = "admincdb";
	private final static String PASSWORD = "qwerty1234";
	private final static String PARAMS = "?zeroDateTimeBehavior=convertToNull";

	private static ConnectionMySQL myConnection = new ConnectionMySQL();
	
	public static ConnectionMySQL getInstance(){	
		return myConnection;	
	}
	public Connection getConnection() throws SQLException {
	    return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	  }
	
	private ConnectionMySQL() {
	  }
	
	
	
}
