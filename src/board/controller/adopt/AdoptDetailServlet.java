package board.controller.adopt;

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
import board.model.vo.Files;
import member.model.vo.Member;

/**
 * Servlet implementation class AdoptDetailServlet
 */
@WebServlet(urlPatterns="/adoptDetail.bo", name="AdoptDetailServlet")
public class AdoptDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("boNo"));
		
		BoardService service = new BoardService();
		Adopt adopt = service.selectedAdopt(bNo); 	
		ArrayList<Files> fileList = service.selectNoticeFile(bNo);
		String rescue = (adopt.getPetRescueDate()).toString();
		
		if(adopt != null && fileList != null ) {
			request.setAttribute("bNo", bNo);
			request.setAttribute("adopt", adopt);
			request.setAttribute("rescue", rescue);
			request.setAttribute("fileList", fileList);
			request.setAttribute("section", "WEB-INF/views/adopt/adoptDetail.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "입양게시판 상세보기에 실패하였습니다.");
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
