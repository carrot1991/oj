package com.oj.jxc.entity;

import java.io.Serializable;

/**
 * 数据对象
 * @since 2014-02-26
 */
public class SBizRegionDO implements Serializable {

    private static final long serialVersionUID = 139338901163973862L;

    /**
     * column s_biz_region.uname  用户名-对下商务角色
     */
    private String uname;

    /**
     * column s_biz_region.regionid  区域编号
     */
    private Integer regionid;

    public SBizRegionDO() {
        super();
    }

    public SBizRegionDO(String uname, Integer regionid) {
        this.uname = uname;
        this.regionid = regionid;
    }

    /**
     * getter for Column s_biz_region.uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * setter for Column s_biz_region.uname
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * getter for Column s_biz_region.regionid
     */
    public Integer getRegionid() {
        return regionid;
    }

    /**
     * setter for Column s_biz_region.regionid
     * @param regionid
     */
    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

}