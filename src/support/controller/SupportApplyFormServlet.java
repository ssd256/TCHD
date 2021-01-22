package support.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

@WebServlet("/supportApplyForm.su")
public class SupportApplyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupportApplyFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			request.setAttribute("section", "WEB-INF/views/support/supportApplyMember.jsp");
		} else {
			request.setAttribute("section", "WEB-INF/views/support/supportApplyNonMember.jsp");
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
