package com.excilys.cdb.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.resources.SortColumn;
import com.excilys.cdb.resources.SortType;

public class Page<T> {

    private int pageNumber;
    private List<T> entities;
    private int totalElements;
    private int elementPerPage;
    private String search;
    private SortColumn sortCol;
    private SortType sortType;


    public Page(int pageNumber, List<T> entities, int totalElements, int elementPerPage, String search,
            SortColumn sortCol, SortType sortType) {
        super();
        this.pageNumber = pageNumber;
        this.entities = entities;
        this.totalElements = totalElements;
        this.elementPerPage = elementPerPage;
        this.search = search;
        this.sortCol = sortCol;
        this.sortType = sortType;
    }

    public Page() {
        super();
    }

    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public List<T> getEntities() {
        return entities;
    }
    public void setEntities(List<T> entities) {
        this.entities = entities;
    }
    public int getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
    public int getElementPerPage() {
        return elementPerPage;
    }
    public void setElementPerPage(int elementPerPage) {
        this.elementPerPage = elementPerPage;
    }
    public String getSearch() {
        return search;
    }
    public void setSearch(String search) {
        this.search = search;
    }
    public SortColumn getSortCol() {
        return sortCol;
    }
    public void setSortCol(SortColumn sortCol) {
        this.sortCol = sortCol;
    }
    public SortType getSortType() {
        return sortType;
    }
    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + elementPerPage;
        result = prime * result + ((entities == null) ? 0 : entities.hashCode());
        result = prime * result + pageNumber;
        result = prime * result + ((search == null) ? 0 : search.hashCode());
        result = prime * result + ((sortCol == null) ? 0 : sortCol.hashCode());
        result = prime * result + ((sortType == null) ? 0 : sortType.hashCode());
        result = prime * result + totalElements;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Page other = (Page) obj;
        if (elementPerPage != other.elementPerPage) {
            return false;
        }
        if (entities == null) {
            if (other.entities != null)
                return false;
        } else if (!entities.equals(other.entities))
            return false;
        if (pageNumber != other.pageNumber) {
            return false;
        }
        if (search == null) {
            if (other.search != null)
                return false;
        } else if (!search.equals(other.search))
            return false;
        if (sortCol != other.sortCol) {
            return false;
        }
        if (sortType != other.sortType) {
            return false;
        }
        if (totalElements != other.totalElements) {
            return false;
        }
        return true;
    }

    /**
     * Add an entity to the current list of entities.
     * @param entity to add to the list
     */
    public void addEntity(T entity) {
        if (entities == null) {
            entities = new ArrayList<>();
        }

        entities.add(entity);
    }
}
