<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, board.model.vo.Board, board.model.vo.Volunteer, board.model.vo.PageInfo, member.model.vo.Member" %>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Volunteer> volunteerList = (ArrayList<Volunteer>)request.getAttribute("volunteerList");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	Member loginUser = (Member)session.getAttribute("loginUser");
	String GmOkNy = "";
	if(loginUser!=null){
		GmOkNy=loginUser.getGm_ok_ny();
	}
	String msg = (String)session.getAttribute("msg");
	String search = (String)request.getAttribute("search");
	String cate = (String)request.getAttribute("cate");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/volunteer/volunteer_list.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/common/common.css" type="text/css">
</head>
<body>
	<section>
		<div class="title">봉사게시판</div>

		<div class="main_div">
			<div class="sub_div">
				<!-- 검색 시작 -->
				<div class="list_search">
					<select id="search_select">
						<option value="boTitle" selected>제목</option>
						<option value="voArea">지역</option>
					</select>
						<input type="text" id="search_input">
						<button type="button" id="search_button">검색</button>
				</div>
				<!-- 검색 종료 -->

				<div class="table_div">
					<table id="list_table">
						<tr>
							<th>번호</th>
							<th>지역</th>
							<th width="400px">제목</th>
							<th>작성자</th>
							<th>작성일자</th>
							<th>모집인원</th>
							<th>마감</th>
							<th>조회수</th>
						</tr>
						<% if(volunteerList.isEmpty()){ %>
						<tr>
							<td colspan="8" id="empty">존재하는 봉사 모집이 없습니다.</td>
						</tr>
						<% } else { %>
						<%		for(Volunteer v : volunteerList){ %>
						<%           if((v.getVoMaxmember()-v.getVoApplymember()) <= 0){ %>		
						<tr class="is deadY">
						<%			 }else{ %>
						<tr class="is deadN">		
						<%} %>
							<td class="event"><%= v.getBoNo() %></td>				<!-- 게시글번호 -->
							<td class="event"><%= v.getVoArea() %></td>				<!-- 봉사지역 -->
							<td class="event" id="tit"><%= v.getBoTitle() %></td>	<!-- 게시글제목 -->
							<td class="event"><%= v.getMemId() %></td>				<!-- 작성자 -->
							<td class="event"><%= v.getBoDate() %></td> 			<!-- 작성일자 -->
							<td class="event"><%= v.getVoMaxmember() %></td>		<!-- 모집인원 -->
							<td class="event" id="deadline">						<!-- 마감유무 -->								
								<% if((v.getVoMaxmember()-v.getVoApplymember()) <= 0){ %>
								<%= "Y" %>
								<% } else { %>
								<%= "N" %>
								<% } %>
							</td>									
							<td class="event"><%= v.getBoCount() %></td>  			<!-- 조회수 -->
						</tr>
						<%		} %>
						<% } %>
					</table>
				</div>
			</div>

			<div class="wrtie_button_div">
				<!-- 관리자와 단체회원만 글쓰기 버튼 보이게끔. -->
				<% if(loginUser != null && loginUser.getMem_id().equals("admin")){ %>
				<input type="button" onclick="location.href='volunteerWriteForm.bo'" id="write_button" value="글쓰기">
				<% } %>
				<% if(loginUser != null && loginUser.getMem_type().equals("GM")){ %>
				<input type="button" onclick="goWriteFormGm();" id="write_button" value="글쓰기">
				<% } %>
			</div>
			
			
			<!-- 페이징 -->
			<div class="paging">
				<!-- 이전 페이지 -->
				<%if(search!=null){ %>
					 <a href="volunteerSearch.bo?currentPage=<%= currentPage-1 %>&search=<%=search %>&cate=<%=cate %>" class="bt" id="beforeBtn">이전 페이지</a>	
				<%}else{ %>
					 <a href="volunteerList.bo?currentPage=<%= currentPage - 1 %>" class="bt" id="beforeBtn">이전 페이지</a>
				<%} %>	
		   	    <!-- 페이지 목록 -->
        		<% for(int p = startPage; p <= endPage; p++){ %>
	        		<% if(p == currentPage){ %>  
	        			<%if(search!=null){ %>
	        				<a href="volunteerSearch.bo?currentPage=<%= p %>&search=<%=search %>&cate=<%=cate %>" class="num on"><%= p %></a>
	        			<%}else{ %>
	        				<a href="volunteerList.bo?currentPage=<%= p %>" class="num on"><%= p %></a>
	        			<%} %>
	        			
	        		<% }else{ %>
	        			<%if(search!=null){ %>
	        				<a href="volunteerSearch.bo?currentPage=<%= p %>&search=<%=search %>&cate=<%=cate %>" class="num"><%= p %></a>
	        			<%}else{ %>
	        				<a href="volunteerList.bo?currentPage=<%= p %>" class="num"><%= p %></a>
	        			<%} %>
	        		<% } %>
       			 <% } %>
		   	    <!-- 다음 페이지 -->
		   	    <%if(search!=null){ %>
					 <a href="volunteerSearch.bo?currentPage=<%=currentPage+1 %>&search=<%=search %>&cate=<%=cate %>" class="bt" id="afterBtn">다음 페이지</a>	
				<%}else{ %>
					 <a href="volunteerList.bo?currentPage=<%=currentPage+1 %>" class="bt" id="afterBtn">다음 페이지</a>		
				<%} %>	
    		</div>
		</div>
	</section>
	<script>
		$(function(){
			
			if(<%= currentPage %> <= 1){
        		var before = $('#beforeBtn');
        		before.css("visibility","hidden");
        	}
			
			if(<%= currentPage %> == <%= maxPage %> || <%=listCount%>==0){
        		var after = $('#afterBtn');
        		after.css("visibility", "hidden");
        	}
			
			$(".event").mouseenter(function(){
				$(this).parent().css({'background':'#eee', 'cursor':'pointer'});
			}).mouseout(function(){
				$(this).parent().css({'background':'none', 'text-decoration':'none'});
			}).click(function(){
				var num = $(this).parent().children().eq(0).text();
				location.href="<%= request.getContextPath() %>/volunteerDetail.bo?bNo=" + num;
			});
		
			$('#search_button').click(function(){
				var select = $('#search_select').val();
				var search = $('#search_input').val();
				if(search.trim().length==0){
					swal("","검색어를 입력해주세요.","info");
					return;
				}
				location.href="volunteerSearch.bo?search="+search+"&cate="+select;
			});
		});
		
		function goWriteFormGm(){
			var loginUser='<%=loginUser%>';
			if(loginUser!='null'){
				var GmOkNy = '<%=GmOkNy%>';
				console.log(GmOkNy);
				if(GmOkNy=='Y'){
					location.href="volunteerWriteForm.bo";
				}else{
					swal("","아직 승인을 받지 못한 회원입니다.\n승인 후 이용가능합니다.","info");
					return;
				}
			}
		}
		
		// 검색어에 특수문자 안됨.
		$(document).ready(function(){
	        
			// 특수문자
		    var replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;
		    // 완성형 아닌 한글
		    var replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
		    
	        $("#search_input").on("focusout", function() {
	            var x = $("#search_input").val();
	            if (x.length > 0) {
	                if (x.match(replaceChar) || x.match(replaceNotFullKorean)) {
	                    x = x.replace(replaceChar, "").replace(replaceNotFullKorean, "");
	                }
	                $("#search_input").val(x);
	            }
	            }).on("keyup", function() {
	                $("#search_input").val($("#search_input").val().replace(replaceChar, ""));
	       });
	    });       
	</script>
</body>
</html>