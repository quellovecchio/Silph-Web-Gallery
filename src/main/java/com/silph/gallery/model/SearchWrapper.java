package com.silph.gallery.model;

/**
 * SearchWrapper
 */
public class SearchWrapper {

    private String search;

    /* 0 = all
     * 1 = photos
     * 2 = albums
     * 3 = photographers
     */
    private int type;

    public SearchWrapper() {
    };

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}