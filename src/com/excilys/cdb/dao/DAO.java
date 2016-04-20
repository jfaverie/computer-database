package com.excilys.cdb.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.cdb.jdbc.ConnectionMySQL;

public abstract class DAO<T> {
	
	
public Connection connect = ConnectionMySQL.getInstance();
	
	/**
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract T findById(long id);
	
	/**
	 * Permet de récupérer un objet via son nom
	 * @param name
	 * @return
	 */
	public abstract T findByName(String name);
	
	/**
	 * Permet de créer une entrée dans la base de données
	 * par rapport à un objet
	 * @param obj
	 */	
	public abstract T create(T obj);
	
	/**
	 * Permet de mettre à jour les données d'une entrée dans la base 
	 * @param obj
	 */
	public abstract T update(T obj);
	
	/**
	 * Permet la suppression d'une entrée de la base
	 * @param obj
	 */
	public abstract void delete(T obj);
	
	/**
	 * Permet de récupérer une liste d'objets
	 * @return
	 */
	public abstract List<T> index();
	

	
	
}
