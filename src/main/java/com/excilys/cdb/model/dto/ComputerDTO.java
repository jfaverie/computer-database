package com.excilys.cdb.model.dto;

import java.time.LocalDate;

public class ComputerDTO {

    private long id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private CompanyDTO company;


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

    public LocalDate getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ComputerDTO other = (ComputerDTO) obj;
        if (company == null) {
            if (other.company != null)
                return false;
        } else if (!company.equals(other.company))
            return false;
        if (discontinued == null) {
            if (other.discontinued != null)
                return false;
        } else if (!discontinued.equals(other.discontinued))
            return false;
        if (id != other.id)
            return false;
        if (introduced == null) {
            if (other.introduced != null)
                return false;
        } else if (!introduced.equals(other.introduced))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    /**
     * Use to return a builder.
     * @return a builder
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final ComputerDTO computerDTO = new ComputerDTO();

        public Builder() {
            super();
        }

        /**
         * Use to set the id.
         * @param id
         *            to set
         * @return the builder
         */
        public Builder id(long id) {
            this.computerDTO.id = id;
            return this;
        }

        /**
         * Use to set the name.
         * @param name
         *            to set
         * @return a builder
         */
        public Builder name(String name) {
            this.computerDTO.name = name;
            return this;
        }

        /**
         * Use to set the introduced date.
         * @param introduced
         *            to set
         * @return the builder
         */
        public Builder introduced(LocalDate introduced) {
            this.computerDTO.introduced = introduced;
            return this;
        }

        /**
         * Use to set the discontinued date.
         * @param discontinued
         *            to set
         * @return the builder
         */
        public Builder discontinued(LocalDate discontinued) {
            this.computerDTO.discontinued = discontinued;
            return this;
        }

        /**
         * Use to set the company.
         * @param companyName
         *            to set
         * @return the builder
         */
        public Builder company(CompanyDTO company) {
            this.computerDTO.company = company;
            return this;
        }

        /**
         * Return the builder.
         * @return the builder
         */
        public ComputerDTO build() {
            return this.computerDTO;
        }

    }

}
