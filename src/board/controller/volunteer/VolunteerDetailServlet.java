package board.controller.volunteer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Comments;
import board.model.vo.Files;
import board.model.vo.Volunteer;

/**
 * Servlet implementation class VolunteerDetailServlet
 */
@WebServlet("/volunteerDetail.bo")
public class VolunteerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		Volunteer volunteer = new BoardService().selectVolunteer(bNo);
		
		ArrayList applyMemberList = new BoardService().selectApplyMember(bNo);
		
		// 댓글도 가져오기.
		ArrayList<Comments> commentsList = new BoardService().selectCommentsList(bNo);
		
		
		HttpSession session = request.getSession();
		ArrayList<Files> file = null;
		
		if(volunteer != null) {
			request.setAttribute("volunteer", volunteer);
			request.setAttribute("commentsList", commentsList);			
			request.setAttribute("applyMemberList", applyMemberList);			
			file = new BoardService().selectNoticeFile(bNo);	
			if(file!=null) {
				request.setAttribute("file", file);				
			}
			request.setAttribute("section", "WEB-INF/views/volunteer/volunteerDetail.jsp");
		}else {
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.setAttribute("msg", "게시글 조회에 실패하였습니다.");
			
			
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
