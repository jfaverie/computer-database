package com.excilys.cdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.mappers.service.CompanyMapper;

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private ComputerDAO computerDAO;

    /**
     * Return a company from an id.
     * @param id
     *            of the company
     * @return the company you want
     */
    public CompanyDTO getById(Long id) {
        return CompanyMapper.convertCompany(companyDAO.findById(id));
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
        return CompanyMapper.convertListCompanies(companyDAO.index(pageNb, elemPerPg));
    }

    /**
     * Add a new company in the database.
     * @param entity
     *            the company to add in the database
     * @return the id of the created company
     */
    public long create(CompanyDTO entity) {
        long id = companyDAO.create(new Company(entity));
        return id;
    }

    /**
     * Update a company in the database.
     * @param entity
     *            the company to update
     */
    public void update(CompanyDTO entity) {
        companyDAO.update(new Company(entity));
    }

    /**
     * Delete a company in the database.
     * @param id
     *            the id of the company to delete
     */
    public void delete(Long id) {
        computerDAO.deleteByCompany(id);
        companyDAO.deleteWithLocalThread(id);
    }

}
