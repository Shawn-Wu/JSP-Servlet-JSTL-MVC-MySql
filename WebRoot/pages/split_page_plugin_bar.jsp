<%@ page pageEncoding="UTF-8"%>
<%--
<div id="splitBarDiv" style="float:right">
	<jsp:include page="/pages/split_page_plugin_bars.jsp"/>
</div>
--%>
<%
	request.setCharacterEncoding("UTF-8") ;
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%
	String url = null ;
	int currentPage = 1 ;
	int lineSize = 5 ;
	String column = null ;
	String keyWord = null ;
	int allRecorders = 0 ;
	int pageSize = 0 ;
	int lsData [] = new int [] {1,5,10,15,20,30,50,100} ;
	String paramName = (String) request.getAttribute("paramName") ;
	String paramValue = (String) request.getAttribute("paramValue") ;
%>

<%	// 接收传递的参数
	try {
		currentPage = (Integer) request.getAttribute("currentPage") ;
	} catch (Exception e) {}
	try {
		allRecorders = (Integer)request.getAttribute("allRecorders") ;
	} catch (Exception e) {}
	try {
		lineSize = (Integer) request.getAttribute("lineSize") ;
	} catch (Exception e) {}
	column = (String) request.getAttribute("column") ;
	keyWord = (String) request.getAttribute("keyWord") ;
	url = basePath + request.getAttribute("url") ;
%>
<%
	if (allRecorders > 0) {
		pageSize = (allRecorders + lineSize - 1) / lineSize ;
	} else {	// 没有记录
		pageSize = 1 ;
	}
%>

<script type="text/javascript">
	function goSplit(vcp) {	// 根据外部传递的cp内容进行操作
		var eleLs = document.getElementById("lsSel").value ;
		try {
			var eleKw = document.getElementById("kw").value ;
			var eleCol = document.getElementById("colSel").value ;
			window.location = "<%=url%>?cp=" + vcp + "&ls=" + eleLs + "&kw=" + eleKw + "&col=" + eleCol + "&<%=paramName%>=<%=paramValue%>" ;
		} catch (Exception) {
			window.location = "<%=url%>?cp=" + vcp + "&ls=" + eleLs + "&<%=paramName%>=<%=paramValue%>" ;
		}
	}
</script>

<input type="button" value="首页" onclick="goSplit(1)" <%=currentPage == 1? "disabled" : ""%>>
<input type="button" value="上一页" onclick="goSplit(<%=currentPage-1%>)" <%=currentPage == 1? "disabled" : ""%>>
<input type="button" value="下一页" onclick="goSplit(<%=currentPage+1%>)" <%=currentPage == pageSize? "disabled" : ""%>>
<input type="button" value="尾页" onclick="goSplit(<%=pageSize%>)" <%=currentPage == pageSize? "disabled" : ""%>>
	跳转到：<select id="cpSel" onchange="goSplit(this.value)">
	<%
		for (int x = 1 ; x <= pageSize ; x ++) {
	%>
			<option value="<%=x%>" <%=currentPage == x ? "selected" : ""%>><%=x%></option>
	<%
		}
	%>
	</select>页&nbsp;
	每页显示：<select id="lsSel" onchange="goSplit(1)">
		<%
			for (int x = 0 ; x < lsData.length ; x ++) {
		%>
				<option value="<%=lsData[x]%>" <%=lineSize==lsData[x]?"selected":""%>><%=lsData[x]%></option>
		<%
			}
		%>
	</select>
	行记录