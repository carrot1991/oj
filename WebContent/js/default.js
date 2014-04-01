var currentInputText = null;
var currentInputHover=null;
var slNavigation = null;//gis对象

/// <summary>
/// 删除信息接口
/// </summary>
/// <param name="id">唯一编码</param>
/// <param name="type">station:站点，reservoir：水库，disaster:灾害点，dike：堤坝, admin:行政</param>
/// <returns></returns>
function deleteInfo( id,  type){
	 var page = slNavigation.Content.SL;
	 return page.DeleteInfo(id,  type);
}

//gis载入后调用
function onSilverlightLoad(sender) {
	
    slNavigation = sender.getHost();
    
    $("#mainTop").show();
    
    //窗体变换
    $(window).resize(function(){
		$.layerResize();
	});
    
    //密码修改
	$("#password").click(function(){
		var content={type:'iframe',message:'user/pw'};
		showIframe(368,230,'密码修改',content);
	});
	
	//载入导航条
	navigationLaod();
	
	//切换
	 $("#himshide").click(function(){
		 $("#himshide").fadeOut("slow");
		 $(".blockUI:eq(2)").fadeIn("slow");
		 var page = slNavigation.Content.SL;
		 page.LoadModule('basin','');
	 });

}
//关闭弹窗
function closed(){
	$.closeLayer("keepsoft_iframe_");
}

//二级导航载入
function secondNavigationLoad(id,item){
	$.getJSON("aut/list",{parentId:id,roleId:roleId},function(data){
		 if(data==null||data.length==0){
			 item.click(function(){
				 layer.alert("您没有该栏目权限!",'权限提示',false);
        	 });
		 }else{
			 var ul=$('<div class="nav_2" ><div class="top"></div><div class="middle"><ul></ul></div><div class="bottom"></div></div>');
			 $.each(data,function(i2,n2){
				var li=$('<li ><span class="'+n2.authCss+'"></span>'+n2.authName+'</li>') ;
				if(i2==0)
					li.addClass("first");
				if(i2==data.length-1)
					li.addClass("last");
				ul.find("ul").append(li);
				itemClick(li,n2.authType,n2.authFunction,n2.id);
			 });
			 item.append(ul);
			 item.hover(function(){
			       $(this).find(".nav_2").slideDown();
			   },function() {
				   $(this).find(".nav_2").hide();
		    });
		 }
	});
}


//导航载入
function navigationLaod(){
	$.getJSON("aut/list",{parentId:'0'},function(json){
		var navigation=$("#navigation").empty();
		$.each(json,function(i,n){
			var item=$('<div class="nav '+n.authCss+'" val="'+n.authFunction+'"></div>');
			navigation.append(item);
			if(n.authType=='hover'){
				secondNavigationLoad(n.id,item);
			}else{
				itemClick(item,n.authType,n.authFunction,n.id);
			}
		});
		var navlast=navigation.find(".nav:last");
		var mapleft=navlast.position().left+navlast.width()+10;
		var mapnav=$('<div class="nav map mxz" val="mxz" style="left:'+mapleft+'px"></div>');
		navigation.append(mapnav);
		var gdarr=[['c17','西藏地形图','hzgr_map','mxz'],['c14','谷歌地形图','t@130','mdx'],['c15','谷歌遥感图','s@123','myg'],['c16','谷歌交通图','m@203000000','mjt']];
		mapDown(gdarr,mapnav);
	});
}
//导航点击动作
function itemClick(el,type,val,parentId){
	switch(type){
	case 'click':
		system(el,val,parentId);
		break;
	case 'gisClick':
		 loadModule(el,val,parentId)
		break;
	}
}
//载入GIS图层
function loadModule(el,val,parentId){
	var vals=val.split(',');
	el.click(function(){
		$("#himshide").hide();
		$("#parentId").val(parentId);
		var page = slNavigation.Content.SL;
		if(vals.length==2)
		   page.LoadModule(vals[0],vals[1]);
		else
		   page.LoadModule(vals[0],'');
	});
}
//其他载入
function system(el,val,parentId){
	el.click(function(){
		$("#himshide").hide();
		if(val=='userAuthority'){
			getAutForRole(714,425,parentId,0);
		}else if(val=='basedate'){
			getAutForRole(1000,480,parentId,0);
		}else{
			var content={type:'iframe',message:'pages/export/export.jsp'};
			showIframe(714,425,'洪水普查数据导出',content);
		}
	});
}
//底图切换
function loadMap(type){
	var page = slNavigation.Content.SL;
	page.LoadMap(type);
}
//底图切换
function mapDown(gdarr,el){
	var mapul=$('<div class="nav_2" ><div class="top"></div><div class="middle"><ul></ul></div><div class="bottom"></div></div>');
	$.each(gdarr,function(i,n){
		if(n[3]!=el.attr("val")){
			var mapli=$('<li ><span class="'+n[0]+'"></span>'+n[1]+'</li>');
			mapul.find("ul").append(mapli);
			mapli.click(function(){
				loadMap(n[2]);
				el.removeClass(el.attr("val")).addClass(n[3]);
				el.attr("val",n[3]);
				mapDown(gdarr,el);
			});
		}
	});
	el.empty().append(mapul);
	el.find("li:first").addClass("first");
	el.find("li:last").addClass("last");
	el.hover(function(){
	       $(this).find(".nav_2").slideDown();
	   },function() {
		   $(this).find(".nav_2").hide();
    });
}


