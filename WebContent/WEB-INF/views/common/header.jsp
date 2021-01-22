<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member"%>
<%
	Member loginUser=  (Member)request.getSession().getAttribute("loginUser"); // 로그인했을 경우 세션에 아이디 저장
	String confirmPw = (String)request.getSession().getAttribute("confirmPw");
%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">

   
 $(function(){
 	 $(window).resize(function(){
		if($(window).width()<=1516){
			$("#remoteDiv").hide();
			$('#topBtn').hide();
		}else{
			$("#remoteDiv").show();
			$('#topBtn').show();
		} 
	 });
 	 
     $("#topBtn").click(function() {
         $('html, body').animate({
             scrollTop : 0
         }, 150);
     });
     
     $('.naviDetail_li').hover(function(){
    	 $(this).find('.menuBar').css("visibility",'visible');
     }, function(){
    	 $(this).find('.menuBar').css("visibility",'hidden');
     })
     
     $('#menuBar1').children().eq(0).click(function(){
    	 location.href="<%= request.getContextPath() %>/adopt.bo";
     });
     
     $('#menuBar1').children().eq(1).click(function(){
    	 location.href="<%= request.getContextPath() %>/volunteerList.bo";
     });
     
     $('#menuBar2').children().eq(0).click(function(){
    	 location.href="<%= request.getContextPath() %>/supportApplyForm.su";
     });
     
     $('#menuBar2').children().eq(1).click(function(){
    	 location.href="<%= request.getContextPath() %>/supportList.su";
     });
     
     $('#menuBar3').children().eq(0).click(function(){ 
    	 location.href="<%= request.getContextPath() %>/list.no";
     });
     
     $('#menuBar3').children().eq(1).click(function(){ 
    	 location.href="<%= request.getContextPath() %>/hospitalList.ho";
     });
     
     $('#menuBar4').children().eq(0).click(function(){
    	 location.href="<%= request.getContextPath() %>/list.qu";
     });
 });
</script>
</head>
<body>
<header>
	<div id="headDiv">
	<% if(loginUser==null){ %>
		<div id="topBar"><a href="loginForm.me">로그인  </a><span> | </span><a href="joinForm.me">회원가입</a></div>
	<%}else if(loginUser.getMem_id().equals("admin")){ %>
		<div id="topBar"><span>관리자 님 안녕하세요</span><a href="logout.me" id="a_logout">로그아웃  </a><span> | </span><a href="approveGroupMember.me">관리자페이지</a></div>
	<%}else{ %>
		<div id="topBar"><span><%=loginUser.getMem_name()%> 님 안녕하세요 </span><a href="logout.me" id="a_logout">로그아웃  </a><span> | </span><a href="myPage.me">마이페이지</a></div>
	<%} %>
		<div id="topDiv">
			<div id="title"><a href="<%=request.getContextPath() %>"><img alt="로고" src="images/MainLogo.png"></a></div>
			<div id="navi">
				<ul class="naviDetail_ul" >
						<li class="naviDetail_li"><a href="introduction.co" style="cursor: pointer">사이트소개</a></li>
						<li class="naviDetail_li"><a>참여하기</a><ul id="menuBar1" class="menuBar">
								<li>입양</li>
								<li>봉사</li>
							</ul>
						</li>
						<li class="naviDetail_li"><a>후원하기</a><ul id="menuBar2" class="menuBar">
								<li>후원 신청</li>
								<li>후원내역 조회</li>
							</ul>
						</li>
						<li class="naviDetail_li"><a>정보마당</a><ul id="menuBar3" class="menuBar">
							<li>공지사항</li>
							<li>동물병원</li>
						</ul ></li>
						<li class="naviDetail_li"><a>고객센터</a><ul id="menuBar4" class="menuBar">
								<li>문의사항</li>
							</ul>
							</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="remoteDiv">   <!-- 리모컨 -->
		<a href="adopt.bo"><img src="images/aside1.png" ></a>
		<a href="volunteerList.bo"><img src="images/aside2.png" ></a>
		<a href="supportApplyForm.su"><img src="images/aside3.png" ></a>
		<a href="list.qu"><img src="images/aside4.png" ></a>
		
	</div>
	<a id="topBtn" href="#"><img src="images/topBtn.png" ></a>
</header>
</body>
</html>