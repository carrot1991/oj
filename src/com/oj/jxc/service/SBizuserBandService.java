package com.oj.jxc.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.entity.BandDictForCheckBox;
import com.oj.jxc.entity.BizContact;
import com.oj.jxc.entity.SBizuserBandDO;
import com.oj.jxc.entity.SUserDO;
import com.oj.jxc.entity.UserBand;

/**
 * 商务部对上商务
 * 
 * @author wu
 * 
 */
@Component
public class SBizuserBandService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SBizuserBandDO> getList() {
		List<SBizuserBandDO> list = this.dao.queryForBeanList("select * from s_bizuser_band", SBizuserBandDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SBizuserBandDO entity) {
		if (entity != null) {
			if (StringUtils.isBlank(String.valueOf(entity.getId()))) {
				return this.insert(entity);
			} else {
				return this.update(entity, "id", ",uname,bandid,");
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_bizuser_band where id = ?", id);
	}

	/**
	 * find entity by id
	 * @param id
	 * @return
	 */
	public SBizuserBandDO findById(String id) {
		return this.dao.queryForBean("select * from s_bizuser_band where id = ?", SBizuserBandDO.class, id);
	}
	public List<Integer> findByUserId(String uname) {
		 return this.dao.queryForList("select bandid from s_bizuser_band where uname = ?", Integer.class, uname);
	}
	public List<BizContact> findContactUp() {
		 return this.dao.queryForBeanList("SELECT CONCAT(tp.title,'-',band.title) title,shortname,tel,email,qq FROM s_bizuser_band bb INNER JOIN s_user u ON bb.uname=u.uname INNER JOIN s_band band ON bb.bandid=band.bandid INNER JOIN s_type tp ON band.typeid=tp.typeid", BizContact.class);
	}

	/**
	 * find entity by uname
	 * @param uname
	 * @return
	 */
	public Map<Integer,String> findByUname(String uname) {
		 
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT b.bandid,CONCAT(t.title,'-',b.title) title ");
		sb.append("FROM s_bizuser_band ub ");
		sb.append("LEFT JOIN s_band b ON ub.bandid=b.bandid ");
		sb.append("LEFT JOIN s_type t ON b.typeid=t.typeid ");
		sb.append("WHERE ub.uname=?");
		
		return (Map<Integer,String>)dao.queryForMaps(sb.toString(), uname);
		
	}
	public List<BandDictForCheckBox> findByUnameForCheckBox(String uname) {
		 
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT bandid  ");
		sb.append("FROM s_bizuser_band ub ");
		sb.append("WHERE ub.uname=?");
		
		return dao.queryForBeanList( sb.toString(), BandDictForCheckBox.class, uname);
		
	}
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
				this.dao.update("insert into s_user_role(uname,rname)values(?,?)", u.getUname(),"upbiz");
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
		
		this.dao.update("delete from s_bizuser_band where uname =?", u.getUname());
		String[] strArray = u.getPids().split(",");
		
		for (String s : strArray){
			this.dao.update("insert into s_bizuser_band (uname,bandid) value (?,?)",  u.getUname(),
					Integer.parseInt(s));
		}
		return "success";
	}
}
