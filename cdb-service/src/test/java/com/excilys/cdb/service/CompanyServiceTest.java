package com.excilys.cdb.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.entities.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class CompanyServiceTest {

    @Autowired
    private CompanyService service;

    @Test
    public void getById() {
        CompanyDTO company = service.getById((long) 1);
        assertNotNull(company);
    }

    @Test
    public void index() {
        Page<CompanyDTO> companies = service.index(1, 20);
        assertNotNull(companies);
        assertEquals(companies.getEntities().size(), 20);
    }

    @Test
    public void createUpdateDelete() {
        int countBefore, countAfter;

        CompanyDTO origin = new CompanyDTO();
        origin.setName("A cool Company");

        // we test if create actually adds an element
        countBefore = service.index(1, 1).getTotalElements();
        long id = service.create(origin);
        countAfter = service.index(1, 1).getTotalElements();

        assertEquals(countBefore + 1, countAfter);

        // we test if getById returns the good element
        CompanyDTO db = service.getById(id);

        assertEquals(db.getName(), origin.getName());

        // we test if update does not add or delete an element
        db.setName("Great Company");

        countBefore = service.index(1, 1).getTotalElements();
        service.update(db);
        countAfter = service.index(1, 1).getTotalElements();

        assertEquals(countBefore, countAfter);

        // we test if update actually updated the element
        origin = service.getById(db.getId());

        assertEquals(db.getName(), origin.getName());

        // we test if delete actually deletes an element
        countBefore = service.index(1, 1).getTotalElements();
        service.delete(origin.getId());
        countAfter = service.index(1, 1).getTotalElements();

        assertEquals(countBefore, countAfter + 1);

        try {
            service.getById(origin.getId());
            fail();
        } catch (Exception ignored) {
        }

    }

}
