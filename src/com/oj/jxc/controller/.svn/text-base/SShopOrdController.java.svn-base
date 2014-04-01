package com.oj.jxc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oj.jxc.commons.Constants;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.commons.PdfExport;
import com.oj.jxc.entity.SmsOrder;
import com.oj.jxc.entity.SmsOrderForBuyer;
import com.oj.jxc.entity.SuggestBuyHist;
import com.oj.jxc.service.SShopCartService;
import com.oj.jxc.service.SShopOrdService;
import com.oj.jxc.service.SmsService;

@RequestMapping("/shopord")
@Controller
public class SShopOrdController extends BaseController {
 
	@Autowired
	SShopOrdService shopOrdService;
	@Resource
	private SmsService smsService;
	@Resource
	private SShopCartService shopCartService;
	
	@RequestMapping(value = "/tongjiForSale", method = RequestMethod.GET)
	@ResponseBody
	public Pager tongjiForSale( Pager pager, String whichDay,HttpSession session) {
		return shopOrdService.tongjiForSale(pager, whichDay,((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname() );
	}
	@RequestMapping(value = "/kucun", method = RequestMethod.GET)
	@ResponseBody
	public Pager kucun( Pager pager,HttpSession session) {
		return shopOrdService.kucun(pager, ((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname() );
	}
	
	@RequestMapping(value = "/suggest", method = RequestMethod.GET)
	@ResponseBody
	public List<SuggestBuyHist> suggest(int nums, HttpSession session) {
		return shopOrdService.suggest(nums);
	}
	
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public ResponseEntity<byte[]> print(String ordno,HttpServletResponse resp) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment", "oj-jxc-order-"+ordno+".pdf");  
	    PdfExport pdf = new PdfExport();
	    return new ResponseEntity<byte[]>(pdf.write(ordno,shopOrdService).toByteArray(),  
	                                      headers, HttpStatus.CREATED);  
	     
	}
	
	/**
	 * 获取所有用户的商品
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/findOrd", method = RequestMethod.GET)
	@ResponseBody
	public Pager listPager(Pager pager, String isZj, HttpSession session) {
		String uname = ((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname();
		if (isZj == null || isZj.equals("")) {
			uname = null;
		}
		return this.shopOrdService.findOrd(pager, uname);
	}
	@RequestMapping(value = "/findOrdForSale", method = RequestMethod.GET)
	@ResponseBody
	public Pager listPagerForSale(Pager pager, String whichDay,HttpSession session) {
		String uname = ((LoginedUser) session.getAttribute(Constants.LOGIN_SESSION_NAME)).getLoginUser().getUname();
		return this.shopOrdService.findOrdForSale(pager, whichDay,uname);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Pager list(Pager pager,  HttpSession session) {
		return this.shopOrdService.findOrd(pager, null);
	}
	
	@RequestMapping(value = "/removeOrd", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> removeOrd( int id ) {
		return returnSuccess(shopOrdService.removeOrd( id ));
	}
	
	/**
	 * 审核
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/examine", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> examine(String status, String id) {
		try {
			//status=1 通过
			if(status.equals("1")){//审核通过
				
				//发送给 供货方
				List<SmsOrder> list = shopCartService.findSaleMobileNoByOrderID( Integer.parseInt(id) );
				for(SmsOrder so : list){
					if(StringUtils.isNotBlank( so.getMobile() )){
						smsService.sendSmsAfterOrder(so.getMobile(),so.getNickname()+"-"+so.getXinghao()+" 数量:"+ so.getAmount(),"hzoj-addr");
					}
				}
				
				//发送给 购买者
				
				List<SmsOrderForBuyer> ret = shopCartService.findBuyerMobileNoByOrderID(Integer.parseInt(id));
				if(ret!=null && !ret.isEmpty()){
					String buyerMobile = ret.get(0).getMobile();
					String details = ret.get(0).getDetails();
					
					if(StringUtils.isNotBlank(buyerMobile)){
						smsService.sendSmsToBuyerAfterConfirm(buyerMobile,details);
					}
					
				}
				
				return this.returnSuccess(this.shopOrdService.update(status, id));
			} 
			return this.returnSuccess("error");
			
		} catch (Exception e) {
			return this.returnSuccess("error");
		}
	}
	
	@RequestMapping(value = "/shipFinish", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> shipFinish( String id) {
		try {
			 
			//发送给 购买者
				 this.returnSuccess(this.shopOrdService.shipFinish(id));
			 
					List<SmsOrderForBuyer> ret = shopCartService.findBuyerMobileNoByOrderID(Integer.parseInt(id));
					if(ret!=null && !ret.isEmpty()){
						String buyerMobile = ret.get(0).getMobile();
						String details = ret.get(0).getDetails();
						int totalfee = ret.get(0).getTotalfee();
						
						if(StringUtils.isNotBlank(buyerMobile)){
							smsService.sendSmsToBuyerAfterShip(buyerMobile,totalfee);
						}
						
					}
					
					return returnSuccess("success");
		} catch (Exception e) {
			return this.returnSuccess("error");
		}
	}	
}