package com.oj.jxc.entity;

import java.io.Serializable;

import net.keepsoft.commons.annotation.Table;
import net.keepsoft.commons.annotation.Temporary;
import net.keepsoft.commons.annotation.IdentityId;
/**
 * 数据对象
 * @since 2014-02-26
 */
@Table("s_prod_def")
public class SProdDefDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4696179770397105893L;

	@Temporary

	@IdentityId
    private int id;

    private int bandid;

    private String nickname;

    private String config;

    private String describ;//综述
    
    private String xinghao;
    
    private int tingchan;
    
    private int isass;//是否配件

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBandid() {
		return bandid;
	}

	public void setBandid(int bandid) {
		this.bandid = bandid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getDescrib() {
		return describ;
	}

	public void setDescrib(String describ) {
		this.describ = describ;
	}

	public String getXinghao() {
		return xinghao;
	}

	public void setXinghao(String xinghao) {
		this.xinghao = xinghao;
	}

	public int getTingchan() {
		return tingchan;
	}

	public void setTingchan(int tingchan) {
		this.tingchan = tingchan;
	}

	public int getIsass() {
		return isass;
	}

	public void setIsass(int isass) {
		this.isass = isass;
	}
     
}