<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
<title>顯示所有會員</title>
</head>
<body>
<div class="center-block">
<h2>會員資料</h2>
<hr>
<c:choose>
    <c:when test="${empty AllMembers}">
    	<font color='red'>查無任何會員資料</font>
    </c:when>
    <c:otherwise>
		<c:forEach var="mem" varStatus="statusX" items="${AllMembers}">
			<c:if test="${statusX.first}">
				<c:out value="<table border='1' cellspacing='5' cellpadding='5' >"
					escapeXml="false" />
				<tr bgcolor="CCCC00">
					<th colspan='6' ALIGN='CENTER'>會員基本資料</th>
				</tr>
				<tr bgcolor="CCCC00">
					<th>會員代號</th>
					<th>姓 名</th>
					<th>電 話</th>
					<th>生 日</th>
					<th>工作經驗(年)</th>
					<th>註冊時間</th>
				</tr>
			</c:if>
			<c:choose>
				<c:when test="${ statusX.count % 2 == 0 }">
					<c:set var="colorVar" value="99ddff" />
				</c:when>
				<c:otherwise>
					<c:set var="colorVar" value="88dd00" />
				</c:otherwise>
			</c:choose>

			<tr bgcolor="${colorVar}">
				<td><a href="<c:url value='/ch01/ex00/FindMemberServlet.do?id=${mem.id}' />">${mem.userId}</a></td>
				<td>${mem.name}</td>
				<td>${mem.phoneNo}</td>
				<td>${mem.birthday}</td>
				<td>${mem.experience}</td>
				<td>${mem.registerTime}</td>
			</tr>
			<c:if test="${statusX.last}">
				<c:out value="</table>" escapeXml="flase" />
			</c:if>
		</c:forEach>
    </c:otherwise>
</c:choose>
	     <p>
			<c:if test="${not empty sessionScope.modify}">
			    <font color='blue'>${sessionScope.modify}</font>   
				<c:remove var="modify" scope="session" />
			</c:if>
			<c:if test="${not empty sessionScope.error}">
			    <font color='red'>${sessionScope.error}</font>
				<c:remove var="error" scope="session" />
			</c:if>
			<br>
			<a href="<c:url value='/index.jsp' />">回首頁</a>
	</div>
</body>
</html>