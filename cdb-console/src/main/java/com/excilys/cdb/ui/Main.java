package com.excilys.cdb.ui;

import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Main {

    protected static final int ELEM_PER_PAGE = 20;
    private final Scanner scanner;

    /**
     * Constructor to init the java scanner.
     */
    private Main() {
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");
    }

    /**
     * Display a choice between all the CRUD for Company and Computer.
     */
    private void master() {
        System.out.println("What do you want to CRUD? [1] for Company ; [2] for Computer ; [3] to exit");
        int choice = scanner.nextInt();

        if (choice == 1) {

            this.companyMenus();

        } else if (choice == 2) {

            this.computerMenus();

        } else {
            System.exit(0);
        }
    }

    /**
     * Display the different menus for computer.
     */
    private void companyMenus() {
        CompanyConsole console = CompanyConsole.getInstance();

        System.out.println(CompanyMenu.menu());

        int choice = scanner.nextInt();

        switch (choice) {
        case 1:
            console.index();
            break;
        case 2:
            console.getById();
            break;
        case 3:
            console.create();
            break;
        case 4:
            console.update();
            break;
        case 5:
            console.delete();
            break;
        case 6:
            master();
            break;
        }

        companyMenus();
    }

    /**
     * Display the different menus for computer.
     */
    private void computerMenus() {
        ComputerConsole console = ComputerConsole.getInstance();

        System.out.println(ComputerMenu.menu());

        int choice = scanner.nextInt();

        switch (choice) {
        case 1:
            console.index();
            break;
        case 2:
            console.getById();
            break;
        case 3:
            console.create();
            break;
        case 4:
            console.update();
            break;
        case 5:
            console.delete();
            break;
        case 6:
            master();
            break;
        }

        computerMenus();
    }

    /**
     * Main method to launch the CLI.
     * @param args
     *            no args needed
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().master());
    }
}
