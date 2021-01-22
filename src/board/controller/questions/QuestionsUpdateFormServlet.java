package board.controller.questions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Notice;
import board.model.vo.Questions;

/**
 * Servlet implementation class QuestionsUpdateFormServlet
 */
@WebServlet("/updateForm.qu")
public class QuestionsUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsUpdateFormServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("qNo"));
		String title = request.getParameter("qTitle");
		String content = request.getParameter("qContent");
		String subject  = request.getParameter("qSubject");
		String pass = request.getParameter("q_password");

		int fileNo=0;
		if(request.getParameter("qFileNo")!=null) {
			fileNo = Integer.parseInt(request.getParameter("qFileNo"));
		}
		
		Questions qu = new Questions(no, title, content, null, null, 0, subject, pass);
		request.setAttribute("fileNo", fileNo);
		request.setAttribute("qu", qu);
		request.setAttribute("section", "WEB-INF/views/questions/questionsUpdate.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
