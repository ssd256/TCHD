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
import board.model.vo.Board;
import board.model.vo.Files;
import board.model.vo.Questions;

/**
 * Servlet implementation class QuestionsDetailServlet
 */
@WebServlet("/detail.qu")
public class QuestionsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsDetailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("bNo"));
		Questions qBoard = new BoardService().selectBoard(no);
		HttpSession session = request.getSession();
		ArrayList<Files> file = null;
		
		if(qBoard != null) {
			request.setAttribute("qBoard", qBoard);
			file = new BoardService().selectNoticeFile(no);	
			if(file!=null) {
				request.setAttribute("file", file);				
			}
			request.setAttribute("section", "WEB-INF/views/questions/questionsDetail.jsp");
		}else {
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.setAttribute("msg", "문의게시글 상세조회에 실패하였습니다.");
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
