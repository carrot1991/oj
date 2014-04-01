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
<title>产品限制信息更新</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<link type="text/css" href="js/layer/skin/css.css" rel="stylesheet" />

<%boolean editMode = (request.getParameter("id")!=null); %>

<script type="text/javascript">

var prodDict;

  $(function(){
	  
	  $.getJSON("proddef/getDict",{random: Math.random()},function(json){
		  if(json!=null){
			  prodDict = json;
		  }
		  //for(i in d)  i 就是键，d[i]就是值
		  var str='';
		  for( i in prodDict){
			  str=str+"<option value='"+i+"'>"+prodDict[i]+"</option>";
		  }
		  //alert(str);
		  $("#prodid").html(str);
	  });
	  
	  $("#save,#cancel").toHover('','select');
	  $("#cancel").click(function(){
		  parent.window.closed();
	  });
	  
	  var id=$.query.get("id");
	  var totalStr=$.query.get("totalStr");
	  if(id!=null){
		  $.getJSON("prodLimit/view",{random: Math.random(),id:id},function(json){
			  if(json!=null){
				  $(".v").each(function(){
					 var name=$(this).attr("name");
					 var value=json[name];
					 
					 if($(this).attr("name")=='totalStr'){
						 $(this).val(totalStr);
					 }
					 if($(this).attr("name")=='adjustRate' || $(this).attr("name")=='dayadJustLimit'){
						 $(this).val( bai(value) );
					 }else{
					 	$(this).val(value);
					 }
				  });
			  }
		  });
		  //$("#prodid").attr("readonly",true);
	  }else{
		   
	  }
	  
	  $("#save").click(function(){
          var param={};
          $(".v").each(function(){
        	  if($(this).attr("name")=='adjustRate' || $(this).attr("name")=='dayadJustLimit'){
        		  param[$(this).attr("name")]= xiaoshu($(this).val());
        	  }else{
        	  	param[$(this).attr("name")]=$(this).val();
        	  }
          });
          $.post("prodLimit/save",param,function(json){
        	  if(json.success=='success'){
        		  var m={text:'保存成功！',fun:function(){parent.window.refresh();}}
        		  $.alert(m,'更新',true);
        	  }else{
        		  $.alert(json.success,'信息更新');
        	  }
          });

	  });
  });
   
	//100*row.dayadjustlimit;
	function bai(val){
		return 100*val;
	}
	
	function xiaoshu(val){
		return val/100;
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
  <input type="hidden" class="v" name="id" id="id"  />
    <div class="div">
         <ul>
         <li>
         <%if(editMode){ %>
	          <input class="v" type="hidden" name="prodid"  id="prodid"  />
	     <%}else{ %>
	     	 <span>选择产品:</span>     
	          <select class="v" id="prodid" name="prodid">
	          </select>
	     <%}%>
	         <li><span>价格调整幅度：</span><input class="v" type="text" name="adjustRate"  id="adjustRate"  /> %</li>
	         <li><span>当天价格调整幅度：</span><input class="v" type="text" name="dayadJustLimit" id="dayadJustLimit"  /> %</li>
	         <li><span>数量上限：</span><input class="v" type="text" name="amountlimit" id="amountlimit"  /></li>
         </ul>
     </div>
     <div class="div">
        <div id="save"></div>
        <div id="cancel" ></div>
     </div>
</body>
</html>