package com.excilys.cdb.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.ComputerValidator;

public class EditComputer extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(EditComputer.class);
    
    private final ComputerService computerService;
    private final CompanyService companyService;
    private final DateTimeFormatter formatter;

    /**
     * Default constructor use for init the computer service.
     */
    public EditComputer() {
        computerService = ComputerService.INSTANCE;
        companyService = CompanyService.INSTANCE;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String computerId = req.getParameter("id");

        try {
            if (computerId != null && !computerId.isEmpty()) {
                ComputerDTO computer = computerService.getById(Long.parseLong(computerId));
                Page<CompanyDTO> companies = companyService.index(0, 100);
                req.setAttribute("computer", computer);
                req.setAttribute("companies", companies.getEntities());
            }

            req.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(req, resp);
        } catch (DAOException e) {
            LOGGER.error("Error when get all companies", e.getMessage());
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ComputerValidator.fromServletRequest(req, true)) {
            Long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("computerName");
            LocalDate introduced = null;
            LocalDate discontinued = null;
            Long companyId = null;
            
            if (!(req.getParameter("introduced").isEmpty())) {
                introduced = LocalDate.parse(req.getParameter("introduced"));
            }

            if (!(req.getParameter("discontinued").isEmpty())) {
                discontinued = LocalDate.parse(req.getParameter("discontinued"));
            }

            if (!(req.getParameter("companyId").isEmpty())) {
                companyId = Long.parseLong(req.getParameter("companyId"));
            }
            
            CompanyDTO company = new CompanyDTO();
            company.setId(companyId);

            ComputerDTO computer = new ComputerDTO();
            computer.setId(id);
            computer.setName(name);
            computer.setIntroduced(introduced);
            computer.setDiscontinued(discontinued);
            computer.setCompany(company);

            computerService.update(computer);

            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }

    }

}