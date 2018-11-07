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
            <h2> 提现 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel  heading-border">
                <div class="panel-menu">
                    <div class="row">
                        <div class="hidden-xs hidden-sm col-md-3">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-refresh"><a href="/bg/t_order_req/show_${type}"></a></i>
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
                    <%--<c:if test="${rough == 'rough'}">--%>
                        <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                            <thead>
                            <tr class="">
                                <th class="hidden-xs">提现单号</th>
                                <th class="hidden-xs">cpId</th>
                                <th class="hidden-xs">金额</th>
                                <th class="hidden-xs">状态</th>
                                <th class="hidden-xs">创建时间</th>
                                <th class="hidden-xs">更新时间</th>
                                <th class="hidden-xs">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="#{history}" var="suc" end="10">
                                <%--<c:if test="${suc.cpId != 'selectAll'}">--%>
                                <tr class="message-unread">
                                    <td>${suc.insteadId}</td>
                                    <td>${suc.cpId}</td>
                                    <td>${suc.money}</td>
                                    <td class="hidden-xs">
                                        <c:if test="${suc.status == 0}">
                                            <span class="badge badge-warning mr10 fs11">未支付</span>
                                        </c:if>
                                        <c:if test="${suc.status == 1}">
                                            <span class="badge badge-warning mr10 fs11">已支付</span>
                                        </c:if>
                                        <c:if test="${suc.status == 2}">
                                            <span class="badge badge-warning mr10 fs11">已拒绝</span>
                                        </c:if>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatDate value="${suc.createTime}" pattern="YY-MM-dd HH:mm:ss"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><spring:formatDate value="${suc.updateTime}" pattern="YY-MM-dd HH:mm:ss"/></span>
                                    </td>
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11"><a href="download?insteadId=${suc.insteadId}">下载</a></span>
                                        <c:if test="${sessionScope.get('tUser').power == 9 && suc.status == 0}">
                                        <span class="badge badge-warning mr10 fs11"><a href="refuse?insteadId=${suc.insteadId}&money=${suc.money}&cpId=${suc.cpId}">拒绝</a></span>
                                        <span class="badge badge-warning mr10 fs11"><a href="handle?insteadId=${suc.insteadId}&cpId=${suc.cpId}">已打款</a></span>
                                        </c:if>
                                    </td>
                                </tr>
                                <%--</c:if>--%>
                                
                            </c:forEach>
                            </tbody>
                        </table>
                    <%--</c:if>--%>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="bottom.jsp"/>
