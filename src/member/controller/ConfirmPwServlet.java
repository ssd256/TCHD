package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;


@WebServlet("/confirmPw.me")
public class ConfirmPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ConfirmPwServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((Member)request.getSession().getAttribute("loginUser")==null) {
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.setAttribute("errorMsg", "세션이 만료되었습니다. 다시 로그인해주세요.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		String inputPw= request.getParameter("inputPw");
		String loginUserId = ((Member)request.getSession().getAttribute("loginUser")).getMem_id();

		
		int result = new MemberService().confirmPw(loginUserId,inputPw);
		if(result>0) {
			request.getSession().setAttribute("confirmPw", "confirmed");
			response.getWriter().println("1");
			//response.sendRedirect("myPage.me");
		}else {
			//response.sendRedirect("myPage.me");
			response.getWriter().println("0");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
