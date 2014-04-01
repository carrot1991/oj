package com.oj.jxc.entity;

import java.io.Serializable;

import net.keepsoft.commons.annotation.IdentityId;
import net.keepsoft.commons.annotation.Table;
import net.keepsoft.commons.annotation.Temporary;

/**
 * 数据对象
 * 产品编号     价格调整幅度          当天价格调整幅度              填写的数量上限比例
 * @since 2014-02-26 s_prod_limit
 */
@Table("s_prod_limit")
public class SProdLimitDO implements Serializable {
 
	@Temporary
	private static final long serialVersionUID = -6641902416590744718L;
	
	@IdentityId
	private Integer prodid;//产品编号  
    private Float adjustRate;//价格调整幅度
    private Float dayadJustLimit;//当天价格调整幅度
    private Integer amountlimit;//填写的数量上限比例
    
    private String prodStr;
    
	public String getProdStr() {
		return prodStr;
	}
	public void setProdStr(String prodStr) {
		this.prodStr = prodStr;
	}
	public Integer getProdid() {
		return prodid;
	}
	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}
	public Float getAdjustRate() {
		return adjustRate;
	}
	public void setAdjustRate(Float adjustRate) {
		this.adjustRate = adjustRate;
	}
	public Float getDayadJustLimit() {
		return dayadJustLimit;
	}
	public void setDayadJustLimit(Float dayadJustLimit) {
		this.dayadJustLimit = dayadJustLimit;
	}
	public Integer getAmountlimit() {
		return amountlimit;
	}
	public void setAmountlimit(Integer amountlimit) {
		this.amountlimit = amountlimit;
	}
    

}