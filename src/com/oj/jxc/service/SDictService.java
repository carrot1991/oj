package com.oj.jxc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SDictDO;

/**
 * 字典
 * 
 * @author wu
 * 
 */
@Component
public class SDictService extends BaseService {
	@Autowired
	JdbcTemplate jdbc;

	/**
	 * get all data
	 * @return
	 */
	public List<SDictDO> getList() {
		List<SDictDO> list = this.dao.queryForBeanList("select * from s_dict", SDictDO.class);
		return list;
	}

	/**
	 * find entity by id
	 * @param id
	 * @return
	 */
	public List<SDictDO> findByType(String type) {
		return this.dao.queryForBeanList("select * from s_dict where type = ?", SDictDO.class, type);
	}

	/**
	 * find entity by id
	 * @param id
	 * @return
	 */
	public SDictDO findById(String id) {
		return this.dao.queryForBean("select * from s_dict where k = ?", SDictDO.class, id);
	}

	public void update(SDictDO dictDO) {
		jdbc.update("update s_dict set v =?,comments=? where k = ?", dictDO.getV(), dictDO.getComments(), dictDO.getK());
	}

	public Pager getList(Pager pager) {
		return this.findPager("select * from s_dict", pager);
	}
}
