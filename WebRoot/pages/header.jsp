<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<div id="headerDiv">
    <img src="images/logo1.png" style="width:237px;height: 72px;">
    <a href="index.jsp">商城首页</a>
    <a href="pages/front/goods/GoodsServletFront/list">商品列表</a>
    <a href="pages/front/shopcar/ShopcarServletFront/list">我的购物车</a>
    <c:if test="${mid == null}">
        <a href="pages/member_login.jsp">登录</a>
        <a href="pages/member_regist.jsp">注册</a>
    </c:if>
    <c:if test="${mid != null}">
        <a href="pages/front/member/MemberInfoServletFront/updatePre">个人信息</a>
        <a href="pages/front/orders/OrdersServletFront/list">全部订单</a>
        <a href="pages/MemberServletFront/logout">安全退出</a>
        <img src="upload/member/${photo}" style="width:50px;height: 50px; ">
    </c:if>
</div>