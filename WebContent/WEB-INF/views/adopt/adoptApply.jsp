<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.model.vo.*, member.model.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/adopt/adopt_apply.css?after" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/common/common.css" type="text/css">
</head>
<body>
<section>	
   	<form id="form" action="<%=request.getContextPath() %>/adoptApply.bo?bNo=${ bNo }" method="post">
	<div id="ment">입양신청 <input type="hidden" id="bNo" name="bNo" value="${ bNo }"></div>
		<div id="info">
			<img src="./images/applyImg.PNG" id="petImg"><p>평생 책임지고 함께하실 준비가 되셨나요?</p>
			<p>상처받은 아이들에게 새로운 가족이 되어주세요</p>
			<hr>
		</div>
		<div id="ment2">입양신청 작성</div>
		<div id="infoBox">
			<ul id="firstInfo">
				<li id="infoTitle">* 반드시 숙지해야 할 상황</li>
				<li class="info">
					입양할 동물들의 건강과 특성과 행동은 환경과 상황에 따라 달라집니다.<br>
					따라서 입양은 주의 사항들을 동반합니다.<br> 
					부디 아래에 적힌 주의사항들을 고려하시기 바랍니다.<br> 
				</li>
			</ul>
			<div id="secondBox">
				<ul id="secondInfo" class="info">
					<li>입양된 반려동물이 입양자의 소유물을 망가뜨릴 수도 있습니다.</li>
					<li>입양된 반려동물로부터 사람들이나 다른 반려동물이 질병이 노출 될 수도 있습니다.</li>
					<li>입양된 반려동물로부터 사람들이나 다른 반려동물이 상해를 입을 수 있습니다.</li>
					<li>입양한 동물이 너무 일찍 죽을 수도 있습니다.</li>
				</ul>
			</div>
		</div>
		<div id="apply">
			<ol type="I" class="applyList">
				<li>일반 정보</li>
			</ol>
			<table id="infoTable" class="readInfo">
				<tr>
					<td class="privteInfo">이름 : </td>
					<td class="private"><input type="text" value="${ member.mem_name }" readonly/></td>
				</tr>
				<tr>
					<td class="privteInfo">전화번호 : </td>
					<td class="private"><input type="tel" value="${ member.mem_phone }" readonly/></td>
				</tr>
				<tr>
					<td class="privteInfo">주소 : </td>
					<td class="private"><input type="text" value="${ member.mem_addr }" readonly/></td>
				</tr>
				<tr>
					<td class="privteInfo">이메일 : </td>
					<td class="private"><input type="email" value="${ member.mem_email }" readonly/></td>
				</tr>
			</table>
			<ol type="I" start=2 class="applyList">
				<li>입양동기</li>
			</ol>
			<ol id="asking" class="write">
				<li>
					입양을 결심하게 된 계기를 말해 주세요.<br>				<!-- required : 텍스트 입력이 필수임을 나타냄 -->
					<textarea id="answer1" class="answer" name="answer1"></textarea>
					<span id="counter1">0</span>/300
				</li>
				<li>
					개를 혼자서 입양하십니까? 함께 입양하는 가족/동거인이 있다면 정보를 알려주세요.<br>
					<textarea id="answer2" class="answer" name="answer2"></textarea>
					<span id="counter2">0</span>/300
				</li>
				<li>
					반려동물을 키우신 적 있습니까? 성인이 된 후 개를 기른 적이 있나요?<br>
					만약 있다면 그 동물에 대해 알려 주세요.<br>
					<textarea id="answer3" class="answer" name="answer3" placeholder="없으시면 없다고 적어주세요"></textarea>
					<span id="counter3">0</span>/300
				</li>
			</ol>
			<ol type="I" start=3 class="applyList">
				<li>앞으로의 다짐</li>
			</ol>
			<ol id="promise" class="write">
				<li>
					반려동물과 앞으로 15년간 평생을 함께 할 준비가 되셨습니까?<br>
					<textarea id="answer4" class="answer" name="answer4"></textarea>
					<span id="counter4">0</span>/300
				</li>
				<li>
					반려동물과 함께 이사를 갈 수 있기 위해 어떤 계획을 가지고 계십니까?<br>
					<textarea id="answer5" class="answer" name="answer5" placeholder="이사계획이 없다면 없다고 적어주세요"></textarea>
					<span id="counter5">0</span>/300
				</li>
				<li>
					휴가를 간다면 그 사이 반려동물을 위해 어떤 준비를 해 줄 생각이십니까?<br>
					<textarea id="answer6" class="answer" name="answer6"></textarea>
					<span id="counter6">0</span>/300
				</li>
				<li>
					어떤 상황이 발생한다면 다른 곳으로 재입양을 보낼 생각을 하십니까?<br>
					<textarea id="answer7" class="answer" name="answer7"></textarea>
					<span id="counter7">0</span>/300
				</li>
			</ol>
			<ol type="I" start=4 class="applyList">
				<li>입양신청 동의</li>
			</ol>
			<textarea id="privateAgree" readonly>
