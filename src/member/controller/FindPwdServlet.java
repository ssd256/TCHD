package member.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.sendMail;
import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/findPwd.me")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindPwdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("input_id");
		
		Member member = new Member(0, id, ""); // 생성자가 겹쳐서 mem_no, mem_id, mem_email로 사용
		
		Member findUser = new MemberService().findPwd(member);
		
		if(findUser != null) {
			try {
				String email = findUser.getMem_email();
				String temporaryPwd = "";
				
				// 임시 비밀번호 발급
				// 배열 이용 ==> 소문자, 숫자, 특수문자 포함 10글자
				char[] pwArr = new char[] {
						'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 
						'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
						'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
						'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '|', '<', '>', '?', ':', '{', '}'
				};
				
				for(int i = 0; i < 10; i++) {
					int selectRandomPwd = (int)(Math.random() * pwArr.length);// 0 ~ 82
					
					temporaryPwd += pwArr[selectRandomPwd];
				}
				
				// DB에 저장된 비밀번호 -> 임시 비밀번호로 변경
				int result = new MemberService().changePwd(id, temporaryPwd);
				
				if(result != 0) {
					new sendMail().sendEmail("pwd", email, temporaryPwd);
					
					response.getWriter().println("1");
				} else {
					response.getWriter().println("0");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.getWriter().println("0");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
