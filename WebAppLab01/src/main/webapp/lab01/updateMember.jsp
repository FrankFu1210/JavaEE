<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE HTML>
<html>
<head>
<style>
</style>
<link rel='stylesheet' href="<c:url value='/css/style.css'  />" />
<meta charset="UTF-8">
<title>MVC</title>
<script type="text/javascript">
  function confirmDelete(userId){
	  var result = confirm("確定刪除此筆記錄(帳號:" + userId + ")?");
	  if (result) {
		  document.forms[0].finalDecision.value = "DELETE";
	      return true;
	  }
	  return false;
  }
  function confirmUpdate(userId){
	  var result = confirm("確定送出此筆記錄(帳號:" + userId + ")?");
	  if (result) {
		  document.forms[0].finalDecision.value = "UPDATE";
	      return true;
	  }
	  return false;
  }
</script>
</head>
<body>
<p>&nbsp;</p>
<hr>
<div class='center' >
<H1 class='center'>更新會員資料</H1>
<hr>
<p> 
<form class='center' action="<c:url value='/lab01/updateMember.do' />" method="POST" >
    
    <input type="hidden" name="id"     value="${mb.id}${param.id}" >
    <input type="hidden" name="memberId"     value="${mb.memberId}${param.memberId}" >
    <input type="hidden" name="finalDecision" value="" > 
    <Table>
         <TR>
             <TD align="RIGHT">帳號：</TD>
             <TD align="LEFT">${mb.memberId}${param.memberId}</TD>
         </TR>
         <TR>
             <TD align="RIGHT">姓名：</TD>
             <TD align="LEFT" >
                <input type="text" name="name" value="${mb.name}${param.name}"  size="30">
                <font color='red' size='-3'>${error.name}</font>
             </TD>
         </TR>             
                      
         <TR>
             <TD align="RIGHT">密碼：</TD>
             <TD align="LEFT" > 
               <input type="text" name="pswd" value="${mb.password}${param.pswd}">
               <font color='red' size='-3'>${error.password}</font>
             </TD>
         </TR>             
		 <TR>
             <TD align="RIGHT">手機：</TD>
             <TD align="LEFT" > 
               <input type="text" name="phone" value="${mb.phone}${param.phone}">
               
             </TD>
         </TR>    
         <TR>
             <TD align="RIGHT">生日：</TD>
             <TD align="LEFT" > 
               <input type="text" name="birthday" value="${mb.birthday}${param.birthday}">
               <font color='red' size='-3'>${error.birthday}</font>
             </TD>
         </TR>  
          <TR>
             <TD align="RIGHT">體重：</TD>
             <TD align="LEFT" > 
               <input type="text" name="weight" value="${mb.weight}${param.weight}">
                <font color='red' size='-3'>${error.weight}</font>
             </TD>
         </TR>    
        <TR>
            <TD colspan="2" align="center">     
            <input type="submit" value="更新" name='updateBtn' onclick="return confirmUpdate('${member.userId}');"> 
            <input type="submit" value="刪除" name='deleteBtn' onclick="return confirmDelete('${member.userId}');" >
            </TD>
            </TR>
         </Table>
         <c:if test="${not empty requestScope.modify}">   
           <c:remove var="member" scope="request" />       
         </c:if>
</form>
<p/>
<small>&lt;&lt;<a href="<c:url value='/lab01/queryMember.do' />">回上一頁</a>&gt;&gt;</small>
</div>
</body>
</html>
