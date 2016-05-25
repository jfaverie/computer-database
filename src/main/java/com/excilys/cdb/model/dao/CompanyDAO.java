package com.excilys.cdb.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.mappers.database.CompanyRowMapper;

@Repository
public class CompanyDAO extends DAO<Company> {

    private static final String FIND_ID = "SELECT id, name from company WHERE id = ?;";
    private static final String FIND_NAME = "SELECT id, name from company WHERE name = ?;";
    private static final String UPDATE = "UPDATE company SET name= ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM company WHERE id = ?;";
    private static final String LISTALL = "SELECT id,name from company LIMIT ?, ?;";
    private static final String COUNT = "SELECT COUNT(*) FROM company";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertCompany;

    @Autowired
    public CompanyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertCompany = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("company")
                .usingColumns("name").usingGeneratedKeyColumns("id");
    }

    @Override
    public Company findById(long id) {
        return jdbcTemplate.queryForObject(FIND_ID, new Object[] { id }, new CompanyRowMapper());
    }

    @Override
    public Company findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_NAME, new Object[] { name }, new CompanyRowMapper());
    }

    @Override
    public long create(Company comp) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", comp.getName());
        return insertCompany.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public void update(Company comp) {
        jdbcTemplate.update(UPDATE, comp.getName(), comp.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(DELETE, id);
    }

    public void deleteWithLocalThread(long companyId) {
        jdbcTemplate.update(DELETE, companyId);
    }

    @Override
    public Page<Company> index(int pageNb, int elemPerPg) {
        Page<Company> page = new Page<>();
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setTotalElements(jdbcTemplate.queryForObject(COUNT, Integer.class));
        page.setEntities(jdbcTemplate.query(LISTALL, new CompanyRowMapper(), pageNb * elemPerPg, elemPerPg));
        return page;
    }

}
