<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String backurl = basePath + "pages/back/login.jsp" ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>微商城</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/mldn.css">
	<script type="text/javascript" src="js/mldn.js"></script>
  </head>
  
  <body>
  <jsp:include page="/pages/header.jsp"/>
  <div style="width:100%;height:70%;">
  	<a href="<%=backurl%>">后台管理入口</a><br>
  	慢慢完善中....<br>
  	JSP+Servlet+JSTL+MVC+MySql完成的一个微商城<br>
  	<h3>商城使用说明：</h3>
  	<h4>未登入的时候可以看商品列表</h4>
  	<h4>注册后需要激活，这里我模拟了发送邮箱激活，激活地址要看tomcat的后台日志。</h4>
  	<h2>项目总结:</h2>
  	<h4>经过这个项目对MVC设计模式更进入的理解</h4>
  	<h4>在项目里代码的复用率不高，经常不能把精力放在业务层的实现上，而是去找一些小错误</h4>
  	<h4>密码入库用MD5加密</h4>
  	<h4>有图片上传功能</h4>
  	<h4>用过滤器来控制页面未登入不能访问</h4>
  	<h4>用cookie操作来记住密码</h4>
  	<h4>采用了分页技术</h4>
  </div>
  <jsp:include page="/pages/footer.jsp"/>
  </body>
</html>
