package com.excilys.cdb.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.service.ComputerService;

public class DeleteComputer extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputer.class);

    @Autowired
    private ComputerService computerService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selection = req.getParameter("selection");

        
        try {
            for (String id : selection.split(",")) {
                computerService.delete(Long.parseLong(id));
            }

            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (DAOException e) {
            LOGGER.error("Error when get all companies: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }
}