package com.excilys.cdb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMySQL {
	
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost/";
	private final static String DB_NAME = "computer-database-db";
	private final static String USERNAME = "admincdb";
	private final static String PASSWORD = "qwerty1234";
	private final static String PARAMS = "?zeroDateTimeBehavior=convertToNull";

	private static ResultSet rs;
	private static Connection connect;
	private static Statement stmt;

	private static void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		connect = DriverManager.getConnection(URL + DB_NAME + PARAMS, USERNAME, PASSWORD);
		stmt = connect.createStatement();
	}
	
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}	
	
}
