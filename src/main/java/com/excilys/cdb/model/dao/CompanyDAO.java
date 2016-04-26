package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.model.jdbc.ConnectionMySQL;

public class CompanyDAO extends DAO<Company> {

    private static final String FIND_ID = "SELECT id, name from company WHERE id = ?;";
    private static final String FIND_NAME = "SELECT id, name from company WHERE name = ?;";
    private static final String CREATE = "INSERT INTO company (name) VALUES (?);";
    private static final String UPDATE = "UPDATE company SET name= ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM company WHERE id = ?;";
    private static final String LISTALL = "SELECT id,name from company;";

    private static CompanyDAO ourInstance = new CompanyDAO();

    public static CompanyDAO getInstance() {
        return ourInstance;

    }

    @Override
    public void create(Company comp) {
        Connection connection = null;

        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(CREATE);
            stmt.setString(1, comp.getName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }

        }

    }

    @Override
    public void update(Company comp) {
        Connection connection = null;

        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(UPDATE);
            stmt.setString(1, comp.getName());
            stmt.setLong(2, comp.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }

        }
    }

    @Override
    public void delete(long id) {
        Connection connection = null;
        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(String.format(DELETE, id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

    }

    @Override
    public Company findById(long id) {
        Company company = new Company();
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(FIND_ID);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            rs.next();
            company.setId(rs.getLong("id"));
            company.setName(rs.getString("name"));
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return company;
    }

    @Override
    public Company findByName(String name) {
        Company company = new Company();
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(FIND_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            rs.next();
            company.setId(rs.getLong("id"));
            company.setName(rs.getString("name"));
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return company;
    }

    @Override
    public Page<Company> index(int pageNb, int elemPerPg) {
        Page<Company> page = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            rs = connection.prepareStatement(String.format(LISTALL, pageNb * elemPerPg, elemPerPg)).executeQuery();
            page = new Page<>();
            page.setPageNumber(pageNb);
            while (rs.next()) {
                Company company = new Company();
                company.setId(rs.getLong("id"));
                company.setName(rs.getString("name"));
                page.addEntity(company);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                rs.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }

        }
        return page;
    }

}
