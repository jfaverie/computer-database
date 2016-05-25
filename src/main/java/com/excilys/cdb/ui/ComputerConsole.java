package com.excilys.cdb.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.entities.Computer;
import com.excilys.cdb.model.exception.DAOException;

public class ComputerConsole {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerConsole.class);

    private KeyboardScanner scanner;
    private ComputerDAO dao;
    private CompanyDAO companyDAO;

    private static ComputerConsole instance = new ComputerConsole();

    public static ComputerConsole getInstance() {
        return instance;
    }

    /**
     * Display CLI commands to index the computers.
     */
    public void index() {
        int currentPage = 0;

        try {
            while (true) {
                System.out.println(ComputerMenu.index());

                if (dao.index(currentPage, Main.ELEM_PER_PAGE).getEntities() != null) {
                    for (Computer entity : dao.index(currentPage, Main.ELEM_PER_PAGE).getEntities()) {
                        System.out.println(entity);
                    }

                    System.out.println("[1] Page Précédente [2] Page Suivante [3] Sortir");
                } else {
                    System.out.println("[1] Page Précédente [3] Sortir");
                }

                int value = scanner.nextInt();

                if (value == 1) {
                    if (currentPage != 0) {
                        currentPage--;
                    }
                } else if (value == 2) {
                    currentPage++;
                } else {
                    break;
                }
            }
        } catch (DAOException e) {
            LOGGER.error("Error when get all computers", e.getMessage());
            System.out.println("Une erreur s'est produite en récupérant tous les ordinateurs");
        }
    }

    /**
     * Display CLI commands to get a computer with an id.
     */
    public void getById() {
        System.out.println(ComputerMenu.getById());

        long id;
        do {
            id = scanner.nextLong();
        } while (id == -1);

        try {
            System.out.println(dao.findById(id));
        } catch (DAOException e) {
            LOGGER.error("Error when get a computer by id", e.getMessage());
            System.out.println("Une erreur s'est produite en récupérant un ordinateur");
        }
    }

    /**
     * Display CLI commands to create a computer.
     */
    public void create() {
        LocalDate date;
        Computer computer = null;

        System.out.println("Entrer le nom de l'ordinateur");
        do {
            computer.setName(scanner.nextString());
        } while (computer.getName().isEmpty());

        System.out.println("Entrer la date de lancement l'ordinateur (yyyy-MM-dd)");
        date = scanner.nextDate();
        computer.setIntroduced(date);

        System.out.println("Entrer l'arrêt de vente de l'ordinateur (yyyy-MM-dd)");
        date = scanner.nextDate();
        computer.setDiscontinued(date);

        System.out.println("Entrer l'identifiant du constructeur");
        computer.setCompany(companyDAO.findById(scanner.nextLong()));

        try {
            dao.create(computer);
        } catch (DAOException e) {
            LOGGER.error("Error when create a computer", e.getMessage());
            System.out.println("Une erreur c'est produite en créant un ordinateur");
        }
    }

    /**
     * Display CLI commands to update a computer.
     */
    public void update() {
        LocalDate date;

        System.out.println("Donner l'identifiant de l'ordinateur à mettre à jour :");
        Computer computer = dao.findById(scanner.nextLong());

        System.out.println("Entrer le nom de l'ordinateur");
        System.out.println("Ancien nom : " + computer.getName());
        do {
            computer.setName(scanner.nextString());
        } while (computer.getName().isEmpty());

        System.out.println("Entrer la date de lancement l'ordinateur (yyyy-MM-dd)");
        System.out.println("Ancienne date : " + computer.getIntroduced());
        date = scanner.nextDate();
        if (date != null) {
            computer.setIntroduced(date);
        }

        System.out.println("Entrer l'arrêt de vente de l'ordinateur (yyyy-MM-dd)");
        System.out.println("Ancienne date : " + computer.getDiscontinued());
        date = scanner.nextDate();
        if (date != null) {
            computer.setDiscontinued(date);
        }

        System.out.println("Entrer l'identifiant du constructeur");
        System.out.println("Ancien identifiant de l'entreprise " + computer.getCompany().getName() + " : "
                + computer.getCompany().getId());
        computer.setCompany(companyDAO.findById(scanner.nextLong()));

        try {
            dao.update(computer);
        } catch (DAOException e) {
            LOGGER.error("Error when get update a computer", e.getMessage());
            System.out.println("Une erreur s'est produite en mettant à jour un ordinateur");
        }
    }

    /**
     * Display CLI commands to delete a computer.
     */
    public void delete() {
        System.out.println("Donner l'identifiant de l'ordinateur à supprimer :");

        try {
            dao.delete(scanner.nextLong());
        } catch (DAOException e) {
            LOGGER.error("Error when delete a computer", e.getMessage());
            System.out.println("Une erreur s'est produite en supprimant un ordinateur");
        }
    }

}
