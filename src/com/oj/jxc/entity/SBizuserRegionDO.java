package com.oj.jxc.entity;

import java.io.Serializable;

/**
 * 数据对象
 * @since 2014-02-26
 */
public class SBizuserRegionDO implements Serializable {

    private static final long serialVersionUID = 139338902076437720L;

    /**
     * column s_bizuser_region.id  自动编号
     */
    private Integer id;

    /**
     * column s_bizuser_region.uname  用户名-对上商务
     */
    private String uname;

    /**
     * column s_bizuser_region.regionid  区域编号,@see s_region
     */
    private Integer regionid;

    public SBizuserRegionDO() {
        super();
    }

    public SBizuserRegionDO(Integer id, String uname, Integer regionid) {
        this.id = id;
        this.uname = uname;
        this.regionid = regionid;
    }

    /**
     * getter for Column s_bizuser_region.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for Column s_bizuser_region.id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getter for Column s_bizuser_region.uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * setter for Column s_bizuser_region.uname
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * getter for Column s_bizuser_region.regionid
     */
    public Integer getRegionid() {
        return regionid;
    }

    /**
     * setter for Column s_bizuser_region.regionid
     * @param regionid
     */
    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

}