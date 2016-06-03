package com.excilys.cdb.tags;

import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Pagination extends SimpleTagSupport {

    private String page;
    private String pageCount;

    public void setPage(String page) {
        this.page = page;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }
}
