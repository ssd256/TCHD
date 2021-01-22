<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="board.model.vo.Questions"%>    
<%
	Questions qu = (Questions)request.getAttribute("qu");
	int fileNo = (int)request.getAttribute("fileNo");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/common.css" type="text/css">
<link rel="stylesheet" href="css/questions/questions_write.css" type="text/css">
</head>
<body>
<section>


		<div id = "ment" class="board_list_wrap">게시글 작성</div>
		<form method="post" action="update.qu" encType="multipart/form-data" onsubmit="return validate();">
     	<div id = "board_list_design">
     	<input type="hidden" name="qNo" value="<%=qu.getBoNo() %>">
          	<% if(fileNo!=0){ %>	<input type="hidden" name="qFileNo" value="<%=fileNo %>">  <%} %>
		<table class="board_list">
			<tr>
			<td width="100px"><span id = "span1">*</span><b>분류</b> </td>
				<td width="750px" class="align_left" id="sub_td">
					<select name="input_subject" id="subject" class="btn">
						<option value = "0">----------------</option>
						<option value = "회원정보">회원정보</option>
						<option value = "입양하기">입양하기</option>
						<option value = "후원하기">후원관련</option>
						<option value = "봉사하기">봉사하기</option>
						<option value = "etc">etc</option>
					</select>
				</td>
			</tr>
		  <tr>
		    <td><span id = "span1">*</span><b>제목</b></td>
		    <td class="align_left">
		    <input type="text" name="input_title" id="input_title" class="btn" size="80" value="<%=qu.getBoTitle()%>" ></td>
		  </tr>
		  <tr>
		  	<td><b>비밀번호</b></td>
		  	<td class="align_left">
		  	<input type="password" name="q_password" class="btn" size="10" maxlength="4"> <span>&nbsp;&nbsp;&nbsp;&nbsp;
		  		문의게시판은 비밀글 작성이 가능합니다. 비밀글 작성을 원할 시 숫자 4자리 암호를 작성해주세요.</span>
		  	</td>
		  </tr>	
		   <tr>
		    <td id = "content_file"><b>첨부파일</b></td>
		    <td class="align_left"><div class="filebox">
  					<label for="ex_file">파일 선택</label>
 					 <input type="file" id="ex_file" name="input_file" accept=".png, .jpg">		
 					 <span>&nbsp;&nbsp;&nbsp;&nbsp;이미지파일(PNG, JPG)만 첨부 가능합니다.</span>
				</div>
			</td>
		  </tr>
		  <tr>
		  <td id ="content_top"><b>내용</b></td>
		    <td>
		    	<textarea name="input_content" id="content" class="content" cols="110" rows="25"><%=qu.getBoContent() %></textarea>
		    </td>
		  </tr>
		</table>
		</div>
		<div class= "button_center">
			<input name="button_list" type="reset" class="btn_no"value="취소">
			<input name="button_content" type="submit" class="btn_ok" value="등록">
		</div>
		</form>
	
	<script type="text/javascript">
      $('#ex_file').focus(function(){
         $(':focus').blur();     
      });
      
      
      
		function validate(){
    	  console.log("실행");
    	  
			
			var title=$('#input_title');
			var content=$('.content');
			
			
			if(title.val().trim().length<1){
				swal("","제목을 입력해주세요","info");
				title.focus();
				return false;
			}
			if(content.val().trim().length<1){
				swal("","내용을 입력해주세요","info");
				content.focus();
				return false;
			}
			
			return true;
	};
   </script>
 </section>
</body>
</html>