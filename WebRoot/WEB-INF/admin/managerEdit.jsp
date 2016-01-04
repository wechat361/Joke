<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>冷笑话管理系统--账号修改</title>
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
    <div class="span4 offset4 dialog">
        <div class="block">
            <div class="block-heading">修改管理员账号</div>
            <div class="block-body">
                <form action="update.do" method="post" id="submitId">
                    <label>姓名</label>
                    <input type="hidden" name="accountId" value="${manager.accountId }"/>
                    <input type="text" class="span12" name="accountName" autocomplete="off" readonly="readonly" value="${manager.accountName }">
                    <label>联系电话</label>
                    <input type="text" class="span12" name="phone" autocomplete="off" value="${manager.phone }">
                    <label>电子邮箱</label>
                    <input type="text" class="span12" name="email" autocomplete="off" value="${manager.email }">
                    <label>地址</label>
                    <input type="text" class="span12" name="address" autocomplete="off" value="${manager.address }">
                    <a href="${pageContext.request.contextPath }/admin/manager/list.do" class="btn btn-primary" >返回</a>
                    <a href="javascript:void(0)" class="btn btn-primary pull-right" onclick="document.getElementById('submitId').submit()">添加</a>
                    <div class="clearfix"></div>
                </form>
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
  </body>
</html>