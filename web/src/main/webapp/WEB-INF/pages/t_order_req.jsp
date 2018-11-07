<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/8
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="top.jsp"/>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 实时成功率 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel  heading-border">
                <div class="panel-menu">
                    <div class="row">
                        <div class="hidden-xs hidden-sm col-md-3">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-refresh"><a href="${basePath}/t_order_req/show_${type}"></a></i>
                                </button>
                                <%--<button type="button" class="btn btn-default light">--%>
                                    <%--<i class="fa fa-trash"></i>--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-default light">--%>
                                    <%--<i class="fa fa-plus" onclick="javascript:window.location.href='/employee/to_add';"></i>--%>
                                <%--</button>--%>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-9 text-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-chevron-left"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-chevron-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body pn">
                        <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                            <thead>
                            <tr class="">
                                <th class="text-center fw600">spId</th>
                                <th class="hidden-xs">spId</th>
                                <th class="hidden-xs">平台单号</th>
                                <th class="hidden-xs">下游单号</th>
                                <th class="hidden-xs">金额</th>
                                <th class="hidden-xs">创建时间</th>
                                <th class="hidden-xs">回调时间</th>
                                <th class="hidden-xs">状态</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr class="message-unread">
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11">${order.cpId}</span>
                                    </td>
                                    <td>${order.spId}</td>
                                    <td>${order.ffId}</td>
                                    <td>${order.cpParam}</td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11">${order.fee / 100.00}</span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatDate pattern="yy-MM-dd hh:mm:ss" value="${order.createTime}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatDate pattern="yy-MM-dd hh:mm:ss" value="${order.updateTime}"/></span>
                                    </td>
                                    <c:if test="${order.isSuccess == 1}">
                                        <c:if test="${order.synStatus != 1}">
                                            <td>
                                                <a href="${basePath}/order/feeBack?ffId=${order.ffId}&cpParam=${order.cpParam}&fee=${order.fee}&cpId=${order.cpId}">回调</a>
                                            </td>
                                        </c:if>
                                        <c:if test="${order.synStatus == 1}">
                                            <td>
                                                <span class="badge badge-warning mr10 fs11">正常</span>
                                            </td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${order.isSuccess != 1}">
                                        <td class="hidden-xs">
                                            <span class="badge badge-warning mr10 fs11">未支付</span>
                                        </td>
                                    </c:if>

                                </tr>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="bottom.jsp"/>
