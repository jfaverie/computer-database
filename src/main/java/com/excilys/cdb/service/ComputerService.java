package com.excilys.cdb.service;

import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;

public class ComputerService {

    private static ComputerService instance = new ComputerService();
    private final ComputerDAO dao;

    public static ComputerService getInstance() {
        return instance;
    }

    /**
     * Init the DAO for the service.
     */
    private ComputerService() {
        dao = ComputerDAO.getInstance();
    }

    /**
     * Return a computer from an id.
     * @param id
     *            of the computer
     * @return the computer you want
     */
    public Computer getById(Long id) {
        return dao.findById(id);
    }

    /**
     * Return all the computer of the database, per page.
     * @param pageNb
     *            the page you want
     * @param elemPerPg
     *            the number of element per page
     * @return a page of computers
     */
    public Page<Computer> index(int pageNb, int elemPerPg) {
        return dao.index(pageNb, elemPerPg);
    }

    /**
     * Add a new computer in the database.
     * @param entity
     *            the computer to add in the database
     */
    public long create(Computer entity) {
        long id = 0;
        id = dao.create(entity);
        return id;
    }

    /**
     * Update a computer in the database.
     * @param entity
     *            the computer to update
     */
    public void update(Computer entity) {
        dao.update(entity);
    }

    /**
     * Delete a computer in the database.
     * @param id
     *            the id of the computer to delete
     */
    public void delete(Long id) {
        dao.delete(id);
    }

}
