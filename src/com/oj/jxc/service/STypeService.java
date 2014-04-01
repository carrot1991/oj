package com.oj.jxc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SBandDO;
import com.oj.jxc.entity.SCategoryDO;
import com.oj.jxc.entity.STypeDO;

/**
 * 
 * 
 * @author wu
 * 
 */
@Component
public class STypeService extends BaseService {
	@Autowired
	JdbcTemplate jdbc;
	public int removeCate(int id) {
		return this.dao.update("delete from s_category where id=? ", id);
	}
	public int remove(int id) {
		return this.dao.update("delete from s_type where typeid=? ", id);
	} 
	public List<SCategoryDO> getCategoryList( ) {
		return this.dao.queryForBeanList("select * from s_category order by level ", SCategoryDO.class);
	}	
	public List<STypeDO> getList(String by) {
		return this.dao.queryForBeanList("select * from s_type order by "+by, STypeDO.class);
	}

	public STypeDO findById(String typeId) {
		return this.dao.queryForBean("select * from s_type where typeid=?  order by level", STypeDO.class, typeId);
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(STypeDO entity) {
		if (entity != null) {
			if (entity.getTypeid() == null || entity.getTypeid() ==0 ) {
				return jdbc.update("insert into s_type (cateid,title,level)values (?,?,?)", entity.getCateid(),entity.getTitle(),entity.getLevel());
			} else {
				return jdbc.update("update s_type set title =?,level=? where typeid =?", entity.getTitle(),entity.getLevel(), entity.getTypeid());
			}
		}
		return 0;
	}
	 
	public Integer saveOrUpdateCate(SCategoryDO entity) {
		if (entity != null) {
			if (entity.getId() == null || entity.getId() ==0 ) {
				return jdbc.update("insert into s_category (title,level)values (?,?)", entity.getTitle(),entity.getLevel());
			} else {
				return jdbc.update("update s_category set title =?,level=? where  id =?", entity.getTitle(), entity.getLevel(),entity.getId());
			}
		}
		return 0;
	}
	public Pager getList(Pager pager) {
		return this.findPagerComplex("select * from s_type order by level", pager);
	}
	
	public Map<Integer,String> getDict(){
		String sql = "SELECT typeid,title,level FROM s_type order by level";
		return dao.queryForMaps(sql, null);
	}
	public List<SCategoryDO> findCategory( ) {
		return dao.queryForBeanList("select * from s_category order by level", SCategoryDO.class);
	}
	public List<STypeDO> findType(int cateid) {
		return this.dao.queryForBeanList("select * from s_type where cateid=? order by level", STypeDO.class,cateid);
	}
}
