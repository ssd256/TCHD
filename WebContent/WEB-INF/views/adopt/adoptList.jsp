<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, board.model.vo.*" %>
<%
	ArrayList<Adopt> aList = (ArrayList<Adopt>)request.getAttribute("aList");
	ArrayList<Files> fList = (ArrayList<Files>)request.getAttribute("fList");
	String userId = (String)request.getAttribute("userId");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/adopt/adopt_list.css?after" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/common/common.css" type="text/css">
</head>
<body>
	<section>
		<form action="<%= request.getContextPath() %>/adoptDetail.bo">
		<div id="ment">입양게시판</div>
		<div id="PageArea">
		<table class="mentArea">
			<tr>
				<td id="ment1" class="mentArea" align="center">입양안내</td>
				<td id="ment2" class="mentArea">가족을 기다리는 동물들, 사지말고 입양하세요.</td>
			</tr>
		</table>
	</div>
		<div id="pet">
			<div id="petSelect">
				<table>
				<tr>
					<td id="imgSpace"></td>
					<td>
						<div id="btn">
							<c:if test="${ userId ne null }">
								<button type="button" class="petButton" name="petUpDate" onclick="writeForm();">+ 등 록</button>
							</c:if>
							<c:if test="${ userId eq null }">
								<button type="button" class="petButton" name="petUpDate" onclick="loginForm();">+ 등 록</button>
							</c:if>
						</div>
					</td>
				</tr>
				</table>
		</div>
			<div id="petPicture">		<!-- 동물 사진 영역 -->
<%-- 			<% if(aList.isEmpty() || fList.isEmpty()){ %> --%>
<!-- 					등록된 사진이 없습니다. -->
<%-- 			<% } else { %> --%>
<%-- 				<% for(int i = 0; i < aList.size(); i++){ %> --%>
<%-- 					<% Adopt a = aList.get(i); %> --%>
<!-- 					<div class="pictureInfo"> -->
<!-- 						<div class="petPictureInfo"> -->
<%-- 							<input type="hidden" id="boardNo" name="boNo" value="<%= a.getBoNo() %>"/> --%>
<%-- 								<% for(int j = 0; j < fList.size(); j++){ %> --%>
<%-- 									<% Files f = fList.get(j); %> --%>
<%-- 									<% if(a.getBoNo() == f.getBoNo()){ %>	  --%>
<%-- 									<img class="pictureForm" src="<%= request.getContextPath() %>/upload_imageFiles/<%= f.getChangeName() %>"/> --%>
<!-- 									<div class="petInfos">  -->
<%-- 										<div id="petName" class="petInfo"><%= a.getPetName() %></div><br> --%>
<%-- 										<div id="petKind" class="petInfo"><%= a.getPetKinds() %>(<%= a.getPetCategory() %>)</div><div id="petSize" class="petInfo">&nbsp; 크기 : <%= a.getPetSize() %></div><br> --%>
<%-- 										<div id="petGender" class="petInfo">성별 : <%= a.getPetGender() %> (중성화 <%= a.getPetUnigender() %>)</div><br> --%>
<%-- 										<span id="petAge" class="petInfo"><%= a.getPetAge() %> /</span> --%>
<%-- 										<span id="petWeight" class="petInfo"><%= a.getPetWeight() %>kg /</span> --%>
<%-- 										<span id="petColor" class="petInfo"><%= a.getPetColor() %></span> 									 --%>
<!-- 									</div> -->
<%-- 									<% } %> --%>
<%-- 								<% } %> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 					<% } %> --%>
<%-- 				<% } %> --%>
			<c:if test="${ empty aList || empty fList }">
				등록된 사진이 없습니다.
			</c:if>
			<c:if test="${ !(empty aList) && !(empty fList) }">
				<c:forEach items="${ aList }" var="a" >
					<div class="pictureInfo">
						<div class="petPictureInfo">
							<input type="hidden" id="boardNo" name="boNo" value="${ a.boNo }"/>
							<c:forEach items="${ fList }" var="f">
								<c:if test="${ a.boNo == f.boNo }">
									<img class="pictureForm" src="<%= request.getContextPath() %>/upload_imageFiles/${ f.getChangeName() }"/> 
									<div class="petInfos">
										<div id="petName" class="petInfo">${ a.petName }</div><br>
										<div id="petKind" class="petInfo">${ a.petKinds }(${ a.petCategory })</div> <div id="petSize" class="petInfo">&nbsp; 크기 : ${ a.petSize }</div><br>
										<div id="petGender" class="petInfo">성별 : ${ a.petGender } (중성화 ${ a.petUnigender })</div><br>
										<span id="petAge" class="petInfo">${ a.petAge } /</span>
										<span id="petWeight" class="petInfo">${ a.petWeight }kg /</span>
										<span id="petColor" class="petInfo">${ a.petColor }</span> 									
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</c:if>
			</div>
		</div>
			<!-- 페이징 -->
			<div class="paging">
			
				<!-- 이전 페이지 -->
				<c:set var="current" value="${ pi.currentPage - 1 }"/>
				<a href="adopt.bo?currentPage=${ current }" class="bt" id="beforeBtn">이전 페이지</a>
				<script>
				var currentPage = "<c:out value='${ pi.currentPage }'/>";
