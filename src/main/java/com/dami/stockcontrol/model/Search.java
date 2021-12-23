package com.dami.stockcontrol.model;

public class Search {

    private String search;
    private int type;

    private static final int PRODUCT = 0;
    private static final int USERS = 1;
    private static final int TRANSACTIONS = 3;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
