package com.excilys.cdb.model.dao;

import java.sql.Connection;

import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.resources.SortColumn;
import com.excilys.cdb.resources.SortType;

public interface DAO<T> {

    /**
     * Permet de récupérer un objet via son ID.
     * 
     * @param id
     *            id of the object you want to find
     * @return the object
     */
    public abstract T findById(long id);

    /**
     * Permet de récupérer un objet via son nom.
     * 
     * @param name
     *            the name of the object you want to find
     * @return the object
     */
    public abstract T findByName(String name);

    /**
     * Create an entry on the DB relative to an object.
     * 
     * @param obj
     *            the object you want to update
     * @return
     */
    public abstract long create(T obj);

    /**
     * Update an entry on the DB.
     * 
     * @param obj
     *            the object you want to update
     */
    public abstract void update(T obj);

    /**
     * Suppress an entry on the DB.
     * @param id
     *            the id of the object
     */
    public abstract void delete(long id);

    /**
     * Get a list of Objects.
     * 
     * @param pageNb
     *            the page number
     * @param elemPerPg
     *            the number of elements per page
     * @return the list of objects
     */
    public abstract Page<T> index(int pageNb, int elemPerPg);

    public abstract Page<T> indexSort(int pageNb, int elemPerPg, SortColumn sc, SortType sortType, String name);

    public abstract void deleteByCompany(long companyId);




}