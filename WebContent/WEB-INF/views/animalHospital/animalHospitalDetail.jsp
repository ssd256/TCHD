<%@page import="animalHospital.model.vo.AnimalHospital"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	AnimalHospital hospital=(AnimalHospital)request.getAttribute("hospital");
	String address = hospital.getHos_addr();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/animalHospital/24hAnimalHospital_details.css" type="text/css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9825f8ee7c5749fcba65382d3b6f9521&libraries=services"></script>
<script>
$(function(){
	var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
	var options = { // 지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level: 3 // 지도의 레벨(확대, 축소 정도)
	};

	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	var geocoder = new kakao.maps.services.Geocoder();
	
	geocoder.addressSearch('<%= address %>', function(result, status) {
	     if (status === kakao.maps.services.Status.OK) {
	    	// 정상적으로 검색이 완료됐으면 
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px; text-align:center; padding:6px 0px;"><%= hospital.getHos_name() %></div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    }
	});    
});
</script>
</head>
<body>
	<section>
		<div id="ment">24시 동물병원</div>
		<br>
		<div id="details_div">
			<div id="map_info_float_div">
				<div id="map"></div>
				<div id="hospital_info_div">
					<div id="hospital_name"><%= hospital.getHos_name() %></div>
					<br>
					<hr style="width: 420px;">
					<br>
					<table id="hospital_info">
						<tr>
							<th>주소</th>
							<td><%= hospital.getHos_addr() %></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><%= hospital.getHos_phone() %></td>
						</tr>
					</table>
				</div>
			</div>
			<div id="list_div" onclick="goList();">
				<a id="list_a">&lt;</a>
				<button id="list_button">목록보기</button>
			</div>
		</div>
		
		<script>
			function goList(){
				history.back();
			}
			
			$("#list_div").hover(function(){
				$(this).css("font-weight","900");
				$("#list_button").css("font-weight","900");
			}, function(){
				$(this).css("font-weight","400");
				$("#list_button").css("font-weight","400");
			});
		</script>
	</section>
</body>
</html>