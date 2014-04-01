package com.oj.jxc.entity;

import java.io.Serializable;

import net.keepsoft.commons.annotation.IdentityId;
import net.keepsoft.commons.annotation.Table;
import net.keepsoft.commons.annotation.Temporary;

/**
 * 数据对象
 * @since 2014-02-26
 */
@Table("s_category")
public class SCategoryDO implements Serializable {

	@Temporary
	private static final long serialVersionUID = 139338907654133687L;

	@IdentityId
	private Integer id;

	private String title;
    private int level;

    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SCategoryDO() {
		super();
	}
  

}