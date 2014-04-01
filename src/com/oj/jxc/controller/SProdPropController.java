/**
 * 
 */
package com.oj.jxc.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SProdPropDO;
import com.oj.jxc.service.SProdPropService;

/**商品属性
 * @author zxd
 * 
 */
@RequestMapping("/prodprop")
@Controller
public class SProdPropController extends BaseController {

	/**
	 * 
	 */
	@Resource
	private SProdPropService prodPropService;

	/**
	 * 
	 * 查询所有
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findPager", method = RequestMethod.GET)
	@ResponseBody
	public Pager findByType(Pager pager) {
		return this.prodPropService.findPager(pager);
	}

	/**
	 * 
	 * 保存或者修改
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> saveOrUpdate(SProdPropDO prodProp) {
		this.prodPropService.saveOrUpdate(prodProp);
		return this.returnSuccess();
	}

}
