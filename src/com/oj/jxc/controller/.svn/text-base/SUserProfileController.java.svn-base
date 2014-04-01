package com.oj.jxc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.Constants;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SGoodsParm;
import com.oj.jxc.entity.SUserProfileDO;
import com.oj.jxc.service.SUserProfileService;

@RequestMapping("/userprofile")
@Controller
public class SUserProfileController extends BaseController {
	
	@Resource
	SUserProfileService profileService;
	
 
	@RequestMapping(value = "/listPager", method = RequestMethod.GET)
	@ResponseBody
	public Pager listPager(Pager pager, String key,HttpSession session) {
		pager = pager == null ? new Pager() : pager;
		return this.profileService.getList(pager,key);
	}
	@RequestMapping(value = "/updateRel", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateRel(String uname,String bizname) {
		profileService.updateRel(uname,bizname);
		return this.returnSuccess();
	}
	@RequestMapping(value = "/getCustomerForBiz", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getCustomerForBiz(HttpSession session) {
		try {
			String me = ((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname();
			return profileService.getCustomerForBiz(me);
		} catch (Exception e) {
			return null;
		}
	}
	@RequestMapping(value = "/getBizForCustomer", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getBizForCustomer() {
		try {
			return profileService.getBizForCustomer();
		} catch (Exception e) {
			return null;
		}
	}
	@RequestMapping(value = "/findByName", method = RequestMethod.GET)
	@ResponseBody	
	public SUserProfileDO findByName(String uname) {
		return profileService.findByName(  uname);
	}
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	@ResponseBody	
	public SUserProfileDO findById(int id) {
		return profileService.findById(  id );
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> update(SUserProfileDO profile) {
		try {
			profileService.saveOrUpdate(profile);
			return this.returnSuccess();
		} catch (Exception e) {
			return this.returnSuccess("error");
		}		
	}	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	@ResponseBody	
	public Map<String, String> remove(String uname) {
		  profileService.remove(  uname );
		  return this.returnSuccess();
	}
}