package com.excilys.cdb.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.util.ComputerValidator;

public class AddComputer extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

    @Autowired
    private ComputerService computerService;
    @Autowired
    private CompanyService companyService;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<CompanyDTO> companies = companyService.index(0, 100);

            req.setAttribute("companies", companies.getEntities());

            req.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req, resp);
        } catch (DAOException e) {
            LOGGER.error("Error when get all companies", e.getMessage());
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ComputerValidator.fromServletRequest(req, false)) {
            String name = req.getParameter("computerName");
            LocalDate introduced = null;
            LocalDate discontinued = null;
            Long companyId = null;

            if (!(req.getParameter("introduced")).isEmpty()) {
                introduced = LocalDate.parse(req.getParameter("introduced"));
            }

            if (!(req.getParameter("discontinued")).isEmpty()) {
                discontinued = LocalDate.parse(req.getParameter("discontinued"));
            }

            if (!(req.getParameter("companyId")).isEmpty()) {
                companyId = Long.parseLong(req.getParameter("companyId"));
            }

            CompanyDTO company = new CompanyDTO();
            company.setId(companyId);

            ComputerDTO computer = new ComputerDTO();
            computer.setName(name);
            computer.setIntroduced(introduced);
            computer.setDiscontinued(discontinued);
            computer.setCompanyName(company.getName());
            computer.setCompanyId(company.getId());

            computerService.create(computer);

            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }


}
