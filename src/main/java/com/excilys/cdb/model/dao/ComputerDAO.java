package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.model.jdbc.ConnectionMySQL;
import com.mysql.jdbc.PreparedStatement;

public class ComputerDAO extends DAO<Computer> {
    private static final String FIND_ID = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.id = ?;";
    private static final String FIND_NAME = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.name = ?;";
    private static final String CREATE = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE computer SET name= ?, introduced= ?, discontinued = ?, company_id = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM computer WHERE id = ?;";
    private static final String LISTALL = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id;";

    private static ComputerDAO ourInstance = new ComputerDAO();

    public static ComputerDAO getInstance() {
        return ourInstance;

    }

    @Override
    public Computer findById(long id) {
        Computer computer = new Computer();
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(FIND_ID);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            rs.next();
            Company company = new Company();
            computer.setId(rs.getLong("cr.id"));
            computer.setName(rs.getString("cr.name"));
            computer.setIntroduced(rs.getDate("cr.introduced").toLocalDate());
            computer.setDiscontinued(rs.getDate("cr.discontinued").toLocalDate());
            company.setId(rs.getLong("cy.id"));
            company.setName(rs.getString("cy.name"));
            computer.setCompany(company);

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
        return computer;
    }

    @Override
    public Computer findByName(String name) {
        Computer computer = new Computer();
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(FIND_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            rs.next();
            Company company = new Company();
            computer.setId(rs.getLong("cr.id"));
            computer.setName(rs.getString("cr.name"));
            computer.setIntroduced(rs.getDate("cr.introduced").toLocalDate());
            computer.setDiscontinued(rs.getDate("cr.discontinued").toLocalDate());
            company.setId(rs.getLong("cy.id"));
            company.setName(rs.getString("cy.name"));
            computer.setCompany(company);

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
                rs.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return computer;
    }

    @Override
    public void create(Computer comp) {
        Connection connection = null;
        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(CREATE);
            stmt.setString(1, comp.getName());
            stmt.setDate(2, Date.valueOf(comp.getIntroduced()));
            stmt.setDate(3, Date.valueOf(comp.getDiscontinued()));
            stmt.setLong(4, comp.getCompany().getId());
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
    public void update(Computer comp) {
        Connection connection = null;
        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(UPDATE);
            stmt.setString(1, comp.getName());
            stmt.setDate(2, Date.valueOf(comp.getIntroduced()));
            stmt.setDate(3, Date.valueOf(comp.getDiscontinued()));
            stmt.setLong(4, comp.getCompany().getId());
            stmt.setLong(5, comp.getId());
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
    public Page<Computer> index(int pageNb, int elemPerPg) {
        Page<Computer> page = null;
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = ConnectionMySQL.getInstance().getConnection();
            rs = connection.prepareStatement(String.format(LISTALL, pageNb * elemPerPg, elemPerPg)).executeQuery();
            while (rs.next()) {
                Computer computer = new Computer();
                Company company = new Company();
                computer.setId(rs.getLong("cr.id"));
                computer.setName(rs.getString("cr.name"));
                computer.setIntroduced(rs.getDate("cr.introduced").toLocalDate());
                computer.setDiscontinued(rs.getDate("cr.discontinued").toLocalDate());
                company.setId(rs.getLong("cy.id"));
                company.setName(rs.getString("cy.name"));
                computer.setCompany(company);
                page.addEntity(computer);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                connection.close();
                rs.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return page;
    }

}
