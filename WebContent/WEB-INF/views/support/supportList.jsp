<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="support.model.vo.Support, java.util.ArrayList, board.model.vo.PageInfo, java.text.DecimalFormat, java.util.Date" %>
<%
	// 비회원
	String supNo = (String)request.getAttribute("supNo");
	Support support = (Support)request.getAttribute("support");

	// 회원
	ArrayList<Support> supportList = (ArrayList<Support>)request.getAttribute("supportList");
	int totalPrice = 0;
	
	// 월별 검색
	int year = 0;
	int month = 0;
	String dateStr = null;
	if(!supportList.isEmpty()){
		Date date = supportList.get(0).getSup_date();
		dateStr = String.valueOf(date);
		System.out.println(dateStr);
		String[] dateArr = dateStr.split("-");
		year = Integer.parseInt(dateArr[0]);
		month = Integer.parseInt(dateArr[1]);
	}else{
		String searchDate = (String)request.getAttribute("search");
		String[] searchDateArr = null;
		if(searchDate != null){
			searchDateArr = searchDate.split("-"); 	
		}
		year = Integer.parseInt(searchDateArr[0]);
		month = Integer.parseInt(searchDateArr[1]);
	}
	
	// 페이징
	int listCount = 0;
	int currentPage = 0;
	int maxPage = 0;
	int startPage = 0;
	int endPage = 0;
	
	if(supportList != null){
		listCount = 0;
		totalPrice = (int)request.getAttribute("totalPrice");
		
		PageInfo pi = (PageInfo)request.getAttribute("pi");
		
		listCount = pi.getListCount();		// 총 게시글 개수
		currentPage = pi.getCurrentPage();	// 현재 페이지 번호
		maxPage = pi.getMaxPage();			// 전체 페이지 중 가장 마지막 페이지
		startPage = pi.getStartPage();		// 페이징 된 페이지 중 시작 페이지
		endPage = pi.getEndPage();			// 페이징 된 페이지 중 마지막 페이지
	}
	
	// 천 단위 콤마(,) 찍기
	DecimalFormat format = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html>
<head>
<link href="css/support/support_list.css" rel="stylesheet" type="text/css">
</head>
<body>
	<section>
		<div id="ment">후원 내역</div>

		<div id="main_div">
			<div>
			<% if(supNo == null){ %>
				<div id="select_option_div">
					<select id="year" class="select_YM">
						<option value="2019">2019</option>
						<option value="2020">2020</option>
					</select> 년
					<select id="month" class="select_YM">
						<option value="01">01</option>
						<option value="02">02</option>
						<option value="03">03</option>
						<option value="04">04</option>
						<option value="05">05</option>
						<option value="06">06</option>
						<option value="07">07</option>
						<option value="08">08</option>
						<option value="09">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select> 월 
					<button id="search_button" onclick="goSearch();">검색</button>
				</div>
			<% } %>
				<div id="support_list_div">
					<table id="support_list_table">
						<tr>
							<th>후원 번호</th>
							<th>후원 날짜</th>
							<th>후원금</th>
						</tr>
					<% if(supNo == null){ %>
						<%-- 회원 (로그인) --%>
						<% for(Support supportMem : supportList){ %>
						<tr>
							<td><%= supportMem.getSup_no() %></td>
							<td><%= supportMem.getSup_date() %></td>
							<td><%= format.format(supportMem.getSup_price()) %></td>
						</tr>
						<% } %>
					<% }else{ %>
						<%-- 비회원 --%>
						<tr>
							<td><%= support.getSup_no() %></td>
							<td><%= support.getSup_date() %></td>
							<td><%= format.format(support.getSup_price()) %></td>
						</tr>
					<% } %>
					</table>
				</div>
			</div>
			
			<% if(supNo == null){ %>
			<%-- 페이징 --%>
			<div class="paging">
				<%-- 이전 페이지 --%>
				<a href="<%= request.getContextPath() %>/supportList.su?<% if(dateStr != null){ %>search=<%= dateStr %>&<% } %>currentPage=<%= currentPage - 1 %>" class="before">&lt;</a>
					
					<% for(int p = startPage; p <= endPage; p++){
						if(p == currentPage){ %>
							<%-- 현재 페이지 --%>
							<a class="choosen"><%= p %></a>
					<%  } else { %>
							<a href="<%= request.getContextPath() %>/supportList.su?<% if(dateStr != null){ %>search=<%= dateStr %>&<% } %>currentPage=<%= p %>" class="num" ><%= p %></a>
				<% 		}
				   	} %>
				
				<%-- 다음 페이지 --%>
				<a href="<%= request.getContextPath() %>/supportList.su?<% if(dateStr != null){ %>search=<%= dateStr %>&<% } %>currentPage=<%= currentPage + 1 %>" class="after">&gt;</a>
				<script>
					if(<%= currentPage %> <= 1 || <%= supportList.isEmpty() %>){
						var before = $(".before");
						before.attr("style", "display:none");
					}
					if(<%= currentPage %> >= <%= maxPage %> || <%= supportList.isEmpty() %>){
						var after = $(".after");
						after.attr("style", "display:none");
					}
				</script>
			</div>
			<% } %>
			
			<div id="total_price">
				<span>총 후원 금액 </span>
			<% if(supNo == null){ %>
				<%-- 회원 (로그인) --%>
				<span id="total_price_won">
					<%= format.format(totalPrice) %>
				</span>
			<% } else{ %>
				<%-- 비회원 --%>
				<span id="total_price_won">
					<%= format.format(support.getSup_price()) %>
				</span>
			<% } %>
				<span>원</span>
			</div>
		</div>
		
		<script>
			$("#list_div").hover(function(){
				$(this).css("font-weight","900");
				$("#list_button").css("font-weight","900");
			}, function(){
				$(this).css("font-weight","400");
				$("#list_button").css("font-weight","400");
			});
			
			$(function(){
				var year = <%= year %>;
				var month = '0' + <%= month %>;
				
				$("#year").val(year);
				$("#month").val(month);
				
				if(<%= totalPrice %> == 0){
					swal("", "해당 월에 존재하는 후원 내역이 없습니다.", "info");
				}
			});
			
			function goSearch(){
				var year = $('#year').val();
				var month = $('#month').val();
				var date = year + '-' + month;
				location.href="<%= request.getContextPath() %>/supportList.su?search=" + date;
			}
		</script>
	</section>
</body>
