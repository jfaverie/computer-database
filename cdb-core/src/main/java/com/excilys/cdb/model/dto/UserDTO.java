package com.excilys.cdb.model.dto;

public class UserDTO {

    private Long id;
    private String login;
    private String password;
    private String userRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
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
        UserDTO other = (UserDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (userRole != other.userRole)
            return false;
        return true;
    }

    /**
     * Use to return a builder.
     * 
     * @return a builder
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final UserDTO userDTO = new UserDTO();

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
            this.userDTO.setId(id);
            return this;
        }

        /**
         * Use to set the name.
         * @param login
         *            to set
         * @return a builder
         */
        public Builder login(String login) {
            this.userDTO.setLogin(login);
            return this;
        }

        /**
         * Use to set the name.
         * @param login
         *            to set
         * @return a builder
         */
        public Builder password(String password) {
            this.userDTO.setPassword(password);
            return this;
        }

        /**
         * Use to set the name.
         * @param login
         *            to set
         * @return a builder
         */
        public Builder userRole(String userRole) {
            this.userDTO.setUserRole(userRole);
            return this;
        }
        
        /**
         * Return the builder.
         * @return the builder
         */
        public UserDTO build() {
            return this.userDTO;
        }
    }
}
