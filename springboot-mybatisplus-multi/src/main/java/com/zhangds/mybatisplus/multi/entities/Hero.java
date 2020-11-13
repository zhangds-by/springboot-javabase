package com.zhangds.mybatisplus.multi.entities;

import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangds
 * @since 2020-09-10
 */
@TableName("t_hero")
public class Hero implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Double hp;
    private Double mp;
    private Double price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    public Double getMp() {
        return mp;
    }

    public void setMp(Double mp) {
        this.mp = mp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Hero{" +
        ", id=" + id +
        ", name=" + name +
        ", hp=" + hp +
        ", mp=" + mp +
        ", price=" + price +
        "}";
    }
}
