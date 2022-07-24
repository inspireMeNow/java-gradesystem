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
    <script type="text/javascript" src="../script/jquery-3.6.0.js"></script>
    <script type="text/javascript">
        $(function () {
            //用于删除的确定提示操作
            $("#del").click(function () {
                //在事件的function函数中，有一个this对象这个对象是当前正在相应事件的dom对象
                /*
                * confirm是确认提示框函数
                * 它有两个按钮，一个确认，一个是取消
                * 返回true表示点击了确认，返回false表示点击了取消
                * */
                return confirm("你确定要删除【"+$(this).parent().parent().find("td:nth-child(3)").text()+"】所教的"+$(this).parent().parent().find("td:nth-child(5)").text()+"?");
            });
            $("#add").click(function (){

            });
        });
    </script>
</head>
<body>
<%@include file="../common/header_admin.jsp"%>
<div class="container-fluid">
    <div style="border: 1px black solid;width: 1200px;position: absolute;left: 320px;">
        <div style="text-align: center;background-color: #575657FF;color: white;" class="WindowHead" id="WindowHead2">
            课程管理
        </div>
        <div  style="border: 1px yellow solid" class="b_content clearfix">
            <div class="tb_cont">
                <table class="frame-table" style="width:100%;border:0;border-collapse:collapse;border-spacing:0; padding:0;">

                    <tbody><tr>
                        <td class="form-style">
                            <div style="font-weight:bold; color:#0000ff;">
                                admin,以下为<span style="color: red">${requestScope.edus.get(0).getCname()}</span>教学信息。
                                <a href="adminServlet?action=findAllCourse" class="btn btn-blue" style="min-width: 25px;text-align: center;background-color:#0096f3;color: white;">返回上一级</a>
                                <a href="admin/edu_update.jsp" class="btn btn-blue" id="add" style="min-width: 25px;text-align: center;background-color:#0096f3;color: white;">添加教学信息</a>
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
                                <th style="width:8%;">工号</th>
                                <th style="width:15%;">姓名</th>
                                <th style="width:15%;">班级代码</th>
                                <th style="width:15%;">所教班级</th>
                                <th style="width:10%;">操作</th>
                            </tr>
                            <c:set var="count" value="0"></c:set>
                            <c:forEach items="${requestScope.edus}" var="edus">
                                <c:set var="count" value="${count+1}"></c:set>
                            <tr>
                                <td>${count}</td>
                                <td>${edus.tno}</td>
                                <td>${edus.tname}</td>
                                <td>${edus.CLcode}</td>
                                <td>${edus.CLname}</td>
                                <td>
                                    <a href="adminServlet?action=findEduByCnoTnoCLcode&Cno=${edus.cno}&Tno=${edus.tno}&Clcode=${edus.CLcode}&Cname=${requestScope.Cname}" class="btn btn-blue" style="min-width: 25px;text-align: center;background-color:#0096f3;color: white;">修改</a>
                                    <a href="adminServlet?action=deleteEdu&Cno=${edus.cno}&Tno=${edus.tno}&Clcode=${edus.CLcode}" class="btn btn-blue" id="del" style="min-width: 25px;text-align: center;background-color:#0096f3;color: white;">删除</a>
                                </td>
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