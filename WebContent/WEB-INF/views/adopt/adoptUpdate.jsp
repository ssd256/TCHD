<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, member.model.vo.Member, board.model.vo.*"%>
<%
// 	int bNo = (int)request.getAttribute("bNo");
	Member loginUser = (Member)request.getAttribute("loginUser");
	Adopt a = (Adopt)request.getAttribute("adopt");
	if(a.getPetComment() == null){
		a.setPetComment("");
	}
	ArrayList<Files> fileList = (ArrayList<Files>)request.getAttribute("fileList");
	String prr[] = a.getPetAge().split("/");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/adopt/adopt_update.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/common/common.css" type="text/css">
</head>
<body>
<section>		<!-- encType="multipart/form-data" -->
   	<form id="form" action="<%= request.getContextPath() %>/adoptUpdate.bo" method="post">
   		<div id="ment">보호동물 게시글 수정</div>
   		<div id="area">
   			<div id="picture">
   				<table id="pictureTable">
					<tr>
						<th>대표 사진<input type="hidden" name="boNo" value="<%= a.getBoNo() %>"/></th>
						<td id="space1"> </td>
						<td colspan="3">
							<div id="titleImgArea" class="pictureArea">
								<% for(int i = 0; i < fileList.size(); i++){ %>
									<input type="hidden" name="fileList" value="<%= fileList.get(i) %>"/>
								<% } %>
								<img id="titleImg" src="<%= request.getContextPath() %>/upload_imageFiles/<%= fileList.get(0).getChangeName() %>"/>
							</div>
						</td>
						<td id="space2"> </td>
						<th>내용 사진</th>
						<td id="space3"> </td>
						<td>
							<div id="contentImgArea1" class="pictureArea">
							<% for(int i = 1; i < fileList.size(); i++){ %>
								<img id="contentImg1" src="<%= request.getContextPath() %>/upload_imageFiles/<%= fileList.get(i).getChangeName() %>"/>
							<% } %>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<table id="firstTable">
				<tr>
					<td class="firstTd"> 구분 </td>
					<td id="kindTd" class="secondTd">
						<div id="petKind">
							<img name="petKind" src="./images/chkbox.png" width="12px" height="12px"/><input type="hidden" name="petKind" value="<%= a.getPetKinds() %>"/> <%= a.getPetKinds() %>
						</div> 
					</td>
					<td class="firstTd"> 성별 </td>
					<td id="genderTd" class="secondTd">
						<div id="petGender">
							<img name="petGender" src="./images/chkbox.png" width="12px" height="12px"/><input type="hidden" name="petGender" value="<%= a.getPetGender() %>"/> <%= a.getPetGender() %>
							<input type="checkbox" name="unigender" value="<%= a.getPetUnigender()%>"/> 중성화
						</div> 
					</td>
					<td class="firstTd"> 크기 </td>
					<td id="sizeTd" class="secondTd">
						<select id="petSizes" name="petSize">
							<option value="0"> ------------</option>	
							<option value="S">소형(S)</option>
							<option value="M">중형(M)</option>
							<option value="L">대형(L)</option>
						</select>
					</td>
					<td class="firstTd"><span>*</span> 연령 </td>
					<td id="ageTd" class="secondTd">
						<select id="petAge" name="petAge">	
							<option value="0"> ---------------------</option>
							<option value="Puppy">Puppy(~ 6개월)</option>
							<option value="Junior">Junior(7개월 ~ 2살)</option>
							<option value="Adult">Adult(3살 ~ 8살)</option>
							<option value="Senior">Senior(9살 ~)</option>
						</select>
					</td>
					<td id="ageTd2">
						<input type="text" id="ageDetail" name="petAgeDetail" placeholder="숫자" value="<%= prr[1].substring(0) %>" required/>
						<select id="detailAges" name="detailAge">
							<option value="개월">개월</option>
							<option value="살">살</option>
						</select>
					</td>
				</tr>
			</table> 
			<table id="secondTable" class="tables">
				<tr>
					<td class="firstTd">
						<span>*</span> 동물이름 :
					</td>
					<td class="secondTd">
						<input id="petName" class="answer" name="petName" value="<%= a.getPetName() %>" required/> 
					</td>
				</tr>
				<tr>
					<td class="firstTd">
						<span>*</span> 종류(품종) :
					</td>
					<td class="secondTd">
						<input id="category" class="answer" name="petCategory" value="<%= a.getPetCategory() %>" required/>
					</td>
				</tr>
				<tr>
					<td class="firstTd">
						<span>*</span> 몸무게(kg) :
					</td>
					<td class="secondTd">		<!-- 사용자가 숫자만 입력 ==> 기본 0.0kg -->
						<input type="text" id="weight" class="answer" name="petWeight" maxlength=3 placeholder=" ex. 0.0kg" value="<%= a.getPetWeight() %>" required>
					</td>
				</tr>
				<tr>
					<td class="firstTd">
						<span>*</span> 색깔 :
					</td>
					<td class="secondTd">
						<input type="text" id="petColor" class="answer" name="petColor" value="<%= a.getPetColor() %>" required>
					</td>
				</tr>
			</table>  	
			<table id="thirdTable" class="tables">
				<tr>
					<td class="firstTd">
						 연락처 :
					</td>
					<td class="secondTd">			
						<input type="tel" id="phone" class="answer" name="phone" value="<%= loginUser.getMem_phone() %>" readonly/>
					</td>
				</tr>
				<tr>
					<td class="firstTd">
						  구조일시 :
					</td>
					<td class="secondTd">		<!-- 사용자가 숫자만 입력 ==> 기본 0.0kg -->
						<input id="rescue" class="answer" type="date" name="rescue" value="<%= a.getPetRescueDate() %>" readonly/>
					</td>
				</tr>
				
				<tr>
						<td class="firstTd">
						</td>
					</tr>
					<tr>
						<td class="secondTd">
						</td>
					</tr>
			</table> 
			<div id="last">
				<div>하고 싶은 말 : </div><textarea id="lastAnswer" name="lastMent"><%= a.getPetComment() %></textarea>
				<span id="counter">0</span>/100
			</div> 
   		</div>
		<div id="buttonArea">	
   			<button id="cancelButton" class="buttons">취소</button>
	   		<button id="okButton" type="button" class="buttons">수정</button>
	   	</div>

	   	<script>
		// insert 당시 입력했던 값 가져옴
		// $("input[name=search_value]")
		// $(tag_name[name=name])
		$('input[name=unigender]').val("<%= a.getPetUnigender() %>");
		$('#petSizes').val("<%= a.getPetSize() %>");
		$('#petAge').val("<%= prr[0].substring(0) %>");		
		
		// #ageDetail : 숫자만 가능
		var ageNum = $('#ageDetail').val();
		ageNum = ageNum.replaceAll(/[가-힣]/g, "");
		$('#ageDetail').val(ageNum);
		// #detailAges : 글자만 가능
		var ageStr = "<%= prr[1].substring(0) %>";
		ageStr = ageStr.replaceAll(/[0-9]/g, "");
		$('#detailAges').val(ageStr);
		
		// 크기 선택 안되게 함   --> 입력 당시 값으로 고정
		$('#petSizes option').not(":selected").attr("disabled", "disabled");
