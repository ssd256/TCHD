<%@page import="board.model.vo.PageInfo"%>
<%@page import="member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Member> memberList = (ArrayList<Member>)request.getAttribute("memberList");

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
<script type="text/javascript">
	$(function(){
		console.log('<%=memberList%>');
	
		$('.memberTr').hover(function(){
			$(this).css("text-decoration","underline");
			$(this).css("cursor","pointer");
		},function(){
			$(this).css("text-decoration","none");
			$(this).css("cursor","none");
		});
		
		$('.memberTr').click(function(){
			swal({
				  title: "가입을 승인하시겠습니까?",
				  text: "회원정보 \n"+
			 			 "아이디 :"+$(this).find('.memId').val()+"\n"+
				 		 "단체명 :"+$(this).find('.memName').val()+"\n"+
				         "대표자명 :"+$(this).find('.memGmName').val()+"\n"+
				         "전화번호 :"+$(this).find('.memPhone').val()+"\n"+
				         "사업자등록번호 :"+$(this).find('.memGmRegno').val()+"\n"+
				         "이메일 :"+$(this).find('.memEmail').val()+"\n"+
				         "주소 :"+$(this).find('.memAddr').val() +"\n"
				         
				         ,
				  icon: "info",
				  buttons: true,
				})
				.then((approve) => {
				  if (approve) {
					  $.ajax({
						 url:  "approve.me", 
						  data: {memNo: $(this).find(".memNo").val()},
						  success: function(data){
							  console.log(data);
								  swal({
									  text:"가입승인 완료",
									  icon: "success"	
								  }).
								  then((ok) => {
									  if(ok){
										  location.href="approveGroupMember.me";
									  }
								  });
					  
						  },
						  error : function(data){
							  alert("ajax에러발생");
						  }
					  });
 
				  } else {
				    swal("취소하였습니다.");
				  }
				});
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
		
	})
</script>
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
			<div id="listMyBoardTitle">사업자(단체)회원 가입승인</div>
			<div id="listMyBoardContent">
				
				<div id="tableMyBoard">
					<table>
						<tr>
							<th>대표자명</th>
							<th>단체명</th>
							<th></th>
						</tr>
					<% if(!memberList.isEmpty()){ %>
						<%for(Member member : memberList){ %>
						<tr class="memberTr">
							<td>
							<input type="hidden" name="memNo" class="memNo" value="<%=member.getMem_no() %>" >
							<input type="hidden" name="memId" class="memId" value="<%=member.getMem_id() %>" >
							<input type="hidden" name="memName" class="memName" value="<%=member.getMem_name() %>" >
							<input type="hidden" name="memPhone" class="memPhone" value="<%=member.getMem_phone() %>" >
							<input type="hidden" name="memAddr" class="memAddr" value="<%=member.getMem_addr() %>" >
							<input type="hidden" name="memEmail" class="memEmail" value="<%=member.getMem_email() %>" >
							<input type="hidden" name="memGmRegno" class="memGmRegno" value="<%=member.getGm_regno() %>" >
							<input type="hidden" name="memGmName" class="memGmName" value="<%=member.getGm_name() %>" >
							<%=member.getGm_name()%></td> 
							<td><%=member.getMem_name() %></td>
							<td id="buttonTd"><button type="button" onclick="">승인</button></td>
						</tr>
						<%} %>
					<%}else{ %>
						
						<tr>
							<td colspan="3">조회 결과가 없습니다.</td>
							
						</tr>
						
					<%} %>	
						
						
					</table>
			<div  class="paging">			
			<a href="approveGroupMember.me?currentPage=<%=currentPage-1 %>" class="bt" id="beforBtn">이전 페이지</a>			
			<%for(int p=startPage; p<=maxPage; p++){ %>
	   					<% if(p==currentPage){ %>
	   					 <a href="approveGroupMember.me?currentPage=<%=p %>" class="num on"><%=p %></a>
	   					<%}else{ %>
	   					 <a href="approveGroupMember.me?currentPage=<%=p %>" class="num"><%=p %></a>
	   					<%} %>
	   		<%} %>	
   		  <a href="approveGroupMember.me?currentPage=<%=currentPage+1 %>" class="bt" id="nextBtn">다음 페이지</a>
           </div>	
				</div>
			</div>
		</div>
</section>
</body>
</html>