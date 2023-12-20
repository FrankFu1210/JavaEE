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
		<h2>依照部門查詢員工</h2>
		<h3>(Hibernate+Filter版, with LazyLoading)</h3>
		<HR>
		<table border='1'>
			<tr>
				<th align='center' colspan='5'>部門: ${department.depName}</th>
			</tr>
			<c:forEach var="employee" items="${department.employees}"  varStatus="statusX" >
				<c:if test="${statusX.first}">
					<c:out value="<table border='1' cellspacing='5' cellpadding='5' >"
						escapeXml="false" />
					<tr bgcolor="CCCC00">
						<td colspan='5' ALIGN='CENTER'>員工基本資料</td>
					</tr>
					<tr bgcolor="CCCC00">
						<th>員工編號</th>
						<th>姓名</th>
						<th>薪水</th>
						<th>體重</th>
						<th>生日</th>
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
					<td>${employee.employeeId}</td>
					<td>${employee.name}</td>
					<td>${employee.salary}</td>
					<td>${employee.weight}</td>
					<td>${employee.birthday}</td>

				</tr>

			</c:forEach>

		</Table>

		<p>
			<a href="<c:url value='/ch04/ex03/queryAllDepartments.do' />">重查一次</a>
			<a href="<c:url value='/index.jsp' />">回首頁</a>
	</div>
</body>

</html>
