package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.model.jdbc.ConnectionMySQL;
import com.excilys.cdb.model.mappers.CompanyMapper;

public enum CompanyService {
    
    INSTANCE;
    private static final CompanyDAO COMPANYDAO = CompanyDAO.INSTANCE;
    private static final ComputerDAO COMPUTERDAO = ComputerDAO.INSTANCE;

    /**
     * Return a company from an id.
     * @param id
     *            of the company
     * @return the company you want
     */
    public CompanyDTO getById(Long id) {
        return CompanyMapper.convertCompany(COMPANYDAO.findById(id));
    }

    /**
     * Return all the company of the database, per page.
     * @param pageNb
     *            the page you want
     * @param elemPerPg
     *            the number of element per page
     * @return a page of companies
     */
    public Page<CompanyDTO> index(int pageNb, int elemPerPg) {
        return CompanyMapper.convertListCompanies(COMPANYDAO.index(pageNb, elemPerPg));
    }

    /**
     * Add a new company in the database.
     * @param entity
     *            the company to add in the database
     */
    public long create(CompanyDTO entity) {
        long id = 0;
        id = COMPANYDAO.create(new Company(entity));
        return id;
    }

    /**
     * Update a company in the database.
     * @param entity
     *            the company to update
     */
    public void update(CompanyDTO entity) {
        COMPANYDAO.update(new Company(entity));
    }

    /**
     * Delete a company in the database.
     * @param id
     *            the id of the company to delete
     */
    public void delete(Long id) {
        Connection connection = ConnectionMySQL.INSTANCE.getConnection();
        try {
            connection.setAutoCommit(false);
            COMPUTERDAO.delete(id, connection);
            COMPANYDAO.delete(id);
        } catch (DAOException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                throw new DAOException(e2);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException("Fail to close the connection", e);
            }
        }
    }


}
