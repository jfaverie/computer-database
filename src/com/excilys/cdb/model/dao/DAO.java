package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.jdbc.ConnectionMySQL;

public abstract class DAO<T> {

	/**
	 * Permet de récupérer un objet via son ID
	 * 
	 * @param id
	 * @return
	 */
	public abstract T findById(long id);

	/**
	 * Permet de récupérer un objet via son nom
	 * 
	 * @param name
	 * @return
	 */
	public abstract T findByName(String name);

	/**
	 * Permet de créer une entrée dans la base de données par rapport à un objet
	 * 
	 * @param obj
	 */
	public abstract void create(T obj);

	/**
	 * Permet de mettre à jour les données d'une entrée dans la base
	 * 
	 * @param obj
	 */
	public abstract void update(T obj);

	/**
	 * Permet la suppression d'une entrée de la base
	 * @param obj
	 */
	public abstract void delete(long id);

	/**
	 * Permet de récupérer une liste d'objets
	 * @return
	 */
	public abstract Page<T> index(int pageNb, int elemPerPg);
	

	
	
}
