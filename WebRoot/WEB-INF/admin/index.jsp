<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>冷笑话管理系统</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">

    <script src="${pageContext.request.contextPath}/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

    <!-- Demo page code -->
    
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body> 
  <!--<![endif]-->
    <!-- header.jsp   start -->
    <%@ include file="header.jsp" %>
    <!-- header.jsp   end -->

  <div class="container-fluid">
        
    <div class="row-fluid">
    	<!-- menu.jsp   start -->
        <%@ include file="menu.jsp" %>
        <!-- menu.jsp   end -->
        <div class="span9">
			<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jqplot/jquery.jqplot.min.js"></script>
			<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/graphDemo.js"></script>

			<div class="stats">
			    <p class="stat"><span class="number">53</span>tickets</p>
			    <p class="stat"><span class="number">27</span>tasks</p>
			    <p class="stat"><span class="number">15</span>waiting</p>
			</div>
			<h1 class="page-title">Dashboard</h1>
			<div class="row-fluid">
			    <div class="block">
			        <p class="block-heading" data-toggle="collapse" data-target="#chart-container">Performance Chart</p>
			        <div id="chart-container" class="block-body collapse in">
			            <div id="line-chart"></div>
			        </div>
			    </div>
			</div>

			<div class="row-fluid">
			    <div class="block span6">
			        <div class="block-heading" data-toggle="collapse" data-target="#tablewidget">管理员</div>
			        <div id="tablewidget" class="block-body collapse in">
			            <table class="table">
			              <thead>
			                <tr>
			                  <th>账号ID</th>
			                  <th>账号名称</th>
			                  <th>状态</th>
			                </tr>
			              </thead>
			              <tbody>
			              <c:forEach items="${managerList}" var="ma" varStatus="m">
			                <tr>
			                  <td>${ma.accountId }</td>
			                  <td>${ma.accountName }</td>
			                  <td><c:if test="${ma.state == 0}" var="sta">有效</c:if><c:if test="${!sta}">无效</c:if></td>
			                </tr>
			                </c:forEach>
			              </tbody>
			            </table>
			            <p><a href="${pageContext.request.contextPath}/admin/manager/list.do?pageIndex=1">More...</a></p>
			        </div>
			    </div>
			    <div class="block span6">
			        <div class="block-heading" data-toggle="collapse" data-target="#widget1container">Collapsible </div>
			        <div id="widget1container" class="block-body collapse in">
			            <h2>Using Ruby?</h2>
			            <p>This template was developed with <a href="http://middlemanapp.com/" target="_blank">Middleman</a> and includes .erb layouts and views.</p>
			            <p>All of the views you see here (sign in, sign up, users, etc) are already split up so you don't have to waste your time doing it yourself!</p>
			            <p>The layout.erb file includes the header, footer, and side navigation and all of the views are broken out into their own files.</p>
			            <p>If you aren't using Ruby, there is also a set of plain HTML files for each page, just like you would expect.</p>
			        </div>
			    </div>
			</div>
			<div class="copyrights">Collect from <a href="http://www.mycodes.net/" title="后台模板" target="_blank">后台模板</a></div>
			<div class="row-fluid">
			    <div class="block span6">
			        <div class="block-heading" data-toggle="collapse" data-target="#widget2container">History<span class="label label-warning">+10</span></div>
			        <div id="widget2container" class="block-body collapse in">
			            <table class="table">
			              <tbody>
			                  <tr>
			                      <td>
			                          <p><i class="icon-user"></i> Mark Otto</p>
			                      </td>
			                      <td>
			                          <p>Amount: $1,247</p>
			                      </td>
			                      <td>
			                          <p>Date: 7/19/2012</p>
			                          <a href="#">View Transaction</a>
			                      </td>
			                  </tr>
			                  <tr>
			                      <td>
			                          <p><i class="icon-user"></i> Audrey Ann</p>
			                      </td>
			                      <td>
			                          <p>Amount: $2,793</p>
			                      </td>
			                      <td>
			                          <p>Date: 7/12/2012</p>
			                          <a href="#">View Transaction</a>
			                      </td>
			                  </tr>
			                  <tr>
			                      <td>
			                          <p><i class="icon-user"></i> Mark Tompson</p>
			                      </td>
			                      <td>
			                          <p>Amount: $2,349</p>
			                      </td>
			                      <td>
			                          <p>Date: 3/10/2012</p>
			                          <a href="#">View Transaction</a>
			                      </td>
			                  </tr>
			                  <tr>
			                      <td>
			                          <p><i class="icon-user"></i> Ashley Jacobs</p>
			                      </td>
			                      <td>
			                          <p>Amount: $1,192</p>
			                      </td>
			                      <td>
			                          <p>Date: 1/19/2012</p>
			                          <a href="#">View Transaction</a>
			                      </td>
			                  </tr>
			                    
			              </tbody>
			            </table>
			        </div>
			    </div>
			    <div class="block span6">
			        <p class="block-heading">Not Collapsible</p>
			        <div class="block-body">
			            <h2>Tip of the Day</h2>
			            <p>Fava bean jícama seakale beetroot courgette shallot amaranth pea garbanzo carrot radicchio peanut leek pea sprouts arugula brussels sprout green bean. Spring onion broccoli chicory shallot winter purslane pumpkin gumbo cabbage squash beet greens lettuce celery. Gram zucchini swiss chard mustard burdock radish brussels sprout groundnut. Asparagus horseradish beet greens broccoli brussels sprout bitterleaf groundnut cress sweet pepper leek bok choy shallot celtuce scallion chickpea radish pea sprouts.</p>
			            <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
			        </div>
			    </div>
			</div>
        </div>
     </div>
  </div>
  <!-- footer  start -->
   <%@ include file="footer.jsp" %>
   <!-- footer  end -->
   <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
  </body>
</html>