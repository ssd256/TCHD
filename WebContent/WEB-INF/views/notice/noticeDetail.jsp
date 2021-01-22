<%@page import="java.util.ArrayList"%>
<%@page import="board.model.vo.Notice, board.model.vo.Files,member.model.vo.Member "%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Notice notice = (Notice)request.getAttribute("notice");
	ArrayList<Files> fileList = (ArrayList<Files>)request.getAttribute("file");
	Files file =null;
	if(fileList!=null){
		for(int i=0; i<fileList.size();i++){
			file = fileList.get(i);
		}
	}
	Member loginUser = (Member)request.getSession().getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/notice/notice_details.css" type="text/css">
</head>
<body>
 <section>
      <div id="ment">공지사항</div>
	<form method="post" action="updateForm.no">
      <div id="details_div">
         <div id="title_info_div">
            <div id="notice_title">
            	<input type="hidden" name="noticeNo" value="<%=notice.getBoNo() %>">
            	<input type="hidden" name="noticeTitle" value="<%=notice.getBoTitle() %>">
            	<input type="hidden" name="noticeContent" value="<%=notice.getBoContent() %>">
            	<input type="hidden" name="noticeSubject" value="<%=notice.getNoticeSubject() %>">
            	<% if(file!=null){%>
            		<input type="hidden" name="noticeFileNo" value="<%=file.getFileNo() %>">
            	<%} %>
               <h1>[<%=notice.getNoticeSubject() %>]<%=notice.getBoTitle() %></h1>
            </div>
            <div id="writer_info">
               <a>관리자</a>
               <a> | </a>
               <a><%=notice.getBoDate() %></a>
               <a> | </a>
               <a><%=notice.getBoCount() %></a>
            </div>
         </div>
         <div id="contents_div">
            <div id="img_div">
            <%if(file!=null){ %>
            <img src="upload_imageFiles/<%=file.getChangeName()%>"></div>
            <%} %>
            <div id="contents"><%=notice.getBoContent() %></div>
         </div>
      </div>
     
         <div id="button_div">
            <button id="list_view_button" type="button" onclick="location.href='list.no'">목록보기</button>
            <%if(loginUser !=null && loginUser.getMem_id().equals("admin")){ %>
               <button type="button" id="edit_button" onclick="goDelete();">삭제하기</button>
     	       <button type="submit" id="edit_button" onclick="location.href='updateForm.no'">수정하기</button>
            <%} %>
         </div>
          </form>
   </section>
<script type="text/javascript">
	function goDelete(){
		swal({
			text: "이 공지사항을 삭제하시겠습니까?",
			icon : "warning",
			buttons: true 	})
		.then((ok)=>{
			if(ok){
				location.href="delete.no?bNo="+<%=notice.getBoNo()%>;
			}
		});
	}
</script>
</body>
</html>