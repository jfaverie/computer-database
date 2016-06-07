package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.dao.UserDAO;
import com.excilys.cdb.model.dto.UserDTO;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.entities.User;
import com.excilys.cdb.model.mappers.service.UserMapper;

@Service
@Transactional
public class UserService implements UserDetailsService  {

    @Autowired
    private UserDAO dao;
    
    /**
     * Return a company from an id.
     * @param id
     *            of the company
     * @return the company you want
     */
    public UserDTO getById(Long id) {
        return UserMapper.convertUser(dao.findById(id));
    }

    /**
     * Return all the company of the database, per page.
     * @param pageNb
     *            the page you want
     * @param elemPerPg
     *            the number of element per page
     * @return a page of companies
     */
    public Page<UserDTO> index(int pageNb, int elemPerPg) {
        return UserMapper.convertListUsers(dao.index(pageNb, elemPerPg));
    }

    /**
     * Add a new company in the database.
     * @param entity
     *            the company to add in the database
     * @return the id of the created company
     */
    public long create(UserDTO entity) {
        long id = dao.create(new User(entity));
        return id;
    }

    /**
     * Update a company in the database.
     * @param entity
     *            the company to update
     */
    public void update(UserDTO entity) {
        dao.update(new User(entity));
    }

    /**
     * Delete a company in the database.
     * @param id
     *            the id of the company to delete
     */
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = dao.findByName(login);

        if (user == null) {
            throw new UsernameNotFoundException("No user for name : " + login);
        }
        String userRole = user.getUserRole();
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getGrantedAuthorities(userRole)
        );
    }

    /**
     * Return the Spring authorities of a User.
     * @param user the user to determin the authorities
     * @return a list of authorities of the user
     */
    private List<GrantedAuthority> getGrantedAuthorities(String userRole) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities
        .add(new SimpleGrantedAuthority(userRole.toString()));

        return authorities;
    }
}
