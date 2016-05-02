package com.excilys.cdb.model.mappers;

import com.excilys.cdb.model.dto.ComputerDTO;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.entities.Page;

public enum ComputerMapper {

    INSTANCE;

    /**
     * Use to convert a computer object into a computerDTO.
     * @param computer
     *            computer needed to be converted
     * @return the converted object
     */
    public static ComputerDTO convertComputer(Computer computer) {
        return ComputerDTO.getBuilder().id(computer.getId()).name(computer.getName())
                .introduced(computer.getIntroduced()).discontinued(computer.getDiscontinued())
                .company(CompanyMapper.convertCompany(computer.getCompany())).build();
    }

    /**
     * Use to convert a list of computers into a list of computerDTO.
     * 
     * @param computers
     *            the list of computers needed to be converted
     * @return the converted list
     */
    public static Page<ComputerDTO> convertList(Page<Computer> computers) {
        Page<ComputerDTO> computersDTO = new Page<ComputerDTO>();

        for (Computer computer : computers.getEntities()) {
            computersDTO.addEntity(ComputerDTO.getBuilder().id(computer.getId()).name(computer.getName())
                    .introduced(computer.getIntroduced()).discontinued(computer.getDiscontinued())
                    .company(CompanyMapper.convertCompany(computer.getCompany())).build());
        }
        return computersDTO;

    }

}
