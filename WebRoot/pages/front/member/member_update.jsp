<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String updateUrl = basePath + "pages/front/member/MemberInfoServletFront/update" ;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城综合实战</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css">
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/member.js"></script>
</head>
<body>
<jsp:include page="/pages/header.jsp"/>
<div id="mainDiv">
    <c:if test="${member != null}">
        <form action="<%=updateUrl%>" method="post" enctype="multipart/form-data" onsubmit="return validateUpdate()">
            <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td colspan="4">用户个人信息</td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>用户ＩＤ：</strong></td>
                    <td colspan="2">${member.mid}</td>
                    <td rowspan="5"><div id="preview"><img src="upload/member/${member.photo}" style="width:100px;height:100px;"></div></td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>真实姓名：</strong></td>
                    <td><input type="text" name="name" id="name" value="${member.name}" onblur="validateName()"></td>
                    <td><span id="nameMsg"></span></td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>联系电话：</strong></td>
                    <td><input type="text" name="phone" id="phone" value="${member.phone}" onblur="validatePhone()"></td>
                    <td><span id="phoneMsg"></span></td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>用户地址：</strong></td>
                    <td><input type="text" name="address" id="address" value="${member.address}" onblur="validateAddress()"></td>
                    <td><span id="addressMsg"></span></td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><strong>个人靓照：</strong></td>
                    <td><input type="file" onchange="preview(this)" name="photo" id="photo"></td>
                    <td><span id="photoMsg"></span></td>
                </tr>
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td colspan="4">
                        <input type="hidden" name="oldpic" id="oldpic" value="${member.photo}">
                        <input type="submit" value="更新">
                        <input type="reset" value="重置">
                    </td>
                </tr>
            </table>
        </form>
    </c:if>
</div>
<jsp:include page="/pages/footer.jsp"/>
</body>
</html>
