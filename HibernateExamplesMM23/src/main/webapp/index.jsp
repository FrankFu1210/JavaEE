<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />
<title>Hibernate</title>

</head>
<body bgcolor="white">
<jsp:include page="/commons/header.jsp" />
<div align="center">
<h3 align="center">使用Hibernate技術存取SQL Server資料庫</h3>
<table border="1">
	<tr height="80" bgcolor="lightblue" align="center">
	    <td  width="350"><p align="left"/>
	       <a href="<c:url value='/ch01/ex00/registerMember.jsp' />">新增會員資料(Hibernate)</a><br>
	       <font face="verdana" size="-2">webapp/ch01/ex00/registerMember.jsp
	      <br>webapp/ch01/ex00/*.jsp<br>ch01.ex00.....*.java</font>
	    </td>
	    <td  width="350"><p align="left">
	      <a href="<c:url value='/ch01/ex00/queryAllMembers.do' />">查詢會員資料(Hibernate)</a><br>
	      <font face="verdana" size="-2">webapp/ch01/ex00/showAllMembers.jsp<br>
	      ch01.ex00.controller.*.java<br>ch01.ex00....*.java</font>
	    </td>
	</tr>
	<tr height="16" bgcolor="lightblue" align="center">
	    <td  colspan='2'>
	         <small><font color='red'>執行ch00.init.CreateInitialData.java才能執行下面兩個範例</font></small>
	    </td>
	</tr>   
	<tr height="80" bgcolor="lightblue" align="center">
	    <td  width="350"><p align="left"/>
	      <a href=" <c:url value='/ch04/ex01/queryAllCategories.do' />">由產品類型查詢產品 FetchType.EAGER</a><br><font face="verdana" size="-2">
	      	webapp/ch04/ex01/*.jsp<br>ch04.ex01.....*.java</font>
	    </td>
	      <td  width="350"><p align="left"/>
	      <a href=" <c:url value='/ch04/ex02/queryAllCategories.do' />">由產品類型查詢產品 FetchType.LAZY</a><br><font face="verdana" size="-2">
	     	webapp/ch04/ex02/*.jsp<br>ch04.ex02.....*.java</font>
	    </td>
	</tr>   
	<tr height="80" bgcolor="lightblue" align="center">
	    <td  width="350"><p align="left"/>
	      <a href=" <c:url value='/ch04/ex03/queryAllDepartments.do' />">由部門查詢員工資料</a><br><font face="verdana" size="-2">
	         webapp/ch04/ex03/*.jsp<br>ch04.ex03.controller.*.java<br>ch04.ex03.....*.java</font>
	    </td>
	    <td  width="350"><p align="left"/>
	      <a href="<c:url value='/ch04/ex03/readMe.jsp' />">自定Filter來延遲關閉Session的做法</a><br><font face="verdana" size="-2">
	      <br>
	     </font>
	    </td>       
	</tr>   
</table>
<p>
<small><font color='red'>必須先1. 建立JSPDB資料庫 2. 修改密碼</font></small>
</div>
</body>
</html>
