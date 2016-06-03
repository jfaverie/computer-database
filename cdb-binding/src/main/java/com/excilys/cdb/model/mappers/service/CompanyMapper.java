package com.excilys.cdb.model.mappers.service;

import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;

public class CompanyMapper {

    /**
     * Use to convert a company object into a companyDTO.
     * @param company
     *            company needed to be converted
     * @return the converted object
     */
    public static CompanyDTO convertCompany(Company company) {
        return company == null ? null : CompanyDTO.getBuilder().id(company.getId()).name(company.getName()).build();
    }

    /**
     * Use to convert a page.
     * @param companies
     *            the page of companies to be converted
     * @return the converted object
     */
    public static Page<CompanyDTO> convertListCompanies(Page<Company> companies) {
        Page<CompanyDTO> companiesDTO = new Page<CompanyDTO>();
        for (Company company : companies.getEntities()) {
            companiesDTO.addEntity(CompanyDTO.getBuilder().id(company.getId()).name(company.getName()).build());
        }
        companiesDTO.setElementPerPage(companies.getElementPerPage());
        companiesDTO.setPageNumber(companies.getPageNumber());
        companiesDTO.setTotalElements(companies.getTotalElements());
        return companiesDTO;
    }

}
