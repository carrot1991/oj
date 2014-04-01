package com.oj.jxc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.entity.SUserRoleDO;

/**
 * 用户角色
 * 
 * @author wu
 * 
 */
@Component
public class SUserRoleService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SUserRoleDO> getList() {
		List<SUserRoleDO> list = this.dao.queryForBeanList("select * from s_user_role", SUserRoleDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SUserRoleDO entity) {
		if (entity != null) {
			if (StringUtils.isBlank(String.valueOf(entity.getId()))) {
				return this.insert(entity);
			} else {
				return this.update(entity, "id", ",uname,rname,");
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_user_role where id = ?", id);
	}

	/**
	 * @param id
	 * @return
	 */
	public List<String> findByRoleId(String user) {
		return this.dao.queryForList(
				"select trim(rcomment) from s_role a ,s_user_role b where a.rname = b.rname and b.uname = ?",
				String.class, user);
	}

}
