<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, board.model.vo.*, member.model.vo.Member, board.model.vo.Questions"  %>     
<%  @SuppressWarnings("unchecked")
	ArrayList<Questions> Qlist = (ArrayList<Questions>)request.getAttribute("Qlist");	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	Member loginUser = (Member)session.getAttribute("loginUser");
	String msg = (String)session.getAttribute("msg");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>    
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet" href="css/questions/questions_list.css" type="text/css">
<meta charset="UTF-8">
<body>
	<section>
		<div id = "ment" class="board_list_wrap">문의게시판</div>
		<table class="board_list">
            <caption>게시판 목록</caption>
                <thead>
                    <tr>
                        <th width="100px">번호</th>
                        <th width="150px">분류</th>
                        <th width="450px">제목</th>
                        <th width="100px">작성자</th>
                        <th width="100px">조회수</th>
                        <th width="100px">날짜</th>
                    </tr>
                </thead>
				 <% if(Qlist.isEmpty()){ %>
		            <tr>
		               <td colspan="6">조회된 리스트가 없습니다.</td>
		            </tr>
		            <% } else{ %>
		         	   <% for(int i=0; i < Qlist.size(); i++){ %>
		         	   <tr>
			         	   <td class="td_boNo">
			         	   
			         	   	<input type="hidden" value="<%=Qlist.get(i).getBoNo() %>">
			         	   	<input type="hidden" value="<%=Qlist.get(i).getBoPwd() %>" class="boPwd">
			         	   <%=Qlist.get(i).getBoNo() %>
			         	   </td>
			         	   <td><%= Qlist.get(i).getQuSub() %></td>
			         	   <td class="tet">
			         		  <%= Qlist.get(i).getBoTitle() %>
			         	   <%	/*암호 아이콘표시*/
			         	  	if(Qlist.get(i).getBoPwd() != null){ 
			         	  	
			         	  	%>	
			         	  	<img src="images/secret.gif">
			         	  	<%
		         	   		}
			         	    %>
			         	     <%	
			         	  	if(Qlist.get(i).getComContent() != null){ 
			         	  	
			         	  	%>	
			         	  	<img src="images/questions.gif">
			         	  	<%
		         	   		}
			         	    %>
			         	   	
			         	   </td>
			         	   <td><%= Qlist.get(i).getMemId() %></td>
			         	   <td><%= Qlist.get(i).getBoCount() %></td>
			         	   <td><%= Qlist.get(i).getBoDate() %></td>
			         	</tr>   
				        <% } %>
				   <% } %>
				   
            </table>
            
  			<div id="wrtie_button_div"> <!-- 로그인유저만 글쓰기 가능하게, 비회원은 로그인페이지로 -->
  				<% if(loginUser!=null){ %> 
         		<button id="write_button" onClick="goWrite()">글쓰기</button>
      			<% } else {%> 
      			<button id="write_button" onClick="goLogin()">글쓰기</button>
      			<% } %>
      		</div>
            
            
            <!-- 페이징 -->    
			<div class="paging">
			
				<!-- 이전 페이지 -->
	         	<a href="list.qu?currentPage=<%= currentPage - 1 %>" class="bt" id="beforeBtn">이전 페이지</a>
		         	<script>
		        	if(<%= currentPage %> <= 1){
		        		var before = $('#beforeBtn');
		        		before.css("visibility","hidden");
		        	}
		       		</script>

		   	    <!-- 페이지 목록 -->
        		<% for(int p = startPage; p <= endPage; p++){ %>
	        		<% if(p == currentPage){ %>  
	        			<a href="list.qu?currentPage=<%= p %>" class="num on"><%= p %></a>
	        		<% } else { %>
	        			<a href="list.qu?currentPage=<%= p %>" class="num"><%= p %></a>
	        		<% } %>
       			 <% } %>
		   	    
		   	    
		   	    <!-- 다음 페이지 -->
		   	    <a href="list.qu?currentPage=<%=currentPage + 1%>" class="bt" id="afterBtn">다음 페이지</a>
				 <script>
		        	if(<%= currentPage %> >= <%= maxPage %>){
		        		var after = $('#afterBtn');
		        		after.css("visibility", "hidden");
		        	}
		        </script>    		  
    		</div>
    	
    	<script>	
        function goWrite(){
			location.href="<%= request.getContextPath() %>/writeForm.qu";
		}
		
		function goLogin(){
			
			window.alert("로그인 후 이용해주시기 바랍니다.");
			location.href="<%= request.getContextPath() %>/loginForm.me";
		}
		
		</script>
       
	
		
	  <script>
		$(function(){
	  		$('.tet').mouseenter(function(){
	   			$(this).parent().css({'background':'#eee', 'cursor':'pointer', 'text-decoration':'underline'});
	   		}).mouseout(function(){
	   			$(this).parent().css({'background': 'none', 'text-decoration':'none'});
	   		}).click(function(){
				var bNo = $(this).parent().children().children('input').val();
				var boPwd = $(this).parent().find('.boPwd').val(); //비번 hidden으로 감춘거 가져옴
				
				
				if(boPwd=='null'){
					location.href="<%= request.getContextPath() %>/detail.qu?bNo=" + bNo;
				}else{
					
					swal({
						title : '비밀글입니다!',					
						content: {
						element : "input",
						attributes : {
						placeholder : "숫자 4자리 암호를 입력해주세요",
						type : "password",
						},
					},
					buttons : {							
						confirm : {
							className : 'swal-btn',
							text : '입력',
							value : true
						}
					}
				}).then((result) => {   //value를 result로 받음
					if(result==boPwd){ 
						swal('입력 성공', '비밀번호가 일치합니다.','success',{
							
							buttons : {
								confirm : {
									className : 'swal-btn',
									text : '확인',
									value : true
								}
							}
						}).then((result) => {
							if(result){
								location.href="<%= request.getContextPath() %>/detail.qu?bNo=" + bNo;
							}
						})
					}  else{
						swal('입력 실패', '비밀번호를 다시 입력해주세요.','warning',{
							
							timer:700
						});
					} 
				});
		   	}
				
			
	   		<!-- -->
	   		
	   		
	   		});

		});
  		
		
</script>
   </section>
 </body>
</html>
