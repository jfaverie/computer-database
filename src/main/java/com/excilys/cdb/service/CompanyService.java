package com.excilys.cdb.service;

import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;

public class CompanyService {

    private static CompanyService instance = new CompanyService();
    private final CompanyDAO dao;

    public static CompanyService getInstance() {
        return instance;
    }

    /**
     * Init the DAO for the service.
     */
    private CompanyService() {
        dao = CompanyDAO.getInstance();
    }

    /**
     * Return a company from an id.
     * @param id
     *            of the company
     * @return the company you want
     */
    public Company getById(Long id) {
        return dao.findById(id);
    }

    /**
     * Return all the company of the database, per page.
     * @param pageNb
     *            the page you want
     * @param elemPerPg
     *            the number of element per page
     * @return a page of companies
     */
    public Page<Company> index(int pageNb, int elemPerPg) {
        return dao.index(pageNb, elemPerPg);
    }

    /**
     * Add a new company in the database.
     * @param entity
     *            the company to add in the database
     */
    public long create(Company entity) {
        long id = 0;
        id = dao.create(entity);
        return id;
    }

    /**
     * Update a company in the database.
     * @param entity
     *            the company to update
     */
    public void update(Company entity) {
        dao.update(entity);
    }

    /**
     * Delete a company in the database.
     * @param id
     *            the id of the company to delete
     */
    public void delete(Long id) {
        dao.delete(id);
    }

}
