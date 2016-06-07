package com.excilys.cdb.model.mappers.service;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.dto.UserDTO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.entities.User;

public class UserMapper {

    /**
     * Use to convert a company object into a companyDTO.
     * @param company
     *            company needed to be converted
     * @return the converted object
     */
    public static UserDTO convertUser(User user) {
        return user == null ? null : UserDTO.getBuilder().id(user.getId()).login(user.getLogin()).password(user.getPassword()).userRole(user.getUserRole()).build();
    }

    /**
     * Use to convert a page.
     * @param users
     *            the page of users to be converted
     * @return the converted object
     */
    public static Page<UserDTO> convertListUsers(Page<User> users) {
        Page<UserDTO> usersDTO = new Page<UserDTO>();
        for (User user : users.getEntities()) {
            usersDTO.addEntity(UserDTO.getBuilder().id(user.getId()).login(user.getLogin()).password(user.getPassword()).userRole(user.getUserRole()).build());
        }
        usersDTO.setElementPerPage(users.getElementPerPage());
        usersDTO.setPageNumber(users.getPageNumber());
        usersDTO.setTotalElements(users.getTotalElements());
        return usersDTO;
    }
}