1. 입양 과정
입양신청자는 성인이어야 하며 모든 가족구성원들이 입양에 찬성, 동의를 해야합니다. 또한 자가 주택이 아닌 경우 집주인의 동의 또한 얻어야 합니다. 행복하묘 함께하개(이하 본 단체)에서 이러한 동의를 서명한 계약서를 요청 할 수도 있습니다. 또한 본 단체에서는 동물을 입양하는데 적합하지 않다고 판단되는 신청자들을 거절할 권리가 있습니다. 
신청서를 잘 작성하신 후에 보호소로 오셔서 한두 번 정도 동물들을 만나보기를 요청 드립니다. 이후 입양 전 한달 간의 테스트 기간을 시작합니다. 한달의 입양 전 테스트기간동안에는 반려동물은 본 단체의 소속이며 담당직원이 정기적으로 연락을 할 것 입니다. 
테스트 기간이 끝낸 후에 그 동물을 입양하기를 원하시면 계약서에 서명하시고 입양 비용을 지불하셔야 됩니다. 모든 단계가 완료되면 반려동물의 소속이 입양자에게로 이전됩니다.
입양자는 입양 계약서의 복사본을 받을 것입니다. 혹 입양 동물이 병력이 있다면 그에 관련 된 병력복사본도 받을 수 있습니다. 입양 후 7일 안에 동물병원에 입양한 동물을 데려가셔서 검사 받으시길 권합니다.
				
2. 입양되기 위한 동물의 자격
건강한 상태에 있거나 질병이 있어도 회복이 가능하다고 판단되는 동물들이 입양 대상이 됩니다. 
모든 동물들은 입양 전 중성화 수술을 받고 필요한 예방접종을 마치는 것이 원칙입니다. 간혹 중성화 수술이 불가능할 정도로 어린 동물의 경우, 건강을 회복하고 체중이 2kg이상이 될 때 중성화를 하겠다라는 계약서에 서명을 하셔야 입양이 이루어질 수 있습니다. 이 경우에는 중성화 수술이 완료된 후에 동물 소속이 입양자에게 이전됩니다.
심각한 건강문제나 고쳐지기 어려운 행동 문제가 있는 동물은 입양 자격에 부합되지 않습니다.
				
3. 입양 후
입양하신 분들은 가끔 본 단체에 연락하셔서 입양된 반려동물이 어떻게 지내고 있는지 알려주시기를 요청 드립니다. 입양된 동물이 새로운 가정에서 잘 적응하고 지내기를 바랍니다. 
만약 입양하신 동물을 훈련하는데 어려움이 있으시거나 그들의 건강이 걱정되신다면 저희에게 이 번호로 (02-123-4567, 평일 09:00 – 17:00)로 연락 주시기 바랍니다. 본 단체의 직원들이 입양된 동물이 잘 적응 할 수 있도록 물심양면으로 도움을 드릴 것입니다.
입양신청서에 명시되어 있듯이 고양이나 개에게 미용을 목적으로 하는 발톱 제거 수술, 꼬리 절제 수술, 귀의 일부를 잘라내는 수술, 성대 제거 수술, 힘줄 절제술 등을 불필요한 수술을 해서는 안 됩니다.
만약 입양자가 본 단체에서 입양한 동물을 파양 하기 바란다면 본 단체의 동의 없이 다른 사람에게 입양 보내시는 안 됩니다. 파양 하는 동물은 다시 본 단체의 쉼터로 데려오셔야 하고 그 비용은 본인이 부담해야 합니다. 
입양비 환불은 불가능합니다.</textarea>
			<br><input type="checkbox" id="agree" class="checkAgree"/> 개인정보 제공에 동의합니다.
			<p>긴 시간 동안 입양신청서에 답해 주셔서 감사합니다.<p>
		</div>
		<div id="buttons">
		<button id="cancel" class="applyButton">취소</button>
			<button id="okay" type="button" class="applyButton">확인</button>
		</div>
	</form>
	<script>
