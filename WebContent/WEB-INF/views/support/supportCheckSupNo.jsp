<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/support/support_check_supNo.css" type="text/css">
</head>
<body>
	<section>
		<div id="ment">후원 내역</div>
	
		<div id="all_div">
			<b>후원 번호를 입력해주세요.</b><br>
			후원 신청할 때 입력하신 이메일을 확인해주세요.
			<div>
				<label>후원 번호</label>
				<input type="text" id="input_supNo" name="input_supNo" autocomplete="off"><br>
				<button id="input_button" onclick="validate();">확인</button>
			</div>
		</div>
		
		<script>
			function validate(){
				var supNo = $("#input_supNo");
				
				if(supNo.val().trim().length == 0){
					swal("", "후원 번호를 입력해주세요.", "info")
					.then((ok) => {
						if(ok){
							supNo.focus();
						}
					});
					
					return;
				}
				
				$.ajax({
					url: "supportCheckSupNo.su",
					type: "post",
					data: {input_supNo:supNo.val()},
					success: function(result){
						console.log("result : " + result);
						if(result == 1){
							location.href="supportList.su?supNo=" + supNo.val();
							supNo.val("");
						}else{
							swal("","입력하신 후원 번호가 존재하지 않습니다.","error")
							.then((ok) => {
								if(ok){
									supNo.val("");
									supNo.focus();
								}
							});
						}
					},
					error: function(data){
						alert("ajax에러 발생");
					}
				});
			}
			
			$("#input_supNo").keyup(function(e){
				if(e.keyCode == 13){
					validate();
				}
			});
		</script>
	</section>
</body>
</html>