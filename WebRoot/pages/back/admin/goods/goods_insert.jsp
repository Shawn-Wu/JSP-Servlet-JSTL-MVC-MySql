<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String insertUrl = basePath + "/pages/back/admin/goods/GoodsServletBack/insert";
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
    <form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()" enctype="multipart/form-data">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4"><span class="title">增加商品信息</span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td width="15%">商品名称：</td>
                <td width="30%"><input type="text" name="title" id="title" class="init" onblur="validateTitle()"></td>
                <td width="20%"><span id="titleMsg"></span></td>
                <td width="35%" rowspan="7"><div id="preview"></div></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>所属分类：</td>
                <td>
                    <c:if test="${allItems != null}" var="res">
                        <select name="iid" id="iid">
                            <c:forEach items="${allItems}" var="item">
                                <option value="${item.iid}">${item.title}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </td>
                <td><span id="iidMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品价格：</td>
                <td><input type="text" name="price" id="price" class="init" onblur="validatePrice()"></td>
                <td><span id="priceMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>库存数量：</td>
                <td><input type="text" name="amount" id="amount" class="init" onblur="validateAmount()"></td>
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
                    <input type="radio" name="status" id="status" checked class="init" value="0">下架
                    <input type="radio" name="status" id="status" class="init" value="1">上架
                </td>
                <td><span id="statusMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品描述：</td>
                <td colspan="3">&nbsp;</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4">
                    <textarea id="note" name="note" cols="80" rows="5"></textarea>
                </td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4">
                    <input type="submit" value="增加">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
