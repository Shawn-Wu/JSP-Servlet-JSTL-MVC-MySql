<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String updateUrl = basePath + "/pages/back/admin/goods/GoodsServletBack/update";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css">
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/goods.js"></script>
</head>
<body>
<div id="mainDiv">
    <c:if test="${goods != null}">
    <form action="<%=updateUrl%>" method="post" onsubmit="return validateInsert()" enctype="multipart/form-data">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4"><span class="title">修改商品信息</span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td width="15%">商品名称：</td>
                <td width="30%"><input type="text" name="title" id="title" class="init" onblur="validateTitle()" value="${goods.title}"></td>
                <td width="20%"><span id="titleMsg"></span></td>
                <td width="35%" rowspan="7"><div id="preview"><img src="upload/goods/${goods.photo}"></div></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>所属分类：</td>
                <td>
                    <c:if test="${allItems != null}" var="res">
                        <select name="iid" id="iid">
                            <c:forEach items="${allItems}" var="item">
                                <option value="${item.iid}" ${goods.item.iid==item.iid?"selected":""}>${item.title}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </td>
                <td><span id="iidMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品价格：</td>
                <td><input type="text" name="price" id="price" class="init" onblur="validatePrice()"  value="${goods.price}"></td>
                <td><span id="priceMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>库存数量：</td>
                <td><input type="text" name="amount" id="amount" class="init" onblur="validateAmount()" value="${goods.amount}"></td>
                <td><span id="amountMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品图片：</td>
                <td><input type="file" name="photo" id="photo" class="init" onchange="preview(this)"></td>
                <td><span id="photoMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>发布状态：</td>
                <td>
                    <input type="radio" name="status" id="status" class="init" value="0" ${goods.status == 0 ? "checked" : ""}>下架
                    <input type="radio" name="status" id="status" class="init" value="1" ${goods.status == 1 ? "checked" : ""}>上架
                    <input type="radio" name="status" id="status" class="init" value="2" ${goods.status == 2 ? "checked" : ""}>删除
                </td>
                <td><span id="statusMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品描述：</td>
                <td colspan="3">&nbsp;</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4">
                    <textarea id="note" name="note" cols="80" rows="5">${goods.note}</textarea>
                </td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4">
                    <input type="hidden" name="oldpic" id="oldpic" value="${goods.photo}">
                    <input type="hidden" name="gid" id="gid" value="${goods.gid}">
                    <input type="hidden" name="back" id="back" value="${back}">
                    <input type="submit" value="修改">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
    </c:if>
</div>
</body>
</html>
