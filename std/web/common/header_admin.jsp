<%--
  Created by IntelliJ IDEA.
  User: fengzhanwei
  Date: 2022/6/27
  Time: 下午 8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="tm-header" id="tm-header">
    <div class="tm-header-wrapper">
        <button class="navbar-toggler" type="button" aria-label="Toggle navigation">
        </button>
        <div class="tm-site-header">
            <h1 class="text-center">管理系统</h1>
        </div>
        <nav class="tm-nav" id="tm-nav">
            <ul>
                <li class="tm-nav-item active"><a href="adminServlet?action=findAllCollege&nature=1" class="tm-nav-link">
                    <i class="fas fa-home"></i>
                    学生信息
                </a></li>
                <li class="tm-nav-item"><a href="adminServlet?action=findAllUsr" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    用户管理
                </a></li>
                <li class="tm-nav-item"><a href="adminServlet?action=findAllCollege&nature=3" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    班级管理
                </a></li>
                <li class="tm-nav-item"><a href="adminServlet?action=findAllCollege&nature=4" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    专业管理
                </a></li>
                <li class="tm-nav-item"><a href="adminServlet?action=findAllCourse" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    课程管理
                </a></li>
                <li class="tm-nav-item"><a href="adminServlet?action=findAllCollege&nature=6" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    学院管理
                </a></li>
                <li class="tm-nav-item"><a href="userServlet?action=loginout" class="tm-nav-link">
                    <i class="fas fa-pen"></i>
                    退出
                </a></li>
            </ul>
        </nav>
    </div>
</header>
