<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>冷笑话管理系统</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/lib/font-awesome/css/font-awesome.css">

    <script src="${pageContext.request.contextPath }/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

    <!-- Demo page code -->
    
    <style type="text/css">
    	.table th ,.table td {text-align:center;}
    	.td2Left {text-align:left;}
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
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
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
            <h1 class="page-title">${jokeInfo.title }</h1>
<div class="btn-toolbar">
	<button class="btn btn-primary" onclick="checkPass(${jokeInfo.id})">审核通过</button>
    <button class="btn btn-primary" onclick="delJoke(${jokeInfo.id})">删除</button>
    <button class="btn btn-primary" onclick="javascript:history.back(-1)">返回</button>
  	<div class="btn-group"></div>
</div>
<div style="text-align:center;align:center;"><font color="red">${errorMsg }</font></div>
<div class="well">
    <table class="table">
      <tbody>
      	<tr>
          <td style="text-align: left">${jokeInfo.content }【<a href="${pageContext.request.contextPath }/admin/jokeInfo/toUpdate.do?id=${jokeInfo.id}">编辑</a>】</td>
          <c:set value="${fn:split(jokeInfo.image, '|')}" var="imgArray"/>
	  	  <c:if test="${imgArray[0] != null && imgArray[0] != '' }">
	  		<td>
	  			<c:forEach items="${imgArray}" var="img">
	  				<img src="${pageContext.request.contextPath}/showPic.jsp?picurl=${img}" >
	  			</c:forEach>
	  		</td>
	  	  </c:if>
        </tr>
      </tbody>
    </table>
</div>

<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Delete Confirmation</h3>
    </div>
    <div class="modal-body">
        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>Are you sure you want to delete the user?</p>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
        <button class="btn btn-danger" data-dismiss="modal">Delete</button>
    </div>
</div>

</div>
</div>
     <!-- footer  start -->
   <%@ include file="footer.jsp" %>
   <!-- footer  end -->
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath }/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
    function delJoke(id){
    	if(confirm("确定要删除吗？")){
    		window.location.href = '${pageContext.request.contextPath }/admin/jokeInfo/delete.do?id='+id;
    	}
    }
    function checkPass(id){
        window.location.href = '${pageContext.request.contextPath }/admin/jokeInfo/checkPass.do?id='+id;
    }
    </script>
  </body>
</html>