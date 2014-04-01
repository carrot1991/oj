/**
 * 
 */
package com.oj.jxc.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SGoodsParm;
import com.oj.jxc.entity.SProdLimitDO;
import com.oj.jxc.service.SProdDefService;
import com.oj.jxc.service.SProdLimitService;

/**
 * @author zxd
 * 
 */
@RequestMapping("/prodLimit")
@Controller
public class SProdLimitController extends BaseController {

	@Resource
	private SProdLimitService prodLimitService;
	
	@Resource SProdDefService prodDefService;

	/**
	 * 查找所有
	 * 
	 */
	@RequestMapping(value = "/listPager", method = RequestMethod.GET)
	@ResponseBody
	public Pager listPager(Pager pager, SGoodsParm goodsParm, HttpSession session) {
		pager = pager == null ? new Pager() : pager;
		return prodLimitService.listPager( pager);
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(SProdLimitDO entity) {
		try {
			this.prodLimitService.saveOrUpdate(entity);
			return returnSuccess();
		} catch (Exception e) {
			return returnSuccess(e.getMessage());
		}
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(int prodid) {
		try {
			this.prodLimitService.remove(prodid);
			return returnSuccess();
		} catch (Exception e) {
			return returnSuccess(e.getMessage());
		}
	}
	/**
	 * 根据prodid查找
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	@ResponseBody
	public SProdLimitDO findById(int id) {
		
		 //String title = this.prodDefService.getDict(id);
		
		 SProdLimitDO ret = this.prodLimitService.findById(id);
		 //ret.setProdStr( title );
		 
		 return ret;
		 
	}
}
