<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fengzhanwei
  Date: 2022/6/28
  Time: 下午 5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>主页</title>
  <!--写base标签，永远固定相对路径跳转的结果-->
  <base href="http://localhost:8080/std_war_exploded//">
  <link rel="stylesheet" href="../fontawesome/css/all.min.css"> <!-- https://fontawesome.com/ -->
  <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet"> <!-- https://fonts.google.com/ -->
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <link href="../css/templatemo-xtra-blog.css" rel="stylesheet">
  <style type="text/css">
    .login_banner {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }
    .login_banner {
      height: 700px;
      background-color: #cceac7;
    }
    .login_banner .register_form {
      width: 506px;
      height: 450px;
      background-color: #c6c698;
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    .login_banner .register_form form .form-item .errMess{
      font-size: 12px;
      color: red;
      visibility: hidden;
      margin: 2px 0;
    }
    .errMess{
      height: 14px;
    }
    .login_banner .register_form form {
      width: 100%;
      height: 100%;
      font-size: 16px;
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    .login_banner .register_form form .form-item div {
      margin: 0;
      display: flex;
      align-items: center;
      justify-content: space-between;
    }
    .login_banner .register_form form .form-item {
      display: flex;
      width: 346px;
      flex-direction: column;
      margin: 0;
    }
    .login_banner .register_form .form-item {
      margin-top: 10px !important;
    }
    .login_banner .register_form form .form-item div input {
      width: 262px;
      height: 40px;
      border: 1px #e3e3e3 solid;
      outline: none;
    }
    .login_banner .register_form form .btn {
      width: 360px;
      height: 40px;
      background-color: #39987c;
      color: #fff;
      outline: none;
      border: none;
      margin-top: 10px;
    }
  </style>
  <script src="../script/jquery-3.6.0.js"></script>
  <script type="text/javascript">
    $(function (){
      $("#sub").click(function (){
        var Scode=$("#Scode").val();
        var Sname=$("#Sname").val();
        var Cocode=$("#Cocode").val();
        if (Scode==""){
          alert("专业代码为空");
          return false;
        }else {
          if (Sname==""){
            alert("专业代码为空");
            return false;
          }else {
            if (Cocode==""){
              alert("学院代码为空");
              return false;
            }
          }
        }
      })
    });
  </script>
</head>
<body>
<%@include file="../common/header_admin.jsp"%>
<div class="container-fluid">
  <div style="border: 1px black solid;width: 1200px;position: absolute;left: 320px;">
    <div class="login_banner">
      <div class="register_form">
        <form action="adminServlet" method="get">
          <input type="hidden" name="action" value="${empty requestScope.spe.scode?"addSpecByScode":"updateSpecByScode"}"/>
          <input type="hidden" name="preScode" value="${requestScope.spe.scode}"/>
          <div class="form-item">
            <div>
              <label>专业代码:</label>
              <input type="text" name="Scode" id="Scode" placeholder="请输入专业代码" value="${requestScope.spe.scode}" />
            </div>
            <span class="errMess">请输入正确的专业代码</span>
          </div>
          <div class="form-item">
            <div>
              <label>专业名称:</label>
              <input type="text" name="Sname" id="Sname" placeholder="请输入专业名称" value="${requestScope.spe.sname}" />
            </div>
          </div>
          <div class="form-item">
            <div>
              <label>学院代码:</label>
              <input type="text" name="Cocode" id="Cocode" placeholder="请输入学院代码" value="${requestScope.spe.cocode}" />
            </div>
          </div>
          <button class="btn">提交</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
