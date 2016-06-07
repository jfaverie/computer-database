package com.excilys.cdb.model.mappers.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Computer;

public class ComputerRowMapper implements RowMapper<Computer> {

    @Override
    public Computer mapRow(ResultSet rs, int rowNb) throws SQLException {
        Computer computer = new Computer();
        Date date;
        computer.setId(rs.getLong("cr.id"));
        computer.setName(rs.getString("cr.name"));
        date = rs.getDate("cr.discontinued");
        if (date != null) {
            computer.setDiscontinued(date.toLocalDate());
        }
        date = rs.getDate("cr.introduced");
        if (date != null) {
            computer.setIntroduced(date.toLocalDate());
        }
        Company company = new Company();
        company.setId(rs.getLong("company_id"));
        company.setName(rs.getString("company_name"));
        if (company != null) {
            computer.setCompany(company);
        }
        return computer;
    }

}
