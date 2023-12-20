<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />
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
			<th>部門代號</th><th>部門名稱</th>
		</tr>        
		<c:forEach var="department" items="${departments}">
           <tr>
			  <td>${department.depId}</td>
			  <td><a href="<c:url value='/ch04/ex03/queryDepartmentById.do?depId=${department.depId}'  />">${department.depName}</a></td>
		   </tr>
		
		</c:forEach>
        
         </Table>

<p>
<a href="<c:url value='/index.jsp' />">回首頁</a>
</div>
</body>

</html>
