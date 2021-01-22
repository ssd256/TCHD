<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.model.vo.Questions, member.model.vo.Member, board.model.vo.*" %>
<%@page import="java.util.ArrayList"%>
<% Questions q = (Questions)request.getAttribute("qBoard");
	Member loginUser = (Member)request.getSession().getAttribute("loginUser");
	ArrayList<Files> fileList = (ArrayList<Files>)request.getAttribute("file");

	ArrayList<Questions> questionsList=(ArrayList<Questions>)request.getAttribute("questionsList");
	Files file =null;
	if(fileList!=null){
		for(int i=0; i<fileList.size();i++){
			file = fileList.get(i);
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/questions/questions_detail.css" type="text/css">
</head>
<body>
 	<section><!-- <span>첨부파일 : example.PNG</span> -->
		<div id = "ment" class="board_list_wrap">문의게시판</div>
		<form method="post" action="updateForm.qu">
     	<div class = "board_list_design">
     		<div class = "head_board">
     			<input type="hidden" name="qNo" value="<%=q.getBoNo() %>">
            	<input type="hidden" name="qTitle" value="<%=q.getBoTitle() %>">
            	<input type="hidden" name="qContent" value="<%=q.getBoContent() %>">
            	<input type="hidden" name="qSubject" value="<%=q.getMemLeave() %>">
            	<% if(file!=null){%>
            		<input type="hidden" name="qFileNo" value="<%=file.getFileNo() %>">
            	<%} %>
     			<h1 div class = "title_area"><%= q.getBoTitle() %>
     				<%	/*암호 아이콘표시*/
			         	  	if(q.getBoPwd() != null){ /* !=이 안먹혀....... ==이 먹는단것은 null값이 들어가고있단걸까요  */
			         	  	
			         	  	%>	
			         	  	<img src="images/secret.gif">
			         	  	<%
		         	   		}
			         	    %>
			         	     <%	
			         	  	if(q.getComContent() != null){ 
			         	  	
			         	  	%>	
			         	  	<img src="images/questions.gif">
			         	  	<%
		         	   		}
			         	    %>
     			</h1>
     			<h4 div class = "info_area"><%= q.getMemId() %> | <%=q.getBoDate() %> | <%=q.getBoCount() %></h4>
     				
     			</div>
     		<div id = "div1" class = "div-color">
				<div id="img_div">
           		 <%if(file!=null){ %>
        		    <img src="upload_imageFiles/<%=file.getChangeName()%>"></div>
        	    <%} %>
        		    <div id="contents"><%=q.getBoContent() %></div>
       			</div>
				
			</div>
     		
     		
     		<%if(q.getComContent() != null){ 
     		%>
			<div id = "hr_line"><hr></div>
			<div class="board_list">
				<div id = "div_head">
					<h3>관리자 답변  <img src="images/supervisor.png" width="25px" height="25px"></h3><%= q.getComContent() %>
				</div>	
				<div id = "div_question">
					<textarea name="content" class="content" id="comment" readonly>안녕하세요. <%= q.getMemId() %> 회원님, <%= q.getMemLeave() %> 관련 문의 주셨네요!  <%=	q.getBoPwd() %>
					</textarea>
					<div id = "notice">*추가 문의사항이 있으실시 해당 페이지 스크릿샷을 첨부로 올려주시면 보다 수월하고 빠른 답변을 받아보실 수 있습니다.*</div>
					
				</div>
				<div class = btn_bottom>
				<%if(loginUser !=null && loginUser.getMem_id().equals("admin")){ %>		
						<input id="btn_recontent" type="button" class="btn" onClick="location.href='list.qu'" value="수정">					
						<input id="btn_delete" type="button" class="btn" onClick="location.href='./questionsList.jsp'" value="삭제">
						<input id="btn_content" type="button" class="btn" onClick="location.href='./questionsDetail.jsp'" value="등록">
				<%} %>
				</div>
			</div>
			
			<%
			}
			%>
			
			<div class="list_div">
				
					<input type="button" class="btn_list_go" value="목록보기" onClick="location.href='list.qu'">
				
				<div class="text_align_right">	 <!-- 작성한 회원이 아니면 삭제 수정버튼 안보이게하기. -->
				
				<% if(loginUser != null && q.getMemId().equals(loginUser.getMem_id())){ %>  
					<input type="button" id="delete" class="btn_list" value="삭제">
					<input type="submit" id="alter" class="btn_list" value="수정" onclick="location.href='<%= request.getContextPath()%>/updateForm.qu?bNo=<%= q.getBoNo()%>'"/> 
				
				<% } %>
					
				</div>
			</div>
			<script type="text/javascript">
			var bNo = <%= q.getBoNo() %>;
			
     			 $('#comment').focus(function(){
    		     $(':focus').blur();     
  				    });
     			 
     			 
     		
	      		$(function(){
	 				console.log('<%=q %>');
	 				console.log('<%=questionsList %>');
	 			});
	      		
	      		
	      		$('#delete').on('click', function(){
	
	    			swal({
	    				title : '게시글 삭제',
	    				text : '해당 게시글을 삭제하시겠습니까?',
	    				value : true
	    			}).then((ok) => {		
	    				location.href = "<%= request.getContextPath() %>/qDelete.qu?bNo=" + bNo;	
	    				swal("삭제 완료", "해당 게시글이 삭제되었습니다.", "success")
	    			});
	    			return true;
	    			
	    		});
	      		
	      		
      		
      		</script>
      		</form>
		</section>
</body>
</html>		