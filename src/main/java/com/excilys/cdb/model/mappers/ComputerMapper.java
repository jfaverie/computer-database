package com.excilys.cdb.model.mappers;

import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;

public class ComputerMapper {

    /**
     * Use to convert a computer object into a computerDTO.
     * @param computer
     *            computer needed to be converted
     * @return the converted object
     */
    public static ComputerDTO convertComputer(Computer computer) {
        return computer == null ? null : ComputerDTO.getBuilder().id(computer.getId()).name(computer.getName())
                .introduced(computer.getIntroduced()).discontinued(computer.getDiscontinued())
                .company(CompanyMapper.convertCompany(computer.getCompany())).build();
    }

    /**
     * Use to convert a page of computers into a page of computerDTO.
     * @param computers
     *            the list of computers needed to be converted
     * @return the converted list
     */
    public static Page<ComputerDTO> convertPageToDTO(Page<Computer> computers) {
        Page<ComputerDTO> computersDTO = new Page<>();

        if (computers.getEntities() != null) {
            for (Computer computer : computers.getEntities()) {
                computersDTO.addEntity(convertComputer(computer));
            }
        }
        computersDTO.setElementPerPage(computers.getElementPerPage());
        computersDTO.setPageNumber(computers.getPageNumber());
        computersDTO.setSearch(computers.getSearch());
        computersDTO.setSortCol(computers.getSortCol());
        computersDTO.setSortType(computers.getSortType());
        computersDTO.setTotalElements(computers.getTotalElements());
        return computersDTO;

    }

}
