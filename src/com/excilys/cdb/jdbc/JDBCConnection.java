package com.excilys.cdb.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCConnection {

  /**
   * Return a JDBC connection to a database.
   * 
   * @return a connection to a database
   * @throws SQLException
   *           if a connection with a database can't be done
   */
  Connection getConnection() throws SQLException;
}