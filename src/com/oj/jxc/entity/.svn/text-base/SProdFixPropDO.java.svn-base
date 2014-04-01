package com.oj.jxc.entity;

import java.io.Serializable;

import net.keepsoft.commons.annotation.Table;
import net.keepsoft.commons.annotation.Temporary;

/**
 * 数据对象
 * @since 2014-02-26
 */
@Table("s_prod_fix_prop")
public class SProdFixPropDO implements Serializable {

	@Temporary
    private static final long serialVersionUID = 139338903833960097L;

    /**
     * column s_prod_fix_prop.typeid  商品大类id,参见 s_tree_def prop=1,如笔记本
     */
    private Integer typeid;

    /**
     * column s_prod_fix_prop.propid  属性编号
     */
    private Integer propid;

    /**
     * column s_prod_fix_prop.name  属性名称,如内存容量
     */
    private String name;

    /**
     * column s_prod_fix_prop.vlue  属性可选值,如 2,4,8
     */
    private String vlue;

    /**
     * column s_prod_fix_prop.unit  单位,如G
     */
    private String unit;

    public SProdFixPropDO() {
        super();
    }

    public SProdFixPropDO(Integer typeid, Integer propid, String name, String vlue, String unit) {
        this.typeid = typeid;
        this.propid = propid;
        this.name = name;
        this.vlue = vlue;
        this.unit = unit;
    }

    /**
     * getter for Column s_prod_fix_prop.typeid
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * setter for Column s_prod_fix_prop.typeid
     * @param typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * getter for Column s_prod_fix_prop.propid
     */
    public Integer getPropid() {
        return propid;
    }

    /**
     * setter for Column s_prod_fix_prop.propid
     * @param propid
     */
    public void setPropid(Integer propid) {
        this.propid = propid;
    }

    /**
     * getter for Column s_prod_fix_prop.name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for Column s_prod_fix_prop.name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for Column s_prod_fix_prop.vlue
     */
    public String getVlue() {
        return vlue;
    }

    /**
     * setter for Column s_prod_fix_prop.vlue
     * @param vlue
     */
    public void setVlue(String vlue) {
        this.vlue = vlue;
    }

    /**
     * getter for Column s_prod_fix_prop.unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * setter for Column s_prod_fix_prop.unit
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

}