<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"
	type="text/css" />
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<div align='CENTER'>
		<h2>依照產品類型查詢產品</h2>
		<h3>Category類別多方物件<font color='red'>未採取延遲載入策略</font></h3>
		<HR>
		<table border='1'>
			<tr>
				<th align='center' colspan='5'>類型: ${category.name}</th>
			</tr>
			<c:forEach var="product" items="${category.products}"  varStatus="statusX" >
				<c:if test="${statusX.first}">
					<c:out value="<table border='1' cellspacing='5' cellpadding='5' >"
						escapeXml="false" />
					<tr bgcolor="CCCC00">
						<td colspan='5' ALIGN='CENTER'>產品基本資料</td>
					</tr>
					<tr bgcolor="CCCC00">
						<th>產品編號</th>
						<th>名稱</th>
						<th>庫存數量</th>
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

				<tr bgcolor='${colorVar}'>
					<td align='center'>${product.productId}</td>
					<td>${product.name}</td>
					<td align='right'>${product.stock}&nbsp;</td>
				</tr>

			</c:forEach>

		</Table>

		<p>
			<a href="<c:url value='/ch04/ex01/queryAllCategories.do' />">重查一次</a>
			<a href="<c:url value='/index.jsp' />">回首頁</a>
	</div>
</body>

</html>
