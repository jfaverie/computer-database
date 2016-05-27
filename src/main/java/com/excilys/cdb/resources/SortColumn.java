package com.excilys.cdb.resources;

public enum SortColumn {
    ID("ID"), NAME("NAME"), INTRODUCED("INTRODUCED"), DISCONTINUED("DISCONTINUED"), COMPANY("cy.name");

    private String col;

    /**
     * The constructor.
     * @param col
     *            the string to put
     */
    SortColumn(String col) {
        this.col = col;
    }

    /**
     * Inject the SQL.
     * @return the string
     */
    public String injectSQL() {
        return col;
    }
}