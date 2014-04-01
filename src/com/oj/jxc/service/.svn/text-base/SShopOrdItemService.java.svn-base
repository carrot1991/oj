package com.oj.jxc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SShopOrdItemDO;

/**
 * 订单详情
 * 
 * @author wu
 * 
 */
@Component
public class SShopOrdItemService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SShopOrdItemDO> getList() {
		List<SShopOrdItemDO> list = this.dao.queryForBeanList("select * from s_shop_ord_item", SShopOrdItemDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SShopOrdItemDO entity) {
		if (entity != null) {
			if (StringUtils.isBlank(String.valueOf(entity.getId()))) {
				return this.insert(entity);
			} else {
				return this.update(entity, "id", ",ordid,prodid,amount,price,createtime,");
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_shop_ord_item where id = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SShopOrdItemDO findById(String id) {
		return this.dao.queryForBean("select * from s_shop_ord_item where id = ?", SShopOrdItemDO.class, id);
	}

	public Pager findOrd(Pager pager, String cartid) {
		return this
				.findPagerComplex(
						"SELECT a.*,b.nickname,b.config,c.title bandname,e.title typename,date_format(createtime, '%Y-%m-%d') createtime1 FROM s_shop_ord_item a, s_prod_def b,s_band c,s_type e WHERE a.prodid = b.id	and b.bandid = c.bandid and c.typeid = e.typeid and ordid=?",
						pager, cartid);
	}
	public Pager findOrdItemPart(Pager pager, String ordid) {
		return this
				.findPagerComplex(
						"SELECT g.uname, p.id partid,b.nickname,b.config,c.title bandname,e.title typename,DATE_FORMAT(a.createtime,'%Y-%m-%d') createtime, p.amount,p.price  FROM  s_shop_ord_item a,  s_shop_ord_item_part p, s_goods g, s_prod_def b, s_band c, s_type e  WHERE a.prodid = b.id	 AND b.bandid = c.bandid  AND a.id = p.itemid AND p.goodsid = g.goodsid  AND c.typeid = e.typeid AND a.ordid=?",
						pager, ordid);
	}
	public Pager findUpOrd(Pager pager, List<Integer> prodid) {
		if (prodid != null && prodid.size() > 0) {
			String ids = "(";
			for (Integer it : prodid) {
				ids += (it + ",");
			}
			ids = ids.substring(0, ids.length() - 1);
			ids += ")";
			String sql = "SELECT a.*, date_format(a.createtime, '%Y-%m-%d') createtime1 FROM s_shop_ord a,(select max(prodid) pid ,cartid from s_shop_ord_item group by cartid) b "
					+ " WHERE a.id = b.cartid AND a. STATUS <> 0 AND b.pid IN " + ids;
			return this.findPagerComplex(sql, pager);
		} else
			return pager;
	}

	public Pager findOrd(Pager pager, List<Integer> regions) {
		if (regions != null && regions.size() > 0) {
			String ids = "(";
			for (Integer it : regions) {
				ids += (it + ",");
			}
			ids = ids.substring(0, ids.length() - 1);
			ids += ")";
//			String sql = "select a.*,date_format(a.createtime, \"%Y-%m-%d\") createtime1 from s_shop_ord a  ,s_user_profile c where a.uname = c.uname  and a.status = 0 and c.regionId in "
			String sql = "select a.*,date_format(a.createtime, '%Y-%m-%d') createtime1 from s_shop_ord a  ,s_user_profile c where a.uname = c.uname and c.regionId in "			
					+ ids;
			return this.findPagerComplex(sql, pager);
		} else
			return pager;
	}
}