package com.excilys.cdb.service;

import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.mappers.ComputerMapper;;

public enum ComputerService {

    INSTANCE;
    private static final ComputerDAO DAO = ComputerDAO.INSTANCE;

    /**
     * Return a computerDTO from a computer id.
     * @param id
     *            of the computer
     * @return the computerDTO you want
     */
    public ComputerDTO getById(Long id) {
        return ComputerMapper.convertComputer(DAO.findById(id));
    }

    /**
     * Return all the computers of the database, per page.
     * @param pageNb
     *            the page you want
     * @param elemPerPg
     *            the number of element per page
     * @return a page of computers
     */
    public Page<ComputerDTO> index(int pageNb, int elemPerPg) {
        return ComputerMapper.convertList(DAO.index(pageNb, elemPerPg));
    }

    /**
     * Add a new computer in the database.
     * @param entity
     *            the computer to add in the database
     */
    public long create(Computer entity) {
        long id = 0;
        id = DAO.create(entity);
        return id;
    }

    /**
     * Update a computer in the database.
     * @param entity
     *            the computer to update
     */
    public void update(Computer entity) {
        DAO.update(entity);
    }

    /**
     * Delete a computer in the database.
     * @param id
     *            the id of the computer to delete
     */
    public void delete(Long id) {
        DAO.delete(id);
    }

}
