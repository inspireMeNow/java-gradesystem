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
    </style>
</head>
<body>
<%@include file="../common/header_teacher.jsp"%>
<div class="container-fluid">
    <div style="border: 1px black solid;width: 1200px;position: absolute;left: 320px;">
        <div style="text-align: center;background-color: #575657FF;color: white;" class="WindowHead" id="WindowHead2">
            <c:if test="${requestScope.title=='1'}">
                成绩管理
            </c:if>
            <c:if test="${requestScope.title=='2'}">
                查看课程
            </c:if>
        </div>
        <div  style="border: 1px yellow solid" class="b_content clearfix">
            <div class="tb_cont">
                <table class="frame-table" style="width:100%;border:0;border-collapse:collapse;border-spacing:0; padding:0;">

                    <tbody><tr>
                        <td class="form-style">
                            <div style="font-weight:bold; color:#0000ff;">
                                ${requestScope.teacher.tno}&nbsp;${requestScope.teacher.tname}，以下为您所教授的课程。
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="height:5px;"></td>
                    </tr>
                    <tr>
                        <td><table class="table_border" id="table3" >
                            <tbody><tr>
                                <th style="width:5%;">序号</th>
                                <th style="width:8%;">课程代码</th>
                                <th style="width:15%;">课程名称</th>
                                <th style="width:15%;">班级名称</th>
                                <c:if test="${requestScope.title=='1'}">
                                    <th style="width:5%;">操作</th>
                                </c:if>
                            </tr>
                            <c:set var="count" value="0"></c:set>
                            <c:forEach items="${requestScope.edus}" var="edus">
                                <c:set var="count" value="${count+1}"></c:set>
                            <tr>
                                <td>${count}</td>
                                <td>${edus.cno}</td>
                                <td>${edus.cname}</td>
                                <td>${edus.CLname}</td>
                                <c:if test="${requestScope.title=='1'}">
                                    <td><a href="TeacherServlet?action=findGradeByCnoCLcode&Cno=${edus.cno}&Tno=${requestScope.teacher.tno}&Clcode=${edus.CLcode}" class="btn btn-blue" style="min-width: 25px;text-align: center;background-color:#0096f3;color: white;">查看详情</a></td>
                                </c:if>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>