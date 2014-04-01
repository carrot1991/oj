package com.oj.jxc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.SystemContextUtils;
import com.oj.jxc.entity.BizContact;
import com.oj.jxc.entity.SBizuserRegionDO;
import com.oj.jxc.entity.UserBand;
import com.oj.jxc.service.SBizuserRegionService;

/**
 * @author downbiz
 * 
 */
@RequestMapping("/userRegion")
@Controller
public class SBizuserRegionController extends BaseController {

	@Resource
	private SBizuserRegionService userRegionService;

	/**
	 * 获取用户列表
	 * 
	 * @param pager useless!
	 * @return
	 */
	@RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
	@ResponseBody
	public List<SBizuserRegionDO> findByUserId(HttpSession session) {
		LoginedUser user = SystemContextUtils.getCurrentUser(session);
		return this.userRegionService.findByUserId(user.getLoginUser().getUname());
	}
	@RequestMapping(value = "/findContactDown", method = RequestMethod.GET)
	@ResponseBody
	public List<BizContact> findContactDown(HttpSession session) {
		LoginedUser user = SystemContextUtils.getCurrentUser(session);
		return this.userRegionService.findContactDown(user.getProfile().getRegionid());
	}
	@RequestMapping(value = "/findByUname", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> findByUname(String uname,HttpSession session) {
		return this.userRegionService.findIdsByUserId(uname);
	}
	/**
	 * 获取所有用户对上商务
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<SBizuserRegionDO> list() {
		return this.userRegionService.getList();
	}

	/**
	 * 修改用户对上商务
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, String> save(SBizuserRegionDO[] entity) {
		if (entity.length > 0)
			return this.returnSuccess(this.userRegionService.saveOrUpdate(entity));
		else
			return this.returnSuccess("error");
	}
	
	@RequestMapping("/saveByComma")
	@ResponseBody
	public Map<String, String> saveByComma(UserBand ub) {
			return this.returnSuccess(this.userRegionService.saveOrUpdateByComma(ub));
	}
}
