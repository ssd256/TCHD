<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/member/join.css" type="text/css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
var checkForm="PM";
$(function(){
	$(".checkMember").click(function(){  //회원유형 라디오버튼 선택시, 개인회원 선택하면 개인회원 전용 폼 , 단체회원 선택하면 단체회원 전용 폼
		checkForm=$(this).val();
		console.log(checkForm);
		$("#joinForm_private")[0].reset();
		$("#joinForm_group")[0].reset();
		if(checkForm=="GM"){  //단체회원 선택
			$("#joinForm_private").css("display","none");
			$("#joinForm_group").css("display","block"); 
		 	$('#joinForm_group').find("#selectGroup").prop("checked",true);
			$('#joinForm_private').find("#selectGroup").prop("checked",true); 
			$('#joinForm_private').find('#checkId_hidden').val("false");
			
		}else{  // 개인회원 선택
		 	$("#joinForm_group").css("display","none");
			$("#joinForm_private").css("display","block"); 
	 		$('#joinForm_group').find("#selectPrivate").prop("checked",true);
			$('#joinForm_private').find("#selectPrivate").prop("checked",true); 
			$('#joinForm_private').find('#checkId_hidden').val("false");
		}
	});
	//---------------------------------------------------------------------------------------
	$(".inputId").on("keyup",function(){  // 아이디는 영어소문자와 숫자만 입력가능
		if(checkForm=="PM"){
			var inputId = $("#joinForm_private").find('.inputId').val();
			inputId = inputId.replace(/[^a-z0-9~!@#$%^&*()_+|<>?:{}]/g,'');
			$("#joinForm_private").find('.inputId').val(inputId);
		}else{
			var inputId = $("#joinForm_group").find('.inputId').val();
			inputId = inputId.replace(/[^a-z0-9]/g,'');
			$("#joinForm_group").find('.inputId').val(inputId);
		}
	});
	//-----------------------------------------------------------------------------------	
	$(".inputPwd").on("keyup",function(){  // 비밀번호는 영어소문자와 숫자만 입력가능
		if(checkForm=="PM"){
			var inputPwd = $("#joinForm_private").find('.inputPwd').val();
			inputPwd = inputPwd.replace(/[^a-z0-9~!@#$%^&*()_+|<>?:{}]/g,'');
			$("#joinForm_private").find('.inputPwd').val(inputPwd);
		}else{
			var inputPwd = $("#joinForm_group").find('.inputPwd').val();
			inputPwd = inputPwd.replace(/[^a-z0-9~!@#$%^&*()_+|<>?:{}]/g,'');
			$("#joinForm_group").find('.inputPwd').val(inputPwd);
		}
	});
	
	//------------------------------------------------------------------------------------
	$(".inputPwd2").on("keyup",function(){  // 비밀번호 확인은 영어소문자와 숫자만 입력가능
		if(checkForm=="PM"){
			var inputPwd2 = $("#joinForm_private").find('.inputPwd2').val();
			inputPwd2 = inputPwd2.replace(/[^a-z0-9~!@#$%^&*()_+|<>?:{}]/g,'');
			$("#joinForm_private").find('.inputPwd2').val(inputPwd2);
		}else{
			var inputPwd2 = $("#joinForm_group").find('.inputPwd2').val();
			inputPwd2 = inputPwd2.replace(/[^a-z0-9~!@#$%^&*()_+|<>?:{}]/g,'');
			$("#joinForm_group").find('.inputPwd2').val(inputPwd2);
		}
	});
	
	//------------------------------------------------------------------------------------
	$('.inputTel').on("keyup",function(){  // 전화번호 필터링 함수
		if(checkForm=="PM"){
			var inputTel = $("#joinForm_private").find('.inputTel').val();
			console.log(inputTel);
			var tmp="";
			inputTel = inputTel.replace(/[^0-9]/g,'');
	
			if(inputTel.length<4){ 
				tmp+=inputTel;
			}else if(inputTel.length<7){ 
				tmp+=inputTel.substr(0,3);
				tmp+='-'; 
				tmp+=inputTel.substr(3);
			}else if(inputTel.length<11){
				tmp += inputTel.substr(0, 3);  
				tmp += '-';
				tmp += inputTel.substr(3, 3); 
				tmp += '-';
				tmp += inputTel.substr(6);
			}else{
				tmp += inputTel.substr(0, 3);  
				tmp += '-';
				tmp += inputTel.substr(3, 4);
				tmp += '-';
				tmp += inputTel.substr(7); 
			}
			$("#joinForm_private").find('.inputTel').val(tmp);
		}else{
			var inputTel = $("#joinForm_group").find('.inputTel').val();
			console.log(inputTel);
			var tmp="";
			inputTel = inputTel.replace(/[^0-9]/g,'');
			
			if(inputTel.length<4){ 
				tmp+=inputTel;
			}else if(inputTel.length<7){
				tmp+=inputTel.substr(0,3);
				tmp+='-'; 
				tmp+=inputTel.substr(3);
			}else if(inputTel.length<11){    
				tmp += inputTel.substr(0, 3);  
				tmp += '-';
				tmp += inputTel.substr(3, 3); 
				tmp += '-';
				tmp += inputTel.substr(6); 
			}else{
				tmp += inputTel.substr(0, 3); 
				tmp += '-';
				tmp += inputTel.substr(3, 4);
				tmp += '-';
				tmp += inputTel.substr(7); 
			}
			$("#joinForm_group").find('.inputTel').val(tmp);
		}
	});
	//--------------------------------------------------------------------
	$('.searchAddress').click(function(){  // 주소검색 api
		new daum.Postcode({
	        oncomplete: function(data) {
	            if(checkForm=="PM"){
	            	$("#joinForm_private").find('#zoneCode').val(data.zonecode);
	            	if(data.userSelectedType=='R'){
	            		$("#joinForm_private").find('#mainAddress').val(data.roadAddress);
	            	}else{
	            		$("#joinForm_private").find('#mainAddress').val(data.jibunAddress);
	            	}
	            }else{
	            	$("#joinForm_group").find('#zoneCode').val(data.zonecode);
	            	if(data.userSelectedType=="R"){
	            		$("#joinForm_group").find('#mainAddress').val(data.roadAddress);
	            	}else{
	            		$("#joinForm_group").find('#mainAddress').val(data.jibunAddress);
	            	}
	            }      	
	        }
	    }).open();
	});
	//-------------------------------------------------------------------------------
	$('.joinAddress2').click(function(){
		if(checkForm=="PM"){
			if($('#joinForm_private').find('#zoneCode').val()!=""){
				$(this).removeAttr("readonly");
			}
		}else{
			if($('#joinForm_group').find('#zoneCode').val()!=""){
				$(this).removeAttr("readonly");
			}
		}
	})
	
	//--------------------------------------------------------------------------
	$('.joinEmail').on("keyup",function(){  // 이메일입력란에 영어소문자와 숫자만 입력가능
		if(checkForm=="PM"){
			var inputEmail = $("#joinForm_private").find(".joinEmail").val();
			inputEmail = inputEmail.replace(/[^a-z0-9]/g,'');
			$('#joinForm_private').find('.joinEmail').val(inputEmail);
		}else{
			var inputEmail = $("#joinForm_group").find(".joinEmail").val();
			inputEmail = inputEmail.replace(/[^a-z0-9]/g,'');
			$('#joinForm_group').find('.joinEmail').val(inputEmail);
		}
	})
	//----------------------------------------------------------------------------
	$('#joinRegNo').on('keyup',function(){  // 사업자 등록번호 필터링 함수
		var joinRegNo = $("#joinRegNo").val();
		console.log(joinRegNo);
		var tmp="";
		joinRegNo = joinRegNo.replace(/[^0-9]/g,'');
		
		if(joinRegNo.length<4){ 
			tmp+=joinRegNo;
		}else if(joinRegNo.length<6){
			tmp+=joinRegNo.substr(0,3);
			tmp+='-'; 
			tmp+=joinRegNo.substr(3);
		}else{
			tmp += joinRegNo.substr(0, 3);  
			tmp += '-';
			tmp += joinRegNo.substr(3, 2); 
			tmp += '-';
			tmp += joinRegNo.substr(5); 
		}
		$("#joinRegNo").val(tmp);
	})
	//-----------------------------------------------------------------------
	$('.joinId').on('change paste keyup', function(){ // 아이디 입력란에 변화가 생겼을 경우 아이디 중복확인이 다시 필요함
		if(checkForm=="PM"){
			$('#joinForm_private').find('#checkId_hidden').val("false");
		}else{
			$('#joinForm_group').find('#checkId_hidden').val("false");
		}
	})
	//---------------------------------------------------------------------------
	$('.btnDuplicate').click(function(){  //아이디 중복확인을 눌렀을 경우
		if(checkForm=="PM"){
			var inputId=$('#joinForm_private').find('.inputId').val();
			if(inputId.trim()==""){
				swal("","아이디를 입력해주세요","info")
				//alert("아이디를 입력해주세요.");
				$('#joinForm_private').find('.inputId').focus();
			}else if(inputId.trim().length<6){
				swal("","아이디는 6글자 이상 입력해주세요.","info");
				id.focus();
				return false;
			}else{
				$.ajax({
					url: "checkId.me",
					data: {inputId:inputId},
					success: function(data){
						if(data==1){
							swal("아이디 중복 확인", "중복되는 아이디가 있습니다.", "warning");
							/* alert("중복되는 아이디가 있습니다.") */
							$('#joinForm_private').find('#checkId_hidden').val("false");
						}else{
							swal("아이디 중복 확인", "이 아이디는 사용가능합니다.", "success");
							/* alert("이 아이디는 사용가능합니다.") */
							$('#joinForm_private').find('#checkId_hidden').val("true");
						}	
					},
					error: function(data){
						alert("Ajax 실행 오류");
					}
				});
			}
		}else{
			var inputId=$('#joinForm_group').find('.inputId').val();
			console.log(inputId);
			if(inputId==""){
				swal("","아이디를 입력해주세요","info")
				//alert("아이디를 입력해주세요.");
				$('#joinForm_group').find('.inputId').focus();
			}else{
				$.ajax({
					url: "checkId.me",
					data: {inputId:inputId},
					success: function(data){
						if(data==1){
							swal("아이디 중복 확인", "중복되는 아이디가 있습니다.", "error");
							//alert("중복되는 아이디가 있습니다.")
							$('#joinForm_group').find('#checkId_hidden').val("false");
						}else{
							swal("아이디 중복 확인", "이 아이디는 사용가능합니다.", "success");
							//alert("이 아이디는 사용가능합니다.")
							$('#joinForm_group').find('#checkId_hidden').val("true");
						}	
					},
					error: function(data){
						alert("Ajax 실행 오류");
					}
				});
			}
		}
	})
})
//----------------------------------------------------------------------------------------


function checkSubmit(){
	if($("#checkInfo").is(":checked")==false){
		swal("","개인정보 수집 및 이용에 동의해주세요.","info");
		$("#checkInfo").focus();
		return false;
	}
	///////////////// 비밀번호 일치한지 체크, 이메일 뒷주소 선택여부 확인,ㅊ
	if(checkForm=="PM"){
	    var id= $('#joinForm_private').find('.inputId');
		var pwd=$("#joinForm_private").find('#joinPwd');
		var pwd2=$("#joinForm_private").find('#joinPwd2');
		var name= $('#joinForm_private').find('.inputName');	
		var joinEmail2 = $("#joinForm_private").find(".joinEmail2").val();
		
		if(id.val().trim().length<6){
			swal("","아이디는 6글자 이상 입력해주세요.","info");
			id.focus();
			return false;
		}
		if(pwd.val().trim().length<8){
			swal("","비밀번호는 8글자 이상 입력해주세요.","info");
			pwd.focus();
			return false;
		}
		if($("#joinForm_private").find("#checkId_hidden").val()=="false"){
			swal("","아이디 중복확인을 해주세요.","info");
			return false;
		}
		
		if(pwd.val().trim()!=pwd2.val().trim()){
			swal("","비밀번호가 일치하지 않습니다.","error");
			pwd2.focus();
			return false;
		}
		if(joinEmail2=="default"){
			swal("","사이트를 선택해주세요.","info");
			return false;
		}
		if(name.trim()==""){
			swal("","이름을 입력해주세요.","info");
			name.focus();
			return false;
		}
		
	}else{
		var id= $('#joinForm_group').find('.inputId');
		var pwd=$("#joinForm_group").find('#joinPwd').val();
		var pwd2=$("#joinForm_group").find('#joinPwd2').val();
		var name= $('#joinForm_group').find('.inputName');
		var gmname= $('#joinForm_group').find('.inputGmName');
		var joinEmail2 = $("#joinForm_group").find(".joinEmail2").val();
	
		if(id.val().trim().length<6){
			swal("","아이디는 6글자 이상 입력해주세요.","info");
			id.focus();
			return false;
		}
		if(pwd.val().trim().length<8){
			swal("","비밀번호는 8글자 이상 입력해주세요.","info");
			pwd.focus();
			return false;
		}
		if($("#joinForm_group").find("#checkId_hidden").val()=="false"){
			swal("","아이디 중복확인을 해주세요.","info");
			return false;
		}
		if(pwd.val().trim()!=pwd2.val().trim()){
			swal("","비밀번호가 일치하지 않습니다.","error");
			pwd2.focus();
			return false;
		}
		if(joinEmail2=="default"){
			swal("","사이트를 선택해주세요.","info");
			return false;
		}
		if(name.trim()==""){
			alert("단체명을 입력해주세요.");
			name.focus();
		}
		if(gmname.trim==""){
			swal("","단체명을 입력해주세요.","info");
			gmname.focus();
			return false;
		}
	}
	
	/////////////////////////////////////////////
	return true;
}

</script>
</head>
<body>
<section>
	<article>
		<div id="joinDiv">
			<div id="joinTitle">회원가입</div>
			<textarea rows="30" cols="135" readonly >
정보통신망법 규정에 따라 함께하묘 행복하개에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.

1. 수집하는 개인정보
이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 네이버 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 네이버는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.

회원가입 시점에 함께하묘 행복하개가 이용자로부터 수집하는 개인정보는 아래와 같습니다.
- 회원 가입 시에 ‘아이디, 비밀번호, 이름, 생년월일, 성별, 휴대전화번호’를 필수항목으로 수집합니다. 만약 이용자가 입력하는 생년월일이 만14세 미만 아동일 경우에는 법정대리인 정보(법정대리인의 이름, 생년월일, 성별, 중복가입확인정보(DI), 휴대전화번호)를 추가로 수집합니다. 그리고 선택항목으로 이메일 주소, 프로필 정보를 수집합니다.
- 단체아이디로 회원가입 시 단체아이디, 비밀번호, 단체이름, 이메일주소, 휴대전화번호를 필수항목으로 수집합니다. 그리고 단체 대표자명을 선택항목으로 수집합니다.
서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.
함께하묘 행복하개 내의 개별 서비스 이용, 이벤트 응모 및 경품 신청 과정에서 해당 서비스의 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.

서비스 이용 과정에서 IP 주소, 쿠키, 서비스 이용 기록, 기기정보, 위치정보가 생성되어 수집될 수 있습니다. 또한 이미지 및 음성을 이용한 검색 서비스 등에서 이미지나 음성이 수집될 수 있습니다.
구체적으로 1) 서비스 이용 과정에서 이용자에 관한 정보를 자동화된 방법으로 생성하여 이를 저장(수집)하거나,
2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지 못 하도록 안전하게 변환하여 수집합니다. 서비스 이용 과정에서 위치정보가 수집될 수 있으며,
네이버에서 제공하는 위치기반 서비스에 대해서는 '함께하묘 행복하개 위치정보 이용약관'에서 자세하게 규정하고 있습니다.
이와 같이 수집된 정보는 개인정보와의 연계 여부 등에 따라 개인정보에 해당할 수 있고, 개인정보에 해당하지 않을 수도 있습니다.

2. 수집한 개인정보의 이용
네이버 및 네이버 관련 제반 서비스(모바일 웹/앱 포함)의 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다.

- 회원 가입 의사의 확인, 연령 확인 및 법정대리인 동의 진행, 이용자 및 법정대리인의 본인 확인, 이용자 식별, 회원탈퇴 의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다.
- 콘텐츠 등 기존 서비스 제공(광고 포함)에 더하여, 인구통계학적 분석, 서비스 방문 및 이용기록의 분석, 개인정보 및 관심에 기반한 이용자간 관계의 형성, 지인 및 관심사 등에 기반한 맞춤형 서비스 제공 등 신규 서비스 요소의 발굴 및 기존 서비스 개선 등을 위하여 개인정보를 이용합니다.
- 법령 및 네이버 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 계정도용 및 부정거래 방지, 약관 개정 등의 고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호 및 서비스 운영을 위하여 개인정보를 이용합니다.
- 유료 서비스 제공에 따르는 본인인증, 구매 및 요금 결제, 상품 및 서비스의 배송을 위하여 개인정보를 이용합니다.
- 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 등 마케팅 및 프로모션 목적으로 개인정보를 이용합니다.
- 서비스 이용기록과 접속 빈도 분석, 서비스 이용에 대한 통계, 서비스 분석 및 통계에 따른 맞춤 서비스 제공 및 광고 게재 등에 개인정보를 이용합니다.
- 보안, 프라이버시, 안전 측면에서 이용자가 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.
3. 개인정보의 파기
회사는 원칙적으로 이용자의 개인정보를 회원 탈퇴 시 지체없이 파기하고 있습니다.
단, 이용자에게 개인정보 보관기간에 대해 별도의 동의를 얻은 경우, 또는 법령에서 일정 기간 정보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 안전하게 보관합니다.

이용자에게 개인정보 보관기간에 대해 회원가입 시 또는 서비스 가입 시 동의를 얻은 경우는 아래와 같습니다.
- 부정 가입 및 이용 방지
가입인증 휴대전화번호 또는DI (만14세 미만의 경우 법정대리인DI) : 수집시점으로부터6개월 보관
탈퇴한 이용자의 휴대전화번호(복호화가 불가능한 일방항 암호화(해시처리)) : 탈퇴일로부터6개월 보관
- QR코드 복구 요청 대응
QR코드 등록 정보:삭제 시점으로부터6개월 보관
- 스마트플레이스 분쟁 조정 및 고객문의 대응
휴대전화번호:등록/수정/삭제 요청 시로부터 최대1년
- 네이버 플러스 멤버십 서비스 혜택 중복 제공 방지
암호화처리(해시처리)한DI :혜택 제공 종료일로부터6개월 보관
전자상거래 등에서의 소비자 보호에 관한 법률, 전자금융거래법, 통신비밀보호법 등 법령에서 일정기간 정보의 보관을 규정하는 경우는 아래와 같습니다. 네이버는 이 기간 동안 법령의 규정에 따라 개인정보를 보관하며, 본 정보를 다른 목적으로는 절대 이용하지 않습니다.
- 전자상거래 등에서 소비자 보호에 관한 법률
계약 또는 청약철회 등에 관한 기록: 5년 보관
대금결제 및 재화 등의 공급에 관한 기록: 5년 보관
소비자의 불만 또는 분쟁처리에 관한 기록: 3년 보관
- 전자금융거래법
전자금융에 관한 기록: 5년 보관
- 통신비밀보호법
로그인 기록: 3개월
회원탈퇴, 서비스 종료, 이용자에게 동의받은 개인정보 보유기간의 도래와 같이 개인정보의 수집 및 이용목적이 달성된 개인정보는 재생이 불가능한 방법으로 파기하고 있습니다. 법령에서 보존의무를 부과한 정보에 대해서도 해당 기간 경과 후 지체없이 재생이 불가능한 방법으로 파기합니다.

전자적 파일 형태의 경우 복구 및 재생이 되지 않도록 기술적인 방법을 이용하여 안전하게 삭제하며, 출력물 등은 분쇄하거나 소각하는 방식 등으로 파기합니다.

참고로 네이버는 ‘개인정보 유효기간제’에 따라 1년간 서비스를 이용하지 않은 회원의 개인정보를 별도로 분리 보관하여 관리하고 있습니다.	
			</textarea>
			<div id="joinCheck"><input type="checkbox" id="checkInfo"><span>개인정보 수집 및 이용 동의 (필수)</span></div>
		</div>
		<form id="joinForm_private" class="joinForm" method="post" method="post" action="join.me" onsubmit="return checkSubmit();">
			<table>
				<tr id="top_tr">
					<td colspan="3" id="join_notice"><span>*</span>필수입력항목</td>
				</tr>
				
				<tr id="select_tr">
					<td class="form_title"><span id="redDot">*</span>회원유형</td>
					<td><input type="radio" name="selectMemberType" id="selectPrivate" class="checkMember"  value="PM" checked="checked">개인</td>
					<td><input type="radio" name="selectMemberType" id="selectGroup" class="checkMember"  value="GM">사업자(단체)</td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>아이디<br>
					<span>(영문, 숫자만 가능.  6~10자 글자 이내)</span></td>
					<td><input type="text" class="inputId" name="joinId" required maxlength="10">
						<input type="hidden" id="checkId_hidden" value="false">
					</td>
					<td><button type="button" class="btnDuplicate">중복 확인</button><br>
					</td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>비밀번호<br>
					<span>(영문, 숫자, 특수문자만 가능.  8~15자 글자 이내)</span></td>
					<td><input type="password" name="joinPwd" id="joinPwd" class='inputPwd' required maxlength="15"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>비밀번호 확인</td>
					<td><input type="password" name="joinPwd2" id="joinPwd2" class='inputPwd2' required maxlength="15"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>이름</td>
					<td><input type="text" name="joinName" class='inputName' required maxlength="13"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title">생년월일</td>
					<td><input type="date"  value="1990-01-01" name="joinBirth"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>전화번호</td>
					<td><input type="tel" class="inputTel" name="joinTel" required maxlength="13"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title" colspan="1">주소</td>
					<td><input type="text" placeholder="우편번호" id="zoneCode" name="joinZoneCode" readonly></td>
					<td><button type="button"  class="searchAddress" >주소 검색</button></td>
				</tr>
				<tr id="address_tr">
					<td></td>
					<td id="address_td"><input type="text" placeholder="주소" id="mainAddress" name="joinAddress" readonly></td>
					<td id="address_td"><input type="text" placeholder="상세주소" id="detailAddress" class="joinAddress2"  name="joinAddress2" readonly></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>이메일</td>
					<td><input type="text" placeholder="이메일주소" name="joinEmail" class="joinEmail" required></td>
					<td>@ <select name="joinEmail2" class="joinEmail2">
						<option value="default" >사이트선택</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="gmail.com">gmail.com</option>
					</select></td>
				</tr>
				<tr id="submit_tr">
					<td colspan="3"><button type="submit">가입</button></td>
				</tr>
			</table>
		</form>
		<!-----------------------------------------------------------------------------------------------  -->
		<form id="joinForm_group" class="joinForm" method="post" action="join.me" onsubmit="return checkSubmit();">
	      	<table>
				<tr id="top_tr">
					<td colspan="3" id="join_notice"><span>*</span>필수입력항목</td>
				</tr>

				<tr id="select_tr">
					<td class="form_title"><span id="redDot">*</span>회원유형</td>
					<td><input type="radio" name="selectMemberType" id="selectPrivate" class="checkMember"  value="PM">개인</td>
					<td><input type="radio" name="selectMemberType" id="selectGroup" class="checkMember"  value="GM" >사업자(단체)</td>
				</tr>
				<tr>
					<td class="form_title">
						<span id="redDot">*</span>아이디<br>
						<span>(영문, 숫자만 가능.  6~10자 글자 이내)</span>
					</td>
					<td><input type="text" class="inputId" name="joinId" required maxlength="10">
						<input type="hidden" id="checkId_hidden" value="false">
					</td>
					<td><button type="button" class="btnDuplicate">중복 확인</button></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>비밀번호<br>
					<span>(영문, 숫자, 특수문자만 가능.  8~15자 글자 이내)</span></td>
					<td><input type="password" name="joinPwd" id="joinPwd" class="inputPwd" required maxlength="15"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>비밀번호 확인</td>
					<td><input type="password" name="joinPwd2" id="joinPwd2" class="inputPwd2" required maxlength="15"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>단체명</td>
					<td><input type="text" name="joinName"  class='inputName' required></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>대표자명</td>
					<td><input type="text" name="joinGmName" class='inputGmName'  required></td>
					<td></td>
				</tr>
				<tr>
				<td class="form_title"><span id="redDot">*</span>사업자 등록번호</td>
					<td><input type="text" name="joinRegNo" id="joinRegNo" maxlength="12" required></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>전화번호</td>
					<td><input type="tel" class="inputTel" name="joinTel" required maxlength="13"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title" colspan="1">주소</td>
					<td><input type="text" placeholder="우편번호" id="zoneCode" name="joinZoneCode"  readonly></td>
					<td><button type="button"  class="searchAddress">주소 검색</button></td>
				</tr>
				<tr id="address_tr">
					<td></td>
					<td id="address_td"><input type="text" readonly placeholder="주소" id="mainAddress" name="joinAddress" readonly></td>
					<td id="address_td"><input type="text" placeholder="상세주소" id="detailAddress" class="joinAddress2" name="joinAddress2" readonly></td>
				</tr>
				<tr>
					<td class="form_title"><span id="redDot">*</span>이메일</td>
					<td><input type="text" placeholder="이메일주소" name="joinEmail" class="joinEmail" maxlength="15" required></td>
					<td>@ <select name="joinEmail2" class="joinEmail2">
						<option value="default">사이트선택</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="gmail.com">gmail.com</option>
					</select></td>
				</tr>
				<tr id="submit_tr">
					<td colspan="3"><button type="submit">가입</button></td>
				</tr>
			</table>
		</form>
	</article>
</section>

</body>
</html>