// 글자 수 카운트 ---------------------------------------------------------------------------
		$(document).ready(function(){
			$('#answer1').keyup(function(e){
				$('#counter1').text($(this).val().length);
				if($(this).val().length >= 300){
					$(this).val($(this).val().substring(0, 300));
					$('#counter1').css('color', 'red');
				} else {
					$('#counter1').css('color', 'black');
				}
			});
				
				$('#answer2').keyup(function(e){
				$('#counter2').text($(this).val().length);
				if($(this).val().length >= 300){
					$(this).val($(this).val().substring(0, 300));
					$('#counter2').css('color', 'red');
				} else {
					$('#counter2').css('color', 'black');
				}	
			});
				
				$('#answer3').keyup(function(e){
				$('#counter3').text($(this).val().length);
				if($(this).val().length >= 300){
					$(this).val($(this).val().substring(0, 300));
					$('#counter3').css('color', 'red');
				} else {
					$('#counter3').css('color', 'black');
				}
			});
				
				
				$('#answer4').keyup(function(e){
				$('#counter4').text($(this).val().length);
				if($(this).val().length >= 300){
					$(this).val($(this).val().substring(0, 300));
					$('#counter4').css('color', 'red');
				} else {
					$('#counter4').css('color', 'black');
				}
			});
				
				$('#answer5').keyup(function(e){
				$('#counter5').text($(this).val().length);
				if($(this).val().length >= 300){
					$(this).val($(this).val().substring(0, 300));
					$('#counter5').css('color', 'red');
				} else {
					$('#counter5').css('color', 'black');
				}
			});
				
				$('#answer6').keyup(function(e){
				$('#counter6').text($(this).val().length);
				if($(this).val().length >= 300){
					$(this).val($(this).val().substring(0, 300));
					$('#counter6').css('color', 'red');
				} else {
					$('#counter6').css('color', 'black');
				}
			});
				
				$('#answer7').keyup(function(e){
				$('#counter7').text($(this).val().length);
				if($(this).val().length >= 300){
					$(this).val($(this).val().substring(0, 300));
					$('#counter7').css('color', 'red');
				} else {
					$('#counter7').css('color', 'black');
				}
			});
			
		});
		
// 정규표현식 --------------------------------------------------------------------------		
		$(function(){	
			$('#answer1').on("keyup", function(){
				var a1 = $('#answer1').val();
				a1 = a1.replace(/[^가-힣!.,\s\d]/g, "");
				$('#answer1').val(a1);
			});
			
			$('#answer2').on("keyup", function(){
				var a2 = $('#answer2').val();
				a2 = a2.replace(/[^가-힣!.,\s\d]/g, "");
				$('#answer2').val(a2);
			});
			
			$('#answer3').on("keyup", function(){
				var a3 = $('#answer3').val();
				a3 = a3.replace(/[^가-힣!.,\s\d]/g, "");
				$('#answer3').val(a3);
			});
			
			$('#answer4').on("keyup", function(){
				var a4 = $('#answer4').val();
				a4 = a4.replace(/[^가-힣!.,\s\d]/g, "");
				$('#answer4').val(a4);
			});
			
			$('#answer5').on("keyup", function(){
				var a5 = $('#answer5').val();
				a5 = a5.replace(/[^가-힣!.,\s\d]/g, "");
				$('#answer5').val(a5);
			});
			
			$('#answer6').on("keyup", function(){
				var a6 = $('#answer6').val();
				a6 = a6.replace(/[^가-힣!.,\s\d]/g, "");
				$('#answer6').val(a6);
			});
			
			$('#answer7').on("keyup", function(){
				var a7 = $('#answer7').val();
				a7 = a7.replace(/[^가-힣!.,\s\d]/g, "");
				$('#answer7').val(a7);
			});
		});

		$('#okay').on("click", function(){
			var chk = $('#agree');
			var a1 = $('#answer1');
			var a2 = $('#answer2');
			var a3 = $('#answer3');
			var a4 = $('#answer4');
			var a5 = $('#answer5');
			var a6 = $('#answer6');
			var a7 = $('#answer7');
			
			if(chk.is(":checked") == false){
				swal("", "개인정보 제공에 동의해주세요", "info");
				chk.focus();
				return false;
			}
			
			if(a1.val().trim().length < 1){
				swal("", "입양 계기를 적어주세요", "info");
				a1.focus();
				return false;
			}
			
			if(a2.val().trim().length < 1){
				swal("", "입양 가족을 적어주세요", "info");
				a2.focus();
				return false;
			}
			
			if(a3.val().trim().length < 1){
				swal("", "반려동물 경험을 적어주세요", "info");
				a3.focus();
				return false;
			}
			
			if(a4.val().trim().length < 1){
				swal("", "앞으로의 다짐을 적어주세요", "info");
				a4.focus();
				return false;
			}
			
			if(a5.val().trim().length < 1){
				swal("", "이사계획이 있다면 적어주세요", "info");
				a5.focus();
				return false;
			}
			
			if(a6.val().trim().length < 1){
				swal("", "휴가대비 계획을 적어주세요", "info");
				a6.focus();
				return false;
			}
			
			if(a7.val().trim().length < 1){
				swal("", "재입양에 대한 의견을 적어주세요", "info");
				a7.focus();
				return false;
			}
			
			swal({
				title : "입양신청 완료",
				text : "상세화면으로 돌아갑니다",
				icon : "success",
			}).then((ok) => {
				if(ok){
					$('#form').submit();
				}
			});
			return true;
		});
		
		
		
		$('#cancel').on("click", function(){
			var bNo = "<c:out value='${ bNo }'/>";
			location.href='<%= request.getContextPath() %>/adoptDetail.bo?boNo=bNo';
			return false;
		});
			
	</script>
</section>
</body>
</html>