package board.controller.volunteer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import board.model.service.BoardService;
import board.model.vo.Comments;

/**
 * Servlet implementation class VolunteerCommentsInsertServlet
 */
@WebServlet("/insertComments.bo")
public class VolunteerCommentsInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerCommentsInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post방식으로 하면 제일 먼저 하던것 UTF-8
		request.setCharacterEncoding("UTF-8");
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		System.out.println(bNo);
		String content = request.getParameter("content");
		
		Comments comments = new Comments();
		comments.setMemNo(memNo);
		comments.setBoNo(bNo);
		comments.setComContent(content);
		
		ArrayList<Comments> commentsList = new BoardService().insertComments(comments);
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(commentsList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
