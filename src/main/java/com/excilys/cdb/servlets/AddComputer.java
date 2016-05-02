package com.excilys.cdb.servlets;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;


@WebServlet(name = "AddComputer", urlPatterns = { "/newcomputer" })
public class AddComputer extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

    private static ComputerService computerService;
    private static CompanyService companyService;
    private Page<Computer> page;
    private final DateTimeFormatter formatter;

    public AddComputer() {
        super();
        computerService = ComputerService.INSTANCE;
        companyService = CompanyService.INSTANCE;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<CompanyDTO> companies = companyService.index(0, 100);

            req.setAttribute("companies", companies.getEntities());

            req.getRequestDispatcher("/WEB-INF/computer/add.jsp").forward(req, resp);
        } catch (DAOException e) {
            LOGGER.error("Error when get all companies", e.getMessage());
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

}
