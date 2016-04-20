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
	private static Connection conn;
	private static Statement stmt;

	private static void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL + DB_NAME + PARAMS, USERNAME, PASSWORD);
		stmt = conn.createStatement();
	}

	public static ResultSet exec(String query) {
		try {
			connect();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet update(String query) {
		try {
			connect();
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void closeMysqlConnection() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
