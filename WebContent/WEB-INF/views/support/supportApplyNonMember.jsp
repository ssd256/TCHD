<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/support/support_apply_nonmember.css" rel="stylesheet" type="text/css">
<script>
	$(document).ready(function(){
		$('#loading_div').hide(); // 첫 시작 시 로딩바 숨기기
	});
	$(document).ajaxStart(function(){
		$('#loading_div').show(); // ajax 실행 시 로딩바 보여줌
	});
	$(document).ajaxStop(function(){
		$('#loading_div').hide(); // ajax 종료 시 로딩바 숨기기
	});
</script>
</head>
<body>
	<section>
		<div id="ment">후원 신청</div>
		<div id="main_div">
			<div>
				<table id="apply_table">
					<tr>
						<td class="steps" id="step1" style="background: rgb(41, 128, 185);">STEP1.<br>후원금 선택</td>
						<td class="gt">&gt;</td>
						<td class="steps" id="step2">STEP2.<br>후원자 정보 입력</td>
						<td class="gt">&gt;</td>
						<td class="steps" id="step3">STEP3.<br>후원금 결제</td>
					</tr>
					<tr>
						<!-- step1 -->
						<td id="select_price" class="apply_table_td" colspan="5">
							<select id="price_control" onChange="directFunction(this.value);">
								<option value="선택안함">--------------------</option>
								<option value="10000">1만원</option>
								<option value="30000">3만원</option>
								<option value="50000">5만원</option>
								<option value="100000">10만원</option>
								<option value="직접입력">직접입력</option>
							</select>
							<input type="text" id="input_direct" maxlength="15" onkeyup="addCommas(this.value);" style="display: none;" autocomplete="off">
						</td>
						
						<!-- step2 -->
						<td id="input_info" class="apply_table_td" colspan="5" style="display: none;">
							<div id="input_info_div">
								* 비회원은 후원자의 정보를 입력해야 후원 신청이 가능합니다.<br>
								&nbsp;&nbsp;정상적으로 신청이 완료되면 입력하신 이메일로 신청 내역을 보내드립니다.<br>
							</div>
							<div>
								<table id="input_info_table">
									<tr>
										<th><span>이름</span></th>
										<td><input type="text" id="input_name" class="input_box" autocomplete="off"></td>
									</tr>
									<tr>
										<th><span>이메일</span></th>
										<td><input type="email" id="input_email" class="input_box" autocomplete="off"></td>
									</tr>
								</table>
							</div>
						</td>
						
						<!-- step3 -->
						<td id="payment" class="apply_table_td" colspan="5" style="display: none;">
							<input type="checkbox" id="payment_check">
							<span>&nbsp;&nbsp;무통장입금 (ㅇㅇ은행 13579-55-24068)</span>
						</td>
					</tr>
				</table>
				<div id="total_price">
					<span>후원 금액 </span>
					<span id="total_price_won">0</span>
					<span>원</span>
				</div>
			</div>
			<div id="vol_content_footer">
				<button id="pre_button" class="buttons" onclick="validateBack();" style="display: none;">이전</button>
				<button id="next_button" class="buttons" onclick="validateNext();">다음</button>
				<button id="apply_button" class="buttons" onclick="validateApply();" style="display: none;">신청</button>
			</div>
			
			<!-- 로딩중 -->
			<div id="loading_div">
				<img src="images/loading.gif">
			</div>
		</div>
		
		<script>
			function directFunction(select){
				if (select == '직접입력') {
					// 직접입력을 선택하면 금액을 입력할 수 있는 텍스트 상자 보이게
					$('#input_direct').show();
				} else {
					$('#input_direct').hide();
				}
			}
			
			function addCommas(input){
				// 금액을 직접 입력하는 텍스트 상자에 대한 정규표현식
				input = input.replace(/[^0-9]/g,''); // 숫자만 입력 가능(숫자가 아닌 다른 문자 입력 시 바로 지워짐)
				input = input.replace(/,/g,''); // , 입력하면 바로 지워짐
				$("#input_direct").val(input.replace(/\B(?=(\d{3})+(?!\d))/g,",")); // 정규식을 이용해 3자리마다 , 추가
				var typing = $("#input_direct").val();
				
				if(typing.charAt(0) != "0"){
					// 직접 입력하는 텍스트 상자에 0이 아닌 숫자를 입력한 경우 바로 합계 금액에 표시됨
					$("#total_price_won").html(typing);
				} else if(typing.charAt(0) == "0"){
					// 직접 입력하는 텍스트 상자에 첫 글자부터 0을 입력하는 경우
					swal("", "올바른 금액을 입력해주세요.", "error")
					.then((ok) => {
						if(ok){
							$("#input_direct").val("");
							$("#input_direct").focus();
						}
					});
				}
				if($("#input_direct").val() == ""){
					// 직접 입력하는 텍스트 상자에 금액을 입력했다가 다 지운 경우
					$("#total_price_won").html("0");
				}
			}
			
			$("#price_control").change(function(){
				// 콤보박스에서 금액을 선택하면 바로바로 총 후원 금액에 표시됨
				var select = $("#price_control").val();
				
				if(select != "직접입력"){
					var selectComma = "";
					
					switch(select){
					case "10000": case "30000": case "50000":
						selectComma = select.substring(0, 2) + ",000";
						break;
					case "100000":
						selectComma = select.substring(0, 3) + ",000";
						break;
					case "선택안함":
						selectComma = "0";
						break;
					}
					$("#total_price_won").html(selectComma);
				}
			});
			
			// 다음버튼
			// step1 -> step2) 금액 선택(입력) 없이 다음 버튼 누른 경우
			//					이전 버튼 none / 신청 버튼 none
			// step2 -> step3) 후원자 정보 다 입력하지 않고 다음 버튼 누른 경우
			//					==> 성명, 생년월일, 연락처, 이메일 필수 / 주소 선택
			//					이전 버튼 inline-block / 신청 버튼 none
			function validateNext(){
				var select = $("#price_control").val();
				var input = $("#input_direct");
				var name = $("#input_name");
				var email = $("#input_email");
				
				if($("#pre_button").css("display") == "none" && $("#apply_button").css("display") == "none"){
					// step1 -> step2
					if(select == "직접입력" && input.val().trim().length == 0){
						swal("","금액을 입력해주세요.","info")
						.then((ok) => {
							if(ok){
								input.focus();
							}
						});
						
						return;
						
					} else if(select == "선택안함"){
						swal("","금액을 선택해주세요.","info");
						
						return;
					}
					
					$("#step1").css("background", "#aaa");
					$("#step2").css("background", "rgb(41, 128, 185)");
					$("#step3").css("background", "#aaa");
					
					$("#select_price").hide();
					$("#input_info").show();
					$("#payment").hide();
					
					$("#pre_button").show();
					$("#next_button").show();
					$("#apply_button").hide();
					
					// 다음 버튼 누르면 무조건 후원자 정보 초기화
					$(".input_box").val("");
				} else if($("#pre_button").css("display") == "inline-block" && $("#apply_button").css("display") == "none"){
					// step2 -> step3
					if(name.val().trim().length == 0){
						swal("","이름을 입력해주세요.","info")
						.then((ok) => {
							if(ok){
								name.focus();
							}
						});
						
						return;
					} else if(email.val().trim().length == 0){
						swal("","이메일을 입력해주세요.","info")
						.then((ok) => {
							if(ok){
								email.focus();
							}
						});
						
						return;
					}
					
					$("#step1").css("background", "#aaa");
					$("#step2").css("background", "#aaa");
					$("#step3").css("background", "rgb(41, 128, 185)");
					
					$("#select_price").hide();
					$("#input_info").hide();
					$("#payment").show();
					
					$("#pre_button").show();
					$("#next_button").hide();
					$("#apply_button").show();
					
					// 다음 버튼 누르면 무조건 체크박스에 체크 안되어있게
					$("#payment_check").prop("checked", false);
				}
			}
			
			// 이전 버튼
			// step2 -> step1) 다음 버튼 inline-block / 신청 버튼 none
			// step3 -> step2) 다음 버튼 none / 신청 버튼 inline-block
			function validateBack(){
				if($("#next_button").css("display") == "inline-block" && $("#apply_button").css("display") == "none"){
					// stpe2 -> step1
					$("#step1").css("background", "rgb(41, 128, 185)");
					$("#step2").css("background", "#aaa");
					$("#step3").css("background", "#aaa");
					
					$("#select_price").show();
					$("#input_info").hide();
					$("#payment").hide();
					
					$("#pre_button").hide();
					$("#next_button").show();
					$("#apply_button").hide();
				} else if($("#next_button").css("display") == "none" && $("#apply_button").css("display") == "inline-block"){
					// step3 -> step2
					$("#step1").css("background", "#aaa");
					$("#step2").css("background", "rgb(41, 128, 185)");
					$("#step3").css("background", "#aaa");
					
					$("#select_price").hide();
					$("#input_info").show();
					$("#payment").hide();
					
					$("#pre_button").show();
					$("#next_button").show();
					$("#apply_button").hide();
				}
			}
			
			// 체크하지 않고 신청 버튼을 누른 경우 swal창이 뜨게
			function validateApply(){
				var select = $("#price_control").val();
				var input = $("#input_direct");
				var name = $("#input_name");
				var email = $("#input_email");
				var check = $("#payment_check");

				if(check.is(":checked") == false){
					swal("", "결제 방법을 체크해주세요.", "info");
					
					return;
				}
				
				$.ajax({
					url: "supportApplyNonMember.su",
					type: "post",
					data: {select:select, input_direct:input.val(), name:name.val(), email:email.val()},
					success: function(result){
						if(result == 1){
							swal("후원 신청 완료","입력하신 이메일로 후원 내역을 전송하였습니다.","success")
							.then((ok) => {
								if(ok){
									location.href="<%=request.getContextPath()%>";
								} else{
									location.href="<%=request.getContextPath()%>";
								}
							});
						} else{
							swal("후원 신청 실패","다시 신청하시기 바랍니다.","error")
							.then((ok) => {
								if(ok){
									location.href="<%=request.getContextPath()%>/supportApplyForm.su";
								} else{
									location.href="<%=request.getContextPath()%>/supportApplyForm.su";
								}
							});
						}
					},
					error: function(result){
						alert("ajax에러 발생");
					}
				});
			}
		</script>
	</section>
</body>
</html>