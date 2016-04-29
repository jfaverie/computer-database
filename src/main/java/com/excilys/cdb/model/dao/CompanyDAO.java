package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.model.jdbc.ConnectionMySQL;

public enum CompanyDAO implements DAO<Company> {

    INSTANCE;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CompanyDAO.class);
    private static final String FIND_ID = "SELECT id, name from company WHERE id = ?;";
    private static final String FIND_NAME = "SELECT id, name from company WHERE name = ?;";
    private static final String CREATE = "INSERT INTO company (name) VALUES (?);";
    private static final String UPDATE = "UPDATE company SET name= ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM company WHERE id = ?;";
    private static final String LISTALL = "SELECT id,name from company LIMIT %d, %d;";
    private static final String COUNT = "SELECT COUNT(*) FROM company";

    @Override
    public Company findById(long id) {
        Company company = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_ID);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            rs.next();
            company.setId(rs.getLong("id"));
            company.setName(rs.getString("name"));
        } catch (SQLException e) {
            throw new DAOException("Fail to get a company by id", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close a connection", e);
            }
        }
        return company;
    }

    @Override
    public Company findByName(String name) {
        Company company = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            rs.next();
            company.setId(rs.getLong("id"));
            company.setName(rs.getString("name"));
        } catch (SQLException e) {
            throw new DAOException("Fail to get a company by name", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to get a company by name", e);
            }
        }
        return company;
    }

    @Override
    public long create(Company comp) {
        Connection connection = null;
        long id = 0;

        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, comp.getName());
            stmt.executeUpdate();
            if (stmt.executeUpdate() > 1) {
                CompanyDAO.LOGGER.info("Insert company");
            } else {
                CompanyDAO.LOGGER.warn("Fail to create a company");
            }
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new DAOException("Fail to get the company id");
            }

        } catch (SQLException e) {
            CompanyDAO.LOGGER.error(e.getMessage());
            throw new DAOException("Fail to create a company", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
            }

        }

        return id;
    }

    @Override
    public void update(Company comp) {

        Connection connection = null;

        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(UPDATE);
            stmt.setString(1, comp.getName());
            stmt.setLong(2, comp.getId());
            stmt.executeUpdate();
            if (stmt.executeUpdate() > 1) {
                CompanyDAO.LOGGER.info("Update a company");
            } else {
                CompanyDAO.LOGGER.warn("Fail to update a company");
            }

        } catch (SQLException e) {
            CompanyDAO.LOGGER.error(e.getMessage());
            throw new DAOException("Fail to update a company", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to update a company", e);
            }

        }
    }

    @Override
    public void delete(long id) {
        Connection connection = null;
        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            if (stmt.executeUpdate() > 1) {
                CompanyDAO.LOGGER.info("Delete a company");
            } else {
                CompanyDAO.LOGGER.warn("Fail to delete a company");
            }
        } catch (SQLException e) {
            CompanyDAO.LOGGER.error(e.getMessage());
            throw new DAOException("Fail to delete a company", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
            }
        }

    }

    @Override
    public Page<Company> index(int pageNb, int elemPerPg) {
        Page<Company> page = new Page<>();
        ResultSet rs = null;
        Connection connection = null;
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);

        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            rs = connection.prepareStatement(String.format(LISTALL, pageNb * elemPerPg, elemPerPg)).executeQuery();
            page = new Page<>();
            page.setPageNumber(pageNb);
            while (rs.next()) {
                Company company = null;
                company.setId(rs.getLong("id"));
                company.setName(rs.getString("name"));
                page.addEntity(company);
            }
            rs.close();
            rs = connection.prepareStatement(String.format(COUNT, pageNb * elemPerPg, elemPerPg)).executeQuery();
            rs.next();

            page.setTotalElements(rs.getInt(1));

        } catch (SQLException e) {
            throw new DAOException("Fail to get all companies", e);
        } finally {
            try {
                rs.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to get all companies", e);
            }

        }
        return page;
    }

}
