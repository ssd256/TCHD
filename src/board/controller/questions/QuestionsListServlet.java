package board.controller.questions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Adopt;
import board.model.vo.Board;
import board.model.vo.Files;
import board.model.vo.PageInfo;
import board.model.vo.Questions;
import member.model.vo.Member;



/**
 * Servlet implementation class QuestionsListServlet
 */
@WebServlet(urlPatterns="/list.qu", name="QuestionsListFormServlet")
public class QuestionsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService qService = new BoardService(); //레퍼런스 변수에 담음
		HttpSession session = request.getSession();
		
	
		
		int listCount;		//총 게시글 개수
		int currentPage;	//현재 페이지
		int pageLimit;		//한 페이지에서 표시될 페이징 수
		int boardLimit;		//한 페이지에서 보일 게시글 최대 개수
		int maxPage;		//전체 페이지 중 가장 마지막 페이지
		int startPage;		//페이징 된 페이지 중 시작페이지
		int endPage;		//페이징 된 페이지 중 마지막페이지
		
		listCount = qService.getListCount(2);
		
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) { //페이지를 누른 상태면  (2페이지,3페이지 등 클릭하지않으면 currentPage가 null임)
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 3; //한번에 페이지 수 3개 보이게 한다.
		boardLimit = 10; //한번에 게시글 수 10개 보이게 한다.
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit); //나머지값(소숫점)이 필요하므로 listCount만 double로 형변환.
						//반올림 함수
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1; 
		
		endPage = startPage + pageLimit - 1; //시작번호+출력개수-1
		if(maxPage < endPage) {
			endPage = maxPage;   //맥스페이지 뒷자리가 0으로 안끝날수도있다. 이 경우엔 맥스페이지수로 맞춰주는 if문을 작성함.
		}
		
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Questions> Qlist = qService.selectQList(pi);
		
		
		if(Qlist != null) {
			 request.setAttribute("section", "WEB-INF/views/questions/questionsList.jsp");
			request.setAttribute("Qlist", Qlist);
			 request.setAttribute("pi", pi);
			 
			
		}else {
			request.setAttribute("section","WEB-INF/views/common/errorPage.jsp"); 
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
