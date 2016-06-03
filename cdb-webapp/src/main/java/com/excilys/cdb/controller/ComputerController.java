package com.excilys.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Page;
import com.excilys.cdb.resources.SortColumn;
import com.excilys.cdb.resources.SortType;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

@Controller
public class ComputerController {

    private final ComputerService computerService;
    private final CompanyService companyService;

    @Autowired
    public ComputerController(ComputerService service, CompanyService companyService) {
        this.computerService = service;
        this.companyService = companyService;
    }

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "nbel", required = false) Integer limit,
            @RequestParam(value = "search", required = false) String name,
            @RequestParam(value = "colId", required = false) Integer sc,
            @RequestParam(value = "colType", required = false) Integer sortType,
            @CookieValue(value = "limit", required = false) String cookieLimit, HttpServletResponse response,
            Model model) {
        Page<ComputerDTO> computers = new Page<>();

        if (limit == null || limit.equals(0)) {
            if (cookieLimit != null) {
                limit = (Integer.parseInt(cookieLimit));
            } else {
                limit = 10;
            }
        }

        response.addCookie(new Cookie("limit", limit + ""));

        if (page == null || page < 1) {
            page = 1;
        }

        if (name == null || name.equals("")) {
            name = "";
        }

        SortColumn sc2 = SortColumn.values()[0];
        SortType sortType2 = SortType.values()[0];

        if (sc == null || sc.equals(0)) {
            sc2 = SortColumn.values()[0];
        } else {
            sc2 = SortColumn.values()[sc];
        }

        if (sortType == null || sortType.equals(0)) {
            sortType2 = SortType.values()[0];
        } else {
            sortType2 = SortType.values()[sortType];
        }

        computers = computerService.indexSort(page, limit, sc2, sortType2, name);

        model.addAttribute("nbComputers", computers.getTotalElements());
        model.addAttribute("currentPage", computers.getPageNumber());
        model.addAttribute("limit", computers.getElementPerPage());
        model.addAttribute("nbPage",
                (int) Math.ceil(computers.getTotalElements() / (double) computers.getElementPerPage()));
        model.addAttribute("computers", computers.getEntities());
        model.addAttribute("computerSort",
                computers.getSortCol().ordinal() == 1 && computers.getSortType().ordinal() == 0 ? 1 : 0);
        model.addAttribute("introSort",
                computers.getSortCol().ordinal() == 2 && computers.getSortType().ordinal() == 0 ? 1 : 0);
        model.addAttribute("discoSort",
                computers.getSortCol().ordinal() == 3 && computers.getSortType().ordinal() == 0 ? 1 : 0);
        model.addAttribute("companySort",
                computers.getSortCol().ordinal() == 4 && computers.getSortType().ordinal() == 0 ? 1 : 0);
        model.addAttribute("sortCol", computers.getSortCol().ordinal());
        model.addAttribute("sortType", computers.getSortType().ordinal());
        model.addAttribute("search", computers.getSearch());

        return "home";

    }

    @RequestMapping(path = "/addComputer", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("companies", companyService.index(0, 100).getEntities());

        return "addComputer";
    }

    @RequestMapping(path = "/addComputer", method = RequestMethod.POST)
    public String create(@Valid ComputerDTO computer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("computer", computer);
            model.addAttribute("companies", companyService.index(0, 100).getEntities());

            List<String> errors = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(((FieldError) error).getField());
            }
            model.addAttribute("errors", errors);
            return "addComputer";
        }

        computerService.create(computer);

        return "redirect:/home";
    }

    @RequestMapping(path = "/editComputer", method = RequestMethod.GET)
    public String update(Long id, Model model) {
        model.addAttribute("computer", computerService.getById(id));
        model.addAttribute("companies", companyService.index(0, 100).getEntities());

        return "editComputer";
    }

    @RequestMapping(path = "/editComputer", method = RequestMethod.POST)
    public String edit(@Valid ComputerDTO computer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("computer", computer);
            model.addAttribute("companies", companyService.index(0, 100).getEntities());

            List<String> errors = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(((FieldError) error).getField());
            }
            model.addAttribute("errors", errors);
            return "editComputer";
        }

        computerService.update(computer);

        return "redirect:/home";
    }

    @RequestMapping(path = "/deleteComputer", method = RequestMethod.POST)
    public String delete(String selection) {
        for (String id : selection.split(",")) {
            computerService.delete(Long.parseLong(id));
        }

        return "redirect:/home";
    }
}
