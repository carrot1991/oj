package com.oj.jxc.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SGoodsDO;
import com.oj.jxc.entity.SGoodsParm;
import com.oj.jxc.entity.SmsOrder;

/**
 * 用户商品
 * 
 * @author wu
 * 
 */
@Component
public class SGoodsService extends BaseService {
	@Autowired
	JdbcTemplate jdbc;
	
	public List<SmsOrder> findMobileNoByGoodID(int goodsid){
		StringBuilder sb = new StringBuilder();
		sb.append( "SELECT up.mobile,1 AS amount,  sd.nickname, sd.xinghao   FROM s_goods goods ");
		sb.append( "INNER JOIN s_prod_def sd ");
		sb.append( "ON goods.prodid = sd.id ");
		sb.append( "INNER JOIN s_band band ");
		sb.append( "ON sd.bandid = band.bandid ");
		sb.append( "INNER JOIN s_user_profile up ");
		sb.append( "ON goods.uname = up.uname ");
		sb.append( "WHERE goods.goodsid=?");
		 
		return  dao.queryForBeanList( sb.toString(), SmsOrder.class,goodsid);
	}
	/**
	 * get all data
	 * @return
	 */
	public List<SGoodsDO> getList() {
		List<SGoodsDO> list = this.dao.queryForBeanList("select * from s_goods", SGoodsDO.class);
		return list;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_goods where goodsid = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SGoodsDO findById(String id) {
		return this.dao.queryForBean("select * from s_goods where goodsid = ?", SGoodsDO.class, id);
	}
	public Pager findGoodsByProdForBiz(Pager pager, String uname) {
 
		StringBuilder sb = new StringBuilder();
		sb.append( "SELECT g.uname,g.goodsid,amount,price,g.prodid,pd.nickname,pd.xinghao,pd.config,band.title,g.ispendingchange,g.ispendingup,date_format(g.createtime, '%Y-%m-%d %H:%i') createtime FROM s_goods g ,s_prod_def pd ");  
		sb.append( ",s_band band WHERE g.prodid = pd.id AND pd.bandid =band.bandid  ");
		sb.append( "AND band.bandid IN(  SELECT bandid FROM s_bizuser_band WHERE uname='"+uname+"' ) ");
		sb.append( "ORDER BY g.goodsid desc");
	//	System.out.println(sb.toString()+"--------------------");
		return findPagerComplex(sb.toString(), pager);
	}
	public String update(String status, String id) {
		//上架，变动
		//1,2  11,12
		if(status.equals("1")){
			this.dao.update("update s_goods set ispendingup=0 where goodsid =?", id);
		}else if(status.equals("2")){
			this.dao.update("update s_goods set ispendingup=1 where goodsid =?", id);
		}else if(status.equals("11")){
			this.dao.update("update s_goods set ispendingchange=0,amount=pendingamount,price=pendingpricewhere goodsid =?",id);
		}else if(status.equals("22")){
			this.dao.update("update s_goods set ispendingchange=1 where goodsid =?",id);
		}
		
		return "success";
	}
	/*
	public int canAddNewProd( String uname) {
		StringBuilder sb = new StringBuilder();
		sb.append( "SELECT COUNT(DISTINCT(t.typeid)) FROM s_type t WHERE t.typeid IN( ");
		sb.append( "SELECT typeid FROM s_band WHERE bandid IN( ");
		sb.append( "SELECT bandid FROM s_prod_def WHERE id IN(SELECT prodid FROM s_goods WHERE uname=?) ");
		sb.append( ") ");
		sb.append( ") ");
		
		int count = dao.queryForObject(sb.toString(), Integer.class, uname);
		return count;
		
	}*/
	public Pager findGoods(Pager pager, SGoodsParm goodsParm, String user) {
		//銷售商
		String sql = null;
		String par = "";
		if (goodsParm.getNickname() != null && !"".equals(goodsParm.getNickname())) {
			par += " and (b.nickname like '%" + goodsParm.getNickname() + "%' ";
			par += " or b.xinghao like '%" + goodsParm.getNickname() + "%')";
		}
		
		if (goodsParm.getConfigFilter() != null) {
			String[] configArray = goodsParm.getConfigFilter().split(",");
			if(configArray!=null && configArray.length>0){
					for(String str : configArray){
						if(str!=null && str.length()>0){
							String[] item = str.split(":");
							par += " and (prop.propid =" + item[0];
							par += " and prop.vlue ='" + item[1] +"') ";
						}
					}
			}
		} 
		
		if (goodsParm.getTingchan()==1) {
			par += " and (tingchan=1 or tingchan=0)  ";
		}else{
			par += " and (tingchan=0)  ";
		}
		if (goodsParm.getIsass()==1) {
			par += " and isass=1  ";
		}
		if (goodsParm.getLowPrice() != null) {
			par += " and price >=  " + goodsParm.getLowPrice();
		}
		if (goodsParm.getHighPrice()!=null && goodsParm.getHighPrice() != null) {
			par += " and price <=  " + goodsParm.getHighPrice();
		}
		if (goodsParm.getType()!=null && !"null".equals(goodsParm.getType())&& !"0".equals(goodsParm.getType())) {
			par += " and d.typeid =  " + goodsParm.getType();
		}		
		if (goodsParm.getBandId()!=null && !"null".equals(goodsParm.getBandId())&& !"0".equals(goodsParm.getBandId())) {
			par += " and b.bandid =  " + goodsParm.getBandId();
		}
		if (goodsParm.getProdId()!=null && !"null".equals(goodsParm.getProdId())&& !"0".equals(goodsParm.getProdId())) {
			par += " and b.id =  " + goodsParm.getProdId();
		}
		
		if (goodsParm.getRole() != null) {
			if (goodsParm.getRole().equals("1") || goodsParm.getRole().equals("6")) {
				
				//修改原因：未审核，也要给用户显示。
				par += " AND a.ispendingchange=0 AND a.ispendingup=0 ";
				 
				StringBuilder sb = new StringBuilder();
				
				sb.append("SELECT ");
				sb.append("b.nickname ");
				sb.append(" , b.xinghao ");
				sb.append(", b.config ,b.tingchan");
				sb.append(", SUM(a.amount) amount ");
				sb.append(", min(a.price) price,a.ispendingup,a.ispendingchange");
				sb.append(", d.title,a.prodid,t.title ttitle ");
				sb.append("FROM ");
				sb.append(" s_goods a ");
				sb.append("INNER JOIN  s_prod_def b ");
				sb.append("    ON (a.prodid = b.id) ");
				sb.append("INNER JOIN  s_band d ");
				sb.append("    ON (b.bandid = d.bandid) ");
				sb.append("INNER JOIN  s_type t ");
				sb.append("    ON (d.typeid = t.typeid) where 1=1 and a.amount >=0");
				sb.append( par );
				sb.append(" GROUP BY t.typeid, d.bandid, b.id order by a.price asc");
				sql = sb.toString();
				//System.out.println(sql+"------------");
			} else if (Integer.parseInt(goodsParm.getRole()) >= 2 && Integer.parseInt(goodsParm.getRole()) <= 4) {
				//代理商 管理员
				sql = "select  uname,amount,price,a.prodid,date_format(a.createtime, \"%Y-%m-%d\") createtime,b.nickname,b.xinghao,b.config,ispendingup,ispendingchange,d.title from s_goods a ,s_prod_def b "
						+ ",s_band d where a.prodid = b.id  and b.bandid = d.bandid "+ par;
				sql += "  order by createtime desc,ispendingup asc";
			} else if (goodsParm.getRole().equals("5")) {
				//供货商
				sql = "select goodsid,amount,price,a.prodid,date_format(a.createtime, \"%Y-%m-%d\") createtime,b.nickname,b.xinghao,b.config,ispendingup,ispendingchange,pendingprice,pendingamount,d.title from s_goods a ,s_prod_def b "
						+ ",s_band d where a.prodid = b.id  and b.bandid = d.bandid " + par;
				
				sql += " and uname ='" + user + "'";
				sql += "  order by createtime desc";
			}
			return findPagerComplex(sql, pager);
		}
		return null;
	}
	public int findGoodsCount( SGoodsParm goodsParm, String user) {
		//銷售商
		String sql = null;
		String par = "";
		
		if (goodsParm.getNickname() != null && !"".equals(goodsParm.getNickname())) {
			par += " and (b.nickname like '%" + goodsParm.getNickname() + "%' ";
			par += " or b.xinghao like '%" + goodsParm.getNickname() + "%')";
		}
		
		if (goodsParm.getConfigFilter() != null) {
			String[] configArray = goodsParm.getConfigFilter().split(",");
			if(configArray!=null && configArray.length>0){
					for(String str : configArray){
						if(str!=null && str.length()>0){
							String[] item = str.split(":");
							par += " and (prop.propid =" + item[0];
							par += " and prop.vlue ='" + item[1] +"') ";
						}
					}
			}
		} 
		
		if (goodsParm.getTingchan()==0) {
			par += " and tingchan<>1  ";
		}
		if (goodsParm.getIsass()==1) {
			par += " and isass=1  ";
		}
		if (goodsParm.getLowPrice() != null) {
			par += " and price >=  " + goodsParm.getLowPrice();
		}
		if (goodsParm.getHighPrice()!=null && goodsParm.getHighPrice() != null) {
			par += " and price <=  " + goodsParm.getHighPrice();
		}
		if (goodsParm.getType()!=null && !"null".equals(goodsParm.getType())&& !"0".equals(goodsParm.getType())) {
			par += " and d.typeid =  " + goodsParm.getType();
		}		
		if (goodsParm.getBandId()!=null && !"null".equals(goodsParm.getBandId())&& !"0".equals(goodsParm.getBandId())) {
			par += " and b.bandid =  " + goodsParm.getBandId();
		}
		if (goodsParm.getProdId()!=null && !"null".equals(goodsParm.getProdId())&& !"0".equals(goodsParm.getProdId())) {
			par += " and b.id =  " + goodsParm.getProdId();
		}
		
		if (goodsParm.getRole() != null) {
			if (goodsParm.getRole().equals("1") || goodsParm.getRole().equals("6")) {
				
				//修改原因：未审核，也要给用户显示。
				//par += " AND a.ispendingchange=0 AND a.ispendingup=0 ";
				 
				StringBuilder sb = new StringBuilder();
				
				sb.append("SELECT count(1) FROM ");
				sb.append(" s_goods a ");
				sb.append("INNER JOIN  s_prod_def b ");
				sb.append("    ON (a.prodid = b.id) ");
				sb.append("INNER JOIN  s_band d ");
				sb.append("    ON (b.bandid = d.bandid) ");
				sb.append("INNER JOIN  s_type t ");
				sb.append("    ON (d.typeid = t.typeid) where 1=1 ");
				sb.append( par );
				sb.append(" GROUP BY t.typeid, d.bandid, b.id order by a.price asc");
				 
				sql = sb.toString();
			} else if (Integer.parseInt(goodsParm.getRole()) >= 2 && Integer.parseInt(goodsParm.getRole()) <= 4) {
				//代理商 管理员
				sql = "select count(1) from s_goods a ,s_prod_def b "
						+ ",s_band d where a.prodid = b.id  and b.bandid = d.bandid "+ par;
			} else if (goodsParm.getRole().equals("5")) {
				//供货商
				sql = "select count(1) from s_goods a ,s_prod_def b "
						+ ",s_band d where a.prodid = b.id  and b.bandid = d.bandid " + par;
				
				sql += " and uname ='" + user + "'";
			}
			return dao.queryForObject(sql, Integer.class );
		}
		return 0;
	}
	public Pager findAsso(Pager pager, SGoodsParm goodsParm ) {
		 
		String sql = null;
		String par = " and b.isass=1 and b.bandid =  " + goodsParm.getBandId();
 
				StringBuilder sb = new StringBuilder();
				
				sb.append("SELECT ");
				sb.append("b.nickname ");
				sb.append(" , b.xinghao ");
				sb.append(", b.config ,b.tingchan");
				sb.append(", SUM(a.amount) amount ");
				sb.append(", a.price,a.ispendingup,a.ispendingchange");
				sb.append(", d.title,a.prodid,t.title ttitle ");
				sb.append("FROM ");
				sb.append(" s_goods a ");
				sb.append("INNER JOIN  s_prod_def b ");
				sb.append("    ON (a.prodid = b.id) ");
				sb.append("INNER JOIN  s_band d ");
				sb.append("    ON (b.bandid = d.bandid) ");
				sb.append("INNER JOIN  s_type t ");
				sb.append("    ON (d.typeid = t.typeid) where 1=1 ");
				sb.append( par );
				sb.append("GROUP BY t.typeid, d.bandid, b.id order by a.price asc");
				 
				sql = sb.toString(); 
			return findPagerComplex(sql, pager);
	}
	
	public String update(final SGoodsDO goods) {
		//String info = String.valueOf(jdbc.queryForInt("select editgoods(?,?,?,?,?)", new Object[] { goods.getUname(),
				//goods.getProdid(), goods.getGoodsid(), goods.getAmount(), goods.getPrice() }));
		
		int flag = -1;
		
		if(goods.getAmount()<0 || goods.getPrice()<=0){
			return "数量来能为负数 价格不能为零或负数";
		}
		
		//修改数量：减少：直接通过
		SGoodsDO current = null;
		try{
			current = dao.queryForBean("select * from s_goods where goodsid=?", SGoodsDO.class,goods.getGoodsid());
		}catch(Exception ex){
			ex.printStackTrace();
		}

		//什么都没变化，直接通过
		if( goods.getPrice()==current.getPrice() && goods.getAmount()==current.getAmount() ){
			return "数量和价格没有变化，无须提交";
		} 
		
		//只是减少数量...
		if(goods.getPrice()==current.getPrice() && goods.getAmount()<=current.getAmount()){
			dao.update("update s_goods set amount=? where goodsid=?",goods.getAmount(), goods.getGoodsid());
		
			return "success";
		}
		 
		
		//价格变化了 或 数量增大了
		if(goods.getPrice()!=current.getPrice() || goods.getAmount()>current.getAmount()){
			flag = jdbc.execute("{call editgoods(?,?,?,?,?, ?)}", new CallableStatementCallback<Integer>() {
				@Override
				public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
					cs.setString(1, goods.getUname());
					cs.setInt(2, goods.getProdid());
					cs.setInt(3, goods.getGoodsid());
					cs.setInt(4, goods.getAmount());
					cs.setInt(5, goods.getPrice());
					cs.execute();
					return cs.getInt(6);
				}
			});	
		}
		
		if (flag==0){
			//if (goods.getGoodsid() != null)
			//	jdbc.update("update s_goods set ispendingchange=1 ,price=?,updatetime=now(),amount=? where goodsid=?",
			//			goods.getPrice(), goods.getGoodsid(), goods.getAmount());
			return "success";
		} else {
			return String.valueOf(flag);
		}
	}

