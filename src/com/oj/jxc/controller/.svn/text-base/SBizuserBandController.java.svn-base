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
import com.oj.jxc.entity.BandDictForCheckBox;
import com.oj.jxc.entity.BizContact;
import com.oj.jxc.entity.SBizuserBandDO;
import com.oj.jxc.entity.SBizuserRegionDO;
import com.oj.jxc.entity.SUserDO;
import com.oj.jxc.entity.UserBand;
import com.oj.jxc.service.SBizuserBandService;

/**商务部对上商务
 * @author upbiz
 * 
 */
@RequestMapping("/userprod")
@Controller
public class SBizuserBandController extends BaseController {

	@Resource
	private SBizuserBandService userProdService;

	/**
	 * 获取用户列表
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> findByUserId(HttpSession session) {
		LoginedUser user = SystemContextUtils.getCurrentUser(session);
		return this.userProdService.findByUserId(user.getLoginUser().getUname());
	}
	
	@RequestMapping(value = "/findContactUp", method = RequestMethod.GET)
	@ResponseBody
	public List<BizContact> findContactUp( ) {
		return this.userProdService.findContactUp();
	}

	
	@RequestMapping(value = "/findByUname", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> findByUname(String uname,HttpSession session) {
		return this.userProdService.findByUname(uname);
	}
	@RequestMapping(value = "/findByUnameForCheckBox", method = RequestMethod.GET)
	@ResponseBody
	public List<BandDictForCheckBox> findByUnameForCheckBox(String uname,HttpSession session) {
		return this.userProdService.findByUnameForCheckBox(uname);
	}
	/**
	 * 获取所有用户对上商务
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<SBizuserBandDO> list() {
		return this.userProdService.getList();
	}

	/**
	 * 修改用户对上商务
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, String> save(SBizuserBandDO entity) {
		this.userProdService.saveOrUpdate(entity);
		return returnSuccess();
	}
//	@RequestMapping("/saveByComma")
//	@ResponseBody
//	public Map<String, String> saveByComma(String uname,String pids) {
//			return this.returnSuccess(this.userProdService.saveOrUpdateByComma(uname, null,pids));
//	}
	
	@RequestMapping(value = "/saveByComma", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveByComma(UserBand ub) {
			return this.returnSuccess(this.userProdService.saveOrUpdateByComma(ub));
	}		
}
