package com.oj.jxc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SProdPropDO;

/**
 * 商品属性
 * 
 * @author wu
 * 
 */
@Component
public class SProdPropService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SProdPropDO> getList() {
		List<SProdPropDO> list = this.dao.queryForBeanList("select * from s_prod_prop", SProdPropDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SProdPropDO entity) {
		if (entity != null) {
			if (StringUtils.isBlank(String.valueOf(entity.getId()))) {
				return this.insert(entity);
			} else {
				return this.update(entity, "id", ",prodid,propid,vlue,");
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_prod_prop where id = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SProdPropDO findById(String id) {
		return this.dao.queryForBean("select * from s_prod_prop where id = ?", SProdPropDO.class, id);
	}

	public Pager findPager(Pager pager) {
		return this.findPager("select * from s_prod_prop", pager);
	}
}
