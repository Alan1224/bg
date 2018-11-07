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
                    <c:if test="${rough == 'rough'}">
                        <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                            <thead>
                            <tr class="">
                                <c:if test="${type == 'sp'}"><th class="text-center fw600">spId</th></c:if>
                                <c:if test="${type == 'cp'}"><th class="text-center fw600">cpId</th></c:if>
                                <th class="hidden-xs">名称</th>
                                <th class="hidden-xs">交易总数</th>
                                <th class="hidden-xs">成功数</th>
                                <th class="hidden-xs">成功率</th>
                                <th class="hidden-xs">一小时</th>
                                <th class="hidden-xs">半小时</th>
                                <th class="hidden-xs">十分钟</th>
                                <th class="hidden-xs">成功金额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="#{success}" var="suc">
                                <c:if test="${suc.cpId != 'selectAll'}">
                                    <c:if test="${suc.count != null}">
                                <tr class="message-unread">
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11">${suc.cpId}</span>
                                    </td>
                                    <td>${suc.name}</td>
                                    <td>${suc.count}</td>
                                    <td>${suc.success}</td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rate}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rateHOUR}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rateMinute30}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rateMinute10}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.successFee}"/></span>
                                    </td>
                                </tr>
                                    </c:if>
                                </c:if>
                                <c:if test="${suc.cpId == 'selectAll'}">
                                <tr class="message-unread">
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11">${suc.cpId}</span>
                                    </td>
                                    <td>${suc.name}</td>
                                    <td>${suc.count}</td>
                                    <td>${suc.success}</td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rate}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rateHOUR}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rateMinute30}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rateMinute10}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.successFee}"/></span>
                                    </td>
                                </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${rough == 'detail'}">
                        <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                            <thead>
                            <tr class="">
                                <th class="text-center fw600">cpId</th>
                                <th class="hidden-xs">名称</th>
                                <th class="hidden-xs">交易总数</th>
                                <th class="hidden-xs">成功数</th>
                                <th class="hidden-xs">成功率</th>
                                <th class="hidden-xs">总金额金额</th>
                                <th class="hidden-xs">成功金额</th>
                                <th class="hidden-xs">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="#{success}" var="suc">
                                <c:if test="${suc.count != null}">
                                <tr class="message-unread">
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11">${suc.cpId}</span>
                                    </td>
                                    <td>${suc.name}</td>
                                    <td>${suc.count}</td>
                                    <td>${suc.success}</td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.rate}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.fee}"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatNumber value="${suc.successFee}"/></span>
                                    </td>
                                    <td>
                                        <a href="${basePath}/cp/to_edit?cpId=${suc.cpId}">编辑</a>
                                    </td>
                                </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="bottom.jsp"/>
