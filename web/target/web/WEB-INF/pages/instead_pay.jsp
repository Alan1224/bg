<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12
  Time: 11:36
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
            <div class="panel heading-border">
                <form id="admin-form" name="addForm" action="${basePath}/instead/instead_update?cpId=${cpId}&withdraw=${withdraw}" enctype="multipart/form-data" method="post">
                    <div class="panel-body bg-light">
                        <div class="section-divider mt20 mb40">
                            <span> 基本信息 </span>
                        </div>
                        <div class="section row">
                            <div class="col-md-6">
                                    <input name="cpId" id="cpId" class="gui-input" placeholder="手续费..." readonly="true" value="${cpId}"/>
                                </label>
                            </div>
                        </div>
                        <div class="section row">
                            <div class="col-md-6">
                                    <input name="withdraw" id="withdraw" class="gui-input" placeholder="可提现..." value="${withdraw}" readonly="true"/>
                                </label>
                            </div>
                        </div>
                        <div class="section row">
                            <div class="col-md-6">
                                <td width="30%">
                                    <input type="file" name="excel"/>
                                </td>
                            </div>
                        </div>
                        <div class="panel-footer text-right">
                            <button type="submit" class="button" onclick="document.getElementById('addForm').submit();"> 提现 </button>
                            <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                        </div>
                    </div>
                </form>
                <c:if test="${instead!= null}">
                <div class="panel-body pn">
                    <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                        <thead>
                        <tr class="">
                            <th class="text-center fw600">收款人</th>
                            <th class="hidden-xs">银行卡</th>
                            <th class="hidden-xs">卡款金额元</th>
                            <th class="hidden-xs">开户银行</th>
                            <th class="hidden-xs">银行地址</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="#{instead}" var="suc">
                                <tr class="message-unread">
                                    <td class="hidden-xs">
                                        <span class="badge badge-warning mr10 fs11">${suc.namePay}</span>
                                    </td>
                                    <td>${suc.car}</td>
                                    <td>${suc.money}</td>
                                    <td>${suc.nameBank}</td>
                                    <td>${suc.bankAddress}</td>
                                </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                </c:if>
            </div>
        </div>
    </div>
</section>
<jsp:include page="bottom.jsp"/>
