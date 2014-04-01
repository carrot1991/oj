/*一些公共方法*/
jQuery.fn.extend({
	 /* hover换样式 */
	 toHover: function(c1,c2) {
		 $(this).hover(function() {
				$(this).removeClass(c1).addClass(c2);
			}, function() {
				$(this).removeClass(c2).addClass(c1);
			});
	  },
	  clearNoNum:function(type){
			var el=this;
			if(type=='1'){
				 el.val(el.val().replace(/[^\d.]/g,""));//先把非数字的都替换掉，除了数字和.
				 el.val(el.val().replace(/^\./g,"")); //必须保证第一个为数字而不是.
				 el.val(el.val().replace(/\.{2,}/g,".")); //保证只有出现一个.而没有多个.
				 el.val(el.val().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); //保证.只出现一次，而不能出现两次以上
				 return;
			}
			el.val(el.val().replace(/[^\d]/g,""));//先把非数字的都替换掉，除了数字
			if(el.val().length>9){
				 el.val(el.val().substring(0,8));
			}
			return;
		},
	  getFormValue:function(){
			var value={},othis=this;
			othis.each(function(){
				var name=$(this).attr("name");
				if($(this).attr("type")=="checkbox"){
					var arr=new Array();
					$("input[name='"+name+"']:checked").each(function(){
						arr.push($(this).val());
					});
					if(arr.length>0) value[name]=String(arr);
				}else if($(this).attr("type")=="radio"){
					value[name]=$("input[name='"+name+"']:checked").val();
				}else{
					var v=$(this).val();
					if($.trim(v)!='') value[name]=v;
				}
			});
			return value;
	 },	/**
		 * 给Input赋值
		 * @param json 数据
		 * @param fun  调用方法
		 */
		setFormValue:function(json,fun){
			$(this).each(function(){
				var name=$(this).attr("name");
				var value=json[name]==null?"":json[name];
				$(this).val(value+'');
			});
			if(jQuery.isFunction(fun)) (fun).call(this);
		}
});
$.extend({
	query:{
		get:function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);  //匹配目标参数
			if (r!=null) return unescape(r[2]); return null; //返回参数值
		}
	},
	floatW:function(n,v){
		var s=String(n).split('.');
		if(s.length<=1)
			return true;
		if(s[1].length<=6)
			return true;
		else 
			return false;
	},
	toWidth:function(){
	  return window.innerWidth||document.documentElement.clientWidth;
	},
	toHeight:function(){
		  return window.innerHeight||document.documentElement.clientHeight;
	},
	loadA:function(content,w){
		var sw='';
		
		if(typeof w=='number')
			sw='width:'+w+'px';
		var html='<div id="load" style="border: solid 3px #939393;line-height: 60px;font-size: 14px;background: #fff; text-align: left;'+sw+'"><div style="width: 32px;height: 32px;float: left;background: url(\'theme/images/load.gif\') no-repeat left center ;margin: 15px 0 15px 10px ;padding-right: 5px;"></div>'+content+'</div>';
		
		$.blockUI({message:html});
	 },
	 alert:function(content,title,isbool,w,h){
		var icon=isbool?'tipsucess':'tiperr';
		 w=typeof w=='string'?w:300;
		 h=(typeof h=='string'?h:100);
		 var txt=typeof content=='string'?content:content.text;
		 var span=$('<span style="font-size:12px;">'+txt+'</span>');
		 $('body').append(span);
		 var textw=span.width();
		 if(textw>w-80)
			 txt="<div style='line-height:20px;'>"+txt+"</div>"; 
		 span.remove();
		 if(typeof content=='string')
			 content=txt;
		 else
			 content.text=txt;
		 $.tipLayer('alert',content,title,w,h,icon);
	 },
	 confirm:function(content,title,w,h){
		 w=typeof w=='string'?w:360;
		 h=(typeof h=='string'?h:130);
		 var txt=typeof content=='string'?content:content.text;
		 var span=$('<span style="font-size:12px;">'+txt+'</span>');
		 $('body').append(span);
		 var textw=span.width();
		 if(textw>w-80)
			 txt="<div style='line-height:20px;'>"+content+"</div>"; 
		 span.remove();
		 if(typeof content=='string')
			 content=txt;
		 else
			 content.text=txt;
		 $.tipLayer('confirm',content,title,w,h);
	 },
	 tipLayer:function(type,content,title,w,h,icon){
		 var param={content:content,isZY:true,parent:'keepsoft_',type:type,moveLayer:false,width : w,height:h};
		 if(typeof title=='string')
			 param.title=title;
		 if(typeof icon=='string')
			 param.icon=icon; 
		 $.layer(param);
	 },
	 
    clearNoNum:function(e,t,type){
		  var el=$(e);
		
		  if(t=='html'){
			  el.html(el.html().replace(/[^\d.]/g,""));//先把非数字的都替换掉，除了数字和.
			  el.html(el.html().replace(/^\./g,"")); //必须保证第一个为数字而不是.
			  el.html(el.html().replace(/\.{2,}/g,".")); //保证只有出现一个.而没有多个.
			  el.html(el.html().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); //保证.只出现一次，而不能出现两次以上
			  return ;
		  }
		  
		  if(typeof type!='undefined'){
			  el.val(el.val().replace(/[^\d]/g,""));//先把非数字的都替换掉，除了数字和
			 if(el.val().length>9){
				 el.val(el.val().substring(0,8));
			 }
			 return;
		  }
		  
		  el.val(el.val().replace(/[^\d.]/g,""));//先把非数字的都替换掉，除了数字和.
		  el.val(el.val().replace(/^\./g,"")); //必须保证第一个为数字而不是.
		  el.val(el.val().replace(/\.{2,}/g,".")); //保证只有出现一个.而没有多个.
		  el.val(el.val().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); //保证.只出现一次，而不能出现两次以上
	  },
	  dateFormat:function(date,format){
			var obj=new Date(date);
			 var o = {  
				        "M+" : obj.getMonth() + 1, // month  
				        "d+" : obj.getDate(), // day  
				        "h+" : obj.getHours(), // hour  
				        "m+" : obj.getMinutes(), // minute  
				        "s+" : obj.getSeconds(), // second  
				        "q+" : Math.floor((obj.getMonth() + 3) / 3), // quarter  
				        "S" : obj.getMilliseconds()  
				        // millisecond  
				    } ;
			 if (/(y+)/.test(format)) {  
			        format = format.replace(RegExp.$1, (obj.getFullYear() + "").substr(4  - RegExp.$1.length));  
			    }  
			 for (var k in o) {  
			        if (new RegExp("(" + k + ")").test(format)) {  
			            format = format.replace(RegExp.$1, RegExp.$1.length == 1  
			                            ? o[k]  
			                            : ("00" + o[k]).substr(("" + o[k]).length));  
			        }  
			    }  
			return format;
		}
});

