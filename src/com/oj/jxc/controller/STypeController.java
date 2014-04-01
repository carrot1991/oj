package com.oj.jxc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.Constants;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SBandDO;
import com.oj.jxc.entity.SCategoryDO;
import com.oj.jxc.entity.SProdDefDO;
import com.oj.jxc.entity.STypeDO;
import com.oj.jxc.service.SBandService;
import com.oj.jxc.service.SProdDefService;
import com.oj.jxc.service.STypeService;

@RequestMapping("/type")
@Controller
public class STypeController extends BaseController {
	@Autowired
	STypeService typeService;
	@Autowired
	SBandService bandService;
	@Autowired
	SProdDefService prodDefService;

	@RequestMapping(value = "/getDict", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer,String> getDict(){
		return typeService.getDict();
	}
	@RequestMapping(value = "/findType", method = RequestMethod.GET)
	@ResponseBody
	public List<STypeDO> findType(int cateid){
		return typeService.findType(cateid);
	}
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(int typeid) {
		int ret = typeService.remove(typeid);
		if(ret>0){
			return this.returnSuccess();
		}else{
			return this.returnSuccess("删除失败");
		}
	}
	
	@RequestMapping(value = "/removeCate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteCate(int cateid) {
		int ret = typeService.removeCate(cateid);
		if(ret>0){
			return this.returnSuccess();
		}else{
			return this.returnSuccess("删除失败");
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> list(String by,HttpSession session) {
		/*List<STypeDO> list = typeService.getList();
		List<STreeDefDO> trees = treeDefService.getList();
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.setExcludes(new String[] {//只要设置这个数组，指定过滤哪些字段。    
		"prop" });

		StringBuffer str = new StringBuffer("[");
		if (trees != null && trees.size() > 0) {
			for (STreeDefDO td : trees) {
				str.append("{id:").append(td.getId()).append(",");
				str.append("name:\"").append(td.getName()).append("\",");
				str.append("pId:").append(td.getProp()).append(",");
				str.append("open:true},");
			}
			str.deleteCharAt(str.length() - 1);
			str.append("]");
		}*/

//		Map<Integer,List<SProdDefDO>> prodMap = new HashMap<Integer,List<SProdDefDO>>();
//		List<SProdDefDO> prods = prodDefService.getList();
//		for(SProdDefDO sd : prods){
//			if(prodMap.containsKey(sd.getBandid())){
//				prodMap.get(sd.getBandid()).add(sd);
//			}else{
//				List<SProdDefDO> values = new ArrayList<SProdDefDO>();
//				values.add(sd);
//				prodMap.put(sd.getBandid(),values);
//			}
//		}
		
		List<SCategoryDO> catlist = typeService.getCategoryList();
		
		List<STypeDO> list = typeService.getList(by);
		
		
		
		List<SBandDO> trees = bandService.getList();
		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.setExcludes(new String[] {//只要设置这个数组，指定过滤哪些字段。    
		"prop" });
		StringBuffer str = new StringBuffer("[");
		LoginedUser user = (LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME);
		String url = null;
	//	System.out.println(user.getRoleInfo().toString()+"---------111-----");
		if (user.getRoleInfo().equals("1") || user.getRoleInfo().equals("6")) {
			url = "pages/buy/listGoods.jsp";
		} else if (user.getRoleInfo().equals("2")) {
			url = "pages/super/goods/listGoods.jsp";
		} else if (user.getRoleInfo().contains("4")) {
			url = "pages/upbiz/listGoods.jsp";
		} else if (user.getRoleInfo().contains("5")) {
			url = "pages/sale/listMyGoods.jsp";
		}else if (user.getRoleInfo().contains("3")) {
			url = "pages/downbiz/listShipOrders.jsp";
		}else {
			return null;
		}
		
		//=========================
		for (int i = 0; i < catlist.size(); i++) {
			SCategoryDO td = catlist.get(i);
			str.append("{id:'cate_").append(td.getId()).append("',");
			str.append("open:false").append(",");
			str.append("name:\"").append(td.getTitle()).append("\",");
			//str.append("url:\"").append(url).append("?type=").append(td.getTypeid()).append("\",target:\"main\",");
			str.append("pId:'0'},");
		}
	
		//=========================大类
		for (int i = 0; i < list.size(); i++) {
			STypeDO td = list.get(i);
			str.append("{id:'type_").append(td.getTypeid()).append("',");
			str.append("open:false").append(",");
			str.append("name:\"").append(td.getTitle()).append("\",");
			//str.append("url:\"").append(url).append("?type=").append(td.getTypeid()).append("\",target:\"main\",");
			//str.append("pId:0},");
			str.append("pId:'cate_").append(td.getCateid()).append("'},");
		}
		
		//==========================品牌
		for (int i = 0; i < trees.size(); i++) {
			SBandDO td = trees.get(i);
			str.append("{id:'band_").append(td.getBandid()).append("',");
			str.append("open:false").append(",");
			str.append("name:\"").append(td.getTitle()).append("\",");
			str.append("url:\"").append(url).append("?type=").append(td.getTypeid()).append("&bandId=")
					.append(td.getBandid()).append("\",target:\"main\",");
			str.append("pId:'type_").append(td.getTypeid()).append("'},");
			
			// new 产品型号 
//			if(prodMap.containsKey(td.getBandid())){
//				List<SProdDefDO> values = prodMap.get(td.getBandid());
//				for(SProdDefDO sd : values){
//					str.append("{id:'pd_").append(sd.getId()).append("',");
//					str.append("name:\"").append(sd.getXinghao()).append("\",");
//					str.append("url:\"").append(url).append("?type=").append(td.getTypeid()).append("&bandId=")
//							.append(td.getBandid()).append("&prodId=")
//							.append(sd.getId()).append("\",target:\"main\",");
//					str.append("pId:'band_").append(td.getBandid()).append("'},");
//				}
//			}
		}
		 
		str.deleteCharAt(str.length() - 1);
		str.append("]");
		 
		return this.returnSuccess(str.toString());
	}

	@RequestMapping(value = "/lists")
	@ResponseBody
	public Pager list(Pager pager) {
		return typeService.getList(pager);
	}
	
	@RequestMapping(value = "/findCategory")
	@ResponseBody
	public List<SCategoryDO> findCategory() {
		return typeService.findCategory();
	}
	@RequestMapping(value = "/saveOrupdate")
	@ResponseBody
	public Map<String, String> saveOrupdate(STypeDO typeDo) {
		try {
			typeService.saveOrUpdate(typeDo);
			return this.returnSuccess();
		} catch (Exception e) {
			return this.returnSuccess("error");
		}

	}
	
	@RequestMapping(value = "/saveOrUpdateCate")
	@ResponseBody
	public Map<String, String> saveOrUpdateCate(SCategoryDO c ) {
		try {
			typeService.saveOrUpdateCate(c);
			return this.returnSuccess();
		} catch (Exception e) {
			return this.returnSuccess("error");
		}

	}
	
	@RequestMapping(value = "/saveOrupdateCate")
	@ResponseBody
	public Map<String, String> saveOrupdateCate(SCategoryDO cateDo) {
		try {
			typeService.saveOrUpdateCate(cateDo);
			return this.returnSuccess();
		} catch (Exception e) {
			return this.returnSuccess("error");
		}

	}
	@RequestMapping(value = "/query")
	@ResponseBody
	public STypeDO saveOrupdate(String typeId) {
		return typeService.findById(typeId);
	}
	 
}