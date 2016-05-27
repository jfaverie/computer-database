package com.excilys.cdb.model.entities;

import java.io.Serializable;

import com.excilys.cdb.model.dto.CompanyDTO;

public class Company implements Serializable {

    private long id = 0;
    private String name = "";

    /**
     * Constructor.
     * @param companyBuilder
     *            the builder
     */
    public Company(CompanyBuilder companyBuilder) {
        super();
    }

    /**
     * Constructor.
     */
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

    /**
     * Create an entity from a DTO.
     * @param dto
     *            the dto used to create the entity
     */
    public Company(CompanyDTO dto) {
        if (dto != null) {
            id = dto.getId();
            name = dto.getName();
        }
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

        /**
         * Builder constructor.
         */
        public CompanyBuilder() {
            super();
        }

        /**
         * Build the id.
         * @param id
         *            the id to build
         * @return the updated object
         */
        private CompanyBuilder id(long id) {
            this.id = id;
            return this;
        }

        /**
         * Build the name.
         * @param name
         *            the name to build
         * @return the updated object
         */
        private CompanyBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Build the id.
         * @return the updated object
         */
        public Company build() {
            return new Company(this);
        }

    }

}