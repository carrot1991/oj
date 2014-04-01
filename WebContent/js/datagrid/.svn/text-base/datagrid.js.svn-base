/**
 * Plugin: Jquery.dataGrid Version: 1.1 Beta Update: 2013.03.07
 */
$.fn.extend({
	/**
	 * width:宽度，
	 * headHeight：头部高度
	 * checkbox:是否显示多选框，默认false
	 * isSerialNo:是否显示排序 false,
	 * isPage:是否显示分页
	 * pagetotal:是否显示页码统计
	 * bodyHeight:内容高度
	 * pageHeight:分页高度
	 * rowHeight:,//行高
	 * url:数据源 ,json格式
	 * param:{} 参数
	 * dataId: 主键
	 * pageSize:一页显示条数
	 * pageNo:页码
	 * itemClass:[] 行样式
	 * dataTotal:[],统计
	 * fields:[] 字段
	 * operates:操作
	 */
	defaults : {
		headHeight:25,
		bodyHeight:300,
		pageHeight:30,
		rowHeight:20,
		pleft:3,
		checkbox:false,
		isSerialNo:false,
		isPage:true,
		pagetotal:true,
		dataId:'id',
		pageSize:15,
		pageNo:1,
		result:'result',
		pageAll:true,
		alert:null,
		
		param:{}
	 },
	 dataGrid:function(options){


		 var cache=$(this);
		 var opts= $.extend({} , $(this).defaults , options);
		 if(typeof opts.url=='undefined'){
			 alert("url不能为空！");
			 return;
		 }

		//开始渲染表格====================================================//
		 var myTableId = "keepsoft-table-" + cache.attr('id');
		 cache.wrap("<div id='" + myTableId + "' class='keepsoft-table'></div>");
		 cache.hide();
		 var myTable=$('#'+myTableId);



		//头部===================================================//
        var tableHead=$('<div class="tableHeadArea" style="height:'+opts.headHeight+'px;"></div>');
        myTable.append(tableHead);

        //统计==================================================//
        var dataTotalDiv=null;
        if(typeof opts.dataTotal!='undefined'){
        	 dataTotalDiv=$('<div class="dataTotalDiv" style="height:'+opts.rowHeight+'px;"></div>');
             myTable.append(dataTotalDiv);
        }



        //主体
        var tableBodyArea=$('<div class="tableBodyArea" style="height:'+(opts.bodyHeight+4)+'px;"></div>');
        var tableBody=$('<div class="tableBody"></div>');
        myTable.append(tableBodyArea);



        //row
        var minWidth=0;
        //height: '+opts.rowHeight+'px;line-height: 20px;
        if(opts.checkbox){
        	minWidth=minWidth+31;
        	var checkSpan='<span class="input" style="width: 30px;text-align: center;padding-left:0;height:'+opts.headHeight+'px;line-height:'+opts.headHeight+'px"></span>';
        	tableHead.append(checkSpan).find("span").append("<input type=\"checkbox\" name=\"all\" id=\"allcheck\" value =\"all\" />");
        	if(dataTotalDiv!=null){
        		dataTotalDiv.append(checkSpan).find("span").css({'height': opts.rowHeight+'px','line-height':opts.rowHeight+'px'});
        	}
        	tableBody.append(checkSpan).find("span").css({'height': opts.rowHeight+'px','line-height':opts.rowHeight+'px'}).append("<input type=\"checkbox\" name=\"dataId\" >");

        }else if(opts.isSerialNo){
        	minWidth=minWidth+31;
        	var checkSpan='<span style="width: 30px;text-align: center;padding-left:0;height:'+opts.headHeight+'px;line-height:'+opts.headHeight+'px"></span>';
        	tableHead.append(checkSpan).find("span").html("序号");
        	if(dataTotalDiv!=null){
        		dataTotalDiv.append(checkSpan).find("span").css({'height': opts.rowHeight+'px','line-height':opts.rowHeight+'px'});
        	}
        	tableBody.append(checkSpan).find("span").css({'height': opts.rowHeight+'px','line-height':opts.rowHeight+'px'});
        }

        $.each(opts.fields,function(i,n){
        	  minWidth=minWidth+n.width+opts.pleft+1;
              var hspan=$('<span style="width: '+n.width+'px;height:'+opts.headHeight+'px;line-height:'+opts.headHeight+'px;">'+n.name+'</span>');
              tableHead.append(hspan);
              if(n.dataUrl){
            	  hspan.attr("dataUrl",n.dataUrl);
              }
              
              if(dataTotalDiv!=null){
            	  var totalSpan=$('<span style="width:'+n.width+'px;height:'+opts.rowHeight+'px;line-height:'+opts.rowHeight+'px;"></span>');
            	  if(typeof (opts.dataTotal)['t'+i]!='undefined')
            		  totalSpan.attr("val",(opts.dataTotal)['t'+i]);
            	  dataTotalDiv.append(totalSpan);
              }
              var bodySpan=$('<span style="width:'+n.width+'px;height:'+opts.rowHeight+'px;line-height:'+opts.rowHeight+'px;"></span>');
              bodySpan.attr("val",n.field);
              if(n.isInput){
            	  bodySpan.attr("contenteditable",true);
            	  bodySpan.css("border","1px solid black");
              }
              if(n.input){
            	  bodySpan.attr("contenteditable",n.input);
              }
              if(n.cls){
            	  bodySpan.attr("cls",n.cls);
              }
            
              if(n.onkeyup){
            	  bodySpan.attr("onkeyup",n.onkeyup);
              }
             
              if(typeof n.fun!='undefined'){
            	  cache.data("_function_"+i,n.fun);
            	  bodySpan.attr("fun",i);
              }
              tableBody.append(bodySpan);
        });

        if(typeof opts.operates!='undefined'){
        	minWidth=minWidth+opts.operates.width+1;
        	tableHead.append('<span style="text-align:center;padding-left:0;width: '+opts.operates.width+'px;height:'+opts.headHeight+'px;line-height:'+opts.headHeight+'px;">'+(typeof opts.operates.name=="undefined"?"操作":opts.operates.name)+'</span>');
        	var span='<span style="text-align:center;padding-left:0;width: '+opts.operates.width+'px;height:'+opts.rowHeight+'px;line-height:'+opts.rowHeight+'px;"></span>';
        	if(dataTotalDiv!=null){
        		dataTotalDiv.append(span);
        	}
        	var operatesSpan=$(span);
        	$.each(opts.operates.value,function(i,n){
        		var span=$("<a val=\""+i+"\">"+n.name+"</a>");
        		if(typeof n.display!='undefined'){
        			span.attr("field",(n.display)[0]);
        			span.attr("v",(n.display)[1]+'');
        		}
        		cache.data('_operatesFun'+i,n.fun);
        		operatesSpan.append(span);
        	});
        	tableBody.append(operatesSpan);
        }
       
        	minWidth=minWidth+20;
     
        tableHead.css("min-width",minWidth+"px");
        tableBodyArea.css("min-width",minWidth+"px");
        if(dataTotalDiv!=null)
        	dataTotalDiv.css("min-width",minWidth+"px");

        //分页
        if(opts.isPage){
        	var tablePagerArea=$('<div class="tablePagerArea" style="height:'+opts.pageHeight+'px;"></div>');
            if(opts.pagetotal){
            	var pagetotal=$('<div class="pagetotal pt_'+opts.pageHeight+'" >共有<span >1</span>条数据，共<span>1</span>页，当前页为第<span>1</span>页</div>');
            	
            	tablePagerArea.append(pagetotal);
            	cache.data("_pagetotal",pagetotal);

				
            }
            var pageshow=$("<div class=\"pageshow\" ></div>");
            cache.data("_pageshow",pageshow);

            tablePagerArea.append(pageshow);
        	myTable.append(tablePagerArea);
        	tablePagerArea.css("min-width",minWidth+"px");
        }

        /*缓存*/
        cache.data("_url",opts.url);
        cache.data("_isPage",opts.isPage);
        cache.data("_pageSize",opts.pageSize);
        cache.data("_param",opts.param);
        cache.data("_isSerialNo",opts.isSerialNo);
        cache.data("_result",opts.result)
        cache.data("_dataId",opts.dataId);
        cache.data("_checkbox",opts.checkbox);
        cache.data("_itemClass",opts.itemClass);

        cache.data("_dataTotalDiv",dataTotalDiv);
        cache.data("_tableBody",tableBody);
        cache.data("_tableBodyArea",tableBodyArea);
        cache.data("_tableHead",tableHead);
        cache.data("_flow",opts.flow);
        cache.data("_pageAll",opts.pageAll);
        cache.data("_alert",opts.alert);
        


        cache.getData(opts.pageNo);
        
	 },
	 getData:function(page){
		 var cache = $(this);
		 var load=false;
		 if(typeof cache.data("_first")!='undefined'){
			cache.data("_first",false);
			load=true;
		 }
		 if(cache.data("_pageNo")!=page){
			 cache.data("_pageNo",page);
			 load=true;
		 }
		if(!load)
			return;
		var param=cache.data("_param");
            param['random']=Math.random();
        	param['pageNo']=cache.data("_pageNo");
        	param['pageSize']=cache.data("_pageSize");
//            if(typeof cache.data("_pagetotal")!='undefined'){
//        }
        $.getJSON(cache.data("_url"), param, function(data) {
           if(data==null)
         	   return;
           var  tableBodyArea=cache.data("_tableBodyArea").empty();
           
           if(data.risk!=null){
        	   $("#fxt").show();
        	  $("#fxt").attr("val",data.risk);
        	  $("#fxt").attr("title",data.risk+"年一遇洪水");
           }
           
           var flow=cache.data("_flow");
           if(!(typeof flow=='undefined'||flow==null)){
        	   var flowVal=data.flow;
        	   if(flowVal!=null){
        		   $.each(flowVal,function(i,n){
            		   flow.eq(i).html(n);
            	   });
        	   }
           }
           
           //统计
           if(cache.data("_dataTotalDiv")!=null){
        	   if(data.extendInfo!=null){
        		   cache.data("_dataTotalDiv").find("span").each(function(){
             		  var val=$(this).attr("val");
             		  if(typeof val!='undefined'){
                           if(typeof data.extendInfo[val]!='undefined')
                         	  $(this).html(data.extendInfo[val]);
             		  }
             	   });
        	   }
           }
           //内容_tableBodyArea
           var djson=null;
           if(cache.data("_result")==null)
        	   djson=data;
           else
        	   djson=data[cache.data("_result")];
           if(djson==null||djson.length==0){
        	   if(cache.data("_alert")!=null)
        	      cache.data("_alert").call(this);
        	   return;
           }
        	   
           $.each(djson,function(i,n){
        	   var  tableBody=cache.data("_tableBody");
        	   var dataId=n[cache.data("_dataId")];
        	   tableBody.find("span").each(function(ii){
        		   var val=$(this).attr("val");
        		   var cls=$(this).attr("cls");
        		   if(!(typeof cls=='undefined'||cls==null)){
        			   var index=cls.indexOf("f$");
        			   if(index>-1){
        				   var _f=cls.substring(2,cls.length);
        				   $(this).attr("f",n[_f]);
        				   $(this).addClass(_f);
        			   }
        		   }
        		   
        		   if(ii==0&&cache.data("_isSerialNo")){
        			   $(this).html((i+1)+cache.data("_pageSize")*(page-1)); }
        		   else if(ii==0&&cache.data("_checkbox"))
        			   $(this).find("input[type='checkbox']").val(dataId);
        		   else if(typeof val!='undefined'){
        			   var fun=$(this).attr("fun");
        			   if(typeof fun=='undefined'){
        				   if(n[val]!=null)
        				      $(this).html(n[val]+"");
        				   else
        					   $(this).html(""); 
        			   }
        			   else{
        				   var p=$(this).attr("p");
							if(typeof p=='undefined')
								$(this).html(cache.data("_function_"+fun).call(this,n[val]));
							else
								$(this).html(cache.data("_function_"+fun).call(this,n));
        			   }
        		   }
        	   });
        	   var myTemp=$('<div class="tableBody"></div>').append(tableBody.html());
        	   if(cache.data("_itemClass")!=null){
        		   myTemp.addClass(cache.data("_itemClass")[i%2]);
        	   }
        	   myTemp.find("a").click(function(){
        		   cache.data('_operatesFun'+$(this).attr("val")).call(this,n);
        	   });
        	   myTemp.find("a").each(function(){
        		  var _a=$(this);
        		  if(typeof _a.attr("field")!='undefined'){
        			  if(n[_a.attr("field")]+''==_a.attr("v"))
        				  _a.show();
        			  else
        				  _a.hide();
        		  }
        	   });
        	   tableBodyArea.append(myTemp);
           });
           //checkbox 全选等
           if(cache.data("_checkbox"))
        	   cache.checked();
           //分页
           if(cache.data("_isPage"))
        	   cache.pager(data.totalCount,data.totalPage,page);
        });
      
//        var dataUrl=cache.data("_tableHead").find("span[dataUrl]").attr("dataUrl");
//        if(typeof dataUrl=='string'){
//        	$.getJSON(dataUrl,function(json){
//        		var jv=$.parseJSON(json.paramValue);
//        		$(".tableBodyArea .tableBody").each(function(){
//        		    
//        			var su=$(this).find("span").eq(3);
//        			//alert(su.attr("f"))
//        			if($.trim(su.html())==''){
//        				su.html(jv[su.attr("f")])
//        			}
//        		})
//        	});
//        }
        load=false;
	 },
	 //分页
	 pager:function(totalCount,totalPage,pageNo,pageInfo){
		  var cache=$(this);
		  var pagetotal=cache.data("_pagetotal");
		  if(pagetotal!=undefined){
			  pagetotal.find("span:eq(0)").empty().append(totalCount);

			  //////////
				_totalSize = totalCount;

		   	  pagetotal.find("span:eq(1)").empty().append(totalPage);
		   	  pagetotal.find("span:eq(2)").empty().append(pageNo);
		  }
	   	
	   	  var pageshow=cache.data("_pageshow").empty();
	   	  if(pageNo<=1){
			  pageshow.append("<span class=\"disabled\">上一页 </span>");
		  }else{
			  pageshow.append("<span class=\"a\" val=\""+(parseInt(pageNo)-1)+"\">上一页</span>");
		  }
	   	  
	   	  if(cache.data("_pageAll")){
	   		  var startPage=1;
			   var endPage=9;
			   if(startPage==1&&parseInt(pageNo)<5){
				   endPage=endPage<=totalPage?endPage:totalPage;
			   }else{
				   if(totalPage>parseInt(pageNo)+4){
					   endPage=parseInt(pageNo)+4;
				   }else{
					   endPage =totalPage;
				   }
				   startPage=endPage-8>0?endPage-8:1;
			   }
			   for(var p=startPage;p<=endPage;p++){
				   if(p==pageNo){
					   pageshow.append("<span class=\"current\">"+p+"</span>");
				   }else{
					   pageshow.append("<span class=\"a\" val=\""+p+"\">"+p+"</span>");
				   }
			   }
	   	  }
			   if(pageNo>=totalPage){
				   pageshow.append("<span class=\"disabled\">下一页 </span>");
			   }else{
				   pageshow.append("<span class=\"a\" val=\""+(parseInt(pageNo)+1)+"\">下一页 </span>");
			   }
			   //pageshow.find(".a").toHover("","ahover");
			   pageshow.find(".a").click(function(){
					cache.getData($(this).attr("val"));
			   });
	   	
	   	  
	   	 
		   
		   

	 },
	//checkbox 全选等
	 checked:function(){
		 var cache = $(this);
		 var cek=cache.data("_cek");
		 cache.data('_tableHead').find("#allcheck").click(function(){
				if($(this).attr("checked")){
					cache.data("_tableBodyArea").find("input[name='dataId']").attr("checked",true);
				}else{
					cache.data("_tableBodyArea").find("input[name='dataId']").removeAttr("checked");
				}
			});
		 if(typeof cek!='undefined'){
			$.each(cek,function(i,n){
				cache.data("_tableBodyArea").find("input[name='dataId'][value='"+n+"']").attr("checked",true);
			});
		 }
		  cache.data("_tableBodyArea").find("input[name='dataId']").click(function(){
				$(this).each(function(){
					if(!$(this).attr("checked")){
						cache.data('_tableHead').find("#allcheck").removeAttr("checked");
						return;
					}
				});
		   });
	 },
	 //刷新
	dataRefresh:function(param){
	   $(this).data("_first",true);
		if(typeof param!='undefined'){
		   
		  $(this).data("_param",param);
		}
		$(this).getData(1);
	 },
	 dataRefresh:function(param,cek){
		   $(this).data("_first",true);
			if(typeof param!='undefined')
			  $(this).data("_param",param);
			if(typeof cek!='undefined')
				  $(this).data("_cek",cek);
			$(this).getData(1);
		 }
});
