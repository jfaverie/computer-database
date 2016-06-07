package com.excilys.cdb.model.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.entities.QUser;
import com.excilys.cdb.model.entities.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserDAO extends DAO<User> {

    private QUser quser = QUser.user;
    private EntityManager em;
    private JPAQueryFactory jpaQuery;
    private SimpleJdbcInsert insertUser;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
        this.jpaQuery = new JPAQueryFactory(entityManager);
    }

    @Override
    public User findById(long id) {
        User user = this.jpaQuery.selectFrom(quser).where(quser.id.eq(id)).fetchOne();
        return user;
    }

    @Override
    public User findByName(String login) {
        User user = this.jpaQuery.selectFrom(quser).where(quser.login.eq(login)).fetchOne();
        return user;
    }

    // @Override
    // public long create(Company comp) {
    // this.em.persist(comp);
    // long id = comp.getId();
    // return id;
    // }

    @Override
    public long create(User user) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", user.getLogin());
        return insertUser.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public void update(User user) {
        this.jpaQuery.update(quser).where(quser.login.eq(user.getLogin())).set(quser.login, user.getLogin()).execute();
    }

    @Override
    public void delete(long id) {
        this.jpaQuery.delete(quser).where(quser.id.eq(id)).execute();
    }

    @Override
    public Page<User> index(int pageNb, int elemPerPg) {
        Page<User> page = new Page<>();
        page.setPageNumber(pageNb);
        page.setElementPerPage(elemPerPg);
        page.setTotalElements((int) this.jpaQuery.selectFrom(quser).fetchCount());
        page.setEntities(this.jpaQuery.selectFrom(quser).limit(elemPerPg).offset(pageNb * elemPerPg).fetch());
        return page;
    }
}
