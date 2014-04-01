package com.oj.jxc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.Constants;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.service.SShopOrdItemService;

@RequestMapping("/shoporditem")
@Controller
public class SShopOrdItemController extends BaseController {
	@Autowired
	SShopOrdItemService ord;

	/**
	 * 获取所有用户的商品
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findOrdItem")
	@ResponseBody
	public Pager listPager(Pager pager, String cartid) {
		return this.ord.findOrd(pager, cartid);
	}
	
	/**
	 * 为对上商务服务：列出详细分解项
	 * @param pager
	 * @param cartid
	 * @return
	 */
	@RequestMapping(value = "/findOrdItemPart")
	@ResponseBody
	public Pager listPagerPart(Pager pager, String ordid) {
		return this.ord.findOrdItemPart(pager, ordid);
	}
	/**
	 * 审核订单列表(对下商务)
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/exList")
	@ResponseBody
	public Pager exList(Pager pager, HttpSession session) {

		return this.ord.findOrd(pager, ((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getRegions());
	}

	/**
	 * 审核订单列表(对上商务)
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/exUpList")
	@ResponseBody
	public Pager exUpList(Pager pager, HttpSession session) {
		return this.ord.findUpOrd(pager,
				((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getProdIds());
	}
}