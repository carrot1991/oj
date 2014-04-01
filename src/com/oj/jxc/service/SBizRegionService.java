package com.oj.jxc.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oj.jxc.entity.SBizRegionDO;

/**
 * 
 * 用户对下商务
 * @author wu
 * 
 */
@Component
public class SBizRegionService extends BaseService {

	/**
	 * get all data
	 * @return
	 */
	public List<SBizRegionDO> getList() {
		List<SBizRegionDO> list = this.dao.queryForBeanList("select * from s_biz_region", SBizRegionDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SBizRegionDO entity) {
		if (entity != null) {
			this.remove(entity);
			return this.insert(entity);
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param entity
	 */
	public void remove(SBizRegionDO entity) {
		this.dao.update("delete from s_biz_region where uname = ? and regionid = ?", entity.getUname(),
				entity.getRegionid());
	}

	/**
	 * find entity by id
	 * @param id
	 * @return
	 */
	public List<Integer> findByUser(String user) {
		return this.dao.queryForList("select regionid from s_bizuser_region where uname = ?", Integer.class, user);
	}

}
