/*package com.excilys.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.model.mappers.service.RequestToPage;

public class Home extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Home.class);
    @Autowired
    private RequestToPage rtp;

    /**
     * @param config
     *            not used
     */
/*
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            rtp.convert(request);
        } catch (DAOException e) {
            LOGGER.error("Error when get all computers", e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }

        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Add research for 2.1 release
        super.doPost(request, response);
    }

}
*/