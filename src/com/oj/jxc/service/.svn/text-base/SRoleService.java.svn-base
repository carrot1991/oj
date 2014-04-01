package com.oj.jxc.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oj.jxc.entity.SRoleDO;

/**
 * 角色
 * 
 * @author wu
 * 
 */
@Component
public class SRoleService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SRoleDO> getList() {
		List<SRoleDO> list = this.dao.queryForBeanList("select * from s_role", SRoleDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SRoleDO entity) {
		if (entity != null) {
			this.remove(entity.getRname());
			return this.insert(entity);
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param rname
	 */
	public void remove(String rname) {
		this.dao.update("delete from s_role where rname = ?", rname);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SRoleDO findById(String id) {
		return this.dao.queryForBean("select * from s_role where rname = ?", SRoleDO.class, id);
	}

}
