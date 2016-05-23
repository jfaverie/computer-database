package com.excilys.cdb.model.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.exception.ConnectionException;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class ConnectionManager {

    @Autowired
    private HikariDataSource ds;
    private Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            logger.error("[Catch] <" + e.getClass().getSimpleName() + "> " + e.getMessage());
            throw new ConnectionException(e);
        }
    }

/*    public void initTransaction(Connection connection) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    public void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    public void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
        threadLocal.remove();
    }
    */
}
