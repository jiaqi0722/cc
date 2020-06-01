package com.cc.api.common.response;

import java.util.ArrayList;
import java.util.List;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: Page
 */
public class Page<T> {
    private Integer totalCount;

    private Integer totalPage;

    private Integer pageSize;

    private Integer pageNumber;

    private List<T> data;

    public Page(Integer totalCount, Integer pageSize, Integer pageNumber, List<T> data) {
        this.totalCount = totalCount;
        this.totalPage = Integer.valueOf((totalCount.intValue() % pageSize.intValue() == 0) ? (totalCount.intValue() / pageSize.intValue()) : (totalCount.intValue() / pageSize.intValue() + 1));
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.data = data;
    }

    public Page(Integer totalCount, Integer pageSize, Integer pageNumber) {
        this(totalCount, pageSize, pageNumber, null);
    }

    public Page() {
        this.totalCount = Integer.valueOf(0);
        this.totalPage = Integer.valueOf(0);
        this.pageNumber = Integer.valueOf(0);
        this.pageSize = Integer.valueOf(0);
        this.data = new ArrayList<>();
    }

    public Boolean hasData() {
        return Boolean.valueOf((this.totalCount.intValue() > 0));
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

