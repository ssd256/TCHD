<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/member/login.css" type="text/css">
<script src="images/jquery.capslockstate.js"></script>
</head>
<body>
	<section>
		<div id="login_div">
			<div id="login_title">Login</div>

			<br>
			<br>

			<div>
				<label class="login_label">&nbsp;&nbsp;&nbsp;아이디&nbsp;&nbsp;</label>
				<input type="text" id="input_id" class="login_input" name="userId" autocomplete="off"> <!-- autocomplete="off" : 기록 안보이게(자동완성X) -->
			</div>

			<br>
			<br>

			<div>
				<label class="login_label">비밀번호&nbsp;&nbsp;</label>
				<input type="password" id="input_pw" class="login_input" name="userPwd">
			</div>
			
			<p id="capslock"><b>CapsLock</b> 키가 눌려있습니다.</p>
			<br>

			<div id="login_find_div">
				<a href="findIdForm.me">아이디 찾기</a>&nbsp;&nbsp; <a>|</a>&nbsp;&nbsp;
				<a href="findPwdForm.me">비밀번호 찾기</a>
			</div>

			<br>
			<br>

			<div id="login_button_div">
				<input type="submit" id="login_button" value="로그인" onclick="validate();">
			</div>

			<br>
			<br>

			<div id="login_signUp_div">
				<label>아직 회원이 아니신가요?&nbsp;&nbsp;</label> <a href="joinForm.me">회원가입</a>
			</div>
		</div>

		<script>
			$('#input_pw').keyup(function(event){
				if(event.keyCode==13){
					validate();
				}
			});
			
			$(document).ready(function(){
				$(window).capslockstate();
				
				$(window).bind("capsOn", function(event){
					if($("#input_pw:focus").length > 0){
						$("#capslock").css("visibility","visible");
					}
				});
				
				$(window).bind("capsOff capsUnknown", function(event){
					$("#capslock").css("visibility","hidden");
				});
				
				$("#input_pw").bind("focusout", function(event){
					$("#capslock").css("visibility","hidden");
				});
				
				$("#input_pw").bind("focusin", function(event){
					if($(window).capslockstate("state") === true){
						$("#capslock").css("visibility","visible");
					}
				});
			});
			
			function validate(){
				var id = $("#input_id");
				var pwd = $("#input_pw");
				
				if(id.val().trim().length == 0){
					/*
						<< alert 대신 예쁜 swal 사용 >>		==> sciprt태그에서 src속성값 설정해야하는데 우리는 index.jsp에 한번에 적어놨음
						swal("title", "text", "icon", {		==> icon : "warning"주황색 느낌표!, "error"빨간색 엑스X, "success"초록색 체크, "info"하늘색 아이i
							button: "확인", 		==> buttons(복수)를 true로 설정하면 기본값인 확인, 취소 버튼 / ["회색배경 검정글씨", "하늘색배경 흰색글씨 포커싱"]
							dangerMode: true	==> dangerMode를 true로 설정하면 취소 버튼에 포커싱되고, 확인 버튼은 빨간색
						})
						.then((변수명)) => {
							조건문으로 조건 설정		==> 버튼(또는 확인)을 클릭하면 변수명=true / swal창 바깥부분(또는 취소)을 클릭하면 변수명=null
						}
					*/
					swal("","아이디를 입력해주세요.","info")
					.then((ok) => { 
						if(ok){
							id.focus();
						}
					});
					
					return;
				}
				
				if(pwd.val().trim().length == 0){
					swal("","비밀번호를 입력해주세요.","info")
					.then((ok) => {
						if(ok){
							pwd.focus();
						}
					});
					
					return;
				}
					
				$.ajax({
					url: "login.me", 
					type: "post", 
					data: {userId:id.val(), userPwd:pwd.val()}, 
					success: function(result){
						if(result == 1 || result==2){
							location.href="<%=request.getContextPath()%>";
						}else if(result==3){
							swal("","아직 승인되지 않은 회원입니다.\n승인을 받기 전까지 특정게시판의 이용에 제한이 있습니다.","info")
							.then((ok)=>{
								if(ok){
									location.href="<%=request.getContextPath()%>";									
								}
							});
						}else{
							swal("로그인 실패","입력한 정보를 가진 회원이 없습니다.","error");
							
							id.val("");
							pwd.val("");
							id.focus();
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