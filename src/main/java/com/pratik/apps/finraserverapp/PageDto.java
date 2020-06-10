package com.pratik.apps.finraserverapp;

import java.util.List;

public class PageDto<T> {
    private int totalPages;
    private int totalItems;
    private int currentPage;
    private List<T> pageItems;

    public PageDto(int totalPages,int totalItems, int currentPage, List<T> items) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageItems = items;
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
