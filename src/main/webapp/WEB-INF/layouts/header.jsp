<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="header">
    <div id="title">
        <h1><a href="${ctx}">CMMS</a>
            <small>--长虹移动应用管理系统</small>
            <shiro:user>
                <div class="dropdown pull-right">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu"
                            data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span>
                        <shiro:principal property="name"/>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-right" role="menu"
                        aria-labelledby="dropdownMenu">
                        <shiro:hasRole name="admin">
                            <li role="presentation"><a href="${ctx}/user">用户管理</a></li>
                            <li role="presentation"><a href="${ctx}/device">设备管理</a></li>
                            <li role="presentation"><a href="${ctx}/container/upgrade">版本升级</a></li>
                            <li role="presentation"><a href="${ctx}/container/feedback">意见反馈</a></li>
                            <li role="presentation"><a href="${ctx}/developer/filling">添加开发者</a></li>
                            <li class="divider"></li>
                        </shiro:hasRole>
                        <li><a href="${ctx}/profile">修改个人信息</a></li>
                        <li><a href="${ctx}/logout">注销</a></li>
                    </ul>
                </div>
            </shiro:user>
        </h1>
    </div>
</div>
