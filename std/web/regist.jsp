<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>用户注册-std信息服务平台</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="keywords" content="用户注册-std信息服务平台" />
  <meta name="description" content="用户注册-std信息服务平台" />
  <link rel="stylesheet" type="text/css" href="css/usersys/dean.css"/>
</head>
<body style="background: #f6f6f6;">
<div class="regist-header">
  <div class="regist-box clearfix">
    <div class="fl">
      <img src="images/usersys/icon/regist_home_icon.png" class="home-icon" />
      <a href="index.jsp">登录界面</a>
    </div>
    <div class="fr header-right-box">
      <a href="regist.jsp" class="header-op">用户注册</a>
    </div>
  </div>
</div>
<div class="regist-box">
  <div class="regist-title clearfix">
    <span class="fl regist-user-icon"></span>
    <span class="fr regist-title-right">
					已有账号？
					<a href="index.jsp" class="login-a">请登录</a>
				</span>
  </div>
</div>
<div class="regist-box">
  <div class="regist-body">
    <div class="regist-body-header clearfix">
      <div class="fl body-header-title">用户注册说明</div>
      <div class="fr body-header-info">
        1、如果您是学生，账号即为学号；<br>
        2、如果您是教师，账号即为工号。
      </div>
    </div>
    <form action="userServlet" method="post">
      <input type="hidden" name="action" value="regist"/>
    <div class="regist-body-content">
      <div class="body-content-list">
        <span style="color: red;text-align: center;position:absolute;width: 592px;margin-top:-24px;">
          ${empty requestScope.ms?"账号为学号或工号":requestScope.ms}
        </span>
        <span class="input-name">账&emsp;&emsp;号:</span>
        <input type="text" class="input-style" id="universityId" name="universityId" value="${requestScope.username}" placeholder="请输入您的账号" onchange="checkUniversityId()" onkeyup="value=value.replace(/[^\d]/g,'') "/>
        <div id="chkUniversityId" style="font-size:12px;"></div>
      </div>
      <div class="body-content-list">
        <span class="input-name">角色选择:</span>
        <select name="Urole" id="Urole" class="input-style2">
          <option value="1">学生</option>
          <option value="2">教师</option>
        </select>
      </div>
      <div class="body-content-list">
        <span class="input-name">设置密码:</span>
        <input type="password" class="input-style" id="newPassword" name="newPassword" value=""  placeholder="不少于6位数字字母组合" onblur="chkPwd(this)"/>
        <div id="chkResult" style="font-size:12px;"></div>
      </div>
      <div class="body-content-list">
        <span class="input-name">确认密码:</span>
        <input type="password" class="input-style" id="newPasswordConfirm" name="newPasswordConfirm" value="" placeholder="请确认您的密码" onblur="confirmPwd()"/>
        <div id="chkMsg" style="font-size:12px;"></div>
      </div>
      <div class="body-content-list">
        <span class="input-name">电子邮箱:</span>
        <input type="text" class="input-style" id="email" name="email" value="${requestScope.email}" placeholder="请填写电子邮箱地址" onchange="checkEmail()" onkeyup="value=value.replace(/[^\w\@\_\.]/ig,'')"/>
        <div id="chkEmail" style="font-size:12px;"></div>
      </div>
      <div class="body-content-list">
        <span class="input-name">邮箱验证</span>
        <input type="text" class="input-style" id="confirmid" name="confirmid" value="${requestScope.email}" placeholder="请输入邮箱验证码" onchange="checkEmail()" onkeyup="value=value.replace(/[^\w\@\_\.]/ig,'')"/>
        <div id="confirmEmail" style="font-size:12px;"></div>
      </div>
      <div class="body-content-list2">
        <span class="input-name">人机验证:</span>
        <input type="text" class="input-style" id="ranstring" name="ranstring" value="" placeholder="请输入验证码" />
        <div class="last-info">
							<span id="randomPhoto"><img src="kaptcha.jpg" height="48" width="120" style="border: 1px solid #666" id="imgRandom"></span>
        </div>
      </div>
      <div class="regist-btn-box">
        <button class="regist-btn" >完成账号注册</button>
      </div>
    </div>
    </form>
  </div>
  <div class="regist-footer">
  </div>
