/**
 *
 *	Plugin: Jquery.webox
 *	Developer: Blank
 *	Version: 1.0 Beta
 *	Update: 2012.07.08
 *
**/
$.extend({
	webox:function(option){
		var _x,_y,m,allscreen=false;
		if(!option){
			alert('options can\'t be empty');
			return;
		};
		if(!option['html']&&!option['iframe']){
			alert('html attribute and iframe attribute can\'t be both empty');
			return;
		};
		option['parent']='webox';
		option['locked']='locked';
		$(document).ready(function(e){
			$('.webox').remove();
			$('.background').remove();
			var width=option['width']?option['width']:400;
			var height=option['height']?option['height']:240;
			$('body').append('<div class="background" style="display:none;"></div><div class="webox" style="width:'+width+'px;height:'+height+'px;display:none;"><div id="inside" style="height:'+height+'px;"><h1 id="locked" onselectstart="return false;">'+(option['title']?option['title']:'')+'<a class="span" href="javascript:listPageCloseFrame(0);"></a></h1>'+(option['iframe']?'<iframe class="w_iframe" src="'+option['iframe']+'" frameborder="0" width="100%" scrolling="yes" style="border:none;overflow-x:hidden;height:'+parseInt(height-30)+'px;"></iframe>':option['html']?option['html']:'')+'</div></div>');
			if(navigator.userAgent.indexOf('MSIE 7')>0||navigator.userAgent.indexOf('MSIE 8')>0){
				$('.webox').css({'filter':'progid:DXImageTransform.Microsoft.gradient(startColorstr=#55000000,endColorstr=#55000000)'});
			}if(option['bgvisibel']){
				$('.background').fadeTo('slow',0.3);
			};
			$('.webox').css({display:'block'});
			$('#'+option['locked']+' .span').click(function(){
				$('.webox').css({display:'none'});
				$('.background').css({display:'none'});
			});
			$('.hideBox').click(function(){//取消对话框
				$('.webox').css({display:'none'});
				$('.background').css({display:'none'});
				
			});
			$('.okBox').click(function(){//需要传递参数，更新数据库
				var haha = $("input[type=radio][name=flag]:checked").val();
				if(haha == null){
					show("什么也没有选中");
					return;
				}
				var hehe = $(this).attr("id");
				var parmetre = $(this).attr("id")+"="+haha+"&id="+$("#rateSettingId").val();
				sendAjax("updateRateSetting",parmetre,function(data){
					show(data.message);
					if(data.status){
						$('.webox').css({display:'none'});
						$('.background').css({display:'none'});
						$("."+hehe).attr("src","img/op_memo_"+haha+".png");
					}
				});
			});
			var marginLeft=parseInt(width/2);
			var marginTop=parseInt(height/2);
			var winWidth=parseInt($(window).width()/2);
			var winHeight=parseInt($(window).height()/2.2);
			var left=winWidth-marginLeft;
			var top=winHeight-marginTop;
			$('.webox').css({left:left,top:top});
			$('#'+option['locked']).mousedown(function(e){
				if(e.which){
					m=true;
					_x=e.pageX-parseInt($('.webox').css('left'));
					_y=e.pageY-parseInt($('.webox').css('top'));
				}
				}).dblclick(function(){
					if(allscreen){
						$('.webox').css({height:height,width:width});
						$('#inside').height(height);
						$('.w_iframe').height(height-30);
						$('.webox').css({left:left,top:top});
						allscreen = false;
					}else{
						allscreen=true;
						var screenHeight = $(window).height();
						var screenWidth = $(window).width();$
						('.webox').css({'width':screenWidth-18,'height':screenHeight-18,'top':'0px','left':'0px'});
						$('#inside').height(screenHeight-20);
						$('.w_iframe').height(screenHeight-50);
					}
				});
			}).mousemove(function(e){
				if(m && !allscreen){
					var x=e.pageX-_x;
					var y=e.pageY-_y;
					$('.webox').css({left:x});
					$('.webox').css({top:y});
				}
			}).mouseup(function(){
				m=false;
			});
			$(window).resize(function(){
				if(allscreen){
					var screenHeight = $(window).height();
					var screenWidth = $(window).width();
					$('.webox').css({'width':screenWidth-18,'height':screenHeight-18,'top':'0px','left':'0px'});
					$('#inside').height(screenHeight-20);
					$('.w_iframe').height(screenHeight-50);
				}
			});
	}
});
/**
 * by 翁世灵
 * 用于弹层关闭,不刷新父页面
 * flag = 0：只关闭弹层，
 * flag = 1：调用父页面manualReload()方法刷新父页面关闭弹出层，父页面必须有manualReload()方法，（如果刷新前需要进行处理的话）
 * flag = 2：直接通过刷新父页面关闭弹出层，刷新前不需要进行处理
 * 使用时通过父页面方法调用 例：window.parent.listPageCloseFrame(0)
 * **/

function listPageCloseFrame(flag){
    $("body").removeClass("no-scroll");
    if(flag==1){
        manualReload();
    } else if(flag==2){
        location.reload();
    }else{
        var w = $("html");
        var we = $(w).find(".webox");
        $(w).find(".background").remove();
        $(we).remove();
    }
}