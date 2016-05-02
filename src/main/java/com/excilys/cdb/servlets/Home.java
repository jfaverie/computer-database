package com.excilys.cdb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.model.exception.DAOException;
import com.excilys.cdb.service.ComputerService;

@WebServlet(name = "Home", urlPatterns = { "/home" })
public class Home extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(Home.class);

    private static ComputerService computerService;
    private Page<Computer> page;

    public Home() {
        super();
        computerService = ComputerService.INSTANCE;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession(true);

        int page;
        int limit;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException ignored) {
            page = 1;
        }

        try {
            limit = Integer.parseInt(request.getParameter("limit"));
            session.setAttribute("limit", limit);
        } catch (NumberFormatException ignored) {
            Object sLimit = session.getAttribute("limit");

            if (sLimit == null) {
                limit = 10;
            } else {
                limit = (Integer) sLimit;
            }
        }

        if (page < 1) {
            page = 1;
        }

        try {
            Page<ComputerDTO> computers = computerService.index(page, limit);

            request.setAttribute("computersCount", computers.getTotalElements());
            request.setAttribute("currentPage", computers.getPageNumber());
            request.setAttribute("pageCount",
                    (int) Math.ceil(computers.getTotalElements() / (double) computers.getElementPerPage()));
            request.setAttribute("computers", computers.getEntities());
        } catch (DAOException e) {
            LOGGER.error("Error when get all computers", e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }

        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Add research for 2.1 release
        super.doPost(req, resp);
    }

}
