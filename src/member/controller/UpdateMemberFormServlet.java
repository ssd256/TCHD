package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;


@WebServlet("/myPage.me") // 마이페이지 기본페이지가 회원정보수정페이지
public class UpdateMemberFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateMemberFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((Member)request.getSession().getAttribute("loginUser")==null) {
			request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
			request.setAttribute("errorMsg", "세션이 만료되었습니다. 다시 로그인해주세요.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		Member loginUser = (Member)request.getSession().getAttribute("loginUser");
		String confirmPw = (String)request.getSession().getAttribute("confirmPw");
	
		if(loginUser==null) {
			response.sendRedirect(request.getContextPath());
		}
		
		if(confirmPw==null) {  // 비밀번호 확인이 안되었을때
			request.setAttribute("section", "WEB-INF/views/member/confirmPw_myPage.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {  // 비밀번호 확인시 

			String memberType= loginUser.getMem_type();

			
			if(memberType.equals("PM")) {
				String memberId = loginUser.getMem_id();
				Member member = new MemberService().selectMemberPm(memberId);
				if(member!=null){
					request.setAttribute("member", member);
					request.setAttribute("section", "WEB-INF/views/member/updateMyInfoPm_myPage.jsp");
					request.getRequestDispatcher("index.jsp").forward(request, response);	
				}else {
					request.getSession().setAttribute("confirmPw", null);
					request.setAttribute("errorMsg", "회원정보 수정페이지로 이동 중 에러가 발생하였습니다.");
					request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
					request.getRequestDispatcher("index.jsp").forward(request, response);	
				}
							
			}else {
				String memberId = loginUser.getMem_id();
				Member member = new MemberService().selectMemberGm(memberId);
				
				if(member!=null) {
					request.setAttribute("member", member);
					request.setAttribute("section", "WEB-INF/views/member/updateMyInfoGm_myPage.jsp");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else {
					request.getSession().setAttribute("confirmPw", null);
					request.setAttribute("errorMsg", "회원정보 수정페이지로 이동 중 에러가 발생하였습니다.");
					request.setAttribute("section", "WEB-INF/views/common/errorPage.jsp");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				
			}
			

		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
