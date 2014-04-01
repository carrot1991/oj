package com.oj.jxc.commons;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.oj.jxc.entity.OrderItem4Print;
import com.oj.jxc.entity.SUserProfileDO;
import com.oj.jxc.service.SShopOrdService;

public class PdfExport {
	private static Font headfont;// 设置字体大小
	private static Font keyfont;// 设置字体大小
	private static Font textFont;// 设置字体大小
	private static Font textBoldFont;// 设置字体大小
	static BaseFont bfChinese;
	
	List<OrderItem4Print> items;
	SUserProfileDO customer;
	static {
		try {
			// bfChinese =
			// BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			//bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
			//		BaseFont.NOT_EMBEDDED);
			
			bfChinese = BaseFont.createFont("STKAITI.TTF",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			//10,8,8
			headfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
			keyfont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
			textFont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
			textBoldFont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeSimplePdf() throws Exception {
		// 1.新建document对象
		// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("C:\\ITextTest.pdf"));
		// 3.打开文档
		document.open();
		// 4.向文档中添加内容
		// 通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
		document.add(new Paragraph("First page of the document."));
		document.add(new Paragraph(
				"Some more text on the first page with different color and font type.",
				FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,
						new Color(255, 150, 200))));
		
		
		// 5.关闭文档
		document.close();
	}
	
	public PdfPTable test() throws Exception{
		PdfPTable table = new PdfPTable(1);
		Paragraph p = new Paragraph("----",textFont);
		p.setAlignment(Element.ALIGN_CENTER);
		PdfPCell cell=new PdfPCell(p);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		return table;
	}
	public PdfPTable writeContent() throws Exception{
		PdfPTable table = new PdfPTable(6);
		Paragraph p = null;
		PdfPCell cell= null;
		boolean border = true;
		fill(table,border,false,cell,p,0,"产品名称（商品编号）");
		fill(table,border,false,cell,p,0,"配 置（规格）");
		fill(table,border,false,cell,p,0,"单价");
		fill(table,border,false,cell,p,0,"数量");
		fill(table,border,false,cell,p,0,"金额");
		fill(table,border,false,cell,p,0,"交货日期");
 
		int total = 0;
		String beizhu="";
		for( OrderItem4Print s : items){
			fill(table,border,false,cell,p,0, s.getNickname());
			fill(table,border,false,cell,p,0, "");
			fill(table,border,false,cell,p,0, String.valueOf( s.getPrice() ));
			fill(table,border,false,cell,p,0, String.valueOf( s.getAmount() ));
			fill(table,border,false,cell,p,0, "");
			fill(table,border,false,cell,p,0, "");
			total+=s.getPrice()*s.getAmount();
			beizhu=s.getBeizhu();
		}
		
		//for(int i=0;i<5;i++){
		//	blank(table,6);
		//}
		
		fill(table,border,false,cell,p,0,"金 额 小 计");
		fill(table,border,false,cell,p,0,""+total);
		blank(table,4);
		
		String jiner;
		
		fill(table,border,false,cell,p,0,"金 额 总 计");
		jiner = new CnUpperCaser(total+"").getCnString();
		fill(table,border,false,cell,p,0,"（大写）"+jiner+"圆整",2,0);
		fill(table,border,false,cell,p,0,"￥:            ",3,0);
		 
		fill(table,border,false,cell,p,0,"占用奖励金额");
		 
		fill(table,border,false,cell,p,0,"（大写）                                    圆整",2,0);
		fill(table,border,false,cell,p,0,"￥:            ",3,0);
		 
		
		fill(table,border,false,cell,p,0,"客户编码");
		fill(table,border,false,cell,p,0,customer.getEnname(),5,0);
		fill(table,border,false,cell,p,0,"备注");
		fill(table,border,false,cell,p,0,beizhu,5,0);
		
		return table;
	}
	public   PdfPTable writeFooter() throws Exception{
		PdfPTable table = new PdfPTable(1);
		Paragraph p = null;
		PdfPCell cell= null;
		boolean border = false;
		fill(table,border,false,cell,p,0," ");
		fill(table,border,false,cell,p,0,"一、结算方式：__________________________________________________________________");
		fill(table,border,false,cell,p,0,"二、付款方式：□现金     □支票");
		fill(table,border,false,cell,p,0,"三、运输方式：□铁路     □公路    □快运    □其它     ，运输费用由              承担。");
		fill(table,border,false,cell,p,0,"       是否运输保险：  □是     □否   （保险费用由           承担）");
		fill(table,border,false,cell,p,0,"四、违约责任：乙方若逾期支付货款，应向甲方支付滞纳金。乙方每推迟 一 天付款，需向甲方支付   0.1 %的滞纳金。货款未付清之前，货物归供货方所有。本合同货款支付的责任由乙方具体承办的订货人个人和订货单位承担无限连带责任。");							
		fill(table,border,false,cell,p,0,"五、验收标准、方法及提出异议期限：乙方收到货物，对质量、数量如有异议，应在 一  日内向甲方书面提出。甲方销售商品，自发货之日起7个自然日内，按国家标准执行退换货规则。");
		fill(table,border,false,cell,p,0,"六、本合同必须填写清楚、完整。合同签订盖章后，具有法律约束力。任何一方不得擅自修改或终止，如需要修改或终止的，应经双方协商同意后，签订修改或终止合同协议书。");
		fill(table,border,false,cell,p,0,"七、如需提供担保，另行订立合同担保书作为合同附件。");
		fill(table,border,false,cell,p,0,"八、甲乙双方如发生争议，应协商解决，如协商不成，可向合同签订地的人民法院诉讼解决。");
		fill(table,border,false,cell,p,0,"九、本合同一式两份，传真件与原件，都具有同等法律效力。");
		fill(table,border,false,cell,p,0,"十、其它约定事项：（如售后服务、保修等）__________________________________________________________");                                    							
		fill(table,border,false,cell,p,0," ");
		
		return table;		
	}
	public   PdfPTable writeItem() throws Exception{
		PdfPTable table = new PdfPTable(2);
		Paragraph p = null;
		PdfPCell cell= null;
		boolean border = true;
		
		fill(table,border,false,cell,p,0,"甲      方");
		fill(table,border,false,cell,p,0,"乙      方");
		fill(table,border,false,cell,p,0,"单位名称（章）：杭州欧嘉科技有限公司");
		fill(table,border,false,cell,p,0,"单位名称（章）：");
		fill(table,border,false,cell,p,0,"单位地址：杭州黄姑山路29号颐高创业大厦810-812室");
		fill(table,border,false,cell,p,0,"单位地址："+customer.getCustaddr());
		fill(table,border,false,cell,p,0,"法定代表人：吕丹丹");
		fill(table,border,false,cell,p,0,"法定代表人："+customer.getContact());
		fill(table,border,false,cell,p,0,"委托代理人：");			
		fill(table,border,false,cell,p,0,"委托代理人：");			
		fill(table,border,false,cell,p,0,"电    话：");		
		fill(table,border,false,cell,p,0,"电    话："+customer.getMobile());		
		fill(table,border,false,cell,p,0,"传    真：0571-56839710");			
		fill(table,border,false,cell,p,0,"传    真：" );			
		fill(table,border,false,cell,p,0,"开户银行：杭州银行西苑支行");			
		fill(table,border,false,cell,p,0,"开户银行："+customer.getCustbank());			
		fill(table,border,false,cell,p,0,"帐    号：09208100012760");			
		fill(table,border,false,cell,p,0,"帐    号："+customer.getCustbankno());			
		fill(table,border,false,cell,p,0,"税    号：330100665237053");			
		fill(table,border,false,cell,p,0,"税    号："+customer.getCusttaxno());			
		fill(table,border,false,cell,p,0,"邮政编码：310012");		
		fill(table,border,false,cell,p,0,"邮政编码： " );		
		return table;	
	}

	private   void blank(PdfPTable table,int no){
		Paragraph p = new Paragraph(" ",textFont);
		PdfPCell cell=new PdfPCell(p);
		for(int i=0;i<no;i++){
			table.addCell(cell);
		}
	}
	private   void  fill(PdfPTable table,boolean border,boolean bold,PdfPCell cell,Paragraph p,int align,String content,int colspan,int rowspan){
 
			p = new Paragraph(content,!bold?textFont:textBoldFont);
			cell=new PdfPCell(p);
			if(align==1){
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			}

			cell.setBorderWidth(border?1:0);
			 
			if(colspan>0){cell.setColspan(colspan);}
			if(rowspan>0){cell.setRowspan(rowspan);}
			
			table.addCell(cell);
		
	}
	private   void fill(PdfPTable table,boolean border,boolean bold,PdfPCell cell,Paragraph p,int align,String content){
		fill(table, border,bold,cell,  p, align, content,0,0);
	}
	public   PdfPTable writeTitle() throws Exception{
		PdfPTable table = new PdfPTable(4);
		Paragraph p = null;
		PdfPCell cell= null;
		boolean border = false;
		boolean bold = true;
		fill(table,border,bold,cell,p,1,"杭州欧嘉科技有限公司",4,0);
		
		border = false;
		bold = false;
		fill(table,border,bold,cell,p,1,"【购销合同】",4,0);
		
		fill(table,border,bold,cell,p,0,"甲方（供货方）：");
		fill(table,border,bold,cell,p,0,"杭州欧嘉科技有限公司");
		fill(table,border,bold,cell,p,0,"合同编号：");
		fill(table,border,bold,cell,p,0,"");
		fill(table,border,bold,cell,p,0,"乙方（订货方）：");
		fill(table,border,bold,cell,p,0, customer.getCustname());
		fill(table,border,bold,cell,p,0,"签订地点：");
		fill(table,border,bold,cell,p,0,"杭州市西湖区");
		
		fill(table,border,bold,cell,p,0,"经甲乙双方协商，乙方向甲方订购下列货物，双方签订本合同并信守以下条款，共同严格履行。",4,0);
		 
		return table;
		
	}
	public   void writePdf(String title, String cont, String createTime,
			String authorName) throws Exception {
		// 1.新建document对象
		// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		// 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("C:\\ITextTest.pdf"));
		// 3.打开文档
		document.open();
		// 4.向文档中添加内容
		// 通过 com.lowagie.text.Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
//		Paragraph pt = new Paragraph("zhong:-" + title, keyfont);// 设置字体样式
//		pt.setAlignment(1);// 设置文字居中 0靠左 1，居中 2，靠右
//		document.add(pt);
//		document.add(new Paragraph("\n"));
//		pt = new Paragraph(createTime + "\t\t\t\t\t\t" + authorName, keyfont);
//		pt.setAlignment(2);
//		document.add(pt);
//		document.add(new Paragraph("\n"));
//		document.add(new Paragraph(createTime + "\t\t\t\t\t\t" + authorName,
//				keyfont));
//		document.add(new Paragraph("\n"));
//		document.add(new Paragraph(
//				"Some more text on the 胜多负少的身份的分公司的风格发的电饭锅的分公司的分公司的的分公司电饭锅是的分公司的风格的分公司的分公司的复合弓好几顿饭发的寡鹄单凫过好地方风格和的发干活的风格和发干活的风格和地方过电饭锅好地方干活的风格和电饭锅好地方干活负少的身份的分公司的风格发的电饭锅的分公司的分公司的的分公司电饭锅是的分公司的风格的分公司的分公司的复合弓好几顿饭发的寡鹄单凫过好地方风格和的发干活的风格和发干活的风格和地方过电饭锅好地方干活的风格和电饭锅好地方干活负少的身份的分公司的风格发的电饭锅的分公司的分公司的的分公司电饭锅是的分公司的风格的分公司的分公司的复合弓好几顿饭发的寡鹄单凫过好地方风格和的发干活的风格和发干活的风格和地方过电饭锅好地方干活的风格和电饭锅好地方干活的风格和符合斯蒂夫 first page with different color andsdfsadfffffffffffffffffffffffffff font type.",
//				keyfont));
		
		document.add(writeTitle());
		document.add(writeContent());
		document.add(writeFooter());
		document.add(writeItem());
		
		// 5.关闭文档
		document.close();
	}

	/**
	 * 输出成字节流，准备送向前端下载
	 * @return
	 * @throws Exception
	 */
	public   ByteArrayOutputStream write(String ordno,SShopOrdService ordService) throws Exception {
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		
		PdfWriter writer = PdfWriter.getInstance(document,bao);
//				new FileOutputStream("C:\\ITextTest.pdf"));
		
		Object[] obj = ordService.getOrd4Print(ordno);
		items = (List<OrderItem4Print>)obj[0];
		customer = (SUserProfileDO)obj[1];
		
		document.open();
		document.add(writeTitle());
		document.add(writeContent());
		document.add(writeFooter());
		document.add(writeItem());
		
		// 5.关闭文档
		document.close();
		return bao;
	}
	public static void main(String[] args) throws Exception {
		//System.out.println("begin");
		//PdfExport.write();
		//System.out.println("end");
	}

}