package com.zhangds.common.model;

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
    private Long total;
    /**
     * 每页数据
     */
    private List<T> rows;
    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
