/**
 * 
 */
package com.oj.jxc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.Constants;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.SystemContextUtils;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SmsOrder;
import com.oj.jxc.service.SGoodsService;
import com.oj.jxc.service.SShopCartService;
import com.oj.jxc.service.SmsService;

/**购物车
 * @author zxd
 * 
 */
@RequestMapping("/shopcart")
@Controller
public class SShopCartController extends BaseController {

	@Resource
	private SShopCartService shopCartService;
	@Resource
	private SGoodsService goodsService;
	
	/**
	 * 获取用户所有购物车
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/currentUserCartPage")
	@ResponseBody
	public Pager findPager(Pager pager, HttpSession session) {
		LoginedUser user = SystemContextUtils.getCurrentUser(session);
		return this.shopCartService.findPager(pager, user);
	}

	/**
	 * 获取用户所有购物车
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/removecart")
	@ResponseBody
	public Map<String, String> removecart(String cartid) {

		return this.returnSuccess(this.shopCartService.update(cartid));
	}

	/**
	 * 提交订单
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/createOrder")
	@ResponseBody
	public Map<String, String> createOrder(String cartid, String beizhu,HttpSession session) {
		String q = null;
		if (cartid.contains(",")) {
			q = this.shopCartService.addOrder(cartid,
					((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname(),
					beizhu);
		} else
			q = "error";
		return this.returnSuccess(q);
	}
}
