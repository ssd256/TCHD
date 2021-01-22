package board.controller.adopt;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Adopt;
import board.model.vo.Files;

/**
 * Servlet implementation class AdoptDeleteServlet
 */
@WebServlet(urlPatterns="/adoptDelete.bo", name="AdoptDeleteServlet")
public class AdoptDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("boNo"));

		int result = new BoardService().deleteAdopt(bNo);
		
		request.setAttribute("boNo", bNo);
		if(result > 0) {
			response.sendRedirect("adopt.bo");
		} else {
			response.sendRedirect("views/adopt/adoptDetail.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
