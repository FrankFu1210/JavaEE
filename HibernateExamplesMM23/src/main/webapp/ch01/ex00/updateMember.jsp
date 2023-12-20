<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
<style>
input[type="text"]{
    font-size:20px;
}
</style>
<title>MVC</title>
<script type="text/javascript">
	function confirmDelete(userId) {
		var result = confirm("確定刪除此筆記錄(會員代號：" + userId + ")?");
		if (result) {
			document.forms[0].finalDecision.value = "DELETE";
			return true;
		}
		return false;
	}
	function confirmUpdate(userId) {
		var result = confirm("確定送出此筆記錄(會員代號：" + userId + ")?");
		if (result) {
			document.forms[0].finalDecision.value = "UPDATE";
			return true;
		}
		return false;
	}
</script>
</head>
<body>
<div class="center-block">
<h2 class='center'>更新會員資料</h2>
<hr>

	<form class='center' action="<c:url value='/ch01/ex00/updateMember.do' />" method="POST">
		<input type="hidden" name="id" 	   value="${param.id}"> 
		<input type="hidden" name="userId" value="${member.userId}${param.userId}">
	    <input type="hidden" name="pswd"   value="${member.password}${param.pswd}"> 
			<input type="hidden" name="finalDecision" value="">
		<Table>
			<TR>
				<TD align="RIGHT">會員代號：</TD>
				<TD align="LEFT">${member.userId}${param.userId}</TD>
				</TR>
				<TR>
             <TD align="RIGHT">姓名：</TD>
             <TD align="LEFT" ><input	type="text" name="name" value="${member.name}${param.name}"  size="20">
             <font color='red' size='-1'>${ErrorMsg.name}</font>
             </TD>
         </TR>             
         <TR>
             <TD align="RIGHT">生日：</TD>
             <TD align="LEFT" ><input type="text" name="birthday" value="${member.birthday}${param.birthday}" size="12">
             <font color='red' size='-1'>${ErrorMsg.birthday}</font>
             </TD>
         </TR>             
         <TR>
             <TD align="RIGHT">電話號碼：</TD>
             <TD align="LEFT" > <input type="text" name="phoneNo" value="${member.phoneNo}${param.phoneNo}">
             <font color='red' size='-1'>${ErrorMsg.phoneNo}</font>
             </TD>
         </TR>             
         <TR>
             <TD align="RIGHT">工作經驗：</TD>
             <TD align="LEFT" > <input type="text" name="experience" value="${member.experience}${param.experience}" size="3"> 年
             <font color='red' size='-1'>${ErrorMsg.experience}</font>
             </TD>
         </TR>   
				<TR>
					<TD colspan="2" align="center"><input type="submit" value="更新"
						name='updateBtn'
						onclick="return confirmUpdate('${member.userId}');"> <input
						type="submit" value="刪除" name='deleteBtn'
						onclick="return confirmDelete('${member.userId}');"></TD>
				</TR>
			</Table>
			<c:if test="${not empty requestScope.modify}">
				<c:remove var="member" scope="request" />
			</c:if>
		</Form>
		<p />
		
		<a href="<c:url value='/ch01/ex00/queryAllMembers.do' />">回上一頁</a>
		
		
	</div>
</body>

</html>
