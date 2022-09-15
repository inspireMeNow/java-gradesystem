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
        .pageStyle {
            margin-right: 3px;
            cursor: pointer;
            height: 30px;
            line-height: 30px;
            padding: 0 15px;
            text-align: center;
            border: none;
            background: #31b9e6;
            color: #fff !important;
            font-size: 12px !important;
            font-weight: bold;
            border-radius: 3px;
            display: inline-block;
        }
    </style>
    <script type="text/javascript">

    </script>
<body>
<%@include file="../common/header_student.jsp"%>
<div class="container-fluid">
    <div style="border: 1px black solid;width: 1200px;position: absolute;left: 320px;">
        <div style="text-align: center;background-color: #575657FF;color: white;" class="WindowHead" id="WindowHead2">
            学籍信息
        </div>
        <div class="instruction-div clearfix">
            <div style="padding:8px 10px;">
                <span class="important">温馨提示：</span>以下是你的个人学籍信息，如有误可以联系教务处修改
            </div>
        </div>
        <div class="tb_cont">
            <table style="width:100%;border:0;border-collapse:collapse;border-spacing:0; padding:0;" class="frame-table">
                <tbody><tr>
                    <td>
                        <table class="table_border" id="table4">
                            <tbody><tr>
                                <td colspan="5" style="background-color:#F5F5F5;height:45px; font-size:20px; font-weight:bold; ">个人信息表</td>
                            </tr>
                            <tr>
                                <td style="width:12%;background-color:#FAF9EC;  ">学生学号</td>
                                <td style="width:25%; text-align:left;">${requestScope.student.sno}</td>
                                <td style="width:12%;background-color:#FAF9EC; ">学生姓名</td>
                                <td style="width:25%; text-align:left;">${requestScope.student.sname}</td>
                            </tr>
                            <tr>
                                <td style="background-color:#FAF9EC; text-align:center;">学生性别</td>
                                <td style="text-align:left;">&nbsp;${requestScope.student.ssex}</td>
                                <td style="background-color:#FAF9EC; ">专业学院</td>
                                <td style="text-align:left;">&nbsp;${requestScope.Coname}</td>
                            </tr>
                            <tr>
                                <td style="background-color:#FAF9EC; ">就读专业</td>
                                <td style="text-align:left;">&nbsp;${requestScope.student.spname}</td>
                                <td style="background-color:#FAF9EC; text-align:center;">专业班级</td>
                                <td style="text-align:left;">&nbsp;${requestScope.student.CLname}</td>
                            </tr>
                            </tbody></table>
                    </td>
                </tr>
                </tbody></table>

        </div>
        </div>
    </div>
</div>
</body>
</html>