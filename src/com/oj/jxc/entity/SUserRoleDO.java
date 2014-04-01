package com.oj.jxc.entity;

import java.io.Serializable;

/**
 * 数据对象
 * @since 2014-02-26
 */
public class SUserRoleDO implements Serializable {

    private static final long serialVersionUID = 139338908990203563L;

    /**
     * column s_user_role.id  自增关联编号
     */
    private Integer id;

    /**
     * column s_user_role.uname  用户名
     */
    private String uname;

    /**
     * column s_user_role.rname  角色名
     */
    private String rname;

    public SUserRoleDO() {
        super();
    }

    public SUserRoleDO(Integer id, String uname, String rname) {
        this.id = id;
        this.uname = uname;
        this.rname = rname;
    }

    /**
     * getter for Column s_user_role.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for Column s_user_role.id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getter for Column s_user_role.uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * setter for Column s_user_role.uname
     * @param uname
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * getter for Column s_user_role.rname
     */
    public String getRname() {
        return rname;
    }

    /**
     * setter for Column s_user_role.rname
     * @param rname
     */
    public void setRname(String rname) {
        this.rname = rname;
    }

}