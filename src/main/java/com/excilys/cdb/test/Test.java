package com.excilys.cdb.test;

import java.sql.SQLException;

import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.entities.Company;

public class Test {

    /**
     * Main method for launch the CUI.
     * @param args no args needed
     * @throws SQLException detected
     */
    public static void main(String[] args) throws SQLException {
        CompanyDAO companyDAO = new CompanyDAO();
        Company pomme = new Company();
        pomme.setName("Pomme");
        companyDAO.create(pomme);
        System.out.println(companyDAO.index(1, 20));
    }
}
