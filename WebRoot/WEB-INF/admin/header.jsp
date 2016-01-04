<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <ul class="nav pull-right">
                <li id="fat-menu" class="dropdown">
                    <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="icon-user"></i> ${sessionScope.currentManager.accountName}
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a tabindex="-1" href="#">设置</a></li>
                        <li class="divider"></li>
                        <li><a tabindex="-1" href="${pageContext.request.contextPath}/admin/logout.do">退出</a></li>
                    </ul>
                </li>
            </ul>
            <a class="brand" href="index.html"><span class="first">dove</span> <span class="second">蒂鸥维毅</span></a>
        </div>
    </div>
</div>