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
 * Servlet implementation class AdoptUpdateForm
 */
@WebServlet(urlPatterns="/adoptUpdateForm.bo", name="AdoptUpadateFormServlet")
public class AdoptUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 유저 정보 및 게시글 번호
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		int bNo = Integer.parseInt(request.getParameter("boNo"));
		
		BoardService service = new BoardService();
		// insert 했던 입양 동물 정보
		Adopt adopt = service.selectedAdopt(bNo);
		
		// insert 했던 파일 정보
		ArrayList<Files> fileList = service.selectNoticeFile(bNo);
		request.setAttribute("loginUser", loginUser);
		request.setAttribute("adopt", adopt);
		request.setAttribute("fileList", fileList);
		request.setAttribute("section", "WEB-INF/views/adopt/adoptUpdate.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
