package com.excilys.cdb.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.dto.UserDTO;
import com.excilys.cdb.model.entities.Company.CompanyBuilder;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String login;
    protected String password;
    protected String userRole;
    static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    /**
     * User default constructor.
     */
    public User() {
    }

    /**
     * User contructor with param.
     * @param login user login
     * @param password user password
     * @param role user role
     */
    public User(String login, String password, String role) {
        LOGGER.debug("User constructor call");
        this.setLogin(login);
        this.setPassword(password);
        this.setUserRole(role);
    }

    /**
     * Constructor.
     * @param userBuilder
     *            the builder
     */
    public User(UserBuilder userBuilder) {
        super();
    }
    /**
     * Constructor.
     * @param companyBuilder
     *            the builder
     */
    public User(UserDTO dto) {
        if (dto != null) {
            id = dto.getId();
            login = dto.getLogin();
            password = dto.getPassword();
            userRole = dto.getUserRole();
        }
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public static Logger getLogger() {
        return LOGGER;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        User other = (User) obj;
        if (id != other.id)
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

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", userRole=" + userRole + "]";
    }

    public static class UserBuilder {

        private long id;
        private String login;
        private String password;
        private String userRole;

        /**
         * Builder constructor.
         */
        public UserBuilder() {
            super();
        }

        /**
         * Build the id.
         * @param id
         *            the id to build
         * @return the updated object
         */
        private UserBuilder id(long id) {
            this.id = id;
            return this;
        }

        /**
         * Build the login.
         * @param login
         *            the login to build
         * @return the updated object
         */
        private UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        /**
         * Build the login.
         * @param login
         *            the login to build
         * @return the updated object
         */
        private UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        /**
         * Build the login.
         * @param login
         *            the login to build
         * @return the updated object
         */
        private UserBuilder userRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        /**
         * Build the id.
         * @return the updated object
         */
        public User build() {
            return new User(this);
        }

    }
}