</div>
<script>
  function chkPwd(obj){
    var t=obj.value;
    var id=getResult(t);

    //定义对应的消息提示
    var msg=new Array(4);
    msg[0]="密码过短。";
    msg[1]="密码强度差。";
    msg[2]="密码强度良好。";
    msg[3]="密码强度高。";

    var sty=new Array(4);
    sty[0]=-45;
    sty[1]=-30;
    sty[2]=-15;
    sty[3]=0;

    var col=new Array(4);
    col[0]="gray";
    col[1]="red";
    col[2]="#ff6600";
    col[3]="Green";

    //设置显示效果
    var bImg="../images/usersys/icon/checkpwd.gif";//一张显示用的图片
    var sWidth=180;
    var sHeight=15;
    var Bobj=document.getElementById("chkResult");

    Bobj.style.fontSize="12px";
    Bobj.style.color=col[id];
    Bobj.style.width=sWidth + "px";
    Bobj.style.height=sHeight + "px";
    Bobj.style.lineHeight=sHeight + "px";
    Bobj.style.background="url(" + bImg + ") no-repeat left " + sty[id] + "px";
    Bobj.style.textIndent="20px";
    Bobj.innerHTML="检测提示：" + msg[id];
  }

  //定义检测函数,返回0/1/2/3分别代表无效/差/一般/强
  function getResult(s){
    if(s.length < 4){
      return 0;
    }
    var ls = 0;
    if (s.match(/[a-z]/ig)){
      ls++;
    }
    if (s.match(/[0-9]/ig)){
      ls++;
    }
    if (s.match(/(.[^a-z0-9])/ig)){
      ls++;
    }
    if (s.length < 6 && ls > 0){
      ls--;
    }
    return ls
  }

  function confirmPwd(){
    var newPassword=document.getElementById("newPassword").value;
    var newPasswordConfirm=document.getElementById("newPasswordConfirm").value;
    if(newPassword!=newPasswordConfirm||newPasswordConfirm.length<6||newPassword.length<6){
      document.getElementById("chkMsg").innerHTML="<font color=red>确认密码不正确，请检查</font>";
      //document.getElementById("checkValue").value="false";
    }
    else{
      document.getElementById("chkMsg").innerHTML="<font color=green>确认密码正确</font>";
      //document.getElementById("checkValue").value="true";
    }
  }

  //字符检测函数
  function charTest(chr){
    var i;
    var smallch="abcdefghijklmnopqrstuvwxyz";
    var bigch="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for(i=0;i<26;i++)
      if(chr==smallch.charAt(i) || chr==bigch.charAt(i))
        return(1);
    return(0);
  }
  //数字和特殊字符检测函数
  function spcharTest(chr){
    var i;
    var spch="_-.0123456789";
    for (i=0;i<13;i++)
      if(chr==spch.charAt(i))
        return(1);
    return(0);
  }
  //校验邮件
  function emailTest(str){
    var i,flag=0;
    var at_symbol=0;
    //“@”检测的位置
    var dot_symbol=0;
    //“.”检测的位置
    //if(char_test(str.charAt(0))==0 )
    //return (1);
    //首字符必须用字母

    for (i=1;i<str.length;i++)
      if(str.charAt(i)=='@')
      {
        at_symbol=i;
        break;
      }
    //检测“@”的位置

    if(at_symbol==str.length-1 || at_symbol==0)
      return(2);
    //没有邮件服务器域名

    if(at_symbol<2)
      return(3);
    //帐号少于三个字符

    if(at_symbol>19 )
      return(4);
    //帐号多于十九个字符

    for(i=1;i<at_symbol;i++)
      if(charTest(str.charAt(i))==0 && spcharTest(str.charAt(i))==0)
        return (5);
    for(i=at_symbol+1;i<str.length;i++)
      if(charTest(str.charAt(i))==0 && spcharTest(str.charAt(i))==0)
        return (5);
    //不能用其它的特殊字符

    for(i=at_symbol+1;i<str.length;i++)
      if(str.charAt(i)=='.') dot_symbol=i;
    for(i=at_symbol+1;i<str.length;i++)
      if(dot_symbol==0 || dot_symbol==str.length-1)
              //简单的检测有没有“.”，以确定服务器名是否合法
        return (6);

    return (0);
    //邮件名合法

  }

  function checkEmail(){
    var email=document.getElementById("email").value;
    if(emailTest(email)!=0){
      document.getElementById("chkEmail").innerHTML="<span style=\"color:#ff0000;\">请输入正确的电子邮箱且不能为空</span>";
      document.getElementById("email").focus();
    }
    else{
      document.getElementById("chkEmail").innerHTML="";
    }
  }

  function checkUniversityId(){
    var universityId=document.getElementById("universityId").value;
    if(universityId.length>15||universityId.length<6){
      document.getElementById("chkUniversityId").innerHTML="<span style=\"color:#ff0000;\">请输入正确的学号且不能为空</span>";
      document.getElementById("universityId").focus();
    }
    else{
      document.getElementById("chkUniversityId").innerHTML="";
    }
  }

</script>
</body>
</html>
