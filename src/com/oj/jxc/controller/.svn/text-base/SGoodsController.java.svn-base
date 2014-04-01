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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.Constants;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SGoodsDO;
import com.oj.jxc.entity.SGoodsParm;
import com.oj.jxc.entity.SmsOrder;
import com.oj.jxc.service.SGoodsService;
import com.oj.jxc.service.SShopCartService;
import com.oj.jxc.service.SmsService;

/**用户商品
 * @author zxd
 * 
 */
@RequestMapping("/goods")
@Controller
public class SGoodsController extends BaseController {

	@Resource
	private SGoodsService goodsService;

	@Resource
	private SmsService smsService;
	
	@Resource
	SShopCartService shopCartService;
	/**
	 * 获取所有用户的商品
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/listPager", method = RequestMethod.GET)
	@ResponseBody
	public Pager listPager(Pager pager, SGoodsParm goodsParm, HttpSession session) {
		pager = pager == null ? new Pager() : pager;
		return this.goodsService.findGoods(pager, goodsParm,
				((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname());
	}
	
	@RequestMapping(value = "/getPagerCount", method = RequestMethod.POST)
	@ResponseBody
	public int getPagerCount( SGoodsParm goodsParm, HttpSession session) {
		return this.goodsService.findGoodsCount( goodsParm,
				((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname());
	}
	/**
	 * 查找相关门类下的配件...
	 * @param pager
	 * @param goodsParm
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/listAsso", method = RequestMethod.GET)
	@ResponseBody
	public Pager listAsso(Pager pager, SGoodsParm goodsParm ) {
		pager = pager == null ? new Pager() : pager;
		return this.goodsService.findAsso(pager, goodsParm  );
	}
	
	/*
	@RequestMapping(value = "/canAddNewProd", method = RequestMethod.GET)
	@ResponseBody
	public int canAddNewProd(HttpSession session) {
		return this.goodsService.canAddNewProd( 
				((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname());
	}*/
	
	@RequestMapping(value = "/listPagerForBiz", method = RequestMethod.GET)
	@ResponseBody
	public Pager findGoodsByProdForBiz(Pager pager, HttpSession session) {
			pager = pager == null ? new Pager() : pager;
			LoginedUser u = (LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME);
			//List<Integer> prodids = u.getProdIds();
			return this.goodsService.findGoodsByProdForBiz(pager,u.getLoginUser().getUname());
	}
	
	@RequestMapping(value = "/examine", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> examine(String status, String id) {
		try {
			return this.returnSuccess(goodsService.update(status,id));
		} catch (Exception e) {
			return this.returnSuccess("error");
		}
	}
	/**
	 * 添加商品
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> add(SGoodsDO goodsDo, HttpSession session) {
		try {
			goodsDo.setUname(((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser()
					.getUname());
			return this.returnSuccess(this.goodsService.add(goodsDo));
		} catch (Exception e) {
			e.printStackTrace();
			return this.returnSuccess("error"+e.getMessage());
		}
	}

	/**
	 * 修改商品
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> update(SGoodsDO goodsDo, HttpSession session) {
		try {
			goodsDo.setUname(((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser()
					.getUname());
			return this.returnSuccess(this.goodsService.update(goodsDo));
		} catch (Exception e) {
			e.printStackTrace();
			return this.returnSuccess("error");
		}
	}

	/**
	 * 购物车 加入成功后要发送短信
	 * @return
	 */
	@RequestMapping(value = "/addCart")
	@ResponseBody
	public Map<String, String> addCart(SGoodsDO goodsDo, HttpSession session) {
		try {
			goodsDo.setUname(((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser()
					.getUname());
			Map<String,Object> map = goodsService.addCart(goodsDo);
			 
			if((Integer)(map.get("retcode"))==0){
				//////////发送短信，取mobile列做为手机号
				String cartid = (String)map.get("cartid");
				
				List<SmsOrder> list = shopCartService.findMobileNoByCartID(cartid);
				for(SmsOrder so : list){
					if(StringUtils.isNotBlank( so.getMobile() )){
						smsService.sendSmsAfterCart(so.getMobile(),so.getNickname()+"-"+so.getXinghao()+" 数量:"+ so.getAmount());
					}
				}
				return returnSuccess();
			} else{
				return returnSuccess("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.returnSuccess("error");
		}
	}

}
