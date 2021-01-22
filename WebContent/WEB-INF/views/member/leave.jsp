<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/member/leave.css" type="text/css">
<link rel="stylesheet" href="css/common/nav.css" type="text/css">
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
		<div id="all_div">
			<div id="ment">회원 탈퇴</div>
			<div id="info">
				탈퇴를 원하시면 비밀번호를 입력해주세요.<br>
				단, 동일한 아이디로 재가입은 불가능합니다.
			</div>
			<div id="box_div">
				<div id="input_div">
					<label id="label_pwd">비밀번호</label>
					<input type="password" id="input_pwd" name="input_pwd">
				</div>
				<button id="leave_button" onclick="validate();">탈퇴</button>
			</div>
		</div>
		
		<script>
		function validate() {
			var pwd = $("#input_pwd");
			
			if(pwd.val().trim().length == 0){
				swal("", "비밀번호를 입력해주세요.", "info")
				.then((ok) => {
					if(ok){
						pwd.focus();
					}
				});
				
				return;
			}
			
			$.ajax({
				url: "leave.me",
				type: "post",
				data: {input_pwd:pwd.val()},
				success: function(result){
					if(result == 1){
						swal("회원 탈퇴 완료","지금까지 ♡함께하묘 행복하개♡를 이용해주셔서 감사합니다.","success")
						.then((ok) => {
							if(ok){
								location.href="<%=request.getContextPath()%>";
							}
						});
					}else{
						swal("회원 탈퇴 실패","입력하신 비밀번호가 회원 정보와 다릅니다.\n비밀번호를 확인해주세요.","error");
						pwd.val("");
					}
				},
				error: function(data){
					alert("ajax에러 발생");
				}
			});
		}
		</script>
	</section>
</body>
</html>