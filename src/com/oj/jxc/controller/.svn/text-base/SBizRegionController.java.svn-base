/**
 * 
 */
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
import com.oj.jxc.entity.SBizRegionDO;
import com.oj.jxc.service.SBizRegionService;

/**
 * @author downbiz
 * 
 */
@RequestMapping("/bizregion")
@Controller
public class SBizRegionController extends BaseController {
	//TODO 这个表貌似没有什么用
	@Resource
	private SBizRegionService bizRegionService;

	/**
	 * 获取当前用户对下商务
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findByUserId(HttpSession session) {
		LoginedUser user = SystemContextUtils.getCurrentUser(session);
		return this.bizRegionService.findByUser(user.getLoginUser().getUname());
	}

	/**
	 * 获取所有用户对下商务
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<SBizRegionDO> list() {
		return this.bizRegionService.getList();
	}

	/**
	 * 修改用户对下商务
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, String> save(SBizRegionDO entity) {
		this.bizRegionService.saveOrUpdate(entity);
		return returnSuccess();
	}
}