// ----------------------------------------------------------------------------------------------------------------------------	   	
	   	
		 // 입력 당시 썼던 글자수 가져옴
	   		$(document).ready(function(){
	   			$('#lastAnswer').val().lenght
	   				$('#counter').text($(this).val().length);
	   				$('#counter').css('color', 'black');
	   		});
	   		
	   	// 하고 싶은 말 글자 수 카운트 및 글자 수 제한
			$('#lastAnswer').keyup(function(e){
				$('#counter').text($(this).val().length);
				if($(this).val().length >= 100){
					$(this).val($(this).val().substring(0, 100));
					$('#counter').css('color', 'red');
				} else {
					$('#counter').css('color', 'black');
				}
	   		});
	   	
// 정규표현식 -----------------------------------------------------------------------------------------------------   		
 	   		$(function(){
 	   			$('#ageDetail').on("keyup", function(){
 		   			var ageNum = $('#ageDetail').val();
 		   			ageNum = ageNum.replace(/[^0-9]/g, "");		// 숫자만 쓰는 나이칸
 		   			$('#ageDetail').val(ageNum);
 	   			});
 	   			
 	   			$('#weight').on("keyup", function(){
 	   				var weight = $('#weight').val();
 	   				weight = weight.replace(/[^\d*(\.\d{0,2})]/g, "");	// 몸무게는 소수점 1자리까지만 가능
 	   				$('#weight').val(weight);
 	   			});
 	   			
 	   			$('#petName').on("keyup", function(){
 	   				var name = $('#petName').val();
 	   				name = name.replace(/[^가-힣]/g, "");			// 이름은 한글만 가능
 	   				$('#petName').val(name);
 	   			});
 	   			
 	   			$('#category').on("keyup", function(){
 	   				var category = $('#category').val();
 	   				category = category.replace(/[^가-힣]/g, "");	// 품종은 한글만 가능
 	   				$('#category').val(category);
 	   			});
 	   			
 	   			$('#petColor').on("keyup", function(){
 	   				var color = $('#petColor').val();
 	   				color = color.replace(/[^가-힣]/g, "");		// 털색은 한글만 가능
 	   				$('#petColor').val(color);
 	   			});
 	   			
 	   			$('#rescue').on("keyup", function(){
 	   				var rescue = $('#rescue').val();
 	   				rescue = rescue.replace(/[^0-9]/g, "");		// 날짜는 숫자만 가능
 	   				$('#rescue').val(rescue);
 	   			});
 	   			
 	   			$('#lastAnswer').on("keyup", function(){
 	   				var answer = $('#lastAnswer').val();
 	   				answer = answer.replace(/[^가-힣!\s\d]/g, "");	// 하고싶은 말은 한글, 숫자, 공백, ! 가능
 	   			});
 	   			
 	   		});
 			
