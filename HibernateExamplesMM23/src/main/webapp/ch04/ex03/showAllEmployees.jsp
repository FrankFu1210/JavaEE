<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />
<meta charset="UTF-8">
<title>Show All Employees</title>
</head>
<body>
<div class="center-block">
<h2 class='center'>員工資料</h2>
<h3>Hibernate版</h3>
<hr>
    <c:forEach var="mem"  varStatus="statusX" items="${allEmployees}">
       <c:if test="${statusX.first}" >
          <c:out value="<table border='1' cellspacing='5' cellpadding='5' >" escapeXml="false"/>
          <tr bgcolor="CCCC00">
	         <td  colspan='5' ALIGN='CENTER'>會員基本資料</td>
	      </tr>
	      <tr  bgcolor="CCCC00">
	         <th>帳 號</th><th>姓 名</th><th>電話</th><th>工作經驗</th>
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
                    
       
       <c:if test="${statusX.last}" >
          <c:out value="</table>" escapeXml="flase" />
       </c:if>		                      
    </c:forEach>
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