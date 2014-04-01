package com.oj.jxc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SGoodsParm;
import com.oj.jxc.entity.SProdLimitDO;

/**
 * 商品价格数量变动约束
 * 
 * @author wu
 * 
 */
@Component
public class SProdLimitService extends BaseService {
	/**
	 * get all data
	 * 
	 * @return
	 */
	public List<SProdLimitDO> getList() {
		List<SProdLimitDO> list = this.dao.queryForBeanList(
				"select * from s_prod_limit", SProdLimitDO.class);
		return list;
	}

	/**
	 * save or update
	 * 
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SProdLimitDO entity) {
		if (entity == null)
			return 0;

		SProdLimitDO entityInDB = findById(entity.getProdid());
		
		if (entityInDB == null) {
			String sql = "insert into s_prod_limit(adjustrate,dayadjustlimit,amountlimit,prodid)values(?,?,?,?)";
			return dao.update(sql, entity.getAdjustRate(),
					entity.getDayadJustLimit(), entity.getAmountlimit(),
					entity.getProdid());
		} else {
			String sql = "update s_prod_limit set adjustrate=?,dayadjustlimit=?,amountlimit=? where prodid=?";

			return dao.update(sql, entity.getAdjustRate(),
					entity.getDayadJustLimit(), entity.getAmountlimit(),
					entity.getProdid());
		}
	}

	/**
	 * remove by id
	 * 
	 * @param id
	 */
	public void remove(int id) {
		this.dao.update("delete from s_prod_limit where prodid = ?", id);
	}

	/**
	 * find DO by id
	 * 
	 * @param id
	 * @return
	 */
	public SProdLimitDO findById(int id) {
		return this.dao.queryForBean(
				"select * from s_prod_limit where prodid = ?",
				SProdLimitDO.class, id);
	}

	public Pager findGoods(Pager pager) {
		return null;
	}

	public Pager listPager(Pager pager) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from s_prod_limit ");
		/*
		sb.append("SELECT CONCAT(t.title,'-',b.title,'-',pd.nickname) totaltitle,pt.* ");
		sb.append("FROM s_prod_limit pt ");
		sb.append("INNER JOIN s_prod_def pd ON pd.id=pt.prodid"); 
		sb.append("INNER JOIN s_band b ON pd.bandid=b.bandid ");
		sb.append("INNER JOIN s_type t ON b.typeid=t.typeid");
		*/
		
		return findPagerComplex(sb.toString(), pager);
	}
}
