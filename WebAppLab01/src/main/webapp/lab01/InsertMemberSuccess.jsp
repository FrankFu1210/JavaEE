<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/style.css' />" />
<meta charset="UTF-8">
<title>新增會員資料成功</title>
</head>
<body>
<div align='center'>
<h1>新增會員資料成功</h1>
<!-- 
本文件顯示會員資料新增成功的訊息，要顯示的會員資料由Controller放入『Session範圍』內
，成為Session物件的屬性物件，識別字串為mb, 我們將屬性物件內的帳號與姓名取出，放在
本文件中，一起送回給瀏覽器
-->
親愛的${memberBean.name}(帳號:${memberBean.memberId})<br>
您已成功加入會員 ,謝謝
<p/>
<a href="<c:url value='/lab01/index.jsp' />">回到會員管理</a>
</div>
</body>
</html>