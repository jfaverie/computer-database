package com.excilys.cdb.service;

import java.sql.Connection;

import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.jdbc.ConnectionMySQL;
import com.excilys.cdb.model.mappers.ComputerMapper;

public enum ComputerService {

    INSTANCE;
    private static final ComputerDAO COMPUTERDAO = ComputerDAO.INSTANCE;

    /**
     * Return a computerDTO from a computer id.
     * @param id
     *            of the computer
     * @return the computerDTO you want
     */
    public ComputerDTO getById(Long id) {
        return ComputerMapper.convertComputer(COMPUTERDAO.findById(id));
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
        return ComputerMapper.convertList(COMPUTERDAO.index(pageNb, elemPerPg));
    }

    /**
     * Add a new computer in the database.
     * @param entity
     *            the computer to add in the database
     */
    public long create(ComputerDTO entity) {
        long id = 0;
        id = COMPUTERDAO.create(new Computer(entity));
        return id;
    }

    /**
     * Update a computer in the database.
     * @param entity
     *            the computer to update
     */
    public void update(ComputerDTO entity) {
        COMPUTERDAO.update(new Computer(entity));
    }

    /**
     * Delete a computer in the database.
     * @param id
     *            the id of the computer to delete
     */
    public void delete(Long id) {
        COMPUTERDAO.delete(id);
    }

}
