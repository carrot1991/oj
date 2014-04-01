package com.oj.jxc.entity;

import java.io.Serializable;
import java.util.Date;

import net.keepsoft.commons.annotation.Temporary;

/**
 * 数据对象
 * @since 2014-03-01
 */
public class SGoodsDO implements Serializable {

	@Temporary
	private static final long serialVersionUID = 139364899212814963L;

	/**
	 * column s_goods.goodsid  本次商品编号
	 */
	private int goodsid;

	/**
	 * column s_goods.uname  上架人,用户
	 */
	private String uname;

	/**
	 * column s_goods.prodid  产品编号:产品是固定的
	 */
	private int prodid;

	/**
	 * column s_goods.amount  报价
	 */
	private int amount;

	/**
	 * column s_goods.price  数量
	 */
	private int price;

	/**
	 * column s_goods.createtime  上架时间
	 */
	private Date createtime;

	/**
	 * column s_goods.firstprice  最初的价格
	 */
	private int firstprice;

	/**
	 * column s_goods.ispendingchange  是否修改价格与数量待审核
	 */
	private int ispendingchange;

	/**
	 * column s_goods.updatetime  更改时间
	 */
	private Date updatetime;

	/**
	 * column s_goods.pendingprice  待修改的价格
	 */
	private int pendingprice;

	/**
	 * column s_goods.pendingamount  待修改的数量
	 */
	private int pendingamount;

	/**
	 * column s_goods.ispendingup  是否待商务审核(初上架)
	 */
	private int ispendingup = 0;
	
	private int auditupstatus = 0;//1通过2不通过
	public int getAuditupstatus() {
		return auditupstatus;
	}

	public void setAuditupstatus(int auditupstatus) {
		this.auditupstatus = auditupstatus;
	}

	public int getAuditchangestatus() {
		return auditchangestatus;
	}

	public void setAuditchangestatus(int auditchangestatus) {
		this.auditchangestatus = auditchangestatus;
	}

	private int auditchangestatus = 0;//1通过2不通过

	public SGoodsDO() {
		super();
	}

	/**
	 * getter for Column s_goods.goodsid
	 */
	public int getGoodsid() {
		return goodsid;
	}

	/**
	 * setter for Column s_goods.goodsid
	 * @param goodsid
	 */
	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	/**
	 * getter for Column s_goods.uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * setter for Column s_goods.uname
	 * @param uname
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * getter for Column s_goods.prodid
	 */
	public int getProdid() {
		return prodid;
	}

	/**
	 * setter for Column s_goods.prodid
	 * @param prodid
	 */
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	/**
	 * getter for Column s_goods.amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * setter for Column s_goods.amount
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * getter for Column s_goods.price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * setter for Column s_goods.price
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * getter for Column s_goods.createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * setter for Column s_goods.createtime
	 * @param createtime
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * getter for Column s_goods.firstprice
	 */
	public int getFirstprice() {
		return firstprice;
	}

	/**
	 * setter for Column s_goods.firstprice
	 * @param firstprice
	 */
	public void setFirstprice(int firstprice) {
		this.firstprice = firstprice;
	}

	/**
	 * getter for Column s_goods.ispendingchange
	 */
	public int getIspendingchange() {
		return ispendingchange;
	}

	/**
	 * setter for Column s_goods.ispendingchange
	 * @param ispendingchange
	 */
	public void setIspendingchange(int ispendingchange) {
		this.ispendingchange = ispendingchange;
	}

	/**
	 * getter for Column s_goods.updatetime
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * setter for Column s_goods.updatetime
	 * @param updatetime
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * getter for Column s_goods.pendingprice
	 */
	public int getPendingprice() {
		return pendingprice;
	}

	/**
	 * setter for Column s_goods.pendingprice
	 * @param pendingprice
	 */
	public void setPendingprice(int pendingprice) {
		this.pendingprice = pendingprice;
	}

	/**
	 * getter for Column s_goods.pendingamount
	 */
	public int getPendingamount() {
		return pendingamount;
	}

	/**
	 * setter for Column s_goods.pendingamount
	 * @param pendingamount
	 */
	public void setPendingamount(int pendingamount) {
		this.pendingamount = pendingamount;
	}

	/**
	 * getter for Column s_goods.ispendingup
	 */
	public int getIspendingup() {
		return ispendingup;
	}

	/**
	 * setter for Column s_goods.ispendingup
	 * @param ispendingup
	 */
	public void setIspendingup(int ispendingup) {
		this.ispendingup = ispendingup;
	}

}