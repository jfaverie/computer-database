package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.entities.QCompany;
import com.excilys.cdb.model.entities.QComputer;
import com.excilys.cdb.model.mappers.database.ComputerRowMapper;
import com.excilys.cdb.resources.SortColumn;
import com.excilys.cdb.resources.SortType;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class ComputerDAO extends DAO<Computer> {

    private static final String FIND_ID = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.id = ?;";
    private static final String FIND_NAME = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.name = ?;";
    private static final String CREATE = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE computer SET name= ?, introduced= ?, discontinued = ?, company_id = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM computer WHERE id = ?;";
    private static final String DELETE_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?;";
    private static final String LISTALL = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id LIMIT ?, ?;";
    private static final String LISTALL_ORDERED = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cy.id company_id, cy.name company_name FROM computer cr LEFT JOIN company cy on cr.company_id = cy.id WHERE cr.name LIKE ? ORDER BY %s LIMIT ?, ?;";
    private static final String COUNT = "SELECT COUNT(*) FROM computer";
    private static final String COUNT_ORDERED = "SELECT COUNT(*) FROM computer cr LEFT JOIN company cy ON cr.company_id = cy.id WHERE cr.name LIKE ?";
    private static final String LISTID = "SELECT id FROM company";

    private volatile AtomicInteger count;
    private JdbcTemplate jdbcTemplate;
    private QComputer computer = QComputer.computer;
    private QCompany company = QCompany.company;
    private EntityManager em;
    private JPAQueryFactory jpaQuery;

    //private SimpleJdbcInsert insertComputer;

    @Autowired
    public ComputerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //this.insertComputer = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("company")
              //  .usingColumns("name").usingGeneratedKeyColumns("id");
        count = new AtomicInteger();
        count.set(jdbcTemplate.queryForObject(COUNT, Integer.class));
    }

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
        this.jpaQuery = new JPAQueryFactory(entityManager);
    }

    @Override
    public Computer findById(long id) {
        Computer comp = this.jpaQuery.selectFrom(computer).where(computer.id.eq(id)).fetchFirst();
        return comp;
    }
    /*
    @Override
    public Computer findById(long id) {
        return jdbcTemplate.queryForObject(FIND_ID, new Object[] { id }, new ComputerRowMapper());
    }
*/
    @Override
    public Computer findByName(String name) {
        Computer comp = this.jpaQuery.selectFrom(computer).where(computer.name.eq(name)).fetchFirst();
        return comp;
    }
/*
    @Override
    public Computer findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_NAME, new Object[] { name }, new ComputerRowMapper());
    }
*/
    
//    @Override
//    public long create(Computer comp) {
//        this.em.persist(comp);
//        long id = comp.getId();
//        return id;
//    }

    @Override
    public long create(Computer comp) {
        String name = comp.getName();
        List<Integer> ids = jdbcTemplate.queryForList(LISTID, Integer.class);
        Date introduced = (comp.getIntroduced() != null ? Date.valueOf(comp.getIntroduced()) : null);
        Date discontinued = (comp.getDiscontinued() != null ? Date.valueOf(comp.getDiscontinued()) : null);
        Long companyId = ((comp.getCompany() != null && ids.contains(comp.getCompany().getId())) ? comp.getCompany().getId() : null);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pst =
                            con.prepareStatement(CREATE, new String[] {"id"});
                        pst.setString(1, name);
                        pst.setDate(2,introduced);
                        pst.setDate(3, discontinued);
                        if (companyId != null) {
                            pst.setLong(4, companyId);
                        } else {
                            pst.setNull(4, java.sql.Types.BIGINT);
                        }
                        return pst;
                    }
                },
                keyHolder);
        return (Long)keyHolder.getKey();
    }

    @Override
    public void update(Computer comp) {
        List<Long> ids = this.jpaQuery.select(computer.id).from(computer).fetch();
        this.jpaQuery.update(computer).where(computer.name.eq(comp.getName()))
        .set(computer.name, comp.getName())
        .set(computer.introduced, (comp.getIntroduced() != null ? comp.getIntroduced() : null))
        .set(computer.discontinued, (comp.getDiscontinued() != null ? comp.getDiscontinued() : null))
        .set(computer.id, (comp.getCompany() != null && ids.contains(comp.getCompany().getId())) ? comp.getCompany().getId() : null).execute();
    }

    /*
    @Override
    public void update(Computer comp) {
        Long id = comp.getId();
        List<Integer> ids = jdbcTemplate.queryForList(LISTID, Integer.class);
        String name = comp.getName();
        Date introduced = (comp.getIntroduced() != null ? Date.valueOf(comp.getIntroduced()) : null);
        Date discontinued = (comp.getDiscontinued() != null ? Date.valueOf(comp.getDiscontinued()) : null);
        Long companyId = ((comp.getCompany() != null && ids.contains(comp.getCompany().getId())) ? comp.getCompany().getId() : null);

        jdbcTemplate.update(UPDATE, name, introduced, discontinued, companyId, id);
    }
*/

    @Override
    public void delete(long id) {
        Computer comp = findById(id);
        System.out.println(comp);
        System.out.println(id);
        this.em.remove(comp);
       // this.jpaQuery.delete(this.computer).where(this.computer.id.eq(id)).execute();
    }

