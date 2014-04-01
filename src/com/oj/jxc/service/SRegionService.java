package com.oj.jxc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.entity.SRegionDO;

/**
 * 组织架构信息
 * 
 * @author wu
 * 
 */
@Component
public class SRegionService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SRegionDO> getList() {
		List<SRegionDO> list = this.dao.queryForBeanList("select * from s_region", SRegionDO.class);
		return list;
	}
	public Map<Integer,String> findTotalTitleByRegionId(){
		String sql = "SELECT id,CONCAT(province,'-',city,'-',county) title FROM s_region ORDER BY id";
		return dao.queryForMaps(sql, null);
	}
	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SRegionDO entity) {
		if (entity != null) {
			if (StringUtils.isBlank(String.valueOf(entity.getId()))) {
				return this.insert(entity);
			} else {
				return this.update(entity, "id", ",province,city,county,");
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_region where id = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SRegionDO findById(String id) {
		return this.dao.queryForBean("select * from s_region where id = ?", SRegionDO.class, id);
	}

	public List<SRegionDO> findByCity(String city) {
		return this.dao.queryForList("select * from s_region where city = ?", SRegionDO.class, city);
	}
}
