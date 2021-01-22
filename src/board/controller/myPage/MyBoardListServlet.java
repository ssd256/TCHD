package board.controller.myPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import member.model.vo.Member;

/**
 * Servlet implementation class MyBoardListServlet
 */
@WebServlet("/listMyBoard.bo")
public class MyBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((Member)request.getSession().getAttribute("loginUser")==null) {
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.setAttribute("errorMsg", "세션이 만료되었습니다. 다시 로그인해주세요.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		
		BoardService bService = new BoardService();
		
		int listCount;   // 총 게시글 개수
		int currentPage;  // 현재 페이지
		int pageLimit;   // 한 페이지에서 표시될 페이지수
		int boardLimit;  // 한 페이지에서 표시될 게시글 최대 개수 
		int maxPage;   //  전체페이지 중 가장 마지막 페이지
		int startPage;  // 페이징 된 페이지 중 시작 페이지
		int endPage;   // 페이징 된 페이 중 마지막 페이지
		
		listCount = bService.selectMyBoardCount(loginUser.getMem_no());
		System.out.println(listCount);
		
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
		
		
		ArrayList<Board> boardList = new BoardService().selectMyBoard(loginUser.getMem_no(),pi);
		HashMap<String, Integer> eachBoardCount = new BoardService().getEachBoardCount(loginUser.getMem_no());	
		System.out.println(eachBoardCount);
		
		request.setAttribute("pi", pi);
		request.setAttribute("eachBoardCount", eachBoardCount);
		request.setAttribute("boardList", boardList);
		request.setAttribute("section", "WEB-INF/views/member/listMyBoard_myPage.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
