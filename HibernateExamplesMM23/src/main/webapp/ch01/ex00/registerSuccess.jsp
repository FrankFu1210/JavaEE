<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE HTM>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
<div align='center'>
<h1>會員資料新增成功</h1>
<h3>新增資料至資料庫時，如新增成功, 以success.jsp來顯示本畫面)</h3>
<Font color='blue' >親愛的${name}您好，您的會員代號為：${userIdKey}</font>
<p/>感謝您加入成為會員，請開始使用本系統<p/>
<small><a href="<c:url value='/index.jsp' />">回首頁</a></small>
</div>
</body>
</html>