function setFormValue(url,areaId,fun){
	$.getJSON(url,function(json){
		$(".v").each(function(){
			var name=$.trim($(this).attr("name"));
			var loadFun=$(this).attr("load");
			var update=$(this).attr("update");
			if(update=='readonly') $(this).attr("readonly",true);
			if(typeof loadFun=='string'){
				var funStr=loadFun+"('"+areaId+"','"+json[name]+"')";
				jQuery.globalEval(funStr);
			}else{
				var value=json[name]==null?"":json[name];
				if($(this).attr("type")=="radio"){
					$("input[name='"+name+"'][value='"+value+"']").prop("checked",true);
				}else if(name=='adcd'){
					
				}else{
					$(this).val(value+'');
				}
				
			}
			
		});
	
	});
}

function showIframe(w,h,titles,urls,b,left){
	var content={borderWidth:6};
	var type=2;
	if($.isArray(urls)){
		content.array=urls;
	}else{
		content.type=urls.type;
		content.message=urls.message;
		type=1;
	}
	var bb=true;
	if(b) bb=false;
	if(!$.isNumeric(left)) left=null;
	$.layer({id:'keepsoft_iframe_',
	     type:type,
	     width:w,
         height:h,
         left:left,
         overlay:{show:bb,css:{backgroundColor: '#000',opacity:0.6,cursor:'wait'}},
	     title:{name:titles},
	     content:content
    }); 
}