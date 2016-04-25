package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.entities.Company;
import com.excilys.cdb.model.exception.DAOException;

public class CompanyConsole {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyConsole.class);

	private final KeyboardScanner scanner;
	private static CompanyConsole ourInstance = new CompanyConsole();

	private final CompanyDAO dao;

	public static CompanyConsole getInstance() {
		return ourInstance;
	}

	private CompanyConsole() {
		scanner = KeyboardScanner.getInstance();
		dao = CompanyDAO.getInstance();

	}

	public void index() {
		int currentPage = 0;

		try {
			while (true) {
				System.out.println(CompanyMenu.index());

				if (dao.index(currentPage, Main.ELEM_PER_PAGE).getEntities() != null) {
					for (Company entity : dao.index(currentPage, Main.ELEM_PER_PAGE).getEntities()) {
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
			LOGGER.error("Error when get all companies", e.getMessage());
			System.out.println("Une erreur s'est produite en récupérant tous les entreprises");
		}
	}

	public void getById() {
		System.out.println(CompanyMenu.getById());

		long id;
		do {
			id = scanner.nextLong();
		} while (id == -1);

		try {
			System.out.println(dao.findById(id));
		} catch (DAOException e) {
			LOGGER.error("Error when get a company by id", e.getMessage());
			System.out.println("Une erreur s'est produite en récupérant une entreprise");
		}
	}

	public void create() {
		System.out.println(CompanyMenu.add());

		Company company = new Company();

		do {
			company.setName(scanner.nextString());
		} while (company.getName().isEmpty());

		try {
			dao.create(company);
		} catch (DAOException e) {
			LOGGER.error("Error when create a company", e.getMessage());
			System.out.println("Une erreur s'est produite en créant une entreprise");
		}
	}

	public void update() {
		Company company = new Company();

		System.out.println("Donnez l'identifiant de l'entreprise à mettre à jour :");

		long id;
		do {
			id = scanner.nextLong();
		} while (id == -1);

		System.out.println("Nouveau nom :");

		String name = scanner.nextString();
		company.setName(name.equals("") ? company.getName() : name);

		try {
			dao.update(company);
		} catch (DAOException e) {
			LOGGER.error("Error when get update a company", e.getMessage());
			System.out.println("Une erreur s'est produite en mettant à jour une entreprise");
		}
	}

	public void delete() {
		System.out.println("Donnez l'identifiant de l'entreprise à supprimer :");

		try {
			dao.delete(scanner.nextLong());
		} catch (DAOException e) {
			LOGGER.error("Error when delete a company", e.getMessage());
			System.out.println("Une erreur s'est produite en supprimant une entreprise");
		}
	}

}
