/**
 * 
 */
package com.oj.jxc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SDictDO;
import com.oj.jxc.service.SDictService;

/**
 * @author zxd
 * 
 */
@RequestMapping("/dict")
@Controller
public class SDictController extends BaseController {

	@Resource
	private SDictService sdictService;

	/**
	 * 获取字典列表
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Pager findList(Pager pager) {
		return this.sdictService.getList(pager);
	}

	/**
	 * 根据ID获取字典
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findByType", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> findByType(String type) {
		List<SDictDO> list = this.sdictService.findByType(type);
		return this.returnSuccess(JSONArray.fromObject(list).toString());
	}

	/**
	 * 根据ID获取字典
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	@ResponseBody
	public SDictDO findById(String id) {
		return this.sdictService.findById(id);
	}

	/**
	 * 添加或者修改
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> update(SDictDO dictDO) {
		try {
			this.sdictService.update(dictDO);
			return this.returnSuccess();
		} catch (Exception e) {
			return this.returnSuccess("error");
		}
	}
}
