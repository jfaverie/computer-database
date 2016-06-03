package com.excilys.cdb.resources;


import com.excilys.cdb.model.entities.QCompany;
import com.excilys.cdb.model.entities.QComputer;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;

public enum SortColumn {

    DEFAULT(QComputer.computer.id, Order.ASC),
    NAME_ASC(QComputer.computer.name, Order.ASC),
    NAME_DESC(QComputer.computer.name, Order.DESC),
    INTRODUCED_ASC(QComputer.computer.introduced, Order.ASC),
    INTRODUCED_DESC(QComputer.computer.introduced, Order.DESC),
    DISCONTINUED_ASC(QComputer.computer.discontinued, Order.ASC),
    DISCONTINUED_DESC(QComputer.computer.discontinued, Order.DESC),
    COMPANY_ASC(QCompany.company.name, Order.ASC),
    COMPANY_DESC(QCompany.company.name, Order.DESC);

    private final OrderSpecifier<? extends Comparable> orderSpecifier;

    /**
     * Initialize column name and ascending option.
     * @param column the column name for the SQL table
     * @param order the ascending option for the order by
     */
    SortColumn(Expression<? extends Comparable> column, Order order) {
        orderSpecifier = new OrderSpecifier<>(order, column);

        PathBuilder<?> orderByExpression = new PathBuilder<>(QComputer.class, "computer");
        OrderSpecifier<? extends Comparable> o = new OrderSpecifier<>(Order.ASC, orderByExpression.get("name", Comparable.class));
    }

    /**
     * Return the OrderSpecifier object who represent an order by for QueryDSL API.
     * @return an OrderSpecifier object for QueryDSL API
     */
    public OrderSpecifier<?> getOrderSpecifier() {
        return orderSpecifier;
    }

}

