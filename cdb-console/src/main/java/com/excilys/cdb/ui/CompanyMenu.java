package com.excilys.cdb.ui;

public class CompanyMenu {

    /**
     * Get a string for the company menu.
     * @return a string with the indication for the company menu
     */
    public static String menu() {
        StringBuilder builder = new StringBuilder();

        builder.append("Company : Liste des actions possibles :\n");
        builder.append("1. Lister toutes les entreprises\n");
        builder.append("2. Entrer l'identifiant de l'entreprise\n");
        builder.append("3. Ajouter une nouvelle entreprise\n");
        builder.append("4. Metter à jour une entreprise\n");
        builder.append("5. Supprimer une entreprise\n");
        builder.append("6. Retour au menu précédent\n");

        return builder.toString();

    }

    /**
     * Get a string for the company index.
     * @return a string with the indication for the company index
     */
    public static String index() {
        StringBuilder builder = new StringBuilder();

        builder.append("Liste des entreprises :\n");

        return builder.toString();
    }

    /**
     * Get a string to get a company by id.
     * @return a string with the indication to get a company by id
     */
    public static String getById() {
        StringBuilder builder = new StringBuilder();

        builder.append("Entrer l'identifiant :\n");

        return builder.toString();
    }

    /**
     * Get a string to add a new company.
     * @return a string with the indication to add a new company
     */
    public static String add() {
        StringBuilder builder = new StringBuilder();

        builder.append("Entrer le nom de l'entreprise :\n");

        return builder.toString();
    }

    /**
     * Get a string to update a company.
     * @return a string with the indication for update a company
     */
    public static String update() {
        StringBuilder builder = new StringBuilder();

        builder.append("Donner l'identifiant et le nouveau nom de l'entreprise à mettre à jour");
        builder.append("Syntaxe : x");

        return builder.toString();
    }

    /**
     * Get a string to remove a company.
     * @return a string with the indication to remove a company
     */
    public static String remove() {
        StringBuilder builder = new StringBuilder();

        builder.append("Entrer l'identifiant :\n");

        return builder.toString();
    }

}
