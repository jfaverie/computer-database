package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.model.jdbc.ConnectionManager;
import com.excilys.cdb.model.jdbc.ConnectionMySQL;
import com.excilys.cdb.resources.SortColumn;
import com.excilys.cdb.resources.SortType;

@Repository
public class ComputerDAO extends DAO<Computer> {

    private static final String FIND_ID = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.id = ?;";
    private static final String FIND_NAME = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.name = ?;";
    private static final String CREATE = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE computer SET name= ?, introduced= ?, discontinued = ?, company_id = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM computer WHERE id = ?;";
    private static final String DELETE_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?;";
    private static final String LISTALL = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id LIMIT %d, %d;";
    private static final String LISTALL_ORDERED = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.name LIKE ? ORDER BY %s LIMIT ?, ?;";
    private static final String COUNT = "SELECT COUNT(*) FROM computer";
    private static final String COUNT_ORDERED = "SELECT COUNT(*) FROM computer cr LEFT JOIN company cy ON cr.company_id = cy.id WHERE cr.name LIKE ? OR cy.name LIKE ?";
    private static final String LISTID = "SELECT id FROM company";

    @Autowired
    private ConnectionManager manager;

    @Override
    public Computer findById(long id) {
        Computer computer = new Computer();
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_ID);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            rs.next();
            Company company = new Company();
            computer.setId(rs.getLong("cr.id"));
            computer.setName(rs.getString("cr.name"));
            Date intro = rs.getDate("cr.introduced");
            if (intro != null) {
                computer.setIntroduced(intro.toLocalDate());
            }
            Date disco = rs.getDate("cr.discontinued");
            if (disco != null) {
                computer.setDiscontinued(disco.toLocalDate());
            }
            Long companyid = rs.getLong("company_id");
            String companyname = rs.getString("company_name");
            if (companyid != null) {
                company.setId(companyid);
                company.setName(companyname);
                computer.setCompany(company);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new DAOException("Fail to find a computer by id", e);
        } finally {
            try {
                rs.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
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
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(FIND_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            rs.next();
            Company company = new Company();
            computer.setId(rs.getLong("cr.id"));
            computer.setName(rs.getString("cr.name"));
            Date intro = rs.getDate("cr.introduced");
            if (intro != null) {
                computer.setIntroduced(intro.toLocalDate());
            }
            Date disco = rs.getDate("cr.discontinued");
            if (disco != null) {
                computer.setDiscontinued(intro.toLocalDate());
            }
            Long companyid = rs.getLong("company_id");
            String companyname = rs.getString("company_name");
            if (companyid != null) {
                company.setId(companyid);
                company.setName(companyname);
                computer.setCompany(company);
            }

        } catch (SQLException e) {
            throw new DAOException("Fail to find a computer by name", e);
        } finally {
            try {
                rs.close();
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
            }
        }
        return computer;
    }

    @Override
    public long create(Computer comp) {
        long id = 0;
        Connection connection = null;
        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, comp.getName());
            ArrayList<Integer> ids = new ArrayList<>();
            ResultSet rs = connection.prepareStatement(LISTID).executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                ids.add((int) id);
            }
            if (comp.getIntroduced() != null) {
                Date intro = Date.valueOf(comp.getIntroduced());
                stmt.setDate(2, intro);
            } else {
                stmt.setNull(2, java.sql.Types.TIMESTAMP);
            }
            if (comp.getDiscontinued() != null) {
                Date disco = Date.valueOf(comp.getDiscontinued());
                stmt.setDate(3, disco);
            } else {
                stmt.setNull(3, java.sql.Types.TIMESTAMP);
            }
            Long companyId = comp.getCompany().getId();
            if (comp.getCompany() != null && ids.contains(companyId)) {
                stmt.setLong(4, companyId);
            } else {
                stmt.setNull(4, java.sql.Types.BIGINT);
            }
            
