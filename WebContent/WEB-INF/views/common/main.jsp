<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/common/main.css" type="text/css">
</head>
<body>
 	<section>	
		<div id="article1">
		</div>
		
		<div id="article2"><img src="images/picture2.png"></div>
		<div id="notice">
			<table>
				<thead>
					<tr class="notice_tr">
						<th class="notice_th">공지사항</th>
						<th class="notice_th">날짜</th>
					</tr>
				</thead>
				<tbody id="notice_tbody">
					
				</tbody>			
			</table>
		</div>
		<p class="clear"></p>
		<div id="article3"><img src="images/picture3.png"></div>
	</section>
	<script>
		$(function(){
			$.ajax({
				url: 'main.ad',
				success: function(data){
					if(data.length>0){
						var article1 = $("#article1");
						for(var i=0;i<data.length; i++){
							var petKinds = "";
							var gender = "";
							var age=data[i].petAge.split('/');
							if(data[i].petKinds=="CAT"){ petKinds="고양이" }else{ petKinds="개"}
							if(data[i].petGender=="M"){gender="수컷"}else{ gender="암컷"};
							var input="<div class='pet' id='pet"+i+"'><div class='petContent' id='petContent"+i+"'>"+
									"<div class='petName'>"+data[i].petName+"</div>"+
									petKinds+" ("+data[i].petCategory+")<br>"+
									gender+" (중성화 "+data[i].petUnigender+")<br>"+
									age[0]+" / "+data[i].petWeight+"kg / "+data[i].petColor+"</div></div>";
							article1.append(input);
							$('#pet'+i).attr("onclick","location.href='adoptDetail.bo?boNo="+data[i].boNo+"'");
						}
						getAdoptImage();
					}
				},
				error: function(data){
					alert("ajax 에러 발생")
				}
			});
		

			$.ajax({
				url:  'main.no',
				success: function(data){
					if(data.length>0){
						var tbody = $("#notice_tbody");
						
						for(var i=0; i<data.length; i++){
							var input="<tr><td id='noticeTitle' onclick='goNotice("+ data[i].boNo+");'> ["+data[i].noticeSubject+"] " +data[i].boTitle+
							"</td><td id='noticeDate'>"+data[i].boDate+"</td></tr>";
							tbody.append(input);
						}						
					}else{
						var tbody = $("#notice_tbody");
						var input="<tr><td id='noticeTitle2'>등록된 공지사항이 없습니다.</td><td id='noticeDate'></td></tr>";
						tbody.append(input);
					}
				},
				error: function(data){
					alert("ajax 에러 발생");
				}
			});
			
			$(document).on("mouseenter",".pet",function(){
				$(this).find('img').css("opacity","0.7");
			}).on("mouseout",".pet",function(){
				$(this).find('img').css("opacity","1.0");
			});
			
			$('#article3').click(function(){
				location.href="supportApplyForm.su";
			}).mouseenter(function(){
				$(this).css("cursor","pointer");
			});
			
		});
		
	
		
		function getAdoptImage(){
			$.ajax({
				url: 'mainImage.ad',
				success: function(data){
					for(var i=0;i<data.length; i++){
						var petContent = $("#petContent"+i);
						var input="<img src='upload_imageFiles/"+data[i].changeName+"'>";
						petContent.prepend(input);
					}
				},
				error: function(data){
					alert("ajax 에러 발생");
				}
			});
		}
		
		function goNotice(boNo){
			location.href="detail.no?bNo="+boNo;
		};
	</script>
</body>
</html>