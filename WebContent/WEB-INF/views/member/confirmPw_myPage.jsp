<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/member/myPage_confirmPw.css" type="text/css">
<link rel="stylesheet" href="css/common/nav.css" type="text/css">
</head>
<body>
<section>	
	<article>
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
		<div  id="confirmPwForm">
		<!-- <form id="confirmPwForm" method="post" action="confirmPw.me"> -->
			비밀번호를 입력해주세요.	
			<div >
				<label>비밀번호</label>
				<input type="password" name="inputPw" id="inputPw" required><br>
				<button type="button"id="checkBtn">확인</button>
				
			</div>
		</div>
		<!-- </form> -->
	</article>
	<script>
		$(function(){
			$("#checkBtn").click(function(){
				var inputPw =$("#inputPw"); 
				console.log(inputPw.val());
				
				if(inputPw.val().length==0){
					swal("","비밀번호를 입력하세요.","info");
				}else{
					
					$.ajax({
						url: "confirmPw.me",
						type: "post",
						data: {inputPw: inputPw.val()},
						success: function(data){
							console.log(data);
							if(data==1){
								swal("","비밀번호가 일치합니다. 마이페이지로 이동합니다,","success")
								.then((ok) =>{
									if(ok){
										location.href="myPage.me";
									}
								});
							}else{
								swal("","비밀번호가 틀립니다.","error");
								inputPw.val("");
							}
							
						},
						error: function(data){
							alert("ajax 에러 발생")
						}
						
					});
				}
				
			});
			
		});
		
		$("#inputPw").keyup(function(e){
			if(e.keyCode == 13){
				$("#checkBtn").click();
			}
		});
	</script>
</section>
</body>
</html>