//    @Override
//    public void delete(long id) {
//        count.decrementAndGet();
//        jdbcTemplate.update(DELETE, id);
//    }

    public void deleteByCompany(long id) {
        this.jpaQuery.delete(this.computer).where(this.computer.company.id.eq(id)).execute();
    }

//    public void deleteByCompany(long id) {
//        int howMany = jdbcTemplate.update(DELETE_BY_COMPANY, id);
//        count.addAndGet(-howMany);
//    }

    @Override
    public Page<Computer> index(int pageNb, int elemPerPg) {
        Page<Computer> page = new Page<>();
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setTotalElements((int) this.jpaQuery.selectFrom(computer).fetchCount());
        page.setEntities(this.jpaQuery.selectFrom(computer).limit(elemPerPg).offset(pageNb*elemPerPg).leftJoin(this.computer.company, this.company).fetch());
        return page;
    }
    
//    @Override
//    public Page<Computer> index(int pageNb, int elemPerPg) {
//
//        Page<Computer> page = new Page<>();
//        page.setPageNumber(pageNb);
//        page.setElementPerPage(elemPerPg);
//        page.setTotalElements(jdbcTemplate.queryForObject(COUNT, Integer.class));
//        page.setEntities(jdbcTemplate.query(LISTALL, new ComputerRowMapper(), pageNb * elemPerPg, elemPerPg));
//        return page;
//    }

    public Page<Computer> indexSort(int pageNb, int elemPerPg, SortColumn sc, SortType sortType, String name) {
        Page<Computer> page = new Page<>();
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setSearch(name);
        page.setSortCol(sc);
        page.setSortType(sortType);
        page.setTotalElements((int) this.jpaQuery.selectFrom(computer).where(computer.name.like(name + "%")).leftJoin(this.computer.company, this.company).fetchCount());
        page.setEntities(this.jpaQuery.selectFrom(computer)
                .where(computer.name.like(name + "%"))
                .orderBy(sc.getOrderSpecifier())
                .limit(elemPerPg)
                .offset(pageNb*elemPerPg).fetch());
        return page;
    }
    
    
//    public Page<Computer> indexSort(int pageNb, int elemPerPg, SortColumn sc, SortType sortType, String name) {
//        Page<Computer> page = new Page<>();
//        page.setPageNumber(pageNb);
//        page.setElementPerPage(elemPerPg);
//        page.setSearch(name);
//        page.setSortCol(sc);
//        page.setSortType(sortType);
//        page.setTotalElements(jdbcTemplate.queryForObject(COUNT_ORDERED, 
//                Integer.class, name + "%"));
//        page.setEntities(jdbcTemplate.query(String.format(LISTALL_ORDERED, "NAME" + ((sortType == SortType.ASC) ? " ASC " : " DESC ")),
//                new ComputerRowMapper(),
//                name + "%",
//                (pageNb) * elemPerPg, 
//                elemPerPg));
//        return page;
//    }

}
