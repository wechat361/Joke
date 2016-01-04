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
    
	<script>window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor/";</script>
    <!-- Demo page code -->
    
    <style type="text/css">
    	.table th ,.table td {text-align:center;}
    	.td2Left {text-align:left;}
        #line-chart { height:300px; width:800px; margin: 0px auto; margin-top: 1em; }
        .brand { font-family: georgia, serif; }
        .brand .first { color: #ccc; font-style: italic; }
        .brand .second { color: #fff; font-weight: bold; }
        .btnright {text-align: right !important; margin-right: 20px;}
        .btnleft {margin-left: 0px;}
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
        <form id="jokeFormId" action="${pageContext.request.contextPath }/admin/jokeInfo/update.do" method="post" enctype="multipart/form-data">
            <h1 class="page-title">${jokeInfo.title }</h1>
            <input type="hidden" name="id" value="${jokeInfo.id }">
            <input type="hidden" name="image" value="${jokeInfo.image}">
			<div class="btn-toolbar btnleft">
				<button class="btn btn-primary" onclick="save()">保存</button>
			    <button class="btn btn-primary" onclick="goback()">返回</button>
			  	<div class="btn-group"></div>
			</div>
			<div style="text-align:center;align:center;"><font color="red">${errorMsg }</font></div>
			<div class="well">
				标题：<input type="text" name="title" value="${jokeInfo.title}" readonly="readonly">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	来源：<input type="text" name="source" value="${jokeInfo.source}" readonly="readonly"><br>
            	分类：<select name="typeId">
            			<option value="">-选择分类-</option>
            		<c:forEach items="${jokeTypeList}" var="jokeType">
            			<c:if test="${jokeType.typeId==jokeInfo.typeId }" var="flag">
            				<option value="${jokeType.typeId}" selected="selected">${jokeType.typeName }</option>
            			</c:if>
            			<c:if test="${!flag}">
            				<option value="${jokeType.typeId}">${jokeType.typeName }</option>
            			</c:if>
            		</c:forEach>
            	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	状态：<select name="state">
            			<c:if test="${jokeInfo.state==1 }" var="flag">
            				<option value="0">未审核</option>
            				<option value="1" selected="selected">审核</option>
            			</c:if>
            			<c:if test="${jokeInfo.state!=1 }">
            				<option value="0" selected="selected">未审核</option>
            				<option value="1">审核</option>
            			</c:if>
            		</select>
				<!-- 加载编辑器的容器 此处script的name值即为textarea的name值 -->
				<script id="container" name="content" type="text/plain">
    			${jokeInfo.content}
				</script>
				<!-- 配置文件 -->
				<script type="text/javascript" src="${pageContext.request.contextPath }/ueditor/ueditor.config.js"></script>
				<!-- 编辑器源码文件 -->
				<script type="text/javascript" src="${pageContext.request.contextPath }/ueditor/ueditor.all.js"></script>
				<!-- 实例化编辑器 -->
				<script type="text/javascript">
				    var ue = UE.getEditor('container');
				</script>
			</div>
			<div class="btn-toolbar btnright">
				<button class="btn btn-primary" onclick="save()">保存</button>
			    <button class="btn btn-primary" onclick="javascript:history.back(-1)">返回</button>
			  	<div class="btn-group"></div>
			</div>
		</form>
			
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
</div>
     <!-- footer  start -->
   <%@ include file="footer.jsp" %>
   <!-- footer  end -->
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath }/lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
    function save(){
        alert("提交");
        $("#jokeFormId").submit();
    }
    function goback(){
    	window.location.href = '${pageContext.request.contextPath }/admin/jokeInfo/list.do';
    }
    </script>
  </body>
</html>