var n = 99;
function $(d){
	return document.getElementById(d);
}

  function gs(d){
  	var t=$(d);
  	if (t){
  		return t.style;
  	}else{
  		return null;
  	}
  	}
  
  function gs2(d,a){
    if (d.currentStyle){ 
      var curVal=d.currentStyle[a]
    }else{ 
      var curVal=document.defaultView.getComputedStyle(d, null)[a]//getComputedStyle获取元素最终使用的CSS属性值。例： var w = window.getComputedStyle("元素", "伪类");
    } 
    return curVal;
  }

  
  function WindowMin(id){
	  /*取消最小化设置
	  document.getElementById("WindowMain"+id).style.width="1024px";
	  document.getElementById("WindowMain"+id).style.height="600px";
	  document.getElementById("WindowMain"+id).style.left=70+(parseInt(Math.random()*10+1)*10)+(parseInt((Math.random()*10+1)/2));
      document.getElementById("WindowMain"+id).style.top=40+(parseInt(Math.random()*10+1)*10)+(parseInt((Math.random()*10+1)/2));
	  document.getElementById("WindowFrame"+id).style.height="600px";
	  document.getElementById("WindowBody"+id).style.height=500 +"px";
	  document.getElementById("WindowMain"+id).style.zIndex=n++;
	  document.getElementById("zIndex_value").innerHTML=n++;
	  */
	  document.getElementById("WindowMain"+id).style.width=document.body.clientWidth-12 +"px";
	  document.getElementById("WindowMain"+id).style.height=document.body.clientHeight-32 +"px";
	  document.getElementById("WindowMain"+id).style.top=2  +"px";
	  document.getElementById("WindowMain"+id).style.left=5  +"px";
	  document.getElementById("WindowBody"+id).style.height=document.body.clientHeight-62 +"px";
	  document.getElementById("WindowFrame"+id).style.height=document.body.clientHeight-63 +"px";
	  document.getElementById("WindowMain"+id).style.zIndex=n++;
	  document.getElementById("zIndex_value").innerHTML=n++;
  }
  function WindowMax(id){
      document.getElementById("WindowMain"+id).style.width=document.body.clientWidth-10 +"px";
	  document.getElementById("WindowMain"+id).style.height=document.body.clientHeight-30 +"px";
	  document.getElementById("WindowMain"+id).style.top=2  +"px";
	  document.getElementById("WindowMain"+id).style.left=5  +"px";
//	  document.getElementById("WindowTitle"+id).style.width=document.body.clientWidth-265;
//	  document.getElementById("WindowBody"+id).style.width=document.body.clientWidth-77;
	  document.getElementById("WindowBody"+id).style.height=document.body.clientHeight-60 +"px";
//	  document.getElementById("WindowFrame"+id).style.width=document.body.clientWidth-77;
	  document.getElementById("WindowFrame"+id).style.height=document.body.clientHeight-61 +"px";
	  
	  document.getElementById("WindowMain"+id).style.zIndex=n++;
	  document.getElementById("zIndex_value").innerHTML=n++;
  }
  
  function WindowClose(id){
  	gs("main").display = "none";//调用gs方法 ，等价于 $("main").style.display = "none";
  }
  
  function ChatSend(obj){
    var o = obj.ChatValue;
    if (o.value.length>0){
      $("ChatContent").innerHTML += "<strong>fzw speak: </strong>"+o.value+"<br/>";
      o.value='';
    }
  }
  
if  (document.getElementById){
    (
        function(){
        	
        if (window.opera){ 
        	document.write("<input type='hidden' id='Q' value=' '>"); 
        }
        
        var dragok = false; //初始化拖动事件
        var y,x,d,dy,dx; //
        
        function move(e)
        {
          if (!e) e = window.event;
          if (dragok){
        	  /**以下配置主要是为了让界面不会跑出到页面以外，2013-1-7更改**/
        	  var x_value=dx + e.clientX - x;
        	  var y_value=dy + e.clientY - y;
        	  //获取整个屏幕的宽度和高度
        	  var p_height=document.body.clientHeight;
        	  var p_width=document.body.clientWidth;
        	  if(x_value<0){
        		  x_value=0;
        	  }
        	  if(x_value>=(p_width))
        	  {
        		  x_value=p_width-40;
        	  }
        	  if(y_value<0){
        		  y_value=0;
        	  }
        	  if(y_value>=(p_height-40))
        	  {
        		  y_value=p_height-40;
        	  }
        	  d.style.left=x_value+ "px";
        	  d.style.top =y_value+ "px";
        	  /**新加代码结束**/
        	  /**此处是原代码，已被注释**/
              //d.style.left = dx + e.clientX - x + "px";
              //d.style.top  = dy + e.clientY - y + "px";
            return false;
          }
        }
        
        function down(e){
          if (!e) e = window.event;
          var temp = (typeof e.target != "undefined")?e.target:e.srcElement;
          if (temp.tagName != "HTML"|"BODY" && temp.className != "dragclass"){
            temp = (typeof temp.parentNode != "undefined")?temp.parentNode.parentNode:temp.parentElement.parentElement;
          }
          if('TR'==temp.tagName){
            temp = (typeof temp.parentNode != "undefined")?temp.parentNode:temp.parentElement;
            temp = (typeof temp.parentNode != "undefined")?temp.parentNode:temp.parentElement;
            temp = (typeof temp.parentNode != "undefined")?temp.parentNode:temp.parentElement;
          }

          if (temp.className == "dragclass"){
            if (window.opera){ document.getElementById("Q").focus(); }
            dragok = true; //改变标识状态，使move事件能够启用
            temp.style.zIndex = n++;//
            d = temp;
            dx = parseInt(gs2(temp,"left"))|0; //鼠标移动时x的偏移值
            dy = parseInt(gs2(temp,"top"))|0; //鼠标移动时y的偏移值
            x = e.clientX; //鼠标发生事件是所在的x轴位置
            y = e.clientY; //鼠标发生事件是所在的y轴位置
            document.onmousemove = move;
            return false;
          }
        }
        
        function up(){
          dragok = false;
          document.onmousemove = null;
        }
        
        document.onmousedown = down;
        document.onmouseup = up;
      }
    )();
  
}
