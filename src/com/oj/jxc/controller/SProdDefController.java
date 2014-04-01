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

import com.oj.jxc.commons.Constants;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.entity.ProdDefWithProp;
import com.oj.jxc.entity.SProdDefDO;
import com.oj.jxc.service.SProdDefService;

/**商品定义
 * @author zxd
 * 
 */
@RequestMapping("/proddef")
@Controller
public class SProdDefController extends BaseController {

	@Resource
	private SProdDefService sprodDefService;

	@RequestMapping(value = "/getDict", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> getDict(){
		return sprodDefService.getDict();
	}
	/**
	 * 
	 * 获取某个品牌的商品定义
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findDefByBand", method = RequestMethod.GET)
	@ResponseBody
	public List<SProdDefDO> findDefByBand(int bandid) {
		return this.sprodDefService.findDefByBandId(bandid);
	}
	
	@RequestMapping(value = "/findDefByBandForSale", method = RequestMethod.GET)
	@ResponseBody
	public List<SProdDefDO> findDefByBandForSale(int bandid,  HttpSession session) {
		return this.sprodDefService.findDefByBandIdForSale(bandid,
				((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname()
				);
	}
	/**
	 * 
	 * 保存或者修改
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveOrUpdate(SProdDefDO prodef ) {
		this.sprodDefService.saveOrUpdate(prodef);
		return this.returnSuccess();
	}
	@RequestMapping(value = "/saveOrUpdateWithProp", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveOrUpdateWithProp(ProdDefWithProp prodef ) {
		this.sprodDefService.saveOrUpdateWithProp(prodef );
		return this.returnSuccess();
	}
	@RequestMapping(value = "/saveOrUpdateSimple", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveOrUpdateSimple(SProdDefDO prodef ) {
		this.sprodDefService.saveOrUpdateSimple(prodef );
		return this.returnSuccess();
	}
	
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> remove(int id) {
		this.sprodDefService.remove(id);
		return this.returnSuccess();
	}
}