<%-- 					if(<%= currentPage %> <= 1){ --%>
					if(currentPage <= 1){
						var before = $('#beforeBtn');
						before.css("visibility", "hidden");
					}
				</script>
				
				<!--  페이지 목록 -->
<%-- 				<% for(int p = startPage; p <= endPage; p++){ %> --%>
<%-- 					<% if(p == currentPage){ %> --%>
<%--                 		<a href="adopt.bo?currentPage=<%= p %>" class="num on"><%= p %></a> --%>
<%--                 	<% } else { %>	 --%>
<%-- 		                <a href="adopt.bo?currentPage=<%= p %>" class="num"><%= p %></a> --%>
<%-- 		            <% } %> --%>
<%-- 	            <% } %> --%>
				<c:set var="p" value="${ pi.startPage }"/>
				<c:forEach var="i" begin="${ p }" end="${ pi.endPage }" step="1">
					<c:if test="${ p eq pi.currentPage }">
						<a href="adopt.bo?currentPage=${ i }" class="num on">${ i }</a>
					</c:if>
					<c:if test="${ p ne pi.currentPage }">
						<a href="adopt.bo?currentPage=${ i }" class="num">${ i }</a> 
					</c:if>
				</c:forEach>
				
	            <!-- 다음 페이지 -->
<%--                 <a href="adopt.bo?currentPage=<%= currentPage + 1 %>" class="num" id="afterBtn">다음 페이지</a> --%>
				<c:set var="current" value="${ pi.currentPage + 1 }"/>
				<a href="adopt.bo?currentPage=${ current }" class="bt" id="afterBtn">다음 페이지</a>
                <script>
                var currentPage = "<c:out value='${ pi.currentPage }'/>";
                var maxPage = "<c:out value='${ pi.maxPage }'/>";
<%--	                 if(<%= currentPage %> >= <%= maxPage%>){ --%>
                	if(currentPage >=  maxPage){
                		var after = $('#afterBtn');
                		after.css("visibility", "hidden");
                	}
                </script>
			</div>
	</form>
		<script>
		function writeForm(){
			location.href='<%= request.getContextPath() %>/adoptWriteForm.bo';
		}
		
		function loginForm(){
			swal("회원 전용 서비스", "로그인 후 이용해주시기 바랍니다.", "info")
			.then((ok) => {
				if(ok){
					location.href='<%= request.getContextPath()%>/loginForm.me';
				}
			});
			return;
		}
		
		$(function(){
			$('.pictureInfo').hover(function(){
				$(this).css('cursor', 'pointer');
			});
		});
		
		$(function(){
			$('.petPictureInfo').click(function(){
				var boNo = $(this).find('input').val();
				location.href="<%= request.getContextPath() %>/adoptDetail.bo?boNo="+boNo;
			});
		});
		</script>
	</section>
</body>
</html>