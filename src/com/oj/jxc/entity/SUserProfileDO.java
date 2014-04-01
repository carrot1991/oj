package com.oj.jxc.entity;

import java.io.Serializable;

import net.keepsoft.commons.annotation.IdentityId;
import net.keepsoft.commons.annotation.Table;
import net.keepsoft.commons.annotation.Temporary;

/**
 * 数据对象
 * @since 2014-02-26
 */
@Table("s_user_profile")
public class SUserProfileDO implements Serializable {

	@Temporary
    private static final long serialVersionUID = 139338908518160361L;

    private String uname;
    private String bizrel;//关联的商务，从此商务可以代此人登录、处理事务
    
	public String getBizrel() {
		return bizrel;
	}

	public void setBizrel(String bizrel) {
		this.bizrel = bizrel;
	}

	@Temporary
	private Integer billDelay;
	//限定上架产品数量 //-1 不限制
	private int prodUplimit=-1;

	private int id;
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public int getProdUpLimit() {
		return prodUplimit;
	}

	public void setProdUpLimit(int d) {
		this.prodUplimit = d;
	}

	//此用户报价按含税价
	private int containtax;
	
	public int getContaintax() {
		return containtax;
	}

	public void setContaintax(int containtax) {
		this.containtax = containtax;
	}

	/**
     * column s_user_profile.province
     */
    private String province;

    /**
     * column s_user_profile.city
     */
    private String city;

    /**
     * column s_user_profile.county
     */
    private String county;



    /**
     * column s_user_profile.memname
     */
    private String memname;

    /**
     * column s_user_profile.enname
     */
    private String enname;

    /**
     * column s_user_profile.custname
     */
    private String custname;

    /**
     * column s_user_profile.custaddr
     */
    private String custaddr;

    /**
     * column s_user_profile.custtaxno
     */
    private String custtaxno;

    /**
     * column s_user_profile.custbank
     */
    private String custbank;

    /**
     * column s_user_profile.custbankno
     */
    private String custbankno;

    /**
     * column s_user_profile.custmem
     */
    private String custmem;

    /**
     * column s_user_profile.custqq
     */
    private String custqq;

    /**
     * column s_user_profile.contact
     */
    private String contact;

    /**
     * column s_user_profile.mobile
     */
    private String mobile;

    /**
     * column s_user_profile.mobile2
     */
    private String mobile2;

    /**
     * column s_user_profile.phone
     */
    private String phone;

    /**
     * column s_user_profile.phone2
     */
    private String phone2;

    /**
     * column s_user_profile.regionId
     */
    private Integer regionid;

    public SUserProfileDO() {
        super();
    }

    public SUserProfileDO(Integer billDelay, String province, String city, String county, String uname, String memname, String enname, String custname, String custaddr, String custtaxno, String custbank, String custbankno, String custmem, String custqq, String contact, String mobile, String mobile2, String phone, String phone2, Integer regionid) {
        this.billDelay = billDelay;
        this.province = province;
        this.city = city;
        this.county = county;
        this.uname = uname;
        this.memname = memname;
        this.enname = enname;
        this.custname = custname;
        this.custaddr = custaddr;
        this.custtaxno = custtaxno;
        this.custbank = custbank;
        this.custbankno = custbankno;
        this.custmem = custmem;
        this.custqq = custqq;
        this.contact = contact;
        this.mobile = mobile;
        this.mobile2 = mobile2;
        this.phone = phone;
        this.phone2 = phone2;
        this.regionid = regionid;
    }

    /**
     * getter for Column s_user_profile.bill_delay
     */
    public Integer getBillDelay() {
        return billDelay;
    }

    /**
     * setter for Column s_user_profile.bill_delay
     * @param billDelay
     */
    public void setBillDelay(Integer billDelay) {
        this.billDelay = billDelay;
    }

    /**
     * getter for Column s_user_profile.province
     */
    public String getProvince() {
        return province;
    }

    /**
     * setter for Column s_user_profile.province
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * getter for Column s_user_profile.city
     */
    public String getCity() {
        return city;
    }

    /**
     * setter for Column s_user_profile.city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * getter for Column s_user_profile.county
     */
    public String getCounty() {
        return county;
    }

    /**
     * setter for Column s_user_profile.county
     * @param county
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * getter for Column s_user_profile.uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * setter for Column s_user_profile.uname
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * getter for Column s_user_profile.memname
     */
    public String getMemname() {
        return memname;
    }

    /**
     * setter for Column s_user_profile.memname
     * @param memname
     */
    public void setMemname(String memname) {
        this.memname = memname;
    }

    /**
     * getter for Column s_user_profile.enname
     */
    public String getEnname() {
        return enname;
    }

    /**
     * setter for Column s_user_profile.enname
     * @param enname
     */
    public void setEnname(String enname) {
        this.enname = enname;
    }

    /**
     * getter for Column s_user_profile.custname
     */
    public String getCustname() {
        return custname;
    }

    /**
     * setter for Column s_user_profile.custname
     * @param custname
     */
    public void setCustname(String custname) {
        this.custname = custname;
    }

    /**
     * getter for Column s_user_profile.custaddr
     */
    public String getCustaddr() {
        return custaddr;
    }

    /**
     * setter for Column s_user_profile.custaddr
     * @param custaddr
     */
    public void setCustaddr(String custaddr) {
        this.custaddr = custaddr;
    }

    /**
     * getter for Column s_user_profile.custtaxno
     */
    public String getCusttaxno() {
        return custtaxno;
    }

    /**
     * setter for Column s_user_profile.custtaxno
     * @param custtaxno
     */
    public void setCusttaxno(String custtaxno) {
        this.custtaxno = custtaxno;
    }

    /**
     * getter for Column s_user_profile.custbank
     */
    public String getCustbank() {
        return custbank;
    }

    /**
     * setter for Column s_user_profile.custbank
     * @param custbank
     */
    public void setCustbank(String custbank) {
        this.custbank = custbank;
    }

    /**
     * getter for Column s_user_profile.custbankno
     */
    public String getCustbankno() {
        return custbankno;
    }

    /**
     * setter for Column s_user_profile.custbankno
     * @param custbankno
     */
    public void setCustbankno(String custbankno) {
        this.custbankno = custbankno;
    }

    /**
     * getter for Column s_user_profile.custmem
     */
    public String getCustmem() {
        return custmem;
    }

    /**
     * setter for Column s_user_profile.custmem
     * @param custmem
     */
    public void setCustmem(String custmem) {
        this.custmem = custmem;
    }

    /**
     * getter for Column s_user_profile.custqq
     */
    public String getCustqq() {
        return custqq;
    }

    /**
     * setter for Column s_user_profile.custqq
     * @param custqq
     */
    public void setCustqq(String custqq) {
        this.custqq = custqq;
    }

    /**
     * getter for Column s_user_profile.contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * setter for Column s_user_profile.contact
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * getter for Column s_user_profile.mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * setter for Column s_user_profile.mobile
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * getter for Column s_user_profile.mobile2
     */
    public String getMobile2() {
        return mobile2;
    }

    /**
     * setter for Column s_user_profile.mobile2
     * @param mobile2
     */
    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    /**
     * getter for Column s_user_profile.phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setter for Column s_user_profile.phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getter for Column s_user_profile.phone2
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * setter for Column s_user_profile.phone2
     * @param phone2
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     * getter for Column s_user_profile.regionId
     */
    public Integer getRegionid() {
        return regionid;
    }

    /**
     * setter for Column s_user_profile.regionId
     * @param regionid
     */
    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

}