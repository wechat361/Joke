<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/tags/string.tld" prefix="string"%>
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
        #line-chart { height:300px; width:800px; margin: 0px auto;  margin-top: 1em; }
        .brand { font-family: georgia, serif; }
        .brand .first { color: #ccc; font-style: italic; }
        .brand .second { color: #fff; font-weight: bold; }
        
        #chart-container{ margin: 10px; }
        .searchTd{ border-top : 0px !important; vertical-align : middle !important; width: 33%; text-align: left !important;padding-left: 5% !important;}
        .searchTd select {margin-bottom: 0px !important;width:auto !important}
        .searchTd2{ border-top : 0px !important; }
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
            <h1 class="page-title">Jokes</h1>
<div class="btn-toolbar">
    <button class="btn btn-primary" onclick="addJoke()"><i class="icon-plus"></i>添加禁词</button>
    <button class="btn" onclick="showOrhidden()">查询</button>
    <button class="btn" onclick="batchDelKeyword()">删除</button>
  	<div class="btn-group"></div>
</div>

<div style="text-align:center;align:center;"><font color="red">${errorMsg }</font></div>
<form:form action="${pageContext.request.contextPath }/admin/keyword/list.do" method="POST" id="formid" modelAttribute="keyword">
<div class="row-fluid">
    <div class="block">
        <div id="chart-container" class="block-body collapse" style="display: none;">
            <table class="table">
		      	<tr>
		          	<td class="searchTd">关键字：<input style="width: auto" name="keyword" id="keywordId" value="${keyword.keyword}"/></td>
		          	<td class="searchTd">添加人：<input style="width: auto" name="account" id="contentId" value="${keyword.account}"/></td>
		          	<td class="searchTd">状态：
			          	<form:select path="state" id="stateId">  
				           	<form:option value="">全部</form:option>
			          		<form:option value="1">有效</form:option>
			          		<form:option value="0">无效</form:option>
				        </form:select>
		          	</td>
		        </tr>
		        <tr>
		          	<td class="searchTd">类型：
			          	<form:select path="type" id="typeId">  
				           	<form:option value="1">默认类型</form:option>
				        </form:select>
		          	</td>
		          	<td class="searchTd"></td>
		          	<td class="searchTd"></td>
		        </tr>
		        <tr>
		          	<td colspan="3" class="searchTd2"><input type="submit" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="重置" onclick="clearForm()"/></td>
		        </tr>
		    </table>
        </div>
    </div>
</div>
<div class="well">
    <table class="table">
      <thead>
        <tr>
          <th><input type="checkbox" onclick="changeCheckBox(this)" id="checkAll"/></th>
          <th style="width:8%">编号</th>
          <th style="width:20%">关键字</th>
          <th style="width:20%">类型</th>
          <th style="width:10%">状态</th>
          <th style="width:10%">添加人</th>
          <th style="width:25%">添加时间</th>
          <th style="width:7%;">操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${keywordList}" var="keywordInfo">
      	<tr>
          <td><input type="checkbox" value="${keywordInfo.id }" name="keywordId" onclick="checkAll()"/></td>
          <td>${keywordInfo.id }</td>
          <td>${string:filterHtml(keywordInfo.keyword)}</td>
          <td>
          	<c:if test="${keywordInfo.type==1 }" var="type">默认类型</c:if>
          	<c:if test="${!type}">非法类型</c:if>
          </td>
          <td>
          	<c:if test="${keywordInfo.state==1 }" var="state"><font color="green">有效</font> </c:if>
          	<c:if test="${!state}"><font color="red">无效</font> </c:if>
          </td>
          <td>${keywordInfo.account}</td>
          <td><fmt:formatDate value="${keywordInfo.createDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
          <td>
              <a href="${pageContext.request.contextPath }/admin/keyword/toUpdate.do?id=${keywordInfo.id}"><i class="icon-pencil"></i></a>
              <a href="javascript:delKeyword('${keywordInfo.id }')" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
</div>
<div class="pagination">
    <%--<ul>
        <li><a href="#">Prev</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">Next</a></li>
    </ul>--%>
    ${pager.pagerStr2 }
</div>
</form:form>
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
    function addJoke(){
    	window.location.href='${pageContext.request.contextPath}/admin/keyword/toAdd.do';
    }
    function delKeyword(id){
    	if(confirm("确定要删除吗？")){
    		window.location.href = '${pageContext.request.contextPath }/admin/keyword/delete.do?id='+id;
    	}
    }
    
    function checkPass(){
    	var keywordIds = document.getElementsByName("keywordId");
    	var ids = "";
    	for(var i = 0; i < keywordIds.length; i++){
    		if(!keywordIds[i].checked){
	    		continue;
    		}
    		ids += "id=" +  keywordIds[i].value;
    		if(i < keywordIds.length - 1){
    			ids += "&";
    		}
		}
    	if(ids == ""){
    		alert("请选择要审核的内容");
    	}else {
        	window.location.href = '${pageContext.request.contextPath }/admin/keyword/checkPass.do?'+ids;
    	}
    }
    
    function batchDelKeyword(){
    	var jokeIds = document.getElementsByName("keywordId");
    	var ids = "";
    	for(var i = 0; i < jokeIds.length; i++){
    		if(!jokeIds[i].checked){
	    		continue;
    		}
    		ids += "id=" +  jokeIds[i].value;
    		if(i < jokeIds.length - 1){
    			ids += "&";
    		}
		}
    	if(ids == ""){
    		alert("请选择要删除的内容");
    	}else if(confirm("确定要删除吗？")){
        	window.location.href = '${pageContext.request.contextPath }/admin/keyword/delete.do?'+ids;
    	}
    }
    
    function changeCheckBox(elem){
    	var jokeIds = document.getElementsByName("keywordId");
    	if(elem.checked){
    		for(var i = 0; i < jokeIds.length; i++){
    			jokeIds[i].checked = true;
    		}
    	}else{
    		for(var i = 0; i < jokeIds.length; i++){
    			jokeIds[i].checked = false;
    		}
    	}
    }
    
    function checkAll(){
    	var count = 0;
    	var jokeIds = document.getElementsByName("keywordId");
    	for(var i = 0; i < jokeIds.length; i++){
			if(jokeIds[i].checked){
				count ++;
			}
		}
    	if(count == jokeIds.length){
    		document.getElementById("checkAll").checked = true;
    	}else{
    		document.getElementById("checkAll").checked = false;
    	}
    }
    
    function showOrhidden(){
    	if($("#chart-container").attr("class") === "block-body collapse"){
    		$("#chart-container").attr("class", "block-body collapse in");
    		$("#chart-container").css({display:''});
    	}else{
    		$("#chart-container").attr("class", "block-body collapse");
    		$("#chart-container").css({display:'none'});
    	}
    }
    function getPage(pageIndex){
    	$("#formid").attr("action", "${pageContext.request.contextPath}/admin/keyword/list.do?pageIndex="+pageIndex)
    	$("#formid").submit();
    }
    
    function clearForm(){
    	$("#keywordId").val("");
    	$("#accountId").val("");
    	$("#stateId").val("");
    }
    </script>
  </body>
</html>