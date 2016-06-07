package com.excilys.cdb.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.entities.QCompany;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

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
    private QCompany company = QCompany.company;
    private EntityManager em;
    private JPAQueryFactory jpaQuery;


    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
        this.jpaQuery = new JPAQueryFactory(entityManager);
    }

    @Override
    public Company findById(long id) {
        Company comp = this.jpaQuery.selectFrom(company).where(company.id.eq(id)).fetchOne();
        return comp;
    }
    /*
     * @Override public Company findById(long id) { return
     * jdbcTemplate.queryForObject(FIND_ID, new Object[] { id }, new
     * CompanyRowMapper()); }
     */

    @Override
    public Company findByName(String name) {
        Company comp = this.jpaQuery.selectFrom(company).where(company.name.eq(name)).fetchOne();
        return comp;
    }

    /*
    @Override
    public Company findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_NAME, new Object[] { name }, new CompanyRowMapper());
    }
    */

//    @Override
//    public long create(Company comp) {
//        this.em.persist(comp);
//        long id = comp.getId();
//        return id;
//    }

    @Override
    public long create(Company comp) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", comp.getName());
        return insertCompany.executeAndReturnKey(parameters).longValue();
    } 

    @Override
    public void update(Company comp) {
        this.jpaQuery.update(company).where(company.name.eq(comp.getName())).set(company.name, comp.getName()).execute();
    }
   /* 
    @Override
    public void update(Company comp) {
        jdbcTemplate.update(UPDATE, comp.getName(), comp.getId());
    }
    */

    @Override
    public void delete(long id) {
        this.jpaQuery.delete(company).where(company.id.eq(id)).execute();
    }
    
    /*
    @Override
    public void delete(long id) {
        jdbcTemplate.update(DELETE, id);
    }
    */

    public void deleteWithLocalThread(long companyId) {
        this.jpaQuery.delete(company).where(company.id.eq(companyId)).execute();
    }
    
    /*
    public void deleteWithLocalThread(long companyId) {
        jdbcTemplate.update(DELETE, companyId);
    } */

    @Override
    public Page<Company> index(int pageNb, int elemPerPg) {
        Page<Company> page = new Page<>();
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setTotalElements((int) this.jpaQuery.selectFrom(company).fetchCount());
        page.setEntities(this.jpaQuery.selectFrom(company).limit(elemPerPg).offset(pageNb*elemPerPg).fetch());
        return page;
    }
    
   /* @Override
    public Page<Company> index(int pageNb, int elemPerPg) {
        Page<Company> page = new Page<>();
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setTotalElements(jdbcTemplate.queryForObject(COUNT, Integer.class));
        page.setEntities(jdbcTemplate.query(LISTALL, new CompanyRowMapper(), pageNb * elemPerPg, elemPerPg));
        return page;
    } */


}
