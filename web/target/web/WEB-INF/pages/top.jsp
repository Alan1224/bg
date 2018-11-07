<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/13
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!-- Mirrored from admindesigns.com/demos/absolute/1.1/admin_forms-validation.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 06 Aug 2015 02:56:15 GMT -->
<head>
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">

    <title> 讯支付 </title>

    <link rel="stylesheet" type="text/css" href="${stylePath}/assets/skin/default_skin/css/theme.css">
    <link rel="stylesheet" type="text/css" href="${stylePath}/assets/admin-tools/admin-forms/css/admin-forms.css">
    <link rel="shortcut icon" href="${stylePath}/assets/img/favicon.ico">
</head>

<body class="admin-validation-page" data-spy="scroll" data-target="#nav-spy" data-offset="200">
<div id="main">
    <header class="navbar navbar-fixed-top navbar-shadow">
        <div class="navbar-branding">
            <a class="navbar-brand" href="dashboard.html">
                <b>讯支付</b>
            </a>
            <span id="toggle_sidemenu_l" class="ad ad-lines"></span>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown menu-merge">
                <a href="#" class="dropdown-toggle fw600 p15" data-toggle="dropdown">
                    <img src="${stylePath}/assets/img/avatars/5.jpg" alt="avatar" class="mw30 br64">
                    <span class="hidden-xs pl15"> ${sessionScope.get("tUser").username} </span>
                    <span class="caret caret-tp hidden-xs"></span>
                </a>
                <ul class="dropdown-menu list-group dropdown-persist w250" role="menu">
                    <%--<li class="list-group-item">--%>
                        <%--<a href="${basePath}/self" class="animated animated-short fadeInUp">--%>
                            <%--<span class="fa fa-user"></span> 个人信息--%>
                            <%--<span class="label label-warning"></span>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <li class="list-group-item">
                        <a href="${basePath}/to_change_password" class="animated animated-short fadeInUp">
                            <span class="fa fa-gear"></span> 设置密码 </a>
                    </li>
                    <li class="dropdown-footer">
                        <a href="${basePath}/quit" class="">
                            <span class="fa fa-power-off pr5"></span> 退出 </a>
                    </li>
                </ul>
            </li>
        </ul>
    </header>
    <aside id="sidebar_left" class="nano nano-light affix">
        <div class="sidebar-left-content nano-content">
            <header class="sidebar-header">
                <div class="sidebar-widget author-widget">
                    <div class="media">
                        <a class="media-left" href="#">
                            <img src="${stylePath}/assets/img/avatars/3.jpg" class="img-responsive">
                        </a>
                        <div class="media-body">
                            <div class="media-author">${sessionScope.get("tUser").username}</div>
                            <div class="media-links">
                                <a href="${basePath}/quit">退出</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="sidebar-widget search-widget hidden">
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="fa fa-search"></i>
                        </span>
                        <input type="text" id="sidebar-search" class="form-control" placeholder="Search...">
                    </div>
                </div>
            </header>
            <ul class="nav sidebar-menu">
                <li class="sidebar-label pt20">日常管理</li>
                <li>
                    <a href="${basePath}/order/to_show_order">
                        <span class="glyphicon glyphicon-book"></span>
                        <span class="sidebar-title">查询订单</span>
                        <%--<span class="sidebar-title-tray">--%>
                <%--<span class="label label-xs bg-primary">New</span>--%>
              <%--</span>--%>
                    </a>
                </li>
                <c:if test="${sessionScope.get('tUser').power > 2}">
                <li class="active">
                    <a href="${basePath}/order/show_cp">
                        <span class="glyphicon glyphicon-home"></span>
                        <span class="sidebar-title">第三方支付统计</span>
                    </a>
                </li>
                <li>
                    <a href="${basePath}/order/show_sp">
                        <span class="fa fa-calendar"></span>
                        <span class="sidebar-title">通道支付统计</span>
                    </a>
                </li>
                </c:if>
                <li class="sidebar-label pt15">基础信息管理</li>
                <c:if test="${sessionScope.get('tUser').power > 2}">
                <li>
                    <a class="accordion-toggle" href="#">
                        <span class="glyphicon glyphicon-check"></span>
                        <span class="sidebar-title">实时成功率</span>
                        <span class="caret"></span>
                    </a>
                    <ul class="nav sub-nav">
                        <li>
                            <a href="${basePath}/order/show?time=10">
                                <span class="glyphicon glyphicon-calendar"></span> 十分钟 </a>
                        </li>
                        <li>
                            <a href="${basePath}/order/show?time=30">
                                <span class="glyphicon glyphicon-calendar"></span> 半小时 </a>
                        </li>
                        <li class="active">
                            <a href="${basePath}/order/show?time=60">
                                <span class="glyphicon glyphicon-check"></span> 一小时 </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a class="accordion-toggle" href="#">
                        <span class="fa fa-columns"></span>
                        <span class="sidebar-title">通道管理</span>
                        <span class="caret"></span>
                    </a>
                    <ul class="nav sub-nav">
                        <li>
                            <a href="${basePath}/cp/list">
                                <span class="glyphicon glyphicon-calendar"></span> Cp配置信息 </a>
                        </li>
                        <li class="active">
                            <a href="${basePath}/cp/list_lately">
                                <span class="glyphicon glyphicon-check"></span> 最近使用CP </a>
                        </li>
                        <li class="active">
                            <a href="${basePath}/cp/to_edit_type">
                                <span class="glyphicon glyphicon-check"></span> 所有商户更改 </a>
                        </li>
                        <li class="active">
                            <a href="${basePath}/cp/to_edit_cp">
                                <span class="glyphicon glyphicon-check"></span> 商户通道更改 </a>
                        </li>
                    </ul>
                </li>
                </c:if>
                <li>
                    <a class="accordion-toggle" href="#">
                        <span class="fa fa-columns"></span>
                        <span class="sidebar-title">提现</span>
                        <span class="caret"></span>
                    </a>
                    <ul class="nav sub-nav">
                        <li>
                            <a href="${basePath}/instead/to_instead"><span class="glyphicon glyphicon-calendar"></span> 查看信息 </a>
                            <a href="${basePath}/instead/history"><span class="glyphicon glyphicon-calendar"></span> 查看记录 </a>
                        </li>
                    </ul>
                </li>
            </ul>
            <div class="sidebar-toggle-mini">
                <a href="#">
                    <span class="fa fa-sign-out"></span>
                </a>
            </div>
        </div>
    </aside>
    <section id="content_wrapper">
