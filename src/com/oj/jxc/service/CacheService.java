/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oj.jxc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oj.jxc.entity.SRegionDO;
import com.oj.jxc.entity.STypeDO;

@Component
public class CacheService {
	@Autowired
	STypeService typeService;
	@Autowired
	SRegionService regionService;
//	Map<String, STreeDefDO> globalDefine = new ConcurrentHashMap<String, STreeDefDO>();

	public static Map<Integer, String> typeDefine = new ConcurrentHashMap<Integer, String>();
	//省 -> 市市市
	private Map<String, Set<String>> cities = new ConcurrentHashMap<String, Set<String>>();
	//省-市 -> 县
	private Map<String, Set<String>> areas = new ConcurrentHashMap<String, Set<String>>();

	public void init(HttpSession session) {
		/*List<STreeDefDO> treeDefs = treeDefService.getList();
		if (treeDefs != null && treeDefs.size() > 0) {
			for (STreeDefDO sTreeDefDO : treeDefs) {
				globalDefine.put("", sTreeDefDO);
			}
		}*/
		//分类
		List<STypeDO> types = typeService.getList("level");
		for (STypeDO st : types) {
			typeDefine.put(st.getTypeid(), st.getTitle());
		}
		//区域
		List<SRegionDO> list = regionService.getList();
		for (SRegionDO r : list) {
			String p = r.getProvince();
			String c = r.getCity();
			String a = r.getCounty();
			if (cities.containsKey(p)) {
				cities.get(p).add(c);
			} else {
				Set<String> t = new HashSet<String>();
				t.add(c);
				cities.put(p, t);
			}
			String apc = p + "-" + c;
			if (areas.containsKey(apc)) {
				areas.get(apc).add(a);
			} else {
				Set<String> t = new HashSet<String>();
				t.add(a);
				areas.put(apc, t);
			}
		}

	}
}
