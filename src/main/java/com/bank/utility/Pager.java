package com.bank.utility;

public class Pager {
    private Integer page;
    private Integer size;

    public Pager() {}

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
