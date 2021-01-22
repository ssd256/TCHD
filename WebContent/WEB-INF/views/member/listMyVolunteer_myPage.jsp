<%@page import="java.text.SimpleDateFormat"%>
<%@page import="member.model.vo.Member"%>
<%@page import="board.model.vo.PageInfo, board.model.vo.Volunteer ,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Volunteer> volunteerList = (ArrayList<Volunteer>)request.getAttribute("volunteerList"); 
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/common/nav.css" type="text/css">
<link rel="stylesheet" href="css/member/myPage_listMyBoard.css" type="text/css">
</head>
<body>
<section>	
		<nav>
			<ul id="pageNavi"> 
				<li id="pageNaviTitle"><a href="myPage.me">마이페이지</a></li>
				<li><a href="myPage.me">회원정보수정</a></li>
				<li><a href="listMyBoard.bo">내가 작성한 글</a></li>
				 <%if(((Member)request.getSession().getAttribute("loginUser")).getMem_type().equals("PM")){ %>
					<li><a href="listMyVolunteer.vo">참여 봉사 내역</a></li>
				<%}else{ %>
					<li><a href="listMyVolunteerGm.vo">개설 봉사 내역</a></li>
				<%} %>
				<li><a href="leaveForm.me">회원 탈퇴</a></li>
			</ul>
		</nav>
		<div id="listMyBoardDiv">
		 <%if(((Member)request.getSession().getAttribute("loginUser")).getMem_type().equals("PM")){ %>
			<div id="listMyBoardTitle">참여 봉사 내역</div>
		<%}else{ %>
			<div id="listMyBoardTitle">개설 봉사 내역</div>
		<%} %>
			<div id="listMyBoardContent">
				
				<div id="tableMyBoard">
					<table>
						<tr>
							<th>봉사일자</th>
							<th>제목</th>
							<th>장소</th>
						</tr>
					<%if(!volunteerList.isEmpty()){ %>
						<%for(Volunteer v : volunteerList){ %>
						<tr class="volunteerTr">
							<input type="hidden" class="bNo" value="<%=v.getBoNo() %>">
							<td><%=format.format(v.getVoDate()) %></td>
							<td><%=v.getBoTitle() %></td>
							<td><%=v.getVoPlace().substring(6,8) %></td>
						</tr>
						<%} %>
					<%}else{ %>
						<tr >
							<td colspan="3">조회결과가 없습니다.</td>
							
						</tr>
					<%} %>
					</table>
					<div  class="paging">			
						<a href="list.no?currentPage=<%=currentPage-1 %>" class="bt" id="beforBtn">이전 페이지</a>			
						<%for(int p=startPage; p<=maxPage; p++){ %>
			   					<% if(p==currentPage){ %>
			   					 <a href="listMyVolunteer.vo?currentPage=<%=p%>" class="num on"><%=p %></a>
			   					<%}else{ %>
			   					 <a href="listMyVolunteer.vo?currentPage=<%=p%>" class="num"><%=p %></a>
			   					<%} %>
			  	 		<%} %>	
		   			  <a href="list.no?currentPage=<%=currentPage+1 %>" class="bt" id="nextBtn">다음 페이지</a>
		        	 </div>	
				</div>
			</div>
		</div>
	</section>	
	<script>
	$(function(){
		if(<%=currentPage %> <=1){
			var before = $('#beforBtn');
			//before.attr("href","");
			before.css("visibility","hidden");
		}
		if(<%=currentPage %>== <%=maxPage%>){
			var before = $('#nextBtn');
			//before.attr("href","");
			before.css("visibility","hidden");
		}
		$('.volunteerTr').hover(function(){
			$(this).children().css({'cursor':'pointer', 'background':'#eee'});
		},function(){
			$(this).children().css({'cursor':'none', 'background':'none'});
		}).click(function(){
			var bNo=$(this).find('.bNo').val();
			location.href="volunteerDetail.bo?bNo="+bNo;
		});
		
	});
	
	
	</script>
</body>
</html>