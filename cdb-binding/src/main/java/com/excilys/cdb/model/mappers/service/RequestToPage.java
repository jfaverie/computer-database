/*
package com.excilys.cdb.model.mappers.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.resources.SortColumn;
import com.excilys.cdb.resources.SortType;
import com.excilys.cdb.service.ComputerService;

@Component
public class RequestToPage {

    @Autowired
    private ComputerService computerService;

    /**
     * Convert a request (not used anymore).
     * @param request
     * @return
     */
    /*
    public Page<ComputerDTO> convert(HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        int page;
        int limit;
        SortColumn sc;
        SortType sortType;
        String name;
        Page<ComputerDTO> computers = new Page<>();

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException ignored) {
            page = 1;
        }

        try {
            limit = Integer.parseInt(request.getParameter("nbel"));
            session.setAttribute("limit", limit);
        } catch (NumberFormatException ignored) {
            limit = 10;
        }

        if (page < 1) {
            page = 1;
        }

        if (request.getParameter("search") == null) {
            name = "";
        } else {
            name = request.getParameter("search");
        }

        try {
            sc = SortColumn.values()[(Integer.parseInt(request.getParameter("colId")))];
        } catch (NumberFormatException ignored) {
            sc = SortColumn.values()[0];
        }

        try {
            sortType = SortType.values()[(Integer.parseInt(request.getParameter("colType")))];
        } catch (NumberFormatException ignored) {
            sortType = SortType.values()[0];
        }

        computers = computerService.indexSort(page, limit, sc, sortType, name);

        request.setAttribute("nbComputers", computers.getTotalElements());
        request.setAttribute("currentPage", computers.getPageNumber());
        request.setAttribute("limit", computers.getElementPerPage());
        request.setAttribute("nbPage",
                (int) Math.ceil(computers.getTotalElements() / (double) computers.getElementPerPage()));
        request.setAttribute("computers", computers.getEntities());
        request.setAttribute("computerSort",
                computers.getSortCol().ordinal() == 1 && computers.getSortType().ordinal() == 0 ? 1 : 0);
        request.setAttribute("introSort",
                computers.getSortCol().ordinal() == 2 && computers.getSortType().ordinal() == 0 ? 1 : 0);
        request.setAttribute("discoSort",
                computers.getSortCol().ordinal() == 3 && computers.getSortType().ordinal() == 0 ? 1 : 0);
        request.setAttribute("companySort",
                computers.getSortCol().ordinal() == 4 && computers.getSortType().ordinal() == 0 ? 1 : 0);
        request.setAttribute("sortCol", computers.getSortCol().ordinal());
        request.setAttribute("sortType", computers.getSortType().ordinal());
        request.setAttribute("search", computers.getSearch());

        return computers;
    }

}
*/