package com.excilys.cdb.model.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.model.exception.JDBCException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum ConnectionMySQL {

    INSTANCE;



    private final HikariDataSource dataSource;
    /**
     * Private constructor of ConnectionMySQL for the singleton pattern.
     */
    private ConnectionMySQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Properties props = new Properties();
            InputStream in = ConnectionMySQL.class.getClassLoader().getResourceAsStream("mysql.properties");
            props.load(in);

            String url = props.getProperty("dataSource.url");
            String user = props.getProperty("dataSource.user");
            String passwd = props.getProperty("dataSource.password");

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(passwd);
            config.addDataSourceProperty("serverTimeZone", "UTC+1");
            config.addDataSourceProperty("zeroDateTimeBehavior", "convertToNull");
            config.addDataSourceProperty("useSSL", "true");
            config.addDataSourceProperty("useUnicode", "true");
            config.addDataSourceProperty("characterEncoding", "UTF-8");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            config.addDataSourceProperty("maximumPoolSize", "16");

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new JDBCException(e);
        }
    }

    /**
     * Return a JDBC connection to a MySQL database.
     * 
     * @return a connection to a database
     * @throws SQLException
     *             if a connection with a database can't be done
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Cannot get a connection ", e);
        }
    }

}
