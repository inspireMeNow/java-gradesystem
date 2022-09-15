<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
        .table_border td {
            border: 1px solid #9F7E42;
            height: 30px;
            text-align: center;
            font-size: 15px;
        }
        .table_border {
            width: 100%;
            border: 0;
            border-collapse: collapse;
            border-spacing: 0px;
        }
        .table_border th {
            border: 1px solid #9F7E42;
            height: 30px;
            background-color: #EFECC5;
            font-weight: bold;
            text-align: center;
            font-size: 15px;
        }
        .instruction-div {
            line-height: 30px;
            float: left;
            width: 100%;
            box-sizing: border-box;
            background: #f9f8e7;
            color: #7a7a7a;
            font-size: 13px;
        }
        .important {
            color: red !important;
        }
        .btn-blue {
            background-color: #0096f3;
            color: #fff !important;
            border: 1px solid #0096f3;
        }
    </style>
</head>
<body>
<%@include file="../common/header_student.jsp"%>
<div class="container-fluid">
    <div style="border: 1px black solid;width: 1200px;position: absolute;left: 320px;">
        <div style="text-align: center;background-color: #575657FF;color: white;" class="WindowHead" id="WindowHead2">
            所选课程
        </div>
        <div class="instruction-div clearfix">
            <div style="padding:8px 10px;"><span class="important">操作提示：</span>
                <strong>
                    <span id="userInfo">${sessionScope.student.sno} ${sessionScope.student.sname}</span>
                </strong>
                如有问题请与教务部门联系。
            </div>
        </div>
        <div class="tb_cont">
            <table class="frame-table" style="width:100%;border:0;border-collapse:collapse;border-spacing:0; padding:0;">
                <tbody><tr>
                    <td><table class="table_border" id="table3">
                        <tbody><tr>
                            <th style="width:5%;">序号</th>
                            <th style="width:8%;">课程代码</th>
                            <th style="width:20%;">课程名称</th>
                            <th style="width:5%;">性质</th>
                            <th style="width:5%;">学分</th>
                            <th style="width:5%;">开课学年</th>
                            <th style="width:5%;">开课学期</th>
                        </tr>
                        <c:set var="count" value="0"></c:set>
                        <c:forEach items="${requestScope.course}" var="course">
                        <c:set var="count" value="${count+1}"></c:set>
                        <tr>
                            <td>${count}</td>
                            <td>${course.cno}</td>
                            <td>${course.cname}</td>
                            <td>${course.cproperty}</td>
                            <td>${course.credit}</td>
                            <td>${course.syear}</td>
                            <td>${course.term}</td>
                        </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="14">已获得课程总学分（成绩及格后即可获得学分）：${requestScope.sum}</td>
                        </tr>
                        </tbody></table></td>
                </tr>

                </tbody></table>
        </div>
    </div>
</div>
</body>
</html>