//风险图与洪水预报切换
function findFXT(type){
	 var page = slNavigation.Content.SL;
     page.LoadModule('risk','risk'+type);
	$("#himshide").fadeIn("slow");
	$(".blockUI").fadeOut("slow");
}

function OneAddOrUpdate( jsonStr,  type){
	var page = slNavigation.Content.SL;
	page.OneAddOrUpdate(jsonStr,type,0);
}


function getAutForRole(w,h,parentId,sid,param){
	 $.getJSON("aut/list",{parentId:parentId,roleId:roleId},function(json){
		 if(json==null||json.length==0){
			 layer.alert("您没有该权限!",'权限提示',false);
		 }else{
			 var content=new Array();
			 var title=new Array();
			 $.each(json,function(i,n){
				 var p={type:'iframe'};
				 title.push(n.authName);
				 if(typeof param=='string')
					 p.message=n.authFunction+"?"+param;
				 else
				     p.message=n.authFunction;
				 content.push(p);
			 });
			 
			 showIframe(w,h,title,content);
		 }
	 });
}



function showHtmlForUrl(n,id,detailId){
	var url='',title='',w=400,h=300,postUrl='',getUrl='',iframeIndex=0,mr=null,type=null;
	switch(n){
	 case '2':url='pages/system/base/area/gdp.html?fresh='+Math.random();
	          title='GDP信息更新';
	          w=940,
	          h=340;
	          postUrl='gdp/save';
	          getUrl='gdp/detail?id='+detailId;break;
	 case '3':url='pages/system/base/area/zaiqing.html?fresh='+Math.random();
	         title='灾情信息更新';
	         w=450;
	         h=330;
	         postUrl='hdis/save';
	         getUrl='hdis/detail?id='+detailId;
	         break;
	 case '4':url='pages/system/base/area/yujing.html?fresh='+Math.random();
	          title='预警信息更新';
	          w=550;
		      h=350;
		      postUrl='staff/save';
		      getUrl='staff/view?id='+detailId;
	          break;
	 case '5':url='pages/system/base/area/yuan.html?fresh='+Math.random();
	          title='预案信息更新';
	          w=450;
		      h=300;
		      postUrl='plan/save';
		      getUrl='plan/view?id='+detailId;
	          break;
	 case '6':url='pages/system/base/reservoir/update.html?fresh='+Math.random();
	     title='水库信息更新';
	     w=940;
	     h=500;
	     postUrl='reservoir/syssave';
	     getUrl='reservoir/detail?id='+detailId;
	     type="reservoir";
	     iframeIndex=1;
     break;
		 case '7':url='pages/system/base/dike/update.html?fresh='+Math.random();
	     title='堤坝信息更新';
	     w=740;
	     h=400;
	     postUrl='dike/syssave';
	     getUrl='dike/detail?id='+detailId;
	     type="dike";
	     iframeIndex=2;
	 break;
		 case '8':url='pages/system/base/rivers/update.html?fresh='+Math.random();
	     title='河流信息更新';
	     w=740;
	     h=400;
	     postUrl='rv/syssave';
	     getUrl='rv/detail?rccd='+detailId;
	     iframeIndex=3;
	 break;
		 case '9':url='pages/system/base/static/update.html?fresh='+Math.random();
	     title='雨量站信息更新';
	     w=880;
	     h=400;
	     postUrl='sti/syssave';
	     getUrl='sti/info?id='+detailId;
	     mr='PP';
	     iframeIndex=4;
	     type="station";
	 break;
		 case '10':url='pages/system/base/static/update.html?fresh='+Math.random();
	     title='水文站信息更新';
	     w=880;
	     h=360;
	     postUrl='sti/syssave';
	     mr='ZZ';
	     getUrl='sti/info?id='+detailId;
	     type="station";
	     iframeIndex=5;
	 break;
		 case '11':url='pages/system/base/static/update.html?fresh='+Math.random();
	     title='气象站信息更新';
	     w=880;
	     h=360;
	     postUrl='sti/syssave';
	     mr='MM';
	     getUrl='sti/info?id='+detailId;
	     type="station";
	     iframeIndex=6;
	 break;
		 case '12':url='pages/system/base/zaihai/update.html?fresh='+Math.random();
	     title='灾害点信息更新';
	     w=580;
	     h=480;
	     postUrl='disaster/sysSave';
	     getUrl='disaster/view?id='+detailId;
	     iframeIndex=7;
	     type="disaster";
	 break;
    }
	$.layer({id:'keepsoft_tooltip',
	     type:1,
	     width:w,
         height:h,
         zIndex:500000,
	     title:{name:title},
	     content:{borderWidth:1,type:'asyn',message:url,callBack:function(){
	    	 $("#keepsoft_tooltip .num").keyup(function(){$(this).clearNoNum('0');});
	    	 $("#keepsoft_tooltip .float").keyup(function(){$(this).clearNoNum('1');});
	    	 $("#keepsoft_tooltip #adcd").val(id);
	    	 $("tr[show='"+n+"']").show();
	    	 $(".v[mr]").attr("mr",mr);
	    	 $(".v[hover]").each(function(){
    			 $(this).keyup(function(event){
    				  var $target = $(event.target);
    				  var position=$target.position();
    				 currentInputText=$(this);
    				 currentInputHover=$(this).attr("hoverId");
    				 $("#"+currentInputHover).css({left:position.left+"px",top:(position.top+20)+"px"});
    				 var e1=currentInputText.attr("e1");
    				 var e2=currentInputText.attr("e2");
    				 var funStr=$(this).attr("hover")+"('"+$(this).val()+"','"+currentInputHover+"','"+e1+"','"+e2+"')";
	    			 jQuery.globalEval(funStr);
	    			
    			 });
    			 $(this).focus(function(){
    				 var funStr=$(this).attr("hover")+"('"+$(this).val()+"','"+$(this).attr("hoverId")+"')";
	    			 jQuery.globalEval(funStr);
    		      });

    			 $(document).bind('mousedown',function(event){
    				  var $target = $(event.target);
    				  var sss=$('#'+currentInputHover);
    				  if((!($target.parents().andSelf().is(sss))) && (!$target.is(currentInputText))){
    					  sss.hide();
    				  }
    				
    			 });
    		 });
	    	 
	    	 if(typeof detailId!='undefined'){
	    		 setFormValue(getUrl,id);
	    	 }else{
	    		 $(".v[load]").each(function(){
	 	    		var el=$(this);
	 	    		var funStr=el.attr("load")+"('"+id+"')";
	 	    		jQuery.globalEval(funStr);
	 	    	 });
	    		
	 	    	 
	    	 }
	    	 $("#keepsoft_tooltip #htmlSave").click(function(){
	    		    var file=$(this).attr("file");
	    		    var values=$(".v").getFormValue();
	    		    layer.load("信息正在保存中...");
	    		    if(typeof file=='string'){
	    		    	$.ajaxFileUpload
	    				({
	    						url:postUrl, 
	    						secureuri:false,
	    						fileElementId:file,
	    						data:values,
	    						dataType: 'json',
	    						success: function (data, status)
	    						{
	    							if(data.success=='success'){
	    								
	    								
	    								 var _iframe=$(window.document).contents().find("#layerIframe"+iframeIndex)[0];
	    			    				  layer.alert({text:title+'保存成功！',fun:function(){
	    			    					  $.closeLayer("keepsoft_tooltip");
	    			    					   _iframe.contentWindow.selected(n,id);
	    			    				  }},'成功提示',true,300,140,1000000111);
	    							}else{
	    								layer.alert(data.success,'失败提示',false,300,140,1000000111);
	    							}
	    							layer.removeLaod();
	    						},
	    						error: function (data, status, e)
	    						{
	    							layer.alert(e,'失败提示',false,300,140,1000000111);
	    							layer.removeLaod();
	    						}
	    					})
	    		    	return;
	    		    }
	    		    
	    		   
	    			$.post(postUrl,values,function(data){
	    				if(data.success=='success'){
	    		
	    					if(type=="station"){
								$.getJSON("api/gis/getStation",{stcd:values.stcd},function(data){
									var jsonStr=JSON.stringify(data);
									OneAddOrUpdate( jsonStr,  type)
								});
							}
							if(type=="reservoir"){
								$.getJSON("api/gis/getReservoir",{rscd:values.rscd},function(data){
									var jsonStr=JSON.stringify(data);
									OneAddOrUpdate( jsonStr,  type)
								});
							}
							
							if(type=="disaster"){
								$.getJSON("api/gis/getDisaster",{disasterId:values.disasterId},function(data){
									var jsonStr=JSON.stringify(data);
									OneAddOrUpdate( jsonStr,  type)
								});
							}
							
							if(type=="dike"){
								$.getJSON("api/gis/getDam",{code:values.code},function(data){
									var jsonStr=JSON.stringify(data);
									OneAddOrUpdate( jsonStr,  type)
								});
							}
	    					
	    					 var _iframe=$(window.document).contents().find("#layerIframe"+iframeIndex)[0];
		    				  layer.alert({text:title+'保存成功！',fun:function(){
		    					  $.closeLayer("keepsoft_tooltip");
		    					  layer.removeLaod();
		    					   _iframe.contentWindow.selected(n,id);
		    					
		    				  }},'成功提示',true,300,140,1000000111);
		    				 
	    				}
	    				else{
	    					
	    					layer.alert({text:data.success,fun:function(){
	    						layer.removeLaod();
	    					}},'失败提示',false,300,140,1000000111);
	    					
	    				}
	    				   
	    			});
	    	 });
	     }}
    });
}

