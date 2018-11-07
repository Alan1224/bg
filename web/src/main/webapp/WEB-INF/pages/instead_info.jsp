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
            <h2> 提现信息 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel  heading-border">
                <div class="panel-menu">
                    <div class="row">
                        <div class="hidden-xs hidden-sm col-md-3">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-refresh"><a href="${basePath}/"></a></i>
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
                                <th class="text-center fw600">商户号</th>
                                <th class="hidden-xs">交易金额</th>
                                <th class="hidden-xs">费率</th>
                                <th class="hidden-xs">可提现金额</th>
                                <th class="hidden-xs">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="#{insteadInfoDto}" var="suc">
                                <c:if test="${suc.cpId != 'selectAll'}">
                                    <c:if test="${suc.dealMoney != 0}">
                                <tr class="message-unread">
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11">${suc.cpId}</span>
                                    </td>
                                    <td>${suc.dealMoney / 100.00}</td>
                                    <td>${suc.rate}</td>
                                    <td>${suc.withdraw / 100.00}</td>
                                    <td>
                                        <a href="${basePath}/instead/instead?cpId=${suc.cpId}&withdraw=${suc.withdraw / 100.00}">提现</a>
                                        <a href="${basePath}/instead/history?cpId=${suc.cpId}">提现记录</a>
                                    </td>
                                </tr>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="bottom.jsp"/>
