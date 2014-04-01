package com.oj.jxc.commons;

import java.io.Serializable;
import java.util.List;

import com.oj.jxc.entity.SUserDO;
import com.oj.jxc.entity.SUserProfileDO;

/**
 * 会话中保存的实体：包括用户本身
 * 对于商务：包括了负责区域、负责产品
 * 对于buy： 包括了其适用账期
 * @author xingzengwu
 */
public class LoginedUser implements Serializable {

	boolean profileCompleted = false;
	int profileNo;
	
	public int getProfileNo() {
		return profileNo;
	}

	public void setProfileNo(int profileNo) {
		this.profileNo = profileNo;
	}

	public boolean isProfileCompleted() {
		return profileCompleted;
	}

	public void setProfileCompleted(boolean profileCompleted) {
		this.profileCompleted = profileCompleted;
	}

	private SUserDO loginUser;
	//角色
	private List<String> roles;

	private SUserProfileDO profile;

	//商务角色
	private List<Integer> regions;
	private List<Integer> prodIds;
	//1销售商 2管理员 3对下商务 4 对上商务 5供貨商6遊客
	private String roleInfo;

	public String getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(String roleInfo) {
		this.roleInfo = roleInfo;
	}

	public boolean isBuyer() {
		return roles.contains("buy");
	}

	public boolean isSale() {
		return roles.contains("sale");
	}

	public boolean isSuperUser() {
		return roles.contains("super");
	}

	public boolean isUpbiz() {
		return roles.contains("upbiz");
	}

	public boolean isDownbiz() {
		return roles.contains("downbiz");
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
	 * @return the regions
	 */
	public List<Integer> getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(List<Integer> regions) {
		this.regions = regions;
	}

	/**
	 * @return the prodIds
	 */
	public List<Integer> getProdIds() {
		return prodIds;
	}

	/**
	 * @param prodIds the prodIds to set
	 */
	public void setProdIds(List<Integer> prodIds) {
		this.prodIds = prodIds;
	}

	public SUserDO getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(SUserDO loginUser) {
		this.loginUser = loginUser;
	}

	public SUserProfileDO getProfile() {
		return profile;
	}

	public void setProfile(SUserProfileDO profile) {
		this.profile = profile;
	}

}
