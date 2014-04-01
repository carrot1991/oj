package com.oj.jxc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.oj.jxc.commons.SmsClient;

@Component
public class SmsService {
	
	private static final String SERVLET_GET = "GET";
	//SDK-MOV-010-00584
	//您上架的商品（型号+数量）已被放入购物车。请留意最新订单信息。
	private static final String message_cart="您上架的商品:%s 已被放入购物车。请留意最新订单信息。";
	private static final String message_order="您上架的商品:%s 已被合作伙伴订购，请送货至 %s";
	private static final String message_order_confirmed="您的定单已审核通过，如有疑问请联系我们。详细项如下：%s";
	private static final String message_order_ship="您在平台订购的商品，总价 %d 已经发出，如有误或其他状况 请联系对应商务人员，谢谢您对平台的支持！";

	
	//String SN="SDK-MOV-010-00584"
	//String PWD="425092";
	public void sendSmsAfterCart(String dest,String goodsInfo){
		String str = String.format(message_cart, goodsInfo);
		sendSms(dest,str);
	}
	public void sendSmsAfterOrder(String dest,String goodsInfo,String addr){
		String str = String.format(message_order, goodsInfo,addr);
		sendSms(dest,str);
	}
	public void sendSmsToBuyerAfterConfirm(String dest,String goodsInfo){
		String str = String.format(message_order_confirmed, goodsInfo);
		sendSms(dest,str);
	}
	public void sendSmsToBuyerAfterShip(String dest,int fee){
		String str = String.format(message_order_ship, fee);
		sendSms(dest,str);
	}	
	private void sendSms(String dest,String goodsInfo){
		try {
			String result_mt = new SmsClient( ).mdSmsSend_u(dest, goodsInfo, "", "", "");
			
			System.out.println(result_mt);
			if(result_mt.startsWith("-")||result_mt.equals(""))
			{
				System.out.print("发送失败");
				return;
			}
			else
			{
				System.out.print("发送成功:"+result_mt);
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
	}
	
	/*
	private void sendSms1(String dest,String content) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sn", "SDK-MOV-010-00584");
		paramMap.put("pwd", DigestUtils.md5Hex( SN+PWD) );
		//paramMap.put("pwd",  PWD );
		paramMap.put("mobile", dest);
		paramMap.put("content", content);
		paramMap.put("ext", "1");
		//paramMap.put("rrid", "");
		//paramMap.put("stime", "");
		
//sn=SDK-XXX-XXX-XXXXX&pwd=MD5(SN+PWD)&mobile=135****4752&content=你好&ext=1&rrid=&stime=
		try {
			String paramStr = prepareParam(paramMap);
			System.out.println(paramStr.toString());
			
			 
			URL url = new URL("http://sdk.entinfo.cn:8060/z_mdsmssend.aspx");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(SERVLET_GET);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			
			os.write(paramStr.toString().getBytes("utf-8"));
			os.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			String result = "";
			while ((line = br.readLine()) != null) {
				result += "/n" + line;
			}
			System.out.println(result);
			br.close();
			 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/

	private  String prepareParam(Map<String, String> paramMap) {
		StringBuffer sb = new StringBuffer();
		if (paramMap.isEmpty()) {
			return "";
		} else {
			for (String key : paramMap.keySet()) {
				String value = paramMap.get(key);
				if (sb.length() < 1) {
					sb.append(key).append("=").append(value);
				} else {
					sb.append("&").append(key).append("=").append(value);
				}
			}
			return sb.toString();
		}
	}
/*
 一步是放入购物车 一步是商务过合同:
您上架的商品（型号+数量）已被放入购物车。请留意最新订单信息。
您上架的商品（型号+数量）已被合作伙伴订购，请送货至（地址）（地址自定义）
*/
	public static void main(String[] args) {
		new SmsService().sendSms("13606604712","123一二三");
	}
}
