package com.excilys.cdb.ui;

public class ComputerMenu {

    /**
     * Get a string for the computer menu.
     * @return a string with the indecation for the computer menu
     */
    public static String menu() {
        StringBuilder builder = new StringBuilder();

        builder.append("Computer : Liste des actions possibles :\n");
        builder.append("1. Lister tous les ordinateurs\n");
        builder.append("2. Entrer l'identifiant de l'ordinateur\n");
        builder.append("3. Ajouter un nouvel ordinateur\n");
        builder.append("4. Mettre à jour une entreprise\n");
        builder.append("5. Supprimer une entreprise\n");
        builder.append("6. Retour au menu précédent\n");

        return builder.toString();

    }

    /**
     * Get a string for the computer index.
     * @return a string with the indication for the computer index
     */
    public static String index() {
        StringBuilder builder = new StringBuilder();

        builder.append("Liste des ordinateurs :\n");

        return builder.toString();
    }

    /**
     * Get a string to get a computer by id.
     * @return a string with the indication to get a computer by id
     */
    public static String getById() {
        StringBuilder builder = new StringBuilder();

        builder.append("Entrer l'identifiant :\n");

        return builder.toString();
    }

    /**
     * Get a string to add a new computer.
     * @return a string with the indication to add a computer
     */
    public static String add() {
        StringBuilder builder = new StringBuilder();

        builder.append("Entrer le nom de l'ordinateur :\n");

        return builder.toString();
    }

    /**
     * Get a string to update a computer.
     * @return a string to update a computer
     */
    public static String update() {
        StringBuilder builder = new StringBuilder();

        builder.append("Donner l'identifiant et le nouveau nom de l'ordinateur à mettre à jour");
        builder.append("Syntaxe : x");

        return builder.toString();
    }

    /**
     * Get a string to remove a computer.
     * @return a string to remove a computer
     */
    public static String remove() {
        StringBuilder builder = new StringBuilder();

        builder.append("Entrer l'identifiant :\n");

        return builder.toString();
    }
}
