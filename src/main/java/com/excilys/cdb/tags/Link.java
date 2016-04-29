package com.excilys.cdb.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Link extends SimpleTagSupport {

    private String name;
    private String target;
    private String page;
    private String limit;
    public void setName(String name) {
        this.name = name;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String url = "<a class=\"btn btn-default\" href=\"%s?page=%s&limit=%s\">%s</a>";

        JspWriter out = getJspContext().getOut();
        out.println(String.format(url, target, page, limit, name));
    }
}
