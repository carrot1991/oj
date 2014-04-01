package com.oj.jxc.entity;

import java.io.Serializable;

import net.keepsoft.commons.annotation.IdentityId;
import net.keepsoft.commons.annotation.Table;
import net.keepsoft.commons.annotation.Temporary;

/**
 * 数据对象
 * @since 2014-02-26
 */
@Table("s_type")
public class STypeDO implements Serializable {

	@Temporary
	private static final long serialVersionUID = 139338907654133687L;

	@IdentityId
	private Integer typeid;

	private Integer cateid;
    private int level;

    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public Integer getCateid() {
		return cateid;
	}

	public void setCateid(Integer cateid) {
		this.cateid = cateid;
	}

	private String title;

	public STypeDO() {
		super();
	}

	public STypeDO(Integer typeid, String title) {
		this.typeid = typeid;
		this.title = title;
	}

	/**
	 * getter for Column s_type.typeid
	 */
	public Integer getTypeid() {
		return typeid;
	}

	/**
	 * setter for Column s_type.typeid
	 * @param typeid
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	/**
	 * getter for Column s_type.title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * setter for Column s_type.title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}