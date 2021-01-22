<%@page import="board.model.vo.PageInfo"%>
<%@page import="board.model.vo.Questions"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Questions> questionsList=(ArrayList<Questions>)request.getAttribute("questionsList");

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
	
	$('.answerBtn').click(function(){
		console.log($(this).parent().parent().find(".qNo").val());
		$('#popUpqNo').val($(this).parent().parent().find(".qNo").val())
		$('#popUpTitle_QA').find("span").text($(this).parent().parent().find(".qTitle").val());
		$('#popUpWriter_QA').find("span").text($(this).parent().parent().find(".qWriter").val());
		$('#popUpDate_QA').find("span").text($(this).parent().parent().find(".qDate").val());
		$('#popUpContent_QA').find("span").text($(this).parent().parent().find(".qContent").val());
		$('#popUpDiv_QA').css("display","block");
	});
	
	$('#popUpCloseBtn').click(function(){
		$('#popUpqNo').val("");
		$('#popUpTitle_QA').find("span").text("");
		$('#popUpWriter_QA').find("span").text("");
		$('#popUpDate_QA').find("span").text("");
		$('#popUpContent_QA').find("span").text("");
		$('#popUpAnswer_QA').find("textarea").val("");
		
		$('#popUpDiv_QA').css("display","none");
	});
	
	$('#popUpAnswerBtn').click(function(){

		
		$.ajax({
			url: "answerQuestion.bo",
			type: "post",
			data: {"qNo":$('#popUpqNo').val() ,"answer":$('#popUpAnswer_QA').find("textarea").val()},
			success: function(data){
				swal({
					text:"답변 완료",
					icon:"success"
					
				}).
				then((ok)=>{
					if(ok){
						location.href="answerQuestionForm.bo";
					}
				});
			},
			error: function(data){
				alert("ajax 에러");
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
			<div id="listMyBoardTitle">대기중인 문의사항</div>
			<div id="listMyBoardContent">
				<div id="tableMyBoard">
					<table>
						<tr>
							<th>작성일자</th>
							<th>문의사항 제목</th>
							<th></th>
						</tr>
					<%if(!questionsList.isEmpty()){%>
						<% for(Questions q : questionsList){ %>
						<tr class="questionTr">
							<td>
							<input type="hidden" name="qNo" class="qNo" value="<%=q.getBoNo() %>">
							<input type="hidden" name="qWriter" class="qWriter" value="<%=q.getMemId() %>">
							<input type="hidden" name="qTitle"class="qTitle"  value="<%=q.getBoTitle() %>">
							<input type="hidden" name="qDate" class="qDate" value="<%=q.getBoDate() %>">
							<input type="hidden" name="qContent" class="qContent" value="<%=q.getBoContent() %>">
							
							<%=q.getBoDate() %></td>
							<td><%=q.getBoTitle() %></td>
							<td id="buttonTd"><button type="button" class='answerBtn'>답변</button></td>
						</tr>
						<%} %>
					<%}else{ %>
						<tr>
							<td colspan="3">조회 결과가 없습니다.</td>
						</tr>
					<%} %>
						
					</table>
			<div  class="paging">			
			<a href="answerQuestionForm.bo?currentPage=<%=currentPage-1 %>" class="bt" id="beforBtn">이전 페이지</a>			
			<%for(int p=startPage; p<=maxPage; p++){ %>
	   					<% if(p==currentPage){ %>
	   					 <a href="answerQuestionForm.bo?currentPage=<%=p %>" class="num on"><%=p %></a>
	   					<%}else{ %>
	   					 <a href="answerQuestionForm.bo?currentPage=<%=p %>" class="num"><%=p %></a>
	   					<%} %>
	   		<%} %>	
   		 	 <a href="answerQuestionForm.bo?currentPage=<%=currentPage+1 %>" class="bt" id="nextBtn">다음 페이지</a>
            </div>
		
			<div id="popUpDiv_QA">
				<input type="hidden" name="popUpqNo" id="popUpqNo" value="">
				<div id="popUpTitle_QA"><p>문의사항 제목<br><span></span></p></div>
				<div id="popUpWriter_QA"><p>작성자<br><span></span></p></div>
				<div id="popUpDate_QA"><p>작성일자<br><span></span></p></div>
				<div id="popUpContent_QA"><p>문의사항내용<br><span></span></p></div>
				<div id="popUpAnswer_QA"><p>답변</p><br><textarea cols="47" rows="3"  ></textarea></div>
				<button type="button" id="popUpCloseBtn">닫기</button><button type="button" id="popUpAnswerBtn">답변</button>
			</div>
		</div>
		</div>
	</div>
	</section>
</body>
</html>