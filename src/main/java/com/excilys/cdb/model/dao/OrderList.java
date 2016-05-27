package com.excilys.cdb.model.dao;

public enum OrderList {

    DEFAULT("cr.id"),
    NAME_ASC("cr.name ASC"),
    NAME_DESC("cr.name DESC"),
    INTRODUCED_ASC("cr.introduced ASC"),
    INTRODUCED_DESC("cr.introduced DESC"),
    DINSCONTINUED_ASC("cr.discontinued ASC"),
    DISCONTINUED_DESC("cr.discontinued DESC"),
    COMPANY_ASC("cy.name ASC"),
    COMPANY_DESC("cy.name DESC");

    private String col;
    
    private OrderList(String col) {
        this.col=col;
    }

    public String injectSQL() {
        return col;
    }
}
