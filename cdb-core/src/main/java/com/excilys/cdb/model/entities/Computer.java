package com.excilys.cdb.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.excilys.cdb.model.dto.ComputerDTO;

@Entity
@Table(name = "computer")
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column(nullable = true)
    private LocalDate introduced;
    @Column(nullable = true)
    private LocalDate discontinued;
    @ManyToOne
   // @JoinColumn(name = "company_id")
    private Company company;

    /**
     * Constructor.
     * 
     * @param computerBuilder
     */
    public Computer(ComputerBuilder computerBuilder) {
        super();
    }

    public Computer() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param id
     *            id of the Computer
     * @param name
     *            name of the Computer
     * @param introduced
     *            date the Computer has been introduced
     * @param discontinued
     *            date the Computer has been discontinued
     * @param company
     *            the company of the computer
     */
    public Computer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
        super();
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.company = company;
    }

    /**
     * Create an entity from a dto.
     * 
     * @param dto
     *            the dto used to create the entity
     */
    public Computer(ComputerDTO dto) {
        if (dto != null) {
            this.id = dto.getId();
            this.name = dto.getName();
            this.introduced = dto.getIntroduced();
            this.discontinued = dto.getDiscontinued();
            if (dto.getCompanyId() != null) {
                this.company = new Company();
                this.company.setId(dto.getCompanyId());
            }
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

    public LocalDate getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDate date) {
        this.introduced = date;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Computer other = (Computer) obj;
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (introduced == null) {
            if (other.introduced != null) {
                return false;
            }
        } else if (!introduced.equals(other.introduced)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", company=" + company + "]";
    }

    public static class ComputerBuilder {

        private long id;
        private String name;
        private LocalDate introduced;
        private LocalDate discontinued;
        private String companyName;
        private long companyId;

        public ComputerBuilder() {
            super();
        }

        private ComputerBuilder id(long id) {
            this.id = id;
            return this;
        }

        private ComputerBuilder name(String name) {
            this.name = name;
            return this;
        }

        private ComputerBuilder introduced(LocalDate introduced) {
            this.introduced = introduced;
            return this;
        }

        private ComputerBuilder discontinued(LocalDate discontinued) {
            this.discontinued = discontinued;
            return this;
        }

        private ComputerBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        private ComputerBuilder companyId(long companyId) {
            this.companyId = companyId;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }

    }

}
