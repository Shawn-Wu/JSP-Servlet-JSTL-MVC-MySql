<%@ page pageEncoding="UTF-8"%>
<%--
<div id="splitSearchDiv">
	<jsp:include page="/pages/split_page_plugin_search.jsp"/>
	<br>
</div>
 --%>
<%
	request.setCharacterEncoding("UTF-8") ;
%>
<%
	String columnData = null ;
	String keyWord = null ;
	int allRecorders = 0 ;
	String column = null ;
%>
<%
	try {
		allRecorders = (Integer) request.getAttribute("allRecorders") ;
	} catch (Exception e) {}
	columnData = (String)  request.getAttribute("columnData") ;
	keyWord = (String)  request.getAttribute("keyWord") ;
	column = (String) request.getAttribute("column") ;
%>
请输入查询关键字：
<%
	if (columnData != null) {
%>
		<select id="colSel">
			<%
				String result [] = columnData.split("\\|") ;
				for (int x = 0 ; x < result.length ; x ++) {
					String temp[] = result[x].split(":") ;
			%>
					<option value="<%=temp[1]%>" <%=column.equals(temp[1])?"selected":""%>><%=temp[0]%></option>
			<%
				}
			%>
		</select>
<%
	}
%>
<input type="text" name="kw" id="kw" value="<%=keyWord%>">
<input type="button" value="检索" onclick="goSplit(1)">
<span>一共查询到<%=allRecorders%>条记录；</span><br>