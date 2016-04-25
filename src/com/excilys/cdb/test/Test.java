package com.excilys.cdb.test;

import java.sql.SQLException;

import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Computer;

public class Test {

	public static void main(String[] args) throws SQLException {
		CompanyDAO companyDAO = new CompanyDAO();
		Company pomme = new Company();
		pomme.setName("Pomme");
		companyDAO.create(pomme);
		System.out.println(companyDAO.index(1, 20));
		}
	}
