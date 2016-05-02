package com.excilys.cdb.model.dto;


public class CompanyDTO {

    private String name;
    private long id;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        CompanyDTO other = (CompanyDTO) obj;
        if (id != other.id)
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

        private final CompanyDTO companyDTO = new CompanyDTO();

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
            this.companyDTO.id = id;
            return this;
        }

        /**
         * Use to set the name.
         * @param name
         *            to set
         * @return a builder
         */
        public Builder name(String name) {
            this.companyDTO.name = name;
            return this;
        }
        
        /**
         * Return the builder.
         * @return the builder
         */
        public CompanyDTO build() {
            return this.companyDTO;
        }

    }
}
