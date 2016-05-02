package com.excilys.cdb.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.excilys.cdb.model.entities.Computer.ComputerBuilder;

public class Company implements Serializable {

    private long id = 0;
    private String name = "";

    /**
     * Constructor.
     */
    public Company(CompanyBuilder companyBuilder) {
        super();
    }
    
    public Company() {
        super();
    }

    /**
     * Constructor.
     * @param id
     *            id of the Company
     * @param name
     *            name of the Company
     */
    public Company(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company [id=" + id + ", name=" + name + "]";
    }

    public static class CompanyBuilder {

        private long id;
        private String name;

        public CompanyBuilder() {
            super();
        }

        private CompanyBuilder id(long id) {
            this.id = id;
            return this;
        }

        private CompanyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Company build() {
            return new Company(this);
        }

    }

}