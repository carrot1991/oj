package com.oj.jxc.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.OrderItem4Print;
import com.oj.jxc.entity.SShopOrdDO;
import com.oj.jxc.entity.SUserProfileDO;
import com.oj.jxc.entity.SuggestBuyHist;

/**
 * 订单
 * 
 * @author wu
 * 
 */
@Component
public class SShopOrdService extends BaseService {
	@Autowired
	SShopCartService shopCart;

	@Autowired
	SUserProfileService upService;
	@Autowired
	JdbcTemplate jdbc;
	
	public Object[] getOrd4Print(String ordNo){
		//分项
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ord.beizhu,sd.nickname,g.uname,sp.amount,sp.price ");
		sb.append("FROM s_shop_ord_item_part sp ");
		sb.append("INNER JOIN s_shop_ord_item so ON sp.itemid=so.id ");
		sb.append("INNER JOIN s_shop_ord  ord ON so.ordid=ord.id ");
		sb.append("INNER JOIN s_goods g ON sp.goodsid=g.goodsid ");
		sb.append("INNER JOIN s_prod_def sd ON g.prodid=sd.id ");
		sb.append("WHERE so.ordid=?");
		
		List<OrderItem4Print> items = dao.queryForBeanList(sb.toString(),OrderItem4Print.class,ordNo);
		 
		SUserProfileDO customer = dao.queryForBean("SELECT * FROM s_user_profile up WHERE uname IN( " + 
				" SELECT uname FROM s_shop_ord WHERE id=?) ", SUserProfileDO.class, ordNo);
		
		return new Object[]{items,customer};
	}
	/**
	 * get all data
	 * @return
	 */
	public List<SShopOrdDO> getList() {
		List<SShopOrdDO> list = this.dao.queryForBeanList("select * from s_shop_ord", SShopOrdDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SShopOrdDO entity) {
		if (entity != null) {
			if (StringUtils.isBlank(String.valueOf(entity.getId()))) {
				return this.insert(entity);
			} else {
				return this.update(entity, "id", ",uname,status,createtime,");
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_shop_ord where id = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SShopOrdDO findById(String id) {
		return this.dao.queryForBean("select * from s_shop_ord where id = ?", SShopOrdDO.class, id);
	}

	public Pager tongjiForSale(Pager pager, String whichDay,String uname){
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT   ");
		sb.append("b.nickname,b.config,c.title bandname,e.title typename, ");
		sb.append("DATE_FORMAT(a.createtime, '%Y-%m-%d') createtime, ");
		sb.append("SUM(p.amount) totalamount ");
		sb.append("FROM ");
		sb.append("s_shop_ord_item a, ");
		sb.append("s_shop_ord_item_part p, ");
		sb.append("s_shop_ord odr, ");
		sb.append("s_goods g, ");
		sb.append("s_prod_def b, ");
		sb.append("s_band c, ");
		sb.append("s_type e ");
		sb.append("WHERE a.prodid = b.id ");
		sb.append("AND b.bandid = c.bandid ");
		sb.append("AND a.id = p.itemid ");
		sb.append("AND a.ordid=odr.id ");
		sb.append("AND odr.status=1 ");
		sb.append("AND p.goodsid = g.goodsid ");
		sb.append("AND c.typeid = e.typeid ");
		sb.append("AND g.uname=?  ");
 
		if(whichDay!=null){
			sb.append(" and date_format(a.createtime, '%Y-%m-%d')='"+whichDay+"'");
		}else{
			String now = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
			sb.append(" and date_format(a.createtime, '%Y-%m-%d')='"+now+"'");
		}
		
		return this
				.findPagerComplex(sb.toString(),pager,uname);
		 
	}
	public Pager kucun(Pager pager, String uname){
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT   ");
		sb.append("b.nickname,b.config,c.title bandname,e.title typename, ");
		sb.append("SUM(g.amount) totalamount ");
		sb.append("FROM  ");
		sb.append("s_goods g, ");
		sb.append("s_prod_def b, ");
		sb.append("s_band c, ");
		sb.append("s_type e  ");
		sb.append("WHERE 1=1  ");
		sb.append("AND (g.ispendingchange=0 AND g.ispendingup=0 AND g.amount>0)");
		sb.append("AND b.bandid = c.bandid  ");
		sb.append("AND g.prodid = b.id ");
		sb.append("AND c.typeid = e.typeid ");
		sb.append("AND g.uname=? group by g.goodsid");
 
		return this
				.findPagerComplex(sb.toString(),pager,uname);
		 
	}
	public List<SuggestBuyHist> suggest(int nums){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT odr.uname,date_format(odr.createtime, '%Y-%m-%d %H:%i') createtime,sd.nickname, ");
		sb.append("	item.amount,item.price ");
		sb.append(" FROM s_shop_ord odr ");
		sb.append(" LEFT JOIN s_shop_ord_item item  ");
		sb.append(" ON odr.id=item.ordid ");
		sb.append(" LEFT JOIN s_prod_def sd ");
		sb.append(" ON item.prodid=sd.id ");
		sb.append(" ORDER BY odr.id DESC limit "+nums);
		
		return this.dao.queryForBeanList(sb.toString(), SuggestBuyHist.class);
	}
	
	public Pager findOrdForSale(Pager pager, String whichDay,String uname) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append("SELECT ");
		sb.append("CONCAT(s_type.title,'-', s_band.title,'-', s_prod_def.nickname,'-',s_prod_def.xinghao) title ");
		sb.append(", s_shop_ord_item_part.amount ");
		sb.append(", s_shop_ord_item_part.price ");
		sb.append(", s_shop_ord_item_part.goodsid ");
		sb.append(", s_shop_ord.status ");
		sb.append(", s_shop_ord.ddh ");
		sb.append(", date_format(s_shop_ord.createtime, '%Y-%m-%d %H:%i') createtime ");
		sb.append("FROM ");
		sb.append("s_shop_ord ");
		sb.append("INNER JOIN s_shop_ord_item "); 
		sb.append("    ON (s_shop_ord.id = s_shop_ord_item.ordid) ");
		sb.append("INNER JOIN s_shop_ord_item_part  ");
		sb.append("    ON (s_shop_ord_item.id = s_shop_ord_item_part.itemid) ");
		sb.append("INNER JOIN s_goods  ");
		sb.append("    ON (s_shop_ord_item.prodid = s_goods.prodid) AND (s_shop_ord_item_part.goodsid = s_goods.goodsid) ");
		sb.append("INNER JOIN s_prod_def  ");
		sb.append("    ON (s_prod_def.id = s_goods.prodid) ");
		sb.append("INNER JOIN s_band  ");
		sb.append("    ON (s_band.bandid = s_prod_def.bandid) ");
		sb.append("INNER JOIN s_type  ");
		sb.append("    ON (s_band.typeid = s_type.typeid) ");
		sb.append("WHERE s_goods.uname=? ");
		
		if(whichDay!=null){
			sb.append(" and date_format(s_shop_ord.createtime, '%Y-%m-%d')='"+whichDay+"'");
		}else{
			String now = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
			sb.append(" and date_format(s_shop_ord.createtime, '%Y-%m-%d')='"+now+"'");
		}
		sb.append("  ORDER BY s_shop_ord.id DESC");
		
		return this
				.findPagerComplex(sb.toString(),pager, uname);
	}
	public Pager findOrd(Pager pager, String uname) {
		if (uname != null && !uname.equals("")) {
			return this
					.findPagerComplex(
							"select *,date_format(createtime, '%Y-%m-%d %H:%i') createtime1 from s_shop_ord where uname =? order by createtime desc",
							pager, uname);
		} else
			return this

					.findPagerComplex(
							"select *,date_format(createtime, '%Y-%m-%d %H:%i') createtime1 from s_shop_ord order by createtime desc",
							pager);
	}
	public String update(String status, String id) {
		if (status.equals("2")) {
			List<String> ls = this.dao.queryForList("select cartid from s_shop_ord_item where ordid = ?", String.class,
					id);
			if (ls != null && ls.size() > 0) {
				for (String st : ls) {
					shopCart.update(st);
				}
			}
		}
		this.dao.update("update s_shop_ord set status = ? where id =?", status, id);
		return "success";
	}
	
	public String shipFinish( String id) {
		this.dao.update("update s_shop_ord  set shipfinished = 1 where id =?", id);
		
		//发送短信...
		return "success";
	}
	
	public String removeOrd(final int id) {
		try{
		jdbc.execute("{call removeord(?)}", new CallableStatementCallback<Integer>() {
			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setInt(1, id);
				cs.execute();
				return 0;
			}
		});
		return "success";
		}catch(Exception ex){
			ex.printStackTrace();
			return "error";
		}
		
	}	
}
