package board.controller.adopt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Adopt;
import board.model.vo.AdoptApply;
import board.model.vo.Board;
import board.model.vo.Files;
import member.model.vo.Member;

/**
 * Servlet implementation class AdoptDeleteServlet
 */
@WebServlet(urlPatterns="/adoptApply.bo", name="AdoptApplyServlet")
public class AdoptApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int bNo = Integer.parseInt(request.getParameter("bNo"));
		int loginUserNo = ((Member)request.getSession().getAttribute("loginUser")).getMem_no();		
		String answer = bNo + "번 게시글, " + request.getParameter("answer1") + " , " +request.getParameter("answer2") + " , " + 
						request.getParameter("answer3") + " , " + request.getParameter("answer4") + " , " + 
						request.getParameter("answer5") + " , " + request.getParameter("answer7");
		
		Board b = new Board(0, null, "입양신청서", answer, null, 0, null, loginUserNo, 5);
		Adopt a = new Adopt(bNo, "Y");
		Files f = new Files(0, bNo, 1, null, null, null, null, 0, "Y");
		AdoptApply apply = new AdoptApply(answer, loginUserNo);
		
		int result = new BoardService().insertApply(b, a, f, apply);
		
		if(result > 0) {
			response.sendRedirect("adopt.bo");
		} else { 
			request.setAttribute("msg", "입양 신청서 작성에 실패하였습니다.");
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
