<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>用户登录-STD信息服务平台</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE11" >
  <meta name="keywords" content="STD成绩服务平台" />
  <meta name="description" content="STD成绩服务平台" />
  <link rel="stylesheet" href="./css/usersys/dean.css" />
  <style>
    .inp {
      border: 1px solid #cccccc;
      border-radius: 2px;
      padding: 0 10px;
      width: 278px;
      height: 40px;
      font-size: 18px;
    }
    .btn {
      border: 1px solid #cccccc;
      border-radius: 2px;
      width: 100px;
      height: 40px;
      font-size: 16px;
      color: #666;
      cursor: pointer;
      background: white linear-gradient(180deg, #ffffff 0%, #f3f3f3 100%);
    }
    .btn:hover {
      background: white linear-gradient(0deg, #ffffff 0%, #f3f3f3 100%)
    }
    #captcha1,
    #captcha2 {
      width: 300px;
      display: inline-block;
    }
    .show {
      display: block;
    }
    .hide {
      display: none;
    }
    #notice1,
    #notice2 {
      color: red;
      margin:0px;
    }
    label {
      vertical-align: top;
      display: inline-block;
      width: 80px;
      text-align: right;
    }
    #wait1, #wait2 {
      text-align: left;
      color: #666;
      margin: 0;
    }
    .phcolor{ color:#999;}
    .mb10px{margin-bottom: 10px;}
    .login-btn-sso{width: 100%; height: 38px; line-height: 38px; font-size: 15px; text-align: center; background: #4EB73C; border: 1px solid #4EB73C; border-radius: 5px; color: #fff; cursor: pointer;}
  </style>
</head>
<body>
<div class="loadingDiv" style="display:none"></div>
<div class="bg-box">
  <div class="header-box">
    <div class="main-box clearfix">
      <div class="left-box">
        <a href="./index.jsp">系统首页</a>
      </div>
      <ul class="right-box">
        <li><a href="regist.jsp">用户注册 </a></li>
      </ul>
    </div>
  </div>
  <div class="login-box">
    <div class="main-box clearfix">
      <div class="img-box">
        <img src="./images/usersys/img/login_title.png"/>
      </div>
      <div class="login-form">
        <form action="userServlet" method="post" id="LoginForm" style="width:300px;">
          <input type="hidden" name="action" value="login"/>
          <h4>STD成绩服务</h4>
          <div class="center-box" >
             <span style="color: red;text-align: center;position:absolute;width: 300px;top: 182px;">
               ${empty requestScope.msg?"请输入用户名和密码":requestScope.msg}
             </span>
            <div class="mb10px">
              <input type="text" name="username" id="username" class="input-box-1" placeholder="学号/工号" value="${requestScope.username}" />
            </div>
            <div class="mb10px">
              <input type="password" name="password" id="password" class="input-box-2" placeholder="输入登录密码" />
            </div>
            <div class="mb10px" style="height:38px;" >
              <input type="text" name="ranstring" id="ranstring"  placeholder="输入验证码" style="float:left;width: 150px; height: 38px;  line-height: 38px; [;line-height: 1px;] font-size: 14px; padding-left: 10px; border: 1px solid #bcbcbc; border-radius: 5px; box-sizing: border-box; " />
              <span id="randomPhoto" style="float:left; height:38px; margin-left:10px;"><img src="kaptcha.jpg" border=0 height="35" width="100"></span>
            </div>
            <div class="mb10px" >
              <button class="login-btn" type="submit" id="submit2" >登录系统</button>
            </div>
            <div class="mb20px clearfix">
              <div class="bar-box"><span class="left-bar"></span>您还没有账号？<span class="left-bar"></span></div>
              <input type="button" class="regist-btn-login" value="账号注册" onclick="window.location='regist.jsp'">
            </div>
            <div class="btm-box clearfix">
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script src="./script/jquery-3.6.0.js"></script>
<script type="text/javascript">
  $(function (){
    $("#submit2").click(function () {
      var username = $("#username").val();
      var password = $("#password").val();
      var ranstring = $("#ranstring").val();
      if (username==""){
        alert("用户名不能为空");
        return false;
      }else{
        if (password==""){
          alert("密码不能为空");
          return false;
        }else {
          if(ranstring==""){
            alert("验证码不能为空");
            return false;
          }
        }
      }

    })
  })
</script>
</body>
</html>