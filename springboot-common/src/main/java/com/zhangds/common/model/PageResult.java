package com.zhangds.common.model;

import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 封装分页结果
 * @author: zhangds
 * @date: 2020/9/4 9:14
 */
public class PageResult<T> {
    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pages;
    /**
     * 当前页
     */
    private Integer pageIndex;
    /**
     * 查询数量
     */
    private Integer size;
    /**
     * 分页数据
     */
    private List<T> rows;
    public PageResult(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    /**
     * 设置当前页和每页显示的数量
     * @param pageForm
     * @return
     */
    public PageResult<T> setIndexAndSize(PageForm<?> pageForm){
        BeanUtils.copyProperties(pageForm,this);
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        this.setPages(this.total % this.size > 0 ? this.total / this.size + 1 : this.total / this.size);
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
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

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
