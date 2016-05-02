package com.excilys.cdb.service;

import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.mappers.CompanyMapper;

public enum CompanyService {
    
    INSTANCE;
    private static final CompanyDAO DAO = CompanyDAO.INSTANCE;

    /**
     * Return a company from an id.
     * @param id
     *            of the company
     * @return the company you want
     */
    public CompanyDTO getById(Long id) {
        return CompanyMapper.convertCompany(DAO.findById(id));
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
        return CompanyMapper.convertListCompanies(DAO.index(pageNb, elemPerPg));
    }

    /**
     * Add a new company in the database.
     * @param entity
     *            the company to add in the database
     */
    public long create(Company entity) {
        long id = 0;
        id = DAO.create(entity);
        return id;
    }

    /**
     * Update a company in the database.
     * @param entity
     *            the company to update
     */
    public void update(Company entity) {
        DAO.update(entity);
    }

    /**
     * Delete a company in the database.
     * @param id
     *            the id of the company to delete
     */
    public void delete(Long id) {
        DAO.delete(id);
    }

}
