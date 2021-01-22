package board.controller.adopt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdoptDeleteServlet
 */
@WebServlet(urlPatterns="/adoptApplyForm.bo", name="AdoptApplyFormServlet")
public class AdoptApplyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptApplyFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginUserId = ((Member)request.getSession().getAttribute("loginUser")).getMem_id();
		Member member = new MemberService().selectMemberPm(loginUserId);
		int bNo = Integer.parseInt(request.getParameter("boNo"));
		
		request.setAttribute("bNo", bNo);
		request.setAttribute("member", member);
		request.setAttribute("section", "WEB-INF/views/adopt/adoptApply.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