function getDepartment(id,sid){
	var el=$("#depid");
	el.empty().append("<option value=''>请选择部门信息</option>");
	$.getJSON("dept/list",{adcd:id},function(data){
		if(data==null) return;
		$.each(data,function(i,n){
			el.append("<option value='"+n.id+"' "+(sid==n.id?"selected":"")+">"+n.name+"</option>");
		});
	});
	
}
function getSttName(id){
	var code=$("#sttName").attr("mr");
	$.get("sti/typeId",{code:code},function(data){
		$("#sttName").val(data);
	});
}
function getDicItemList(el,type,sid){
	$.getJSON("dici/list",{type:type},function(data){
		if(data==null) return;
		$.each(data,function(i,n){
			el.append("<option value='"+n.id+"' "+(sid==n.id?"selected":"")+">"+n.value+"</option>");
		});
	});
}

function getDambody(id,sid){
	var el=$("#dambodyType");
	el.empty().append("<option value=''>请选择坝体类型</option>");
	getDicItemList(el,'dambody',sid);
}

function getFrgrd(id,sid){
	var el=$("#frgrdType");
	el.empty().append("<option value=''>请选择报汛站等级</option>");
	getDicItemList(el,'frgrd',sid);
}

function getDikeType(id,sid){
	var el=$("#dikeTypeBind");
	el.empty().append("<option value=''>请选择堤坝（段）类型</option>");
	getDicItemList(el,'dikeType',sid);
}

function getRivers(v,h,e1,e2){
	$.getJSON("rv/mapsearch",{keyword:v},function(data){
		var table=$("#"+h).find("table").empty();
		if(data==null){
			$("#"+h).hide();
			
		}else{
			for(var i in data){
				var tr=$("<tr v=\""+i+"\" n=\""+data[i]+"\" class=\"ml\"><td >"+data[i].replace(v,"<b>"+v+"</b>")+"</td></tr>");
				table.append(tr);
			}
			$("#"+h).show();
		}
		
		$("#"+h).find("tr").hover(function(){
			$(this).addClass("bg");
		},function(){
			$(this).removeClass("bg");
		}).click(function(){
			$("#"+e1).val($(this).attr("v"));
			$("#"+e2).val($(this).attr("n"));
			$("#"+h).hide();
			
		});
	});
	
	
}





