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

import com.oj.jxc.entity.SRegionDO;
import com.oj.jxc.service.SRegionService;

/**区域
 * @author zxd
 * 
 */
@RequestMapping("/region")
@Controller
public class SRegionContoller extends BaseController {

	@Resource
	private SRegionService regionService;
	
	@RequestMapping(value = "/getDict", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> getDict(){
		return regionService.findTotalTitleByRegionId();
	}
	/**
	 * 获取某个市的区域
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findByCity", method = RequestMethod.GET)
	@ResponseBody
	public List<SRegionDO> findByCity(String city) {
		return this.regionService.findByCity(city);
	}

}
