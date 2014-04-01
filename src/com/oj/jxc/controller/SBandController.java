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

import com.oj.jxc.entity.BandDict;
import com.oj.jxc.entity.SBandDO;
import com.oj.jxc.service.SBandService;

/**
 * @author zxd
 * 
 */
@RequestMapping("/band")
@Controller
public class SBandController extends BaseController {

	@Resource
	private SBandService bandService;

	@RequestMapping(value = "/getDict", method = RequestMethod.GET)
	@ResponseBody
	public List<BandDict> getDict(){
		return bandService.getDict();
	}
	/**
	 * 根据Id查找品牌
	 * 
	 * @return
	 */
	@RequestMapping("/findById")
	@ResponseBody
	public Map<String, String> findById(String id) {
		this.bandService.findById(id);
		return returnSuccess();
	}
	/**
	 * 查找所有品牌
	 * 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<SBandDO> findBand() {
		return this.bandService.getList();
	}
	@RequestMapping(value = "/findBandByType", method = RequestMethod.GET)
	@ResponseBody
	public List<SBandDO> findBandByType(int typeid) {
		return this.bandService.findByType(typeid);
	}
	/**
	 * 更新品牌
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(SBandDO entity) {
		try {
			this.bandService.saveOrUpdate(entity);
			return returnSuccess();
		} catch (Exception e) {
			return returnSuccess(e.getMessage());
		}
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> save(int bandid) {
		try {
			this.bandService.remove(bandid);
			return returnSuccess();
		} catch (Exception e) {
			return returnSuccess(e.getMessage());
		}
	}
}
