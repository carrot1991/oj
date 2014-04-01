package com.oj.jxc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2014-02-26
 */
public class SShopOrdItemDO implements Serializable {

    private static final long serialVersionUID = 139338906311186367L;

    /**
     * column s_shop_ord_item.id  自增序列号
     */
    private Integer id;

    /**
     * column s_shop_ord_item.ordid  定单号
     */
    private String ordid;

    /**
     * column s_shop_ord_item.prodid  产品编号
     */
    private Integer prodid;

    /**
     * column s_shop_ord_item.amount  数量
     */
    private Integer amount;

    /**
     * column s_shop_ord_item.price  价格
     */
    private Integer price;

    /**
     * column s_shop_ord_item.createTime  时间
     */
    private Date createtime;

    public SShopOrdItemDO() {
        super();
    }

    public SShopOrdItemDO(Integer id, String ordid, Integer prodid, Integer amount, Integer price, Date createtime) {
        this.id = id;
        this.ordid = ordid;
        this.prodid = prodid;
        this.amount = amount;
        this.price = price;
        this.createtime = createtime;
    }

    /**
     * getter for Column s_shop_ord_item.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for Column s_shop_ord_item.id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getter for Column s_shop_ord_item.ordid
     */
    public String getOrdid() {
        return ordid;
    }

    /**
     * setter for Column s_shop_ord_item.ordid
     * @param ordid
     */
    public void setOrdid(String ordid) {
        this.ordid = ordid;
    }

    /**
     * getter for Column s_shop_ord_item.prodid
     */
    public Integer getProdid() {
        return prodid;
    }

    /**
     * setter for Column s_shop_ord_item.prodid
     * @param prodid
     */
    public void setProdid(Integer prodid) {
        this.prodid = prodid;
    }

    /**
     * getter for Column s_shop_ord_item.amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * setter for Column s_shop_ord_item.amount
     * @param amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * getter for Column s_shop_ord_item.price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * setter for Column s_shop_ord_item.price
     * @param price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * getter for Column s_shop_ord_item.createTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * setter for Column s_shop_ord_item.createTime
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}