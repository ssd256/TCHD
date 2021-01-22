package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member member = new Member(userId,userPwd);
		
		Member loginUser = new MemberService().login(member);
		if(loginUser!=null) {
			request.getSession().setAttribute("loginUser", loginUser);
			// Session이 기본적으로 갖는 유효 시간은 30분!
			if(loginUser.getGm_ok_ny()==null) { // 개인회원 로그인
				response.getWriter().println("1");
			}else if(loginUser.getGm_ok_ny().equals("Y")){  // 승인받은 단체회원 로그인
				response.getWriter().println("2");
			}else {  							// 승인받지 않은 단체회원 로그인
				response.getWriter().println("3");
			}
		} else {
			response.getWriter().println("0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
