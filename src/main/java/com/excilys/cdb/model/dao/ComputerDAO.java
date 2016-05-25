package com.excilys.cdb.model.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.mappers.database.ComputerRowMapper;
import com.excilys.cdb.resources.SortColumn;
import com.excilys.cdb.resources.SortType;

import ch.qos.logback.core.net.SyslogOutputStream;

@Repository
public class ComputerDAO extends DAO<Computer> {

    private static final String FIND_ID = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.id = ?;";
    private static final String FIND_NAME = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.name = ?;";
    private static final String UPDATE = "UPDATE computer SET name= ?, introduced= ?, discontinued = ?, company_id = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM computer WHERE id = ?;";
    private static final String DELETE_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?;";
    private static final String LISTALL = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id LIMIT ?, ?;";
    private static final String LISTALL_ORDERED = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.name LIKE ? ORDER BY ? LIMIT ?, ?;";
    private static final String COUNT = "SELECT COUNT(*) FROM computer";
    private static final String COUNT_ORDERED = "SELECT COUNT(*) FROM computer cr LEFT JOIN company cy ON cr.company_id = cy.id WHERE cr.name LIKE ? OR cy.name LIKE ?";

    private volatile AtomicInteger count;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertComputer;

    @Autowired
    public ComputerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertComputer = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("company")
                .usingColumns("name").usingGeneratedKeyColumns("id");
        count = new AtomicInteger();
        count.set(jdbcTemplate.queryForObject(COUNT, Integer.class));
    }

    @Override
    public Computer findById(long id) {
        return jdbcTemplate.queryForObject(FIND_ID, new Object[] { id }, new ComputerRowMapper());
    }

    @Override
    public Computer findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_NAME, new Object[] { name }, new ComputerRowMapper());
    }

    @Override
    public long create(Computer comp) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", comp.getName());
        parameters.put("introduced", (comp.getIntroduced() != null ? Date.valueOf(comp.getIntroduced()) : null));
        parameters.put("discontinued", (comp.getDiscontinued() != null ? Date.valueOf(comp.getDiscontinued()) : null));
        parameters.put("company_id", (comp.getCompany() != null ? comp.getCompany().getId() : null));
        count.incrementAndGet();
        return insertComputer.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public void update(Computer comp) {
        Long id = comp.getId();
        String name = comp.getName();
        Date introduced = (comp.getIntroduced() != null ? Date.valueOf(comp.getIntroduced()) : null);
        Date discontinued = (comp.getDiscontinued() != null ? Date.valueOf(comp.getDiscontinued()) : null);
        Long companyId = (comp.getCompany() != null ? comp.getCompany().getId() : null);

        jdbcTemplate.update(UPDATE, name, introduced, discontinued, companyId, id);
    }

    @Override
    public void delete(long id) {
        count.decrementAndGet();
        jdbcTemplate.update(DELETE, id);
    }

    public void deleteByCompany(long id) {
        int howMany = jdbcTemplate.update(DELETE_BY_COMPANY, id);
        count.addAndGet(-howMany);
    }

    @Override
    public Page<Computer> index(int pageNb, int elemPerPg) {

        Page<Computer> page = new Page<>();
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setTotalElements(jdbcTemplate.queryForObject(COUNT, Integer.class));
        page.setEntities(jdbcTemplate.query(LISTALL, new ComputerRowMapper(), pageNb * elemPerPg, elemPerPg));
        return page;
    }

    public Page<Computer> indexSort(int pageNb, int elemPerPg, SortColumn sc, SortType sortType, String name) {
        Page<Computer> page = new Page<>();
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setSearch(name);
        page.setSortCol(sc);
        page.setSortType(sortType);
        page.setTotalElements(jdbcTemplate.queryForObject(COUNT_ORDERED, 
                Integer.class, name + "%", name + "%"));
        page.setEntities(jdbcTemplate.query(LISTALL_ORDERED,
                new ComputerRowMapper(), 
                "%" + name + "%",
                sc + ((sortType == SortType.ASC) ? " ASC " : " DESC "),
                pageNb * elemPerPg, 
                elemPerPg));
        return page;
    }

}
