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
        var Tno=$("#Tno").val();
        var Tname=$("Tname").val();
        var Cno=$("#Cno_new").val();
        var CLname=$("#CLname").val();
        if (Tno==""){
          alert("工号不能为空");
          return false;
        }else{
          if (Tname==""){
            alert("姓名不能为空");
            return false;
          }else{
            if (Cno==""){
              alert("课程代码不能为空");
              return false;
            }else {
              if (CLname==""){
                alert("班级名称不能为空");
                return false;
              }
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
          <input type="hidden" name="action" value="${empty requestScope.edu.tno?"addEdu":"updateEdu"}"/>
          <input type="hidden" name="preTno" value="${requestScope.edu.tno}">
          <input type="hidden" name="Cno" value="${requestScope.edu.cno}"/>
          <input type="hidden" name="preCLcode" value="${requestScope.edu.CLcode}"/>
          <input type="hidden" name="preCname" value="${requestScope.edu.cname}"/>
          <div class="form-item">
            <div>
              <label>工号:</label>
              <input type="text" name="Tno" placeholder="请输入工号" id="Tno"  value="${requestScope.edu.tno}"/>
            </div>
          </div>
          <div class="form-item">
            <div>
              <label>姓名:</label>
              <c:if test="${not empty requestScope.edu}">
                <input type="text" name="Tname_new" placeholder="" id="Tname_new" value="${requestScope.edu.tname}"/>
              </c:if>
              <c:if test="${empty requestScope.edu}">
                <input type="text" name="Tname" placeholder="请输入姓名" id="Tname" value="${requestScope.edu.tname}"/>
              </c:if>
            </div>
            <span class="errMess" >请输入正确的姓名</span>
          </div>
          <div class="form-item">
            <div>
              <c:if test="${not empty requestScope.edu}">
                <label>课程代码:</label>
                <label style="width: 262px;height: 40px;background-color: white;font-size: 20px;">${requestScope.edu.cno}</label>
              </c:if>
              <c:if test="${empty requestScope.edu}">
                <label>课程代码:</label>
                <input type="text" name="Cno_new" placeholder="请输入课程代码" id="Cno_new" value=""/>
              </c:if>
            </div>
          </div>
          <div class="form-item">
            <div>
              <label>所教班级:</label>
              <input type="text" name="CLname" placeholder="请输入所教班级" id="CLname" value="${requestScope.edu.CLname}" />
            </div>
            <span class="errMess">请输入正确的班级名称</span>
          </div>
          <button class="btn">提交</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
