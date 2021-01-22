<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");
	String updateCheck = (String)request.getAttribute("updateCheck");
	if(updateCheck==null){
		updateCheck="false";
	}
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/member/join.css" type="text/css">
<link rel="stylesheet" href="css/common/nav.css" type="text/css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
		<div id="updateDiv">
			<div id="updateTitle">회원정보수정</div>
		
		</div>
		<form id="updateForm" method="post" action="update.me" onsubmit="return checkSubmit();">
			<table>
				<tr id="top_tr">
					<td class="form_title">이름</td>
					<td><input type="text" name="userName" class="inputName" value="<%= member.getMem_name()%>" required>
						<input type="hidden" name="userNo" value="<%=member.getMem_no() %>">
					</td>
					
					<td></td>
				</tr>
				<tr>
					<td class="form_title">새 비밀번호</td>
					<td><input type="password" name="userPwd" class="inputPwd" maxlength="15">
						<input type="hidden" name="originalPwd" value="<%=member.getMem_pw() %>">		
					</td>
					<td><span id="info_span">비밀번호 변경 시 입력해주세요.</span></td>
				</tr>
				<tr>
					<td class="form_title">비밀번호확인</td>
					<td><input type="password" name="userPwd2" class="inputPwd2" maxlength="15"></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title">생년월일</td>
					<td><input type="date" name="userBirth" value="<%= member.getPm_birth() %>" ></td>
					<td></td>
				</tr>
				<tr>
					<td class="form_title">전화번호</td>
					<td><input type="text" name="userPhone" class="inputPhone" value="<%= member.getMem_phone() %>" maxlength="13" required></td>
					<td></td>
				</tr>
				<tr>
				<%
					String[] addressArr = null;
					if(member.getMem_addr()!=null){
						addressArr = member.getMem_addr().split(",");	
						
					}else{
						addressArr = new String[]{"","",""};
					}
				
				%>
					<td class="form_title" rowspan="2">주소</td>
					<td><input type="text" placeholder="우편번호" name="zoneCode" id="zoneCode" value="<%= addressArr[0]%>" readonly></td>
					<td><button type="button" class="searchAddress">주소 검색</button></td>
				</tr>
				<tr id="address_tr">
					<td id="address_td"><input type="text" name="mainAddress" id="mainAddress" value="<%=addressArr[1]%>" placeholder="주소"></td>
					<%if(addressArr.length>2){ %>
					<td id="address_td"><input type="text" name="detailAddress" id="detailAddress" value="<%=addressArr[2]%>" placeholder="상세주소"></td>
					<%}else{ %>
				   <td id="address_td"><input type="text" name="detailAddress" id="detailAddress" value="" placeholder="상세주소"></td>
					<%} %>
				</tr>
				<tr>
				<%
					String[] emailArr = member.getMem_email().split("@");
				%>
					<td class="form_title">이메일</td>
					<td><input type="text" placeholder="이메일주소" name="email" class="inputEmail" value="<%= emailArr[0] %>" required></td>
					<td>@ <select id="email2" name="email2">
						<option value="default" >사이트선택</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="gmail.com">gmail.com</option>
					</select></td>
				</tr>
				<tr id="submit_tr">
					<td colspan="3"><button type="submit">수정</button></td>
				</tr>
			</table>
		</form>
<script type="text/javascript">
	$(function(){
		var emailSelect_Ori = '<%=emailArr[1]%>';
		$('#email2').val(emailSelect_Ori);
		
		var updateCheck = '<%=updateCheck %>';
		if(updateCheck!="false"){
			swal("","회원정보가 수정되었습니다.","success");
		}

		///////////////////////////////////////////////////////////////////
		$(".inputPwd").on("keyup",function(){  // 비밀번호는 영어소문자와 숫자만 입력가능
		
			var inputPwd = $('.inputPwd').val();
			inputPwd = inputPwd.replace(/[^a-z0-9~!@#$%^&*()_+|<>?:{}]/g,'');
			$('.inputPwd').val(inputPwd);
			
		});
		////////////////////////////////////////////////////////////////////
		$(".inputPwd2").on("keyup",function(){  // 비밀번호 확인은 영어소문자와 숫자만 입력가능
			
			var inputPwd2 = $('.inputPwd2').val();
			inputPwd2 = inputPwd2.replace(/[^a-z0-9~!@#$%^&*()_+|<>?:{}]/g,'');
			$('.inputPwd2').val(inputPwd2);
			
		});
		////////////////////////////////////////////////////////////////////////
		$('.searchAddress').click(function(){  // 주소검색 api
			new daum.Postcode({
		        oncomplete: function(data) {
	            	$('#zoneCode').val(data.zonecode);
	            	if(data.userSelectedType=='R'){
	            		$('#mainAddress').val(data.roadAddress);
	            	}else{
	            		$('#mainAddress').val(data.jibunAddress);
	            	}
     	
		        }
		    }).open();
		});
		////////////////////////////////////////////////////////////////////////
		$('.inputPhone').on("keyup",function(){  // 전화번호 필터링 함수
		
				var inputTel = $('.inputPhone').val();
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
				$(".inputPhone").val(tmp);
			
		});
		///////////////////////////////////////////////////////////
		$('.inputEmail').on("keyup",function(){  // 이메일입력란에 영어소문자와 숫자만 입력가능
			
			var inputEmail = $(".inputEmail").val();
			inputEmail = inputEmail.replace(/[^a-z0-9]/g,'');
			$('.joinEmail').val(inputEmail);
			
		})
		


	})
			
	function checkSubmit(){
		
	
		///////////////// 비밀번호 일치한지 체크, 이메일 뒷주소 선택여부 확인
		
			var pwd=$('.inputPwd');
			var pwd2=$('.inputPwd2');
			var name= $('.inputName');	
			var joinEmail2 = $(".joinEmail2").val();
			
			if(pwd.val().trim().length<8 && pwd.val().trim().length>1){
				swal("","비밀번호는 8자리 이상 입력해주세요.","info");
				pwd.focus();
				return false;
			}else if(pwd.val().trim()!=pwd2.val().trim()){
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

			/////////////////////////////////////////////
			return true;
	}

</script>
</section>
</body>
</html>