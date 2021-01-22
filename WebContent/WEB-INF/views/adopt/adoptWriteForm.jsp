<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member" %>
<%
	String userPhone = (String)request.getAttribute("userPhone");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/common/common.css" type="text/css">
<link rel="stylesheet" href="css/adopt/adopt_write.css" type="text/css">
</head>
<body>
	<section>
		<div id="ment">보호동물 게시글 등록</div>
   		<form id="form" action="<%= request.getContextPath() %>/adoptInsert.bo" method="post" encType="multipart/form-data">			
   		<div id="area">
   			<div id="picture">
   				<table id="pictureTable">
					<tr>
						<th>대표 사진</th>
						<td id="space1"> </td>
						<td colspan="3">
							<div id="titleImgArea" class="pictureArea">
								<img id="titleImg"/>
							</div>
						</td>
						<td id="space2"> </td>
						<th>내용 사진</th>
						<td id="space3"> </td>
						<td>
							<div id="contentImgArea1" class="pictureArea">
								<img id="contentImg1"/>
							</div>
						</td>
						<td>
							<div id="contentImgArea2" class="pictureArea">
								<img id="contentImg2"/>
							</div>
						</td>
						<td>
							<div id="contentImgArea3" class="pictureArea">
								<img id="contentImg3"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
				<table id="firstTable">
					<tr>
						<td class="firstTd"><span>*</span> 구분 </td>
						<td id="kindTd" class="secondTd">
							<input type="radio" id="dog" name="petKind" value="DOG" checked/> 개 
							<input type="radio" id="cat" name="petKind" value="CAT"/> 고양이
						</td>
						<td class="firstTd"><span>*</span> 성별 </td>
						<td id="genderTd" class="secondTd">
							<input type="radio" name="petGender" value="F" checked/> 암컷 
							<input type="radio" name="petGender" value="M"/> 수컷
							<input type="checkbox" name="unigender"/> 중성화
						</td>
						<td class="firstTd"><span>*</span> 크기 </td>
						<td id="sizeTd" class="secondTd">
							<select id="petSizes" name="petSize">	
								<option value="0"> ------------</option>
								<option value="S">소형</option>
								<option value="M">중형</option>
								<option value="L">대형</option>
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
							<input type="text" id="ageDetail" name="petAgeDetail" maxlength=2 placeholder="숫자"/>
							<select id="detailAge" name="detailAge">
								<option value=0>----</option>
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
							<input id="petName" class="answer" name="petName"/> 
						</td>
					</tr>
					<tr>
						<td class="firstTd">
							<span>*</span> 종류(품종) :
						</td>
						<td class="secondTd">
							<input id="category" class="answer" name="petCategory"/>
						</td>
					</tr>
					<tr>
						<td class="firstTd">
							<span>*</span> 몸무게(kg) :
						</td>
						<td class="secondTd">		
							<input type="text" id="weight" class="answer" name="petWeight" maxlength=4 placeholder="ex. 0.0"/>
						</td>
					</tr>
					<tr>
						<td class="firstTd">
							<span>*</span> 색깔 :
						</td>
						<td class="secondTd">
							<input type="text" id="petColor" class="answer" name="petColor"/>
						</td>
					</tr>
				</table>
				<table id="thirdTable" class="tables">
					<tr>
						<td class="firstTd">
							   연락처 :
						</td>
						<td class="secondTd">		
							<input type="tel" id="phone" class="answer" name="phone" value="<%= userPhone %>" readonly/>
						</td>
					</tr>
					<tr>
						<td class="firstTd">
							<span>*</span> 구조일시 :
						</td>
						<td class="secondTd">	
							<input id="rescue" class="answer" type="date" name="rescue"/>
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
					<div>하고 싶은 말 : </div><textarea id="lastAnswer" name="lastMent"></textarea>
					<span id="counter">0</span>/100
				</div> 
	   		</div>
	   		<div id="fileArea">	<!-- 파일 업로드 부분 -->
	   			<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)"/>
	   			<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)"/>
	   			<input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this,3)"/>
	   			<input type="file" id="thumbnailImg4" multiple="multiple" name="thumbnailImg4" onchange="LoadImg(this,4)"/>
	   		</div>
			<div id="buttonArea">
		   		<button id="cancelButton" class="buttons">취소</button>
		   		<button id="okButton" type="button" class="buttons">확인</button>
		   	</div>
	   	<script>
   		// 하고 싶은 말 글자 수 카운트 및 글자 수 제한
   		$(document).ready(function(){
			$('#lastAnswer').keyup(function(e){
				$('#counter').text($(this).val().length);
				if($(this).val().length >= 100){
					$(this).val($(this).val().substring(0, 100));
					$('#counter').css('color', 'red');
				} else {
					$('#counter').css('color', 'black');
				}
			})
			
   		});
   	
   		// 사진 업로드 시 자리 지정
   		$(function(){
   			$("#fileArea").hide();
   			
   			$("#titleImgArea").click(function(){
   				$("#thumbnailImg1").click();
   			});
   			$("#contentImgArea1").click(function(){
   				$("#thumbnailImg2").click();
   			});
   			$("#contentImgArea2").click(function(){
   				$("#thumbnailImg3").click();
   			});
   			$("#contentImgArea3").click(function(){
   				$("#thumbnailImg4").click();
   			});
   		});
   		
   		// 이미지 업로드 함수
   		function LoadImg(value, num){
   			if(value.files && value.files[0]){
   				var reader = new FileReader();
   				
   				reader.onload = function(e){
   					switch(num){
   					case 1:
   						$('#titleImg').attr("src", e.target.result);
   						break;
   					case 2:
   						$('#contentImg1').attr("src", e.target.result);
   						break;
   					case 3:
   						$('#contentImg2').attr("src", e.target.result);
   						break;
   					case 4:
   						$('#contentImg3').attr("src", e.target.result);
   						break;
   					}
   				}
   				reader.readAsDataURL(value.files[0]);
   			}	   			
   		}
   		
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
   		
	   	
   		$('#okButton').on("click", function(){	 // 정보 넘기는 함수
   			var size = $('#petSizes');			 // select : 소형(S), 중형(M), 대형(L)
   			var age = $('#petAge');				 // select : Puppy, Junior, Adult, Senior
	   		var ageNum = $('#ageDetail');		 // 숫자만 받는 나이칸
	   		var ageStr = $('#detailAge');		 // select : 개월, 살 
	   		var name = $('#petName');			 // 이름
	   		var category = $('#category');		 // 품종
	   		var weight = $('#weight');			 // 무게
	   		var color = $('#petColor');			 // 털 색
	   		var rescue = $('#rescue');			 // 구조일자
	   		var thumbnail = $('#thumbnailImg1'); // 대표 사진
	   		
   			if(!thumbnail.val()){
   				swal("", "대표사진을 첨부해주세요", "info");
   				thumbnail.focus();
   				return false;
   			}
	   		
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
				title : "게시글 등록 성공", 
				text : "목록화면으로 돌아갑니다", 
				icon : "success",
			}).then((ok) => {
					if(ok){
						$('#form').submit();
					}
				});
			
   	   		return true;
   			
   	 	});
   		
   		$('#cancelButton').on('click', function(){
   			location.href='<%= request.getContextPath() %>/adopt.bo';
   			return false;
   		});
   		
   		
	   	</script>
   	</form>
	</section>
</body>
</html>









