package com.oj.jxc.entity;

import java.io.Serializable;

/**
 * 数据对象
 * @since 2014-02-26
 */
public class SDictDO implements Serializable {

    private static final long serialVersionUID = 139338902493574648L;

    /**
     * column s_dict.k  项
     */
    private String k;

    /**
     * column s_dict.v  值
     */
    private String v;

    /**
     * column s_dict.comments  描述
     */
    private String comments;

    /**
     * column s_dict.type  分类
     */
    private String type;

    public SDictDO() {
        super();
    }

    public SDictDO(String k, String v, String comments, String type) {
        this.k = k;
        this.v = v;
        this.comments = comments;
        this.type = type;
    }

    /**
     * getter for Column s_dict.k
     */
    public String getK() {
        return k;
    }

    /**
     * setter for Column s_dict.k
     * @param k
     */
    public void setK(String k) {
        this.k = k;
    }

    /**
     * getter for Column s_dict.v
     */
    public String getV() {
        return v;
    }

    /**
     * setter for Column s_dict.v
     * @param v
     */
    public void setV(String v) {
        this.v = v;
    }

    /**
     * getter for Column s_dict.comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * setter for Column s_dict.comments
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * getter for Column s_dict.type
     */
    public String getType() {
        return type;
    }

    /**
     * setter for Column s_dict.type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

}