<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="animalHospital.model.vo.AnimalHospital, java.util.ArrayList, board.model.vo.PageInfo" %>
<%
	ArrayList<AnimalHospital> hospitalList =(ArrayList<AnimalHospital>)request.getAttribute("hospitalList");
	String selectedAddr = (String)request.getAttribute("selectedAddr");
	if(selectedAddr == null){
		selectedAddr = "전체보기";
	}
	
	// 페이징
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();		// 총 게시글 개수
	int currentPage = pi.getCurrentPage();	// 현재 페이지 번호
	int maxPage = pi.getMaxPage();			// 전체 페이지 중 가장 마지막 페이지
	int startPage = pi.getStartPage();		// 페이징 된 페이지 중 시작 페이지
	int endPage = pi.getEndPage();			// 페이징 된 페이지 중 마지막 페이지
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/animalHospital/24hAnimalHospital_list.css" type="text/css">
</head>
<body>
	<section>
		<div id="ment">24시 동물병원</div>
		
		<div id="info_local_div">
			<div id="info">
				* 지역을 선택하시면 해당 지역에 있는<br>
				&nbsp;&nbsp;24시 동물병원의 정보를 확인하실 수 있습니다.
			</div>
			<div>
				<table id="local_select_table">
					<tr class="localSelectTr">
						<td id="selectAllTd">전체보기</td>
						<td>서울특별시</td>
						<td>부산광역시</td>
						<td>대구광역시</td>
						<td>인천광역시</td>
						<td>광주광역시</td>
					</tr>
					<tr class="localSelectTr">
						<td>대전광역시</td>
						<td>울산광역시</td>
						<td>세종특별자치시</td>
						<td>경기도</td>
						<td>강원도</td>
						<td>충청북도</td>
					</tr>
					<tr class="localSelectTr">
						<td>충청남도</td>
						<td>전라북도</td>
						<td>전라남도</td>
						<td>경상북도</td>
						<td>경상남도</td>
						<td>제주특별자치도</td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		<div id="list_size">
			<span>총 <%= listCount %>개</span>
		</div>
		<div id="animal_hospital_div">
			<table id="animal_hospital_table">
				<tr>
					<th>병원명</th>
					<th style="width: 170px;">전화번호</th>
					<th>주소</th>
					<th style="display: none;"></th>
				</tr>
			<%if(hospitalList.isEmpty()){ %>
				<tr>
					<td colspan="4">조회결과가 없습니다.</td>
				</tr>
			<%}else{ %>	
				<%for(AnimalHospital hospital : hospitalList){ %>
				<tr class="hospitalListTr">
					<td><%= hospital.getHos_name() %></td>
					<td><%= hospital.getHos_phone() %></td>
					<td><%= hospital.getHos_addr() %></td>
					<td style="display: none;"><%= hospital.getHos_no() %></td>
				</tr>
				<%} %>
			<%} %>
			</table>
		</div>
		
		<%-- 페이징 --%>
			<div class="paging">
				<%-- 이전 페이지 --%>
				<a href="<%= request.getContextPath() %>/hospitalList.ho?addr=<%= selectedAddr %>&currentPage=<%= currentPage - 1 %>" class="before">&lt;</a>
				
				<% for(int p = startPage; p <= endPage; p++){
						if(p == currentPage){ %>
							<%-- 현재 페이지 --%>
							<a class="choosen"><%= p %></a>
					<%  } else { %>
							<a href="<%= request.getContextPath() %>/hospitalList.ho?addr=<%= selectedAddr %>&currentPage=<%= p %>" class="num" ><%= p %></a>
				<% 		}
				   }%>
				
				<%-- 다음 페이지 --%>
				<a href="<%= request.getContextPath() %>/hospitalList.ho?addr=<%= selectedAddr %>&currentPage=<%= currentPage + 1 %>" class="after">&gt;</a>
				<script>
					if(<%= currentPage %> <= 1 || <%= hospitalList.isEmpty() %>){
						var before = $(".before");
						before.attr("style", "display:none");
					}
					if(<%= currentPage %> >= <%= maxPage %> || <%= hospitalList.isEmpty() %>){
						var after = $(".after");
						after.attr("style", "display:none");
					}
				</script>
			</div>
		
		
		<script>
			// 지역 선택 테이블
			$(function(){
				var selectedAddr = '<%= selectedAddr %>';
				if(selectedAddr == "null"){
					$("#selectAllTd").css({'background':'rgba(41, 128, 185, 0.6)', 'color':'#fafafa'});
				}
				var td = $('#local_select_table').children().children().children();
				$.each(td, function(index, item){
					if(selectedAddr == item.innerHTML){
						item.style.background = "rgba(41, 128, 185, 0.6)";
						item.style.color = "#fafafa";
						item.setAttribute("class", "selected");
					}
				});
				
				// hover(mouseenter, mouseleave)
				$(".localSelectTr").children().hover(function(){
					$(this).css('cursor', 'pointer');
				}, function(){
					$(this).css('cursor', 'none');
				});
				
				if(selectedAddr != "null"){
					$(".localSelectTr").children().hover(function(){
						$(this).css({'cursor':'pointer', 'background':'rgba(41, 128, 185, 0.6)', 'color':'#fafafa'});
					}, function(){
						$(this).css({'cursor':'none', 'background':'#eee', 'color':'black'});
					});
				}
				
				$('.selected').hover(function(){
					$(this).css({'cursor':'pointer', 'background':'rgba(41, 128, 185, 0.6)', 'color':'#fafafa'});
				}, function(){
					$(this).css({'cursor':'pointer', 'background':'rgba(41, 128, 185, 0.6)', 'color':'#fafafa'});
				});
			});
			
			$('.localSelectTr').children().click(function(){
				var addr = $(this).text();
				location.href="<%= request.getContextPath() %>/hospitalList.ho?addr=" + addr;
			});
			
			// 병원 목록 테이블
			$(function(){
				$(".hospitalListTr").mouseenter(function(){
					$(this).css({'cursor':'pointer', 'background':'#eee'});
					$(this).children().css('border-right', '2px solid white');
				}).mouseleave(function(){
					$(this).css({'cursor':'none', 'background':'none'});
					$(this).children().css('border-right', 'none');
				}).click(function(){
					var hosNo = $(this).children().eq(3).text();
					location.href="<%= request.getContextPath() %>/hospitalDetail.ho?hosNo=" + hosNo;
				});
			});
		</script>
	</section>
</body>
</html>