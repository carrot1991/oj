/*!
 * @Name : layer v1.0.0.1 弹层组件开发版
 * @author: zhangxiaodong
 * @date: 2013-05-28		
 */
;void function(window, undefined){	
layerRun = function(){
var mode = document.documentMode || 0;
var ie6 =  /MSIE 6.0/.test(navigator.userAgent) && !mode;
var txtwidth=function(txt,size){
	var size=$.isNumeric(size)?size:12;
	var span=$('<span style="font-size:'+size+'px;display:none;">'+txt+'</span>');
	$('body').append(span);
	var width=span.width();
	span.remove();
	return width;
};
/*加载css路径*/
var $ = jQuery, w = $(window), ready = {
   global: {
		iE6 : !-[1,] && !window.XMLHttpRequest,
		times : 0
	}
  };

window['layer'] = {
	load:function(text,w,h,zIndex){
		w=$.isNumeric(w)?w:200;
		h=$.isNumeric(h)?h:50;
		zIndex=$.isNumeric(zIndex)?zIndex:22222222;
		$.layer({
			id:'keepsoft_loading',
			type : 3,
			content : text,
			width : w,//宽度
			height: h,
			zIndex:zIndex
		}); 
		return;
	},removeLaod:function(){
		$.closeLayer('keepsoft_loading');
		return;
	},alert:function(content,title,isbool,w,h,zIndex){
		w=$.isNumeric(w)?w:300;
		h=$.isNumeric(h)?h:140;
		var con={};
		var closeFun=null;
		if (typeof content=='string') con.text=content;
		else {con=content;}
		con.icon=isbool?'tipsucess':'tiperr';
		con.cancel=false;
		layer.dialog(con,title,w,h,zIndex);
	},confirm:function(content,title,w,h){
		w=$.isNumeric(w)?w:360;
		h=$.isNumeric(h)?h:130;
		content.cancel=true;
		content.icon='tip';
		layer.dialog(content,title,w,h);
	},dialog:function(content,name,w,h,zIndex) {
		var title={};
		var tw=txtwidth(content.text);
		if(tw>w-80) content.text="<div style='line-height:20px;'>"+content.text+"</div>"; 
		content.tw=w-80;
		content.bh=31;
		content.borderWidth=1;
		title.name=name;
		zIndex=$.isNumeric(zIndex)?zIndex:2222222;
		$.layer({
			 id:'keepsoft_dialog',
			 title:title,
			 width : w,
			 height: h,
			 content:content,
			 zIndex:zIndex
		});
	}
};
var Class = function(options,el){	
	ready.global.times++;
	this.el=el;
	this.full= (el == window);
	this.index = ready.global.times;
	var config = this.config;
	if(!$.isNumeric(options.height)) options.height=$(window).innerHeight();
	if(!$.isNumeric(options.width)) options.width=$(window).innerWidth();
	this.config = $.extend({} , config , options);
	this.title= $.extend({} , config.title , options.title);
	this.overlay=$.extend({} , config.overlay , options.overlay);
	this.content= $.extend({} , config.content , options.content);
	this.addlayer();
};

Class.gr = Class.prototype;

/*默认配置*/
Class.gr.config = {
	width : 450,//宽度
	height: 330,//高度
	left:null,
	top:null,
	id:'keepsoft',//标识，唯一
	type:0,//弹窗类型
	title:{name:'系统提示',height:29,radius:3},
	overlay:{show:true,css:{backgroundColor: '#000',opacity:0.6,cursor:'wait'}} ,//是否显示 遮罩
	moveLayer:true,//窗体是否移动
	zIndex:9999,
	content:null,
	closefun:null //关闭时调用
};

Class.gr.type = ['dialog', 'single','tabs', 'loading','tips'];

/*生成容器*/
Class.gr.space = function(){
	var othis=this, times = this.index,config = this.config,title=this.title,content=this.content;
	this.zIndex = config.zIndex + times;
	var zIndex=this.zIndex;
	var overlay='';
	if(this.overlay.show){
		overlay=$('<div id="'+config.id+'_shade" style="z-index:'+zIndex+'" class="keepfoft_overlay"></div>');
		overlay.css(this.overlay.css);
	}
	var boxhtml=$('<div id="'+config.id+'" class="keepfoft_layer"></div>');
	boxhtml.css({"width":config.width+"px","height":config.height+"px","z-index":zIndex+times});
	
	if(config.type==3){
		this.loading(boxhtml);
		return [overlay,boxhtml];
	}
	var th=0;
	if(title!=null) th=this.titleLayer(boxhtml);//是否显示标题
	if(config.type==0){
	    this.dialog(boxhtml,th);
	    return [overlay,boxhtml];
	}
	
	if(config.type==2){
		this.tabs(boxhtml,th);
		return [overlay,boxhtml];
	}
	
	
	if(config.type==1){
		var bodyheight=config.height-th;
		var bodywidth=config.width-content.borderWidth;
		var layerContent=$('<div class="layerContent" style="height:'+bodyheight+'px;width:'+bodywidth+'px"></div>');
		this.appendContent(layerContent);
		boxhtml.append(layerContent);
		return [overlay,boxhtml];
	}
};

Class.gr.tabs=function(boxhtml,th){
	var othis=this,config = this.config,content=this.content;
	var bodyheight=config.height-th;
	var bodywidth=config.width-content.borderWidth;
	var layerContent=$('<div class="layerContent" style="height:'+bodyheight+'px;width:'+bodywidth+'px"></div>');
	$.each(content.array,function(i,n){
		var layerItem=$('<div id="layerItem_'+i+'" class="layerItem" style="width:'+bodywidth+'px;height:'+bodyheight+'px;display:none"></div>');
		if(i==0) {
			layerItem.show();
			othis.appendContent(layerItem,i);
		}
		layerContent.append(layerItem);
	});
	boxhtml.append(layerContent);
};
/*载入条时使用*/
Class.gr.loading=function(boxhtml){
	var config = this.config,content=this.content;
	layerContent=$('<div class="layerContent" style="width:'+config.width+'px;height:'+config.height+'px"><div class="subwrap"><div class="layertext"></div></div></div>');
	layerContent.find(".layertext").append("<span class=\"loadicon\"></span><span>"+config.content+"</span>");
	boxhtml.append(layerContent);
};
/*alert,confirm等类似内容，type==0时调用*/
Class.gr.dialog=function(boxhtml,th){
	var othis=this,config = this.config,content=this.content;
	var bodyheight=config.height-content.bh-th;
	var bodywidth=config.width-content.borderWidth;
	var layerContent=$('<div class="layerContent" style="height:'+bodyheight+'px;width:'+bodywidth+'px"></div>');
	layerContent.append('<div class="subwrap"><div class="layertext"><span class="tipicon '+content.icon+'"></span><span class="tipcontent" style="width: '+content.tw+'px;">'+content.text+'</span></div></div>');
	var tooltipbtnwrap=$('<div class="tooltipbtnwrap" style="width:'+(bodywidth-15)+'px"></div>');	
	if(content.cancel){
		var qx=$('<span class="qx"></span>');
		tooltipbtnwrap.append(qx);
		qx.click(function(){
			othis.closed(config.id);
		});
	}
	var qd=$('<span class="qd"></span>');
	tooltipbtnwrap.append(qd);
	qd.click(function(){
		if(jQuery.isFunction(content.fun)) (content.fun).call(this);
		othis.closed(config.id);
	});
	boxhtml.append(layerContent);
	boxhtml.append(tooltipbtnwrap);
};
/*标题 title!=null的时候调用*/
Class.gr.titleLayer=function(boxhtml){
	var othis=this, config = this.config,title=this.title;
	var wrapTitle=$('<div class="wrapTitle"><div class="layerright"></div><div class="layerleft"></div></div>');
	var closed=$('<div class="closed"></div>');
	wrapTitle.append(closed);
	closed.click(function(){
		othis.closed(config.id);
	});
	var th=title.height;
	if(typeof title.name=='string'){
		var name=othis.titleItem(title.name);
		wrapTitle.append(name);
	
	}else if($.isArray(title.name)){
		$.each(title.name,function(i,n){
			var name=othis.titleItem(n);
			wrapTitle.append(name);
			name.click(function(){
				wrapTitle.find(".selected").removeClass("selected");
				$(this).addClass("selected");
				othis.appendContent(boxhtml.find("#layerItem_"+i),i);
				boxhtml.find(".layerItem").hide();
				boxhtml.find("#layerItem_"+i).show();
			});
		});
		wrapTitle.find(".layertitle:first").addClass("selected");
	}
	boxhtml.append(wrapTitle);
	return th;
};
Class.gr.appendContent=function(layerItem,i){
	var arr=$.isNumeric(i)?this.content.array[i]:this.content;
	switch(arr.type){
	case 'asyn':
		layerItem.addClass('keepfoft_iframe_load');
		layerItem.load(arr.message, function() {
			var _fun=arr.callBack;
			if(jQuery.isFunction(_fun)){
				_fun.call(this);
			}else if(typeof _fun=='string'){
				if(_fun.indexOf("(")==-1) _fun+='()';
				eval(_fun);
			}
			layerItem.removeClass('keepfoft_iframe_load');
		});
		break;
	case 'html':
		layerItem.empty().append(arr.message);
		break;
	case 'iframe':
		var iframe=$('<iframe src="'+arr.message+'" onload="javascript:$(\'#layerIframe'+i+'\').removeClass(\'keepfoft_iframe_load\');" id="layerIframe'+i+'" frameborder="0" width="'+layerItem.css('width')+'" marginwidth="0" marginheight="0"  height="'+(layerItem.height()-1)+'px" name="layerIframe'+i+'" scrolling="yes"></iframe>');
		iframe.addClass('keepfoft_iframe_load');
		layerItem.empty().append($(iframe));
		break;
	}
};
/*添加layer*/
Class.gr.addlayer = function(){
	var othis = this,space=this.space(),config = this.config,content=this.content;
	var overlay=space[0];
	var layerUI=space[1];
	
	this.center(layerUI);
	$("body").append(overlay);
	$("body").append(layerUI);
	if(config.moveLayer&&(!ie6)){
		var _w=0;
		var _titleWrap=$("#"+config.id+" .wrapTitle");
		$("#"+config.id+" .wrapTitle").children().each(function(){
			_w=_w+$(this).outerWidth(true);
		});
		_w=_titleWrap.width()-_w-4;
		var moveTitle=$("<div style=\"cursor: move;height:"+_titleWrap.height()+"px;width:"+_w+"px;float:left\"></div>");
		_titleWrap.append(moveTitle);
		othis.move(moveTitle);
	}
	if (ie6){
		overlay.height(document.body.scrollTop+window.screen.height);
		var _ieTop =  layerUI.offset().top;
		var ie6Top = function(){
			layerUI.css({top : $(document).scrollTop() + _ieTop});
		};
		var titileIE6=layerUI.find(".layertitle");
		for(var i=0;i<titileIE6.length;i++){
			var itemIE6=layerUI.find(".layertitle:eq("+i+")");
			var textIE6=itemIE6.text();
			itemIE6.empty().append(textIE6);
		}
		ie6Top();
		w.scroll(ie6Top);
		DD_belatedPNG.fix('.wrapTitle .closed,.wrapTitle .layerleft,.wrapTitle .layerright,.tipicon');
	}
};
Class.gr.center=function(el){
	var config = this.config;
	var l =$.isNumeric(config.left)?config.left:(($(window).innerWidth() - config.width)/2);
	var t =$.isNumeric(config.top)?config.top: (($(window).innerHeight() - config.height)/2);
	el.css("left",(l > 0 ? (l+'px') : '0')); 
	el.css("top",(t > 0 ? (t+'px') : '0'));
};
Class.gr.titleItem=function(name){
	var layertitle=$('<div class="layertitle"></div>');
	if(this.title.radius==0){
		layertitle.append(name);
	}else{
		for(var i=1;i<this.title.radius;i++){
			layertitle.append("<div class='r_"+i+"'></div>");
		}
		layertitle.append("<div class='r_"+this.title.radius+"'>"+name+"</div>");
	}
	return layertitle;
};
Class.gr.move=function(movediv){
	var config = this.config;
	var layerE, ismove;
    var moveX, moveY, move, setY = 0;
    var w = $(window);

    movediv.on('mousedown', function(M){
       	M.preventDefault();
       	ismove = true;
       	layerE = $("#"+config.id);
       	var xx = layerE.offset().left, yy = layerE.offset().top, ww = layerE.width() - 6, hh = layerE.height() - 6;
       	if(!$('#gr_moves')[0]){
       		$('body').append('<div id="gr_moves" class="keepfoft_moves" style="left:'+ xx +'px; top:'+ yy +'px; width:'+ ww +'px; height:'+ hh +'px; z-index:3000000000"></div>');
       	}
       	move = $('#gr_moves');
       	moveX = M.pageX - move.position().left;
			moveY = M.pageY - move.position().top;
			layerE.css('position') !== 'fixed' || (setY = w.scrollTop());
       });
    $(document).mousemove(function(M){			
   		if(ismove){
   			M.preventDefault();
   			var x = M.pageX - moveX;
   			if(layerE.css('position') === 'fixed'){
   				var y = M.pageY - moveY;	
   			}else{
   				var y = M.pageY - moveY;	
   			}
   			move.css({left: x, top: y});									
   		}					  						   
   	}).mouseup(function(){
   		if(ismove){
   			if(parseInt(layerE.css('margin-left')) == 0){
   				var lefts = parseInt(move.css('left'));
   			}else{
   				var lefts = parseInt(move.css('left')) + (-parseInt(layerE.css('margin-left')))
   			}
   			layerE.css('position') === 'fixed' || (lefts = lefts - layerE.parent().offset().left);
   			layerE.css({left: lefts, top: parseInt(move.css('top')) - setY});
   			move.remove();
   		}
   		ismove = false;
   	});
    
};
Class.gr.closed=function(id){
	var closefun=this.config.closefun;
	if($.isFunction(closefun)){
		closefun.call(this);
	}
	if($("#"+id+"_shade")) $("#"+id+"_shade").remove();
	$("#"+id).remove();
	
	
};
$.layer = function(options){
	 new Class(options,window);
};
$.closeLayer=function(id){
	if(typeof id=='undefined'){
		$(".keepfoft_overlay").remove();
	    $(".keepfoft_layer").remove();	
		return;
	}
	$("#"+id+"_shade").remove();
    $("#"+id).remove();
 };
};
layerRun();	
}(window);