	public String add(final SGoodsDO goodsDo) {
		//String info = String.valueOf(jdbc.queryForInt("select upgoods(?,?,?)", new Object[] { goodsDo.getProdid(),
				//goodsDo.getAmount(), goodsDo.getPrice() }));
		
		if(goodsDo.getAmount()<0 || goodsDo.getPrice()<=0){
			return "数量来能为负数 价格不能为零或负数";
		}
		 
		Integer flag = jdbc.execute("{call upgoods(?,?,?,?)}", new CallableStatementCallback<Integer>() {
			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setInt(1, goodsDo.getProdid());
				cs.setInt(2, goodsDo.getAmount());
				cs.setInt(3, goodsDo.getPrice());
				cs.execute();
				return cs.getInt(4);
			}
		});
		
		
		if (flag!=-1) {
			jdbc.update(
					"insert into s_goods (uname,prodid,amount,price,createtime,ispendingup,firstprice,pendingprice,pendingamount) values (?,?,?,?,now(),1,?,?,?)",
					goodsDo.getUname(), goodsDo.getProdid(), goodsDo.getAmount(), goodsDo.getPrice(), goodsDo.getPrice(),goodsDo.getPrice(), goodsDo.getAmount());
			return "success";
		}
		return "error";
	}

	public Map<String,Object> addCart(final SGoodsDO goodsDo) {
		final Map<String,Object> map = new HashMap<String,Object>();
		jdbc.execute("{call addcart(?,?,?,?,?)}", new CallableStatementCallback<Integer>() {
			@Override
			public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setString(1, goodsDo.getUname());
				cs.setInt(2, goodsDo.getProdid());
				cs.setInt(3, goodsDo.getAmount());
				
				cs.execute();
				map.put("retcode", cs.getInt(4));
				map.put("cartid", cs.getString(5));
				
				return 0;
			}
		});
		
		return map;
	}
}
