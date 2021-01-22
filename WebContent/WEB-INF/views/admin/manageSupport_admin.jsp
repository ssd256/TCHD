<%@page import="java.util.Calendar"%>
<%@page import="board.model.vo.PageInfo, java.util.Date, java.text.DecimalFormat, support.model.vo.Support, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Support> supportList = (ArrayList<Support>)request.getAttribute("supportList");
	int totalPrice  =(int)request.getAttribute("totalPrice");
	
	DecimalFormat format = new DecimalFormat("###,###");
	int year=0;
	int month=0;
	String dateStr=null;
	if(!supportList.isEmpty()){
		Date date = supportList.get(0).getSup_date();
		dateStr = String.valueOf(date);
		System.out.println(dateStr);
		String[] dateArr = dateStr.split("-");
		year = Integer.parseInt(dateArr[0]);
		month = Integer.parseInt(dateArr[1]);
	}else{
		String searchDate = (String)request.getAttribute("search");
		String[] searchDate2 =null;
		if(searchDate!=null){
			searchDate2 = searchDate.split("-"); 	
		}
		year=Integer.parseInt(searchDate2[0]);
		month=Integer.parseInt(searchDate2[1]);
	}
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
				<li id="pageNaviTitle"><a href="approveGroupMember.me">관리자 페이지</a></li>
				<li><a href="approveGroupMember.me">사업자(단체) 가입승인</a></li>
				<li><a href="answerQuestionForm.bo">대기중인 문의사항</a></li>
				<li><a href="manageSupport.bo">후원관리</a></li>
			</ul>
		</nav>
		<div id="listMyBoardDiv">
			<div id="listMyBoardTitle">후원관리</div>
			<div id="listMyBoardContent">
				<div id="selectOptionDiv">
					<select id='year'>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
					</select>년
					<select id="month">
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
					</select>월 <button id="searchBtn" onclick="goSearch();">검색</button>
				</div>
				<div id="tableMyBoard">
					<table>
						<tr>
							<th>회원아이디</th>
							<th>회원(단체)명</th>
							<th>회원유형</th>
							<th>후원날짜</th>
							<th>후원금액</th>
						</tr>
					<% if(supportList.isEmpty()){ %>
						<tr>
							<td colspan="5">조회 결과가 없습니다.</td>
						</tr>
					<%}else{%>
						<%for(Support s : supportList){ %>
							<tr>
								<td><%=s.getMem_id() %></td>
								<td><%=s.getMem_name() %></td>
								<td>
								<%if(s.getMem_type()==null){ %>
									비회원
								<%}else if(s.getMem_type().equals("GM")){ %>
									단체회원 
								<%}else{ %>
									개인회원
								<%} %>
								</td>
								<td><%=s.getSup_date() %></td>
								<td><%=format.format(s.getSup_price())%></td>
							</tr>
						<%} %>
					<%} %>
					<tr id="totalPrice">
					<td colspan="5" ><span id='totalSpan'>총 <%= listCount %>건</span> 해당 기간의 총 후원 금액 : <%=format.format(totalPrice) %><span> 원</span></td>
					</tr>
					</table>
					<div  class="paging">			
						<a href="manageSupport.bo?<%if(dateStr!=null){ %>search=<%=dateStr%>&<%} %>currentPage=<%=currentPage-1 %>" class="bt" id="beforBtn">이전 페이지</a>			
						<%for(int p=startPage; p<=maxPage; p++){ %>
				   					<% if(p==currentPage){ %>
				   					 <a href="manageSupport.bo?<%if(dateStr!=null){ %>search=<%=dateStr%>&<%} %>currentPage=<%=p %>" class="num on"><%=p %></a>
				   					<%}else{ %>
				   					 <a href="manageSupport.bo?<%if(dateStr!=null){ %>search=<%=dateStr%>&<%} %>currentPage=<%=p %>" class="num"><%=p %></a>
				   					<%} %>
				   		<%} %>	
			   		 	 <a href="manageSupport.bo?<%if(dateStr!=null){ %>search=<%=dateStr%>&<%} %>currentPage=<%=currentPage+1 %>" class="bt" id="nextBtn">다음 페이지</a>
		            </div>
				</div>
			</div>
		</div>
	</section>
	<script>
	$(function(){
		var year=<%=year %>;
		var month='0'+<%=month %>;

		$('#year').val(year);
		$('#month').val(month);
				
		
		///////////////////
		
		
		if(<%=currentPage %> <=1){
			var before = $('#beforBtn');
			//before.attr("href","");
			before.css("visibility","hidden");
		}
		if(<%=currentPage %>== <%=maxPage%> || <%=listCount%>==0){
			var before = $('#nextBtn');
			//before.attr("href","");
			before.css("visibility","hidden");
		}
	})
	
	function goSearch(){
		var year = 	$('#year').val();
		var month = $('#month').val();
		var date= year+'-'+month;
		location.href="manageSupport.bo?search="+date;
	}
	
	
		
	
	</script>
</body>
</html>