            stmt.executeUpdate();
            rs.close();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new DAOException("Fail to get the computer id");
            }

        } catch (SQLException e) {
            throw new DAOException("Fail to create a new computer", e);
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
    public void update(Computer comp) {
        Connection connection = null;
        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            PreparedStatement stmt = connection.prepareStatement(UPDATE);
            stmt.setString(1, comp.getName());
            ArrayList<Integer> ids = new ArrayList<>();
            ResultSet rs = connection.prepareStatement(LISTID).executeQuery();
            while (rs.next()) {
                Integer id = 0;
                id = rs.getInt("id");
                ids.add(id);
            }
            if (comp.getIntroduced() != null) {
                Date intro = Date.valueOf(comp.getIntroduced());
                stmt.setDate(2, intro);
            } else {
                stmt.setNull(2, java.sql.Types.TIMESTAMP);
            }
            if (comp.getDiscontinued() != null) {
                Date disco = Date.valueOf(comp.getDiscontinued());
                stmt.setDate(3, disco);
            } else {
                stmt.setNull(3, java.sql.Types.TIMESTAMP);
            }
            Long companyId = comp.getCompany().getId();
            if (companyId != null && ids.contains(companyId)) {
                stmt.setLong(4, companyId);
            } else {
                stmt.setNull(4, java.sql.Types.BIGINT);
            }
            stmt.setLong(5, comp.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Fail to update a computer", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
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
        } catch (SQLException e) {
            throw new DAOException("Fail to delete a computer", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
            }
        }

    }

    @Override
    public void deleteByCompany(long id) {
        Connection connection = manager.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(DELETE_BY_COMPANY);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Fail to delete a computer", e);
        }
    }

    @Override
    public Page<Computer> index(int pageNb, int elemPerPg) {
        Page<Computer> page = new Page<>();
        Connection connection = null;
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        ResultSet rs = null;
        try {
            connection = ConnectionMySQL.INSTANCE.getConnection();
            rs = connection.prepareStatement(String.format(LISTALL, pageNb * elemPerPg, elemPerPg)).executeQuery();
            while (rs.next()) {
                Computer computer = new Computer();
                Company company = new Company();
                computer.setId(rs.getLong("cr.id"));
                computer.setName(rs.getString("cr.name"));
                Date intro = rs.getDate("cr.introduced");
                if (intro != null) {
                    computer.setIntroduced(intro.toLocalDate());
                }
                Date disco = rs.getDate("cr.discontinued");
                if (disco != null) {
                    computer.setDiscontinued(disco.toLocalDate());
                }
                Long companyid = rs.getLong("company_id");
                String companyname = rs.getString("company_name");
                if (companyid != null) {
                    company.setId(companyid);
                    company.setName(companyname);
                    computer.setCompany(company);
                }
                page.addEntity(computer);
            }
            rs.close();
            rs = connection.prepareStatement(COUNT).executeQuery();
            rs.next();
            page.setTotalElements(rs.getInt(1));

        } catch (SQLException e) {
            throw new DAOException("Fail to get all computers", e);
        } finally {
            try {
                connection.close();
                rs.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
            }
        }
        return page;
    }

    @Override
    public Page<Computer> indexSort(int pageNb, int elemPerPg, SortColumn sc, SortType sortType, String name) {
        Page<Computer> page = new Page<>();
        Connection connection = null;
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setSearch(name);
        page.setSortCol(sc);
        page.setSortType(sortType);
        ResultSet rs = null;
        try {
            PreparedStatement stmt = null;
            connection = ConnectionMySQL.INSTANCE.getConnection();
            // if (!name.isEmpty()) {
            stmt = connection.prepareStatement(
                    String.format(LISTALL_ORDERED, sc + ((sortType == SortType.ASC) ? " ASC " : " DESC ")));
            stmt.setString(1, "%" + name + "%");
            stmt.setInt(2, pageNb * elemPerPg);
            stmt.setInt(3, elemPerPg);
            /*
             * } else { stmt =
             * connection.prepareStatement(String.format(LISTALL, pageNb *
             * elemPerPg, elemPerPg)); }
             */

            rs = stmt.executeQuery();
            while (rs.next()) {
                Computer computer = new Computer();
                Company company = new Company();
                computer.setId(rs.getLong("cr.id"));
                computer.setName(rs.getString("cr.name"));
                Date intro = rs.getDate("cr.introduced");
                if (intro != null) {
                    computer.setIntroduced(intro.toLocalDate());
                }
                Date disco = rs.getDate("cr.discontinued");
                if (disco != null) {
                    computer.setDiscontinued(disco.toLocalDate());
                }
                Long companyid = rs.getLong("company_id");
                String companyname = rs.getString("company_name");
                if (companyid != null) {
                    company.setId(companyid);
                    company.setName(companyname);
                    computer.setCompany(company);
                }
                page.addEntity(computer);
            }
            stmt.close();
            rs.close();
            if (!name.isEmpty()) {
                stmt = connection.prepareStatement(COUNT_ORDERED);
                stmt.setString(1, name + "%");
                stmt.setString(2, name + "%");
            } else {
                stmt = connection.prepareStatement(COUNT);
            }
            rs = stmt.executeQuery();
            rs.next();
            page.setTotalElements(rs.getInt(1));

        } catch (SQLException e) {
            throw new DAOException("Fail to get all computers", e);
        } finally {
            try {
                connection.close();
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
            }
        }
        return page;
    }

}
