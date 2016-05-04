package com.excilys.cdb.service;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;

public class ComputerServiceTest {

    private ComputerService service;

    @Before
    public void setUp() throws Exception {
        service = ComputerService.INSTANCE;
    }

    @Test
    public void getById() {
        Computer computer = service.getById((long) 1);
        assertNotNull(computer);
    }

    @Test
    public void index() {
        Page<Computer> computers = service.index(1, 20);
        assertNotNull(computers);
        assertEquals(computers.getEntities().size(), 20);
    }

    @Test
    public void createUpdateDelete() {
        int countBefore, countAfter;

        Computer origin = new Computer();
        origin.setName("A cool Computer");

        // we test if create actually adds an element
        countBefore = service.index(1, 1).getTotalElements();
        long id = service.create(origin);
        countAfter = service.index(1, 1).getTotalElements();

        assertEquals(countBefore + 1, countAfter);

        // we test if getById returns the good element
        Computer db = service.getById(id);

        assertEquals(db.getIntroduced(), origin.getIntroduced());
        assertEquals(db.getDiscontinued(), origin.getDiscontinued());
        assertEquals(db.getName(), origin.getName());

        // we test if update does not add or delete an element
        db.setName("Great Computer");
        db.setIntroduced(LocalDate.of(1999, 01, 12));
        db.setDiscontinued(LocalDate.of(2001, 01, 01));

        countBefore = service.index(1, 1).getTotalElements();
        service.update(db);
        countAfter = service.index(1, 1).getTotalElements();

        assertEquals(countBefore, countAfter);

        // we test if update actually updated the element
        Computer db2 = service.getById(db.getId());

        assertEquals(db.getName(), db2.getName());
        assertEquals(db.getIntroduced(), db2.getIntroduced());
        assertEquals(db.getDiscontinued(), db2.getDiscontinued());

        // we test if delete actually deletes an element
        countBefore = service.index(1, 1).getTotalElements();
        service.delete(db2.getId());
        countAfter = service.index(1, 1).getTotalElements();

        assertEquals(countBefore, countAfter + 1);

        try {
            service.getById(origin.getId());
            fail();
        } catch (Exception ignored) {
        }

    }

}
