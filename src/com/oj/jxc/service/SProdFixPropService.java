package com.oj.jxc.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SProdFixPropDO;

/**
 * 
 * 商品属性值
 * @author wu
 * 
 */
@Component
public class SProdFixPropService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SProdFixPropDO> getList() {
		List<SProdFixPropDO> list = this.dao.queryForBeanList("select * from s_prod_fix_prop", SProdFixPropDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SProdFixPropDO entity) {
		if (entity != null) {
			if (entity.getPropid()!=null && entity.getPropid()!=0) {
				this.remove(entity.getPropid() );
			}
			return this.insert(entity);
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(int id) {
		this.dao.update("delete from s_prod_fix_prop where  propid = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SProdFixPropDO findById(String id) {
		return this.dao.queryForBean("select * from s_prod_fix_prop where propid = ?", SProdFixPropDO.class, id);
	}

	public List<SProdFixPropDO> findByType(int type) {
		if(type==0){
			return this.dao.queryForBeanList("select * from s_prod_fix_prop order by typeid,propid", SProdFixPropDO.class);
		}else{
			return this.dao.queryForBeanList("select * from s_prod_fix_prop where typeid = ?  order by propid", SProdFixPropDO.class, type);
		}
	}

	public Pager findPager(Pager pager) {
		return this.findPager("select * from s_prod_fix_prop ", pager);
	}
	
	public Map<Integer,String> getDict(int typeid){
		String sql = "select * from s_prod_fix_prop where typeid="+typeid;
		return dao.queryForMaps(sql, null);
	}
}
