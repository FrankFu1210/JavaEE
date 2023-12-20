<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html >
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
<title>MVC</title>
<style>
input[type="text"], input[type="password"]{
    font-size:18px;
}
</style>
</head>
<body>
<div align='center'>
<H2>加入會員</H2>
<small>
     呼叫ch01.ex00.dao.MemberDao介面的save()進行新增<BR>
     (實作類別為ch01.ex00.dao.impl.MemberDaoImpl_Hibernate.java)
</small>
<hr>

<form action="<c:url value='/ch01/ex00/registerMember.do' />" method="POST">
    <table>
         <tr>
             <td align="RIGHT">會員代號：</td>
             <td align="LEFT"><input	type="text" name="userId" value="${param.userId}" size="12">
             <font color='red' size='-1'>${ErrorMsg.userId}</font>
             </td>
         </tr>
         <tr>
             <td align="RIGHT">密碼：</td>
             <td align="LEFT" ><input	type="password" name="pswd" value="${param.pswd}" size="12">
             <font color='red' size='-1'>${ErrorMsg.pswd}</font>
             </td>
         </tr>             
         <tr>
             <td align="RIGHT">姓名：</td>
             <td align="LEFT" ><input	type="text" name="name" value="${param.name}"  size="12">
             <font color='red' size='-1'>${ErrorMsg.name}</font>
             </td>
         </tr>             
         <tr>
             <td align="RIGHT">生日：</td>
             <td align="LEFT" ><input type="text" name="birthday" value="${param.birthday}" size="10">
             <font color='red' size='-1'>${ErrorMsg.birthday}</font>
             </td>
         </tr>             
         <tr>
             <td align="RIGHT">電話號碼：</td>
             <td align="LEFT" > <input type="text" name="phoneNo" value="${param.phoneNo}">
             <font color='red' size='-1'>${ErrorMsg.phoneNo}</font>
             </td>
         </tr>             
         <tr>
             <td align="RIGHT">工作經驗：</td>
             <td align="LEFT" > <input type="text" name="experience" value="${param.experience}" size="3"> 年
             <font color='red' size='-1'>${ErrorMsg.experience}</font>
             </td>
         </tr>    
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"> </td>
            </tr>
         </table>
</form>
<p>
<small><a href="<c:url value='/index.jsp' />">回首頁</a></small>
</p>
</div>
</body>
</html>
