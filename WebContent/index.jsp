<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>함께하묘 행복하개</title>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="stylesheet" href="css/common/common.css" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<% String section = (String)request.getAttribute("section"); 
	if(section==null){
		section="WEB-INF/views/common/main.jsp";
	}
%>
<body>
	<jsp:include page="WEB-INF/views/common/header.jsp"/> <!-- Header include -->
	<hr class="clear">
	<jsp:include page="<%=section %>" />
	<jsp:include page="WEB-INF/views/common/footer.jsp" /> <!--Footer include  -->
</body>
</html>