package com.oj.jxc.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.entity.BizContact;
import com.oj.jxc.entity.SBizuserRegionDO;
import com.oj.jxc.entity.UserBand;

/**
 * 商务部对下商务
 * 
 * @author wu
 * 
 */
@Component
public class SBizuserRegionService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SBizuserRegionDO> getList() {
		List<SBizuserRegionDO> list = this.dao.queryForBeanList("select * from s_bizuser_region",
				SBizuserRegionDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public String saveOrUpdate(SBizuserRegionDO[] entity) {
		String uname = entity[0].getUname();
		this.dao.update("delete from s_bizuser_region where uname =?", uname);
		for (SBizuserRegionDO sBizuserRegionDO : entity) {
			this.dao.update("insert into s_bizuser_region (uname,regionid) value (?,?)", sBizuserRegionDO.getUname(),
					sBizuserRegionDO.getRegionid());
		}
		return "success";
	}
 
	//对下、物流共用同一种模式。
	public String saveOrUpdateByComma(UserBand u) {
		if(u.getPids().length()==0 )return "fail";
		
		int existCount = dao.queryForObject("select count(1) from s_user where uname=?", Integer.class, u.getUname());
		
		if(existCount ==0){
			if(StringUtils.isNotBlank( u.getUname() ) && StringUtils.isNotBlank( u.getPwd() )){
				this.dao.update("insert into s_user(uname,pwd,email,tel,qq)values(?,?,?,?,?)", 
						u.getUname(),
						u.getPwd(),
						u.getEmail(),
						u.getTel(),
						u.getQq());
				this.dao.update("insert into s_user_role(uname,rname)values(?,?)", u.getUname(),u.getRole());
			}else{
				return "请输入用户名与密码";
			}
		}else{
			this.dao.update("update s_user set email=?,tel=?,qq=? where uname=?", 
					u.getEmail(),
					u.getTel(),
					u.getQq(),
					u.getUname());
		}
		
		this.dao.update("delete from s_bizuser_region where uname =?", u.getUname());
		String[] strArray = u.getPids().split(",");
		
		for (String s : strArray){
			this.dao.update("insert into s_bizuser_region (uname,regionid) value (?,?)",  u.getUname(),
					s);
		}
		return "success";
	}	
	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_bizuser_region where id = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SBizuserRegionDO findById(String id) {
		return this.dao.queryForBean("select * from s_bizuser_region where id = ?", SBizuserRegionDO.class, id);
	}

	/**
	 * find DO by userId
	 * @param id
	 * @return
	 */
	public List<SBizuserRegionDO> findByUserId(String userId) {
		return this.dao.queryForList("select * from s_bizuser_region where uname = ?", SBizuserRegionDO.class, userId);
	}
	
	public List<BizContact> findContactDown(int regionid) {
		//(SELECT regionid FROM s_user_profile WHERE uname=?)
		 return this.dao.queryForBeanList("SELECT CONCAT(r.province,'-',r.city,'-',r.county) title,shortname,tel,email,qq FROM s_bizuser_region br INNER JOIN s_region r ON br.regionid=r.id INNER JOIN s_user u ON br.uname=u.uname WHERE r.id=?", BizContact.class,regionid);
	}	
	
	@SuppressWarnings("unchecked")
	public Map<Integer,String> findIdsByUserId(String uname) {
		String sql = "SELECT br.regionid regionid,CONCAT(r.province,'-',r.city,'-',r.county) title FROM s_bizuser_region br LEFT JOIN s_region r ON br.regionid=r.id  where br.uname = ?";
		return (Map<Integer,String>)dao.queryForMaps(sql, uname);
		
	}
}
