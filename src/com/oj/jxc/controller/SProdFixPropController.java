/**
 * 
 */
package com.oj.jxc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SProdFixPropDO;
import com.oj.jxc.service.SProdFixPropService;

/**商品属性值
 * @author zxd
 * 
 */
@RequestMapping("/prodfixprop")
@Controller
public class SProdFixPropController extends BaseController {

	@Resource
	private SProdFixPropService prodFixProp;

	@RequestMapping(value = "/getDict", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> getDict(int typeid){
		return prodFixProp.getDict(typeid);
	}	
	/**
	 * 
	 * 查询所有
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findPager", method = RequestMethod.GET)
	@ResponseBody
	public Pager findByType(Pager pager) {
		return this.prodFixProp.findPager(pager);
	}

	/**
	 * 
	 * 根据商品大类查询
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/listByType", method = RequestMethod.GET)
	@ResponseBody
	public List<SProdFixPropDO> listByType(int typeid) {
		return prodFixProp.findByType(typeid);
	}

	/**
	 * 
	 * 根据商品大类查询
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(int id) {
		this.prodFixProp.remove(id);
		return this.returnSuccess();
	}

	/**
	 * 
	 * 保存或者修改
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveOrUpdate(SProdFixPropDO prodFixProp) {
		this.prodFixProp.saveOrUpdate(prodFixProp);
		return this.returnSuccess();
	}

}
