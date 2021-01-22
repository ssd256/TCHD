<%@page import="board.model.vo.PageInfo, member.model.vo.Member, board.model.vo.Notice, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Notice> noticeList = (ArrayList<Notice>)request.getAttribute("noticeList");
	Member loginUser = (Member)request.getSession().getAttribute("loginUser");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	String search = (String)request.getAttribute("search");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/notice/notice_list.css" type="text/css">
</head>
<body>
<section>
      <div id="ment">공지사항</div>
      <div id="search_div">
         <input type="text" id="search_input">
         <button id="search_button" onclick="goSearch();">검색</button>
      </div>
      <div id="notice_list_div">
         <table id="notice_list_table">
            <tr>
               
               <th>제목</th>
               <th>작성자</th>
               <th>작성일자</th>
               <th>조회수</th>
            </tr>
           <%if(noticeList.isEmpty()){ %>
           	<tr>
           		<td colspan="4">조회결과가 없습니다.</td>
           	</tr>
           <%}else{ %>
           	<%for(int i=0; i<noticeList.size();i++){ %>
            <tr class="noticeTr">
               <input type="hidden" class="bNo" value='<%=noticeList.get(i).getBoNo() %>'>
               <td class="tit">[<%=noticeList.get(i).getNoticeSubject()%>] <%=noticeList.get(i).getBoTitle() %></td>
               <td>관리자</td>
               <td><%=noticeList.get(i).getBoDate() %></td>
               <td><%=noticeList.get(i).getBoCount() %></td>
            </tr>
            <%} %>
           <%} %> 
        
 
         </table>
      </div>
      <div id="wrtie_button_div">
      <%if(loginUser !=null && loginUser.getMem_id().equals("admin")){ %>
         <button id="write_button" onclick="location.href='writeForm.no'">글쓰기</button>
       <%} %>
      </div>
  		<div  class="paging">		
  			<%if(search!=null){ %>
				 <a href="search.no?currentPage=<%=currentPage-1 %>&search=<%=search %>" class="bt" id="beforBtn">이전 페이지</a>	
			<%}else{ %>
				 <a href="list.no?currentPage=<%=currentPage-1 %>" class="bt" id="beforBtn">이전 페이지</a>		
			<%} %>		
			<%for(int p=startPage; p<=maxPage; p++){ %>
	   					<% if(p==currentPage){ %>
	   						<%if(search!=null){ %>
	   							 <a href="search.no?currentPage=<%=p %>&search=<%=search %>" class="num on"><%=p %></a>
	   						<%}else{ %>
	   							 <a href="list.no?currentPage=<%=p %>" class="num on"><%=p %></a>
	   						<%} %>	
	   					<%}else{ %>
	   					 	<%if(search!=null){ %>
	   							 <a href="search.no?currentPage=<%=p %>&search=<%=search %>" class="num"><%=p %></a>
	   						<%}else{ %>
	   							 <a href="list.no?currentPage=<%=p %>" class="num"><%=p %></a>
	   						<%} %>
	   					<%} %>
	   		<%} %>
	   		<%if(search!=null){ %>
				 <a href="search.no?currentPage=<%=currentPage+1 %>&search=<%=search %>" class="bt" id="nextBtn">다음 페이지</a>	
			<%}else{ %>
				 <a href="list.no?currentPage=<%=currentPage+1 %>" class="bt" id="nextBtn">다음 페이지</a>		
			<%} %>		
          </div>	
   </section>
<script>
$(function(){
	$('.noticeTr').hover(function(){
		$(this).children().css({'cursor':'pointer', 'background':'#eee'});
	}, function(){
		$(this).children().css({'cursor':'none', 'background':'none'});
	}).click(function(){
		var bNo=$(this).find('.bNo').val();
		location.href="detail.no?bNo="+bNo;
	});
	
	if(<%=currentPage %> <=1){
		var before = $('#beforBtn');
		//before.attr("href","");
		before.css("visibility","hidden");
	}
	if((<%=currentPage %>== <%=maxPage%> || <%=listCount%>==0)){
		var before = $('#nextBtn');
		//before.attr("href","");
		before.css("visibility","hidden");
	}
	
	
});
	function goSearch(){
		var search = $('#search_input');
		if(search.val().trim().length==0){
			swal("","검색어를 입력해주세요.","info");
			search.focus();	
		}else{
			location.href="search.no?search="+search.val().trim();
		}
	}
</script>
</body>
</html>