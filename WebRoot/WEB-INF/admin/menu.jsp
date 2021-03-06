<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="span3">
      <div class="sidebar-nav">
          <div class="nav-header" data-toggle="collapse" data-target="#joke-menu"><i class="icon-dashboard"></i>内容管理</div>
          <ul id="joke-menu" class="nav nav-list collapse in">
              <li><a href="${pageContext.request.contextPath}/login.do">首页</a></li>
              <li ><a href="${pageContext.request.contextPath}/admin/jokeInfo/list.do">笑话列表</a></li>
              <li ><a href="${pageContext.request.contextPath}/user.html">Sample Item</a></li>
              <li ><a href="${pageContext.request.contextPath}/gallery.html">Gallery</a></li>
              <li ><a href="${pageContext.request.contextPath}/calendar.html">Calendar</a></li>
              <li ><a href="${pageContext.request.contextPath}/faq.html">Faq</a></li>
              <li ><a href="${pageContext.request.contextPath}/help.html">Help</a></li>
          </ul>
          
          <div class="nav-header" data-toggle="collapse" data-target="#keywords-menu"><i class="icon-briefcase"></i>禁词管理</div>
	      <ul id="keywords-menu" class="nav nav-list collapse in">
	          <li ><a href="${pageContext.request.contextPath}/admin/keyword/list.do">禁词管理</a></li>
	      </ul>
	      
	      <div class="nav-header" data-toggle="collapse" data-target="#user-menu"><i class="icon-briefcase"></i>用户管理</div>
	      <ul id="user-menu" class="nav nav-list collapse in">
	          <li ><a href="${pageContext.request.contextPath}/admin/manager/list.do">用户列表</a></li>
	      </ul>
          
          <div class="nav-header" data-toggle="collapse" data-target="#manager-menu"><i class="icon-briefcase"></i>账号管理<span class="label label-info">+10</span></div>
	      <ul id="manager-menu" class="nav nav-list collapse in">
	          <li ><a href="${pageContext.request.contextPath}/admin/manager/list.do">账号列表</a></li>
	          <li ><a href="${pageContext.request.contextPath}/admin/manager/add.do">新增账号</a></li>
	          <li ><a href="${pageContext.request.contextPath}/admin/manager/toResetPwd.do">重置密码</a></li>
	      </ul>

          <div class="nav-header" data-toggle="collapse" data-target="#system-menu"><i class="icon-exclamation-sign"></i>系统管理</div>
          <ul id="system-menu" class="nav nav-list collapse in">
	          <li ><a href="${pageContext.request.contextPath}/403.html">403 page</a></li>
	          <li ><a href="${pageContext.request.contextPath}/404.html">404 page</a></li>
	          <li ><a href="${pageContext.request.contextPath}/500.html">500 page</a></li>
	          <li ><a href="${pageContext.request.contextPath}/503.html">503 page</a></li>
          </ul>

          <div class="nav-header" data-toggle="collapse" data-target="#legal-menu"><i class="icon-legal"></i>Legal</div>
          <ul id="legal-menu" class="nav nav-list collapse in">
	          <li ><a href="${pageContext.request.contextPath}/privacy-policy.html">Privacy Policy</a></li>
	          <li ><a href="${pageContext.request.contextPath}/terms-and-conditions.html">Terms and Conditions</a></li>
          </ul>
    </div>
</div>