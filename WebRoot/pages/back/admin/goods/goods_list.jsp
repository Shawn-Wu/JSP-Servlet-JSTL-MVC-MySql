<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String updateUpUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?type=up" ;
    String updateDownUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?type=down" ;
    String updateDeleteUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updateStatus?type=delete" ;
    String updatePreUrl = basePath + "pages/back/admin/goods/GoodsServletBack/updatePre" ;
    String deleteUrl = basePath + "pages/back/admin/goods/GoodsServletBack/delete?p=p" ;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css">
    <script type="text/javascript" src="js/mldn.js"></script>
</head>
<body>
<div id="mainDiv">
    <h1>商品列表</h1>
    <c:if test="${allGoods != null}" var="res">
        <div id="splitSearchDiv">
            <jsp:include page="/pages/split_page_plugin_search.jsp"/>
            <br>
        </div>
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td><input type="checkbox" onclick="checkboxSelect(this,'gid')"></td>
                <td>图片</td>
                <td>名称</td>
                <td>价格</td>
                <td>发布日期</td>
                <td>库存</td>
                <td>访问量</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${allGoods}" var="goods">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><input type="checkbox" id="gid" name="gid" value="${goods.gid}:${goods.photo}"></td>
                    <td><img src="upload/goods/${goods.photo}" style="width:50px;height:50px;"></td>
                    <td>${goods.title}</td>
                    <td>${goods.price}</td>
                    <td>${goods.pubdate}</td>
                    <td>${goods.amount}</td>
                    <td>${goods.bow}</td>
                    <td>
                        <c:if test="${goods.status == 0}">
                            下架
                        </c:if>
                        <c:if test="${goods.status == 1}">
                            上架
                        </c:if>
                        <c:if test="${goods.status == 2}">
                            回收站
                        </c:if>
                    </td>
                    <td>
                        <a href="<%=updatePreUrl%>?gid=${goods.gid}">修改</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${param.status != 1}">
            <input type="button" value="批量上架" onclick="updateAll('<%=updateUpUrl%>','ids','gid')">
        </c:if>
        <c:if test="${param.status != 0}">
            <input type="button" value="批量下架" onclick="updateAll('<%=updateDownUrl%>','ids','gid')">
        </c:if>
        <c:if test="${param.status != 2}">
            <input type="button" value="移到回收站" onclick="deleteAll('<%=updateDeleteUrl%>','ids','gid')">
        </c:if>
        <c:if test="${param.status == 2}">
            <input type="button" value="彻底删除" onclick="deleteAll('<%=deleteUrl%>','ids','gid')">
        </c:if>
        <div id="splitBarDiv" style="float:right">
            <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
        </div>
    </c:if>
</div>
</body>
</html>
