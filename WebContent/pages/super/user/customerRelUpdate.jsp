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
<title>用户信息更新</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<link type="text/css" href="js/layer/skin/css.css" rel="stylesheet" />
<script type="text/javascript">
  
var regionDict;

  $(function(){
	  $("#save,#cancel").toHover('','select');
	  $("#cancel").click(function(){
		  parent.window.closed();
	  });
	  
	  $.getJSON("userprofile/getBizForCustomer",{random: Math.random()},function(jsonstr){
		  if(jsonstr!=null){
			  	var str='<select class=v name=bizrel id=bizrel>';
				var json=eval(jsonstr);
				str+="<option  value=>请选择 -----</option>";
				for(d in json){
					//alert(d);  
					str+="<option  value="+json[d]+">"+json[d]+"</option>";
				}
				str+='</select>';
				
				$('#bizList').html(str);
		  }
	  });
	  
	  var id = $.query.get("id");
	  if(id!=''){
	  //alert(uname);
		   //var id=$.query.get("id");
	  //if(id!=null){
		  $.getJSON("userprofile/findById",{random: Math.random(),'id':id},function(jsonstr){
			  if(jsonstr!=null){
				  var json=eval(jsonstr);
				   
				  $(".v").each(function(){
					 var nm=$(this).attr("name");
					 var v=json[nm];
					 
					 $(this).val(v);
					  
				  }); 
				
			  }
		  });
	  } 
	  
	  $("#save").click(function(){
          if($('#bizrel').val()==''){
        	  alert('请选择一个商务人员 先.');
        	  return;
          }
		  var param={};
          $(".v").each(function(){
        	  param[$(this).attr("name")]=$(this).val();
        	  
          });
          $.post("userprofile/updateRel",{'uname':$('#uname').val(),'bizname':$('#bizrel').val()},function(json){
        	  if(json.success=='success'){
        		  $.alert('用户与商务关联更新成功','用户与商务关联',true);
        	  }else{
        		  $.alert(json.success,'用户与商务关联');
        	  }
          });

	  });
  });
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

	         <li><span>名称</span><input class="v" type="text" name="uname"  id="uname"  /></li>
	         
	         <li><span>选择商务人员</span>
              <div id="bizList">
               </div></li>
                    
         </ul>
     </div>
     <div class="div">
        <div id="save"></div>
        <div id="cancel" ></div>
     </div>
</body>
</html>