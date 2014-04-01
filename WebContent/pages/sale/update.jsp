<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息更新</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<link type="text/css" href="js/layer/skin/css.css" rel="stylesheet" />
<%
boolean containTaxMode = false;
com.oj.jxc.commons.LoginedUser loginedUser = (com.oj.jxc.commons.LoginedUser)session.getAttribute("OJ_JXC_USER");
if(loginedUser.getProfile()!=null){
	System.out.println("contains profile");
	if(loginedUser.getProfile().getContaintax()==1){
		System.out.println("contains profile 1" );
		containTaxMode = true;
	}else{
		System.out.println("contains profile 0" );
	}
}
%>

<script type="text/javascript">
var containTaxMode = <%=(containTaxMode?"true":"false")%>;
var typeDictStr='';
var bandDictStr='';

//用户输入的价格打96折，即不含税的价格
function calcPriceIn(value){
	if(!containTaxMode){
		return value;
	}else{
		return  Math.floor(value*0.96);
	}
}
function calcPriceOut(value){
	if(!containTaxMode){
		return value;
	}else{
		return  Math.floor(value/0.96);
	}
}
  $(function(){
	  
	  getCategory();
	  
	  //getType('typeid');
		
	  $("#save,#cancel").toHover('','select');
	  $("#cancel").click(function(){
		  parent.window.closed();
	  });
	  $("#save").click(function(){
          var param={};
          $(".v").each(function(){
        	  if($(this).attr("name")=='price'){
        		param[$(this).attr("name")]= calcPriceIn($(this).val());
        	  }else{
        	  	param[$(this).attr("name")]=$(this).val();
        	  }
          });
          
          //param['typeid']= $('#typeid').val();
         // param['bandid']= $('#bandid').val();
         // var pd =  $('#mypro').val();
          //param['prodid']= pd;
          
          $.post("goods/add",param,function(json){
        	  if(json.success=='success'){
        		  var m={text:'商品信息更新成功！',fun:function(){parent.window.refresh();}}
        		  $.alert(m,'商品信息更新',true);
        	  }else{
        		  $.alert(json.success,'商品信息更新');
        	  }
          });

	  });
  });
  
  function getCategory(){
		$.getJSON("type/findCategory",
				{random: Math.random()},function(jsonstr){
			  if(jsonstr!=null){
				  var str='';
				  str += "<option value='---'>请选择大类</option>";
				  var json=eval(jsonstr);//array
				   
				  for(var i=0;i<json.length;i++){
					  d = json[i];
					  str += "<option  value="+d.id+">"+d.title+"</option>";
				  }
				  $('#cateid').html(str); 
			  }
		  });
	}
  
  function getType(cateid){
		$.getJSON("type/findType",
				{random: Math.random(),
			'cateid':cateid
			},function(jsonstr){
			  if(jsonstr!=null){
				  var str='';
				  str += "<option value='---'>请选择大类</option>";
				  var json=eval(jsonstr);//array
				   
				  for(var i=0;i<json.length;i++){
					  d = json[i];
					  str += "<option  value="+d.typeid+">"+d.title+"</option>";
				  }
				  $('#typeid').html(str);
			  }
		  });
	}

	 
	function getBandByType(tid){
		$.getJSON("band/findBandByType",{random: Math.random(),typeid:tid},function(jsonstr){
			  if(jsonstr!=null){
				  var str='';
				  str += "<option value='---'>请选择品牌</option>";
				  var json=eval(jsonstr);//array
				  for(var i=0;i<json.length;i++){
					  d = json[i];
					  str += "<option value="+d.bandid+">"+d.title+"</option>";
				  }
				  $('#bandid').html(str);
			  }
		});
	}

	function getProdByBand(bid){
		
			$.getJSON("proddef/findDefByBandForSale",{'bandid':bid,random: Math.random()},function(jsonstr){
				  if(jsonstr!=null){
					  var str='';
					  str += "<option value='---'>请选择产品</option>";
					  var json=eval(jsonstr);//array
				  	  for(var i=0;i<json.length;i++){
				  		  d = json[i];
						  str += "<option value='"+d.id+"'>"+d.nickname+"</option>";
					  }
					  $('#prodid').html(str);
				  }
			  });	
	}
</script>
<style type="text/css">
   body,p,table,p,img {margin:0; padding:0;font-family:"宋体",Verdana, Arial,Sans; font-size:12px;}
   ul,li{margin:0; padding:0; list-style:none;}
   table,td {border-collapse:collapse;empty-cells:show;}
   body{background: #f3f3f3;}
   .div{padding-top: 15px;}
   li{padding:5px 0;}
   li span{width: 120px;float:left;text-align: right;padding-top: 5px;}
   li i{font-style: normal;float:left;padding-top: 2px;}
   li i input{margin-top: 5px;}
   .div #save{width:58px;height: 25px;background:url("js/layer/skin/images/icon.png") no-repeat 0 -214px;margin-left: 120px;cursor: pointer;float:left;  }
   .div #save.select{background-position:0 -239px;}
   .div #cancel{width:58px;height: 25px;background:url("js/layer/skin/images/icon.png") no-repeat 0 -114px;margin-left: 18px;cursor: pointer;float:left;   }
   .div #cancel.select{background-position:0 -139px;}
</style>
</head>
<body>
    <div class="div">
         <ul>
   
	         <li><span>大类:</span>
			<select id="cateid" name="cateid" class="v" onchange='getType(this.value);'>
		    </select></li>    
	         <li><span>分类:</span>
			<select id="typeid" name="typeid" class="v" onchange='getBandByType(this.value);'>
		    </select></li>
			<li><span>品牌:</span>
			<select id="bandid" name="bandid" class="v" onchange='getProdByBand(this.value);'>
			</select></li>
	         <li><span>产品 :</span>
			<select id="prodid" name="prodid" class="v">
			</select>
			</li>
			
	         <li><span>价格：</span><input class="v" type="text" name="price"  id="price"  />
	        <!--
	         ( (containTaxMode?"含税价":"未含税价")) 
	         --> 
	         </li>
	         <li><span>数量：</span><input class="v" type="text" name="amount" id="amount"  /></li>
	         <!-- 
	         <li><span>名称：</span><input class="v" type="text" name="nickname"  id="nickname"  /></li>
	         <li><span>配置：</span><input class="v" type="text" name="config" id="config"  /></li>
	          -->
         </ul>
     </div>
     <div class="div">
        <div id="save"></div>
        <div id="cancel" ></div>
     </div>
</body>
</html>