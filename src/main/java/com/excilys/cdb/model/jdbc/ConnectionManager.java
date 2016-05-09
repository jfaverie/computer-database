package com.excilys.cdb.model.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.cdb.model.exception.JDBCException;

public class ConnectionManager {

    private static final ConnectionManager INSTANCE = new ConnectionManager();

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    private final ThreadLocal<Connection> threadLocal;

    private ConnectionManager() {
        threadLocal = new ThreadLocal<>();
    }

    public Connection getConnection() {
        if (threadLocal.get() == null) {
            threadLocal.set(ConnectionMySQL.INSTANCE.getConnection());
        }
        return threadLocal.get();
    }


    public void initTransaction(Connection connection) {
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
}
