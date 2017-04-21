<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String updateActiveUrl = basePath + "pages/back/admin/member/MemberServletBack/updateStatus?type=active" ;
    String updateLockUrl = basePath + "pages/back/admin/member/MemberServletBack/updateStatus?type=lock" ;
    String showUrl = basePath + "pages/back/admin/member/MemberServletBack/show" ;
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
    <h1>用户列表</h1>
    <c:if test="${allMembers != null}" var="res">
        <div id="splitSearchDiv">
            <jsp:include page="/pages/split_page_plugin_search.jsp"/>
            <br>
        </div>
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td><input type="checkbox" onclick="checkboxSelect(this,'mid')"></td>
                <td>用户名</td>
                <td>姓名</td>
                <td>电话</td>
                <td>注册日期</td>
                <td>状态</td>
            </tr>
            <c:forEach items="${allMembers}" var="member">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><input type="checkbox" id="mid" name="mid" value="${member.mid}"></td>
                    <td><a href="<%=showUrl%>?mid=${member.mid}">${member.mid}</a></td>
                    <td>${member.name}</td>
                    <td>${member.phone}</td>
                    <td>${member.regdate}</td>
                    <td>
                        <c:if test="${member.status == 0}">
                            锁定
                        </c:if>
                        <c:if test="${member.status == 1}">
                            激活
                        </c:if>
                        <c:if test="${member.status == 2}">
                            未激活
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="批量激活" onclick="updateAll('<%=updateActiveUrl%>','ids','mid')">
        <input type="button" value="批量锁定" onclick="updateAll('<%=updateLockUrl%>','ids','mid')">
        <div id="splitBarDiv" style="float:right">
            <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
        </div>
    </c:if>
</div>
</body>
</html>
