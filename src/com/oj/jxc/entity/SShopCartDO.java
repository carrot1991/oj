package com.oj.jxc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2014-02-26
 */
public class SShopCartDO implements Serializable {

	private static final long serialVersionUID = 139338905583749142L;

	/**
	 * column s_shop_cart.id  自增序列号
	 */
	private Integer id;

	/**
	 * column s_shop_cart.uname  用户
	 */
	private String uname;

	/**
	 * column s_shop_cart.prodid  产品编号
	 */
	private Integer prodid;

	/**
	 * column s_shop_cart.amount  数量
	 */
	private Integer amount;

	/**
	 * column s_shop_cart.price  价格 最低价格
	 */
	private Integer price;

	/**
	 * column s_shop_cart.createTime  放入时间
	 */
	private Date createtime;

	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public SShopCartDO() {
		super();
	}

	public SShopCartDO(Integer id, String uname, Integer prodid, Integer amount, Integer price, Date createtime) {
		this.id = id;
		this.uname = uname;
		this.prodid = prodid;
		this.amount = amount;
		this.price = price;
		this.createtime = createtime;
	}

	/**
	 * getter for Column s_shop_cart.id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * setter for Column s_shop_cart.id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * getter for Column s_shop_cart.uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * setter for Column s_shop_cart.uname
	 * @param uname
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * getter for Column s_shop_cart.prodid
	 */
	public Integer getProdid() {
		return prodid;
	}

	/**
	 * setter for Column s_shop_cart.prodid
	 * @param prodid
	 */
	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}

	/**
	 * getter for Column s_shop_cart.amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * setter for Column s_shop_cart.amount
	 * @param amount
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * getter for Column s_shop_cart.price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * setter for Column s_shop_cart.price
	 * @param price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * getter for Column s_shop_cart.createTime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * setter for Column s_shop_cart.createTime
	 * @param createtime
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}