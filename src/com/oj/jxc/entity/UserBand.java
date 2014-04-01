package com.oj.jxc.entity;

import java.io.Serializable;

public class UserBand implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6328445521106329389L;
	private String uname;
	private String role;
    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	private String pwd;
    private String qq;
    private String email;
    private String tel;    
    private String pids;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPids() {
		return pids;
	}
	public void setPids(String pids) {
		this.pids = pids;
	}
	
}
