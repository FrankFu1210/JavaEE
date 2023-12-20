<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>

<link rel='stylesheet' href="<c:url value='/css/style.css'  />" />
<meta charset="UTF-8">
<title>會員資料管理</title>
</head>  
<body>
<p>&nbsp;</p>
<hr>
<div class='center' >
<h2>會員管理</h2>
<hr>
<a href="<c:url value='/lab01/InsertMemberForm.jsp' />" >會員資料新增</a><br>
<a href="<c:url value='/lab01/queryMember.do' />" >會員資料查詢</a><br>
<br>
<a href="<c:url value='/index.jsp' />">回前頁</a>
</div>
</body>
</html>