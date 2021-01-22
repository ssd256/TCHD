package board.controller.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Notice;
import board.model.vo.PageInfo;


@WebServlet("/search.no")
public class NotcieSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public NotcieSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search= request.getParameter("search");
		
		int listCount;   // 총 게시글 개수
		int currentPage;  // 현재 페이지
		int pageLimit;   // 한 페이지에서 표시될 페이지수
		int boardLimit;  // 한 페이지에서 표시될 게시글 최대 개수 
		int maxPage;   //  전체페이지 중 가장 마지막 페이지
		int startPage;  // 페이징 된 페이지 중 시작 페이지
		int endPage;   // 페이징 된 페이 중 마지막 페이지
		
		listCount = new BoardService().searchNoticeCount(search);
		
		currentPage =1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		pageLimit = 3;
		boardLimit = 10;
		
		maxPage = (int) Math.ceil((double)listCount/boardLimit);
		startPage = pageLimit*((currentPage-1)/pageLimit) + 1; //  1, 11, 21, 31, .... //   10*n + 1(n>=0)
		endPage =  startPage + pageLimit - 1;// 10,20,30,40
		
		if(maxPage == endPage) {
			endPage=maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage,listCount,pageLimit,boardLimit,maxPage,startPage, endPage);

		
		
		ArrayList<Notice> noticeList = new BoardService().searchNotice(search,pi);
		
		request.setAttribute("search", search);
		request.setAttribute("pi", pi);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("section", "WEB-INF/views/notice/noticeList.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
