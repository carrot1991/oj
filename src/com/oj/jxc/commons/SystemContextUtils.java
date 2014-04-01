package com.oj.jxc.commons;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;

public class SystemContextUtils {
	
	/**
	 * 小时
	 */
	public final static int HOVER=1;
	
	/**
	 * 日
	 */
	public final static int DAY=2;
	
	/**
	 * 获取用户信息
	 * 
	 * @param session
	 * @return
	 */
	public static LoginedUser getCurrentUser(HttpSession session) {
		return (LoginedUser) session
				.getAttribute(Constants.LOGIN_SESSION_NAME);
	}
	 
   
	public static boolean hasOrg(String orgId,List<String> orgList){

		if (CollectionUtils.isNotEmpty(orgList) && orgList.contains(orgId)) {
			return true;
		}
		return false;
	}

}
