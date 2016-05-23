package com.excilys.cdb.model.mappers;


import com.excilys.cdb.model.dto.CompanyDTO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.entities.Page;

public class CompanyMapper {

    /**
     * Use to convert a computer object into a computerDTO.
     * @param computer
     *            computer needed to be converted
     * @return the converted object
     */
    public static CompanyDTO convertCompany(Company company) {
        return company == null ? null : CompanyDTO.getBuilder().id(company.getId()).name(company.getName()).build();
    }

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
