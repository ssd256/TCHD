<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/member/myPage_confirmPw.css" type="text/css">
</head>
<body>
<section>
	<div id="joinCompleteDiv">
		<p id="p1">에러 발생</p>
		<p><%=request.getAttribute("errorMsg") %></p>
		<button type="button" onclick="location.href='<%=request.getContextPath()%>'">메인페이지로</button>
	</div>
</section>

</body>
</html>