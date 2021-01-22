<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/member/find_pwd.css" type="text/css">
<script>
	$(document).ready(function() {
		$('#loading_div').hide();
	});
	$(document).ajaxStart(function() {
		$('#loading_div').show();
	});
	$(document).ajaxStop(function() {
		$('#loading_div').hide();
	});
</script>
</head>
<body>
	<section>
		<div id="pw_find_div">
			<div id="find_button_div">
				<input type="button" class="find_button" value="아이디 찾기"
					onclick="location.href='findIdForm.me'" style="cursor: pointer">
				<input type="button" class="find_button" value="비밀번호 찾기" disabled
					style="background: rgba(41, 128, 185, 0.8); color: white; cursor: default;">
			</div>

			<br>

			<div id="pw_find_message">
				<div id="pw_find_title">비밀번호 찾기</div>
				<div>
					비밀번호가 기억나지 않으세요?<br> 아이디를 입력하시면 가입하신 이메일을 통해 비밀번호 재설정이 가능합니다.<br>
					본인인증 시 입력하시는 정보는 인증 이외의 용도로 사용 또는 저장하지 않습니다.
				</div>
			</div>

			<br><br>

			<div id="input_div">
				<input type="text" id="input_id" class="input" name="input_id" placeholder="아이디" autocomplete="off">
			</div>
			<br>
			<div id="input_button_div">
				<button id="input_button" onclick="validate();">확인</button>
			</div>

			<!-- 로딩중 -->
			<div id="loading_div">
				<img src="images/loading.gif">
			</div>
		</div>

		<script>
			function validate() {
				var id = $("#input_id");

				if (id.val().trim().length == 0) {
					swal("","아이디를 입력해주세요.","info")
					.then((ok) => {
						if(ok){
							id.focus();
						}
					});
					
					return;
				}
				
				$.ajax({
					url: "findPwd.me",
					type: "post",
					data: {input_id:id.val()},
					success: function(result){
						if(result == 1){
							swal("메일 전송 완료","입력하신 이메일로 임시 비밀번호를 전송하였습니다.","success")
							.then((ok) => {
								if(ok){
									location.href="loginForm.me";
								}
							});
						}else{
							swal("메일 전송 실패","입력하신 아이디와 일치하는 회원이 없습니다.","error");
							id.val("");
						}
					},
					error: function(data){
						alert("ajax에러 발생");
					}
				});
			}

			$("#input_id").keyup(function(e){
				if(e.keyCode == 13){
					validate();
				}
			});
		</script>
	</section>
</body>
</html>