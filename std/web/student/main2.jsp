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
    <script type="text/javascript" src="../script/jquery-3.6.0.js"></script>
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
<%@include file="../common/header_student.jsp"%>
<div class="container-fluid">
    <div style="border: 1px black solid;width: 1200px;position: absolute;left: 320px;">
        <div style="text-align: center;background-color: #575657FF;color: white;" class="WindowHead" id="WindowHead2">
            成绩查询
        </div>
        <div  style="border: 1px yellow solid" class="b_content clearfix">
            <div class="tb_cont">
                <table class="frame-table" style="width:100%;border:0;border-collapse:collapse;border-spacing:0; padding:0;">

                    <tbody><tr>
                        <td class="form-style">
                            <div style="font-weight:bold; color:#0000ff;">
                                ${sessionScope.student.sno} ${sessionScope.student.sname}同学，以下为按照培养计划显示的课程，以课程代码为准，相同课程代码取最高分，仅供参考，详情请查看全部成绩。
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
                                <th style="width:20%;">课程名称</th>
                                <th style="width:5%;">性质</th>
                                <th style="width:5%;">学分</th>
                                <th style="width:5%;">开课学年</th>
                                <th style="width:5%;">开课学期</th>
                                <th style="width:8%;">成绩</th>
                                <th style="width:5%;">备注</th>
                            </tr>
                            <c:forEach items="${requestScope.grade}" var="grade">
                            <tr>
                                <td>1</td>
                                <td>${grade.cno}</td>
                                <td>${grade.cname}</td>
                                <td>${grade.cproperty}</td>
                                <td>${grade.credit}</td>
                                <td>${grade.syear}</td>
                                <td>${grade.term}</td>
                                <c:if test="${empty grade.remark}">
                                    <c:if test="${grade.grade==0}">
                                        <td>null</td>
                                    </c:if>
                                    <c:if test="${grade.grade!=0}">
                                        <td>${grade.grade}</td>
                                    </c:if>
                                </c:if>
                                <c:if test="${not empty grade.remark}">
                                    <td>${grade.grade}</td>
                                </c:if>
                                <td><font color="red">${grade.remark}</font></td>
                            </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="9">成绩平均分：${requestScope.average}</td>
                            </tr>
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