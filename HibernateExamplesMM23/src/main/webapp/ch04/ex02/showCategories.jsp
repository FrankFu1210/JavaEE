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
<h2>依照產品類型查詢產品</h2>
<h3>Category類別的 fetch = FetchType.LAZY</h3>
<HR>
    <table border='1'>
        <tr>
			<th>代號</th><th>類型</th>
		</tr>        
		<c:forEach var="category" items="${categories}">
           <tr>
			  <td align='center'>${category.categoryId}</td>
			  <td><a href="<c:url value='/ch04/ex02/queryCategoryById.do?catId=${category.categoryId}'  />">${category.name}</a></td>
		   </tr>
		
		</c:forEach>
        
         </Table>

<p>
<a href="<c:url value='/index.jsp' />">回首頁</a>
</div>
</body>

</html>
