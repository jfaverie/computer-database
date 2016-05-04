package com.excilys.cdb.util;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import com.excilys.cdb.model.dto.ComputerDTO;

public class ComputerValidator {

    public static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final String DECIMAL_REGEX = "\\d+";

    /**
     * Check the validity of a computer before create it with a
     * httpServletRequest.
     * @param req
     *            the request who contains computer fields
     * @param checkId
     *            if the id field needs to be checked, set to false to create a
     *            new computer
     * @return true if all fields are valid, false else
     */
    public static boolean fromServletRequest(HttpServletRequest req, boolean checkId) {
        String name = req.getParameter("computerName");
        String introduced = req.getParameter("introduced");
        String discontinued = req.getParameter("discontinued");
        String company = req.getParameter("companyId");
        
        if (name.isEmpty()) {
            return false;
        }

        if (checkId) {
            String id = req.getParameter("id");

            if (id.isEmpty() || !id.matches(DECIMAL_REGEX)) {
                return false;
            }
        }
        if (!(introduced.isEmpty()) && !introduced.matches(DATE_REGEX)) {
            return false;
        }

        if (!(discontinued.isEmpty()) && !discontinued.matches(DATE_REGEX)) {
            return false;
        }

        if (!(company.isEmpty()) && !company.matches(DECIMAL_REGEX)) {
            return false;
        }
        return true;
    }
    public static boolean fromDto(ComputerDTO dto, boolean checkId) {
        if (checkId) {
            Long id = new Long(dto.getId());
            if (id == null || dto.getId() <= 0) {
                return false;
            }
        }

        if (dto.getName().isEmpty()) {
            return false;
        }
        
        LocalDate intro = dto.getIntroduced();
        LocalDate disco = dto.getDiscontinued();
        
        if (intro != null && disco != null && intro.isAfter(disco)) {
            return false;
        }
        
        return true;
    }
}
