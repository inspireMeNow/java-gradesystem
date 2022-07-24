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
    <script type="text/javascript">
        //自动定位滚动条的位置
        window.onbeforeunload = function () {
            var scrollPos;
            if (typeof window.pageYOffset != 'undefined') {
                scrollPos = window.pageYOffset;
            }
            else if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
                scrollPos = document.documentElement.scrollTop;
            }
            else if (typeof document.body != 'undefined') {
                scrollPos = document.body.scrollTop;
            }
            document.cookie = "scrollTop=" + scrollPos; //存储滚动条位置到cookies中
        }
        window.onload = function () {
            if (document.cookie.match(/scrollTop=([^;]+)(;|$)/) != null) {
                var arr = document.cookie.match(/scrollTop=([^;]+)(;|$)/); //cookies中不为空，则读取滚动条位置
                document.documentElement.scrollTop = parseInt(arr[1]);
                document.body.scrollTop = parseInt(arr[1]);
            }
        }
    </script>
</head>
<body>
<%@include file="../common/header_teacher.jsp"%>
<div class="container-fluid">
    <div style="border: 1px black solid;width: 1200px;position: absolute;left: 320px;">
        <div style="text-align: center;background-color: #575657FF;color: white;" class="WindowHead" id="WindowHead2">
            成绩管理
        </div>
        <div  style="border: 1px yellow solid" class="b_content clearfix">
            <div class="tb_cont">
                <table class="frame-table" style="width:100%;border:0;border-collapse:collapse;border-spacing:0; padding:0;">

                    <tbody>
                    <tr>
                        <td class="form-style">
                            <div style="font-weight:bold; color:#0000ff;">
                                ${requestScope.teacher.tno}&nbsp;${requestScope.teacher.tname}，以下为您所教授${requestScope.cla.CLname}的学生。
                                <a href="TeacherServlet?action=findEdu&Tno=${requestScope.teacher.tno}&nature=1" class="btn btn-blue" style="min-width: 25px;text-align: center;background-color:#0096f3;color: white;">返回上一级</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><table class="table_border" id="table3" >
                            <tbody><tr>
                                <th style="width:5%;">序号</th>
                                <th style="width:20%;">课程名</th>
                                <th style="width:20%;">学号</th>
                                <th style="width:20%;">姓名</th>
                                <th style="width:5%;">成绩</th>
                                <th style="width:15%;">备注</th>
                                <th style="width:15%;">操作</th>
                            </tr>
                            <c:set var="count" value="0"></c:set>
                            <c:forEach items="${requestScope.grades}" var="grades">
                                <c:set var="count" value="${count+1}"></c:set>
                            <tr>
                                <td>${count}</td>
                                <td>${grades.get(0).cname}</td>
                                <td>${grades.get(0).sno}</td>
                                <td>${grades.get(0).sname}</td>
                                <form action="TeacherServlet?action=addOrUpdateGrade" method="post">
                                <input type="hidden" id="Sno" name="Sno" value="${grades.get(0).sno}">
                                <input type="hidden" id="Cno" name="Cno" value="${grades.get(0).cno}">
                                    <input type="hidden" id="Tno" name="Tno" value="${requestScope.teacher.tno}">
                                    <input type="hidden" id="CLcode" name="CLcode" value="${requestScope.cla.CLcode}">
                                <td><input type="number" id="grade" name="grade" value="${grades.get(0).grade}"></td>
                                <td><input type="text" id="Remark" name="Remark" value="${grades.get(0).remark}"></td>
                                <td><input type="submit" class="btn btn-blue" style="min-width: 25px;text-align: center;background-color:#0096f3;color: white;"></td>
                                </form>
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