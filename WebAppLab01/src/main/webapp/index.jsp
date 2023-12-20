<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/style.css'  />" />
<meta charset="UTF-8">  
</head>
<body>  
<p>&nbsp;</p>
<hr>
<div class='center' >
<h1 class='center'>綠色健康旅遊系統</h1>
<br>
<a href="<c:url value='/lab01/index.jsp' />">會員管理(JDBC版)</a>
<hr>
<font size='-1' color='red'>需要先執行\src\main\resources\SQLServer_init.sql或MySQL_init.sql建立表格:memberlab01'</font>
<br>
<font size='-3'>SQL Server</font>
</div>

</body>
</html>
