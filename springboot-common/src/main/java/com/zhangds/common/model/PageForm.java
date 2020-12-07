package com.zhangds.common.model;

public class PageForm<T extends PageForm<?>> {

    private Integer pageIndex;

    private Integer size;

    public T calcCurrent(){
        pageIndex = (pageIndex - 1 ) * size;
        return (T) this;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
