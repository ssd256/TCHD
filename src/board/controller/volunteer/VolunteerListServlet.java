package board.controller.volunteer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.tools.Tool;

import board.model.service.BoardService;
import board.model.vo.PageInfo;
import board.model.vo.Volunteer;

/**
 * Servlet implementation class VolunteerListServlet
 */
@WebServlet("/volunteerList.bo")
public class VolunteerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardService vService = new BoardService();
		HttpSession session = request.getSession();
		
		int listCount;		// 총 게시글 개수
		int currentPage;	// 현재 페이지
		int pageLimit; 		// 한 페이지에서 표시될 페이징 수
		int boardLimit;		// 한 페이지에서 보일 게시글 최대 개수
		int maxPage;		// 전체 페이지 중에서 마지막 페이지
		int startPage;		// 페이징 된 페이지 중 시작 페이지
		int endPage;		// 페이징 된 페이지 중 마지막 페이지
		
		listCount = vService.getListCount(3);
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 3; // 3개씩 보이게.
		boardLimit = 10; // 10개씩 보이게.
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);

		ArrayList<Volunteer> volunteerList = vService.selectVList(pi);
		
		
		if(volunteerList != null) {
			request.setAttribute("section", "WEB-INF/views/volunteer/volunteerList.jsp");
			request.setAttribute("volunteerList", volunteerList);
			request.setAttribute("pi", pi);
		} else {
			request.setAttribute("section","WEB-INF/views/common/errorPage.jsp"); 
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
		}
		
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
