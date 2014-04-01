package com.oj.jxc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据对象
 * @since 2014-02-26
 */
public class SShopOrdDO implements Serializable {

    private static final long serialVersionUID = 139338905947800987L;

    /**
     * column s_shop_ord.id  根据时间生成的惟一串
     */
    private String id;

    /**
     * column s_shop_ord.uname  用户
     */
    private String uname;

    /**
     * column s_shop_ord.status  状态(0未审核、1已审核、2已完成、3已删除)
     */
    private Integer status;

    /**
     * column s_shop_ord.createTime  时间
     */
    private Date createtime;
    
    private String ddh;//订单虚

    public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}

	public SShopOrdDO() {
        super();
    }

    public SShopOrdDO(String id, String uname, Integer status, Date createtime) {
        this.id = id;
        this.uname = uname;
        this.status = status;
        this.createtime = createtime;
    }

    /**
     * getter for Column s_shop_ord.id
     */
    public String getId() {
        return id;
    }

    /**
     * setter for Column s_shop_ord.id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getter for Column s_shop_ord.uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * setter for Column s_shop_ord.uname
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * getter for Column s_shop_ord.status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * setter for Column s_shop_ord.status
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * getter for Column s_shop_ord.createTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * setter for Column s_shop_ord.createTime
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

}