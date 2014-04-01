package com.oj.jxc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.Constants;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SUserDO;
import com.oj.jxc.entity.SUserProfileDO;
import com.oj.jxc.service.SDictService;
import com.oj.jxc.service.SUserProfileService;
import com.oj.jxc.service.SUserService;

@RequestMapping("/user")
@Controller
public class SUserController extends BaseController {
	@Autowired
	SUserService service;
	@Autowired
	SDictService dictService;
	@Autowired
	com.oj.jxc.service.CacheService cacheService;

	@Autowired
	SUserProfileService profileService;
	
	@RequestMapping(value = "/autoLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> autoLogin(String uname, HttpSession session) {
		session.setAttribute(Constants.LOGIN_SESSION_NAME, null);
		session.removeAttribute(Constants.LOGIN_SESSION_NAME);
		String pwd = service.getPwd(uname);
		return login(uname,pwd,session);
	}

	/**
	 * 登录
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(String uname, String pwd, HttpSession session) {
		LoginedUser user;
		try {
			user = service.login(uname, pwd);
			if (user != null) {
				
				SUserProfileDO profile = profileService.findByName(uname);
				if(profile!=null){
					int limit = profileService.findProdUpLimit();
					profile.setProdUpLimit(limit);
					user.setProfile(profile);
				}
				
				session.setAttribute(Constants.LOGIN_SESSION_NAME, user);
				
				//业务通知
				//1销售商 2管理员 3对下商务 4 对上商务 5代理商 6遊客7 ship
				String msg = service.findMyBusiness(user.getLoginUser().getUname(),user.getRoleInfo() );
				
				session.setAttribute("HOMEPAGE_MESSAGE", msg);
				
				return this.returnSuccess("main");
			} else {
				return this.returnSuccess("error");
			}
			//版权
			//cacheService.init(session);
			

		} catch (Exception e) {
			e.printStackTrace();
			return this.returnSuccess("error");
		}

	}
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public SUserDO find(String uname) {
		try {
			return service.find(uname);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveContact(SUserDO u) {
		try {
			service.saveContact(u);
			return this.returnSuccess("success");
		} catch (Exception e) {
			return null;
		}
	}	
	/**
	 * 登录
	 * 
	 */
	@RequestMapping(value = "/reg")
	@ResponseBody
	public Map<String, String> reg(String uname, String pwd, String role,boolean enabled,String tel) {
		try {
			return this.returnSuccess(service.add(uname, pwd, role,enabled,tel));
		} catch (Exception e) {
			e.printStackTrace();
			return this.returnSuccess("error");
		}
	}
	
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Map<String, String> remove(String uname) {
		try {
			return this.returnSuccess(service.remove(uname ));
		} catch (Exception e) {
			return this.returnSuccess("error");
		}
	}
	
	@RequestMapping(value = "/audit")
	@ResponseBody
	public Map<String, String> audit(String uname,int flag) {
		try {
			return this.returnSuccess(service.audit(uname,flag));
		} catch (Exception e) {
			e.printStackTrace();
			return this.returnSuccess("error");
		}

	}	
	@RequestMapping(value = "/changePwd")
	@ResponseBody
	public Map<String, String> changePwd(String uname, String pwd ) {
		try {
			return this.returnSuccess(service.changePwd(uname, pwd ));
		} catch (Exception e) {
			e.printStackTrace();
			return this.returnSuccess("error");
		}
	}
	
	@RequestMapping(value = "/saveShip")
	@ResponseBody
	public Map<String, String> saveShip(String uname, String pwd ,String tel) {
		try {
			return this.returnSuccess(service.add(uname, pwd,"ship",true,tel ));
		} catch (Exception e) {
			e.printStackTrace();
			return this.returnSuccess("error");
		}
	}
	
	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	public Map<String, String> resetPwd(String uname ) {
		return changePwd(uname,"123456");
	}
	/**
	 * 获取用户所有购物车
	 * 
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findPager")
	@ResponseBody
	public Pager findPager(Pager pager,String roleId,String key) {
		pager = pager == null ? new Pager() : pager;
		return this.service.findPager(pager, roleId,key);
	}
}