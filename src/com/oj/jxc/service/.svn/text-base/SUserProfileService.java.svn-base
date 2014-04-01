package com.oj.jxc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SUserProfileDO;

/**
 * 用户详细信息
 * 
 * @author wu
 * 
 */
@Component
public class SUserProfileService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SUserProfileDO> getList() {
		List<SUserProfileDO> list = this.dao.queryForBeanList("select * from s_user_profile", SUserProfileDO.class);
		return list;
	}
	public int updateRel(String uname,String bizname) {
		return dao.update( "UPDATE s_user_profile set bizrel=? where uname=?",bizname,uname);
	}
	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SUserProfileDO entity) {
		if(entity == null || StringUtils.isBlank(entity.getUname()))
		{
			return -1;
		}
		
		SUserProfileDO entityInDB = findByName(entity.getUname());
		
			if (entityInDB == null) {
				return this.insert(entity);
			} else {
				return dao.update(
			"UPDATE s_user_profile SET memname =?, enname =?, custname =?, custaddr =?, " + 
				" custtaxno =?, custbank =?, custbankno =?, custmem =?, custqq =?, contact =?, " + 
					"mobile =?, mobile2 =?, phone = ? , phone2 = ?,regionid=?,containtax=? WHERE uname =?",
			entity.getMemname(),
			entity.getEnname(),
			entity.getCustname(),
			entity.getCustaddr(),
			
			entity.getCusttaxno(),
			entity.getCustbank(),
			entity.getCustbankno(),
			entity.getCustmem(),
			entity.getCustqq(),
			entity.getContact(),
			
			entity.getMobile(),
			entity.getMobile2(),
			entity.getPhone(),
			entity.getPhone2(),
			entity.getRegionid(),
			entity.getContaintax(),
			entity.getUname()
			);
			}
	}

	/**
	 * remove by id
	 * @param uname
	 */
	public void remove(String uname) {
		this.dao.update("delete from s_user_profile where uname = ?", uname);
	}

	/**
	 * find DO by id
	 * @param uname
	 * @return
	 */
	public SUserProfileDO findByName(String uname) {
		return this.dao.queryForBean("select * from s_user_profile where uname = ?", SUserProfileDO.class, uname);
	}
	
	public Integer findProdUpLimit() {
		try{
		return this.dao.queryForObject("select v from s_dict where k=?", Integer.class, "prod_up_limit");
		}catch(Exception ex){
			return -1;
		}
	}
	
	public SUserProfileDO findById(int id) {
		return this.dao.queryForBean("select * from s_user_profile where id = ?", SUserProfileDO.class, id);
	}
	public List<String> getBizForCustomer() {
		return this.dao.queryForList("select uname from s_user_role where rname in('upbiz','downbiz') order by uname ",String.class);
	}
	public List<String> getCustomerForBiz(String bizname) {
		return this.dao.queryForList("select uname from s_user_profile where bizrel=? ",String.class,bizname);
	}	
	public Pager getList(Pager pager,String key) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT sp.*,u.isvalid FROM s_user_profile sp INNER JOIN s_user u ON sp.uname=u.uname ");
		if(StringUtils.isNotBlank(key)){
			sb.append(" WHERE sp.uname LIKE '%"+ key +"%' ");
		}
		sb.append(" ORDER BY u.isvalid,sp.uname");

		return this.findPager(sb.toString(), pager);
	}	
}
