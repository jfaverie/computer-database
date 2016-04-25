package com.excilys.cdb.model.jdbc;

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
	// Instance singleton
	private static ConnectionMySQL INSTANCE = null;
	
	
	public Connection getConnection(){	
		Connection connection = null;
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		return connection;	
	}
	
	// retourne l'instance et la crée si elle n'existe pas
	public static ConnectionMySQL getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionMySQL();
		}
		return INSTANCE;
	}
	
}
