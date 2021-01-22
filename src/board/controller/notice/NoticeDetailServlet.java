package board.controller.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Files;
import board.model.vo.Notice;

@WebServlet("/detail.no")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeDetailServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		Notice notice = new BoardService().selectNotice(bNo);
		ArrayList<Files> file = null;
		if(notice!=null) {
			request.setAttribute("notice", notice);
			file = new BoardService().selectNoticeFile(bNo);	
			if(file!=null) {
				request.setAttribute("file", file);				
			}
			request.setAttribute("section", "WEB-INF/views/notice/noticeDetail.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);			
		}else {
			request.setAttribute("errorMsg", "해당 게시글의 접근에 실패하였습니다.");
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);	
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
