package com.excilys.cdb.test;

import java.sql.SQLException;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.entities.Company;
import com.excilys.cdb.entities.Computer;

public class Test {

	public static void main(String[] args) throws SQLException {
		CompanyDAO companyDAO = new CompanyDAO();
		Company pomme = new Company();
		pomme.setName("Pomme");
		companyDAO.create(pomme);
		System.out.println(companyDAO.index());
		}
	}
