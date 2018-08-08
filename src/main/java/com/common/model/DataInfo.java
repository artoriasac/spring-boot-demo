package com.common.model;

import java.util.List;

/**
 * @Author:artorias
 * @Description:
 * @Date:create in 15:53 2018/7/30 0030
 * Modeified By:
 */
public class DataInfo<T> {
    private int pageSize;
    private int pages;
    private long total;
    private int pageNum;
    private List<T> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
