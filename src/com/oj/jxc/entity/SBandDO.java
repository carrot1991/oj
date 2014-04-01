package com.oj.jxc.entity;

import java.io.Serializable;

import net.keepsoft.commons.annotation.Table;
import net.keepsoft.commons.annotation.IdentityId;
import net.keepsoft.commons.annotation.Temporary;
/**
 * 数据对象
 * @since 2014-02-26
 */
@Table("s_band")
public class SBandDO implements Serializable {

	@Temporary
    private static final long serialVersionUID = 139338892868713653L;

	@IdentityId
    private Integer bandid;

    private Integer typeid;

    private String title;
    private int level;

    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public SBandDO() {
        super();
    }

    public SBandDO(Integer bandid, Integer typeid, String title) {
        this.bandid = bandid;
        this.typeid = typeid;
        this.title = title;
    }

    /**
     * getter for Column s_band.bandid
     */
    public Integer getBandid() {
        return bandid;
    }

    /**
     * setter for Column s_band.bandid
     * @param bandid
     */
    public void setBandid(Integer bandid) {
        this.bandid = bandid;
    }

    /**
     * getter for Column s_band.typeid
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * setter for Column s_band.typeid
     * @param typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * getter for Column s_band.title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for Column s_band.title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}