// 칸 미입력 시 뜨는 창 ------------------------------------------------------------------			
 			$('#okButton').on("click", function(){		// 정보 넘기는 함수
 	   			var size = $('#petSizes');			 // select : 소형(S), 중형(M), 대형(L)
 	   			var age = $('#petAge');				 // select : Puppy, Junior, Adult, Senior
 		   		var ageNum = $('#ageDetail');		 // 숫자만 받는 나이칸
 		   		var ageStr = $('#detailAge');		 // select : 개월, 살 
 		   		var name = $('#petName');			 // 이름
 		   		var category = $('#category');		 // 품종
 		   		var weight = $('#weight');			 // 무게
 		   		var color = $('#petColor');			 // 털 색
 		   		var rescue = $('#rescue');			 // 구조일자

 		   		
 		   		if(size.val() == 0){
 		   			swal("", "크기를 선택해주세요", "info");
 		   			size.focus();
 		   			return false;
 		   		}
 		   		
 		   		if(age.val() == 0){
 		   			swal("", "나이구분을 선택해주세요", "info");
 		   			age.focus();
 		   			return false;
 		   		}
 		   		
 		   		if(ageNum.val().trim().length < 1){
 		   			swal("", "나이를 입력해주세요", "info");
 		   			ageNum.focus();
 		   			return false;
 		   		}
 		   		
 		   		if(ageStr.val() == 0){
 		   			swal("", "개월, 살을 선택해주세요", "info");
 		   			ageStr.focus();
 		   			return false;
 		   		}
 		   		
 		   	if(name.val().trim() == ""){
	   			swal("", "이름을 입력해주세요", "info");
	   			name.focus();
	   			return false;
	   		}
	   		
	   		if(category.val().trim() == ""){
	   			swal("", "품종을 입력해주세요", "info");
	   			category.focus();
	   			return false;
	   		}
	   		
	   		if(weight.val().trim() == ""){		
	   			swal("", "몸무게를 입력해주세요", "info");
	   			weight.focus();
	   			return false;
	   		}
	   		
	   		if(color.val().trim() == ""){
	   			swal("", "털색을 입력해주세요", "info");
	   			color.focus();
	   			return false;
	   		}
	   	
	   		if(rescue.val().trim() == ""){
	   			swal("", "구조날짜를 입력해주세요", "info");
	   			rescue.focus();
	   			return false;
	   		}
 		   		
					swal({
						title : '게시글 수정', 
						text : '상세페이지로 이동합니다', 
						icon : 'success',
					}).then((ok) => {
						if(ok){
							$('#form').submit();
						}
					});
					
					return true;				
 			});
 			
 			$('#cancelButton').on('click', function(){
 				location.href='<%= request.getContextPath() %>/adoptDetail.bo?boNo=<%= a.getBoNo() %>'
 	   			return false;
 	   		});
	   		</script>
	</form>   	
</section>
